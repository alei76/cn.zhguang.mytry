/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * ZkConfigManager.java File Created at 下午3:25:41
 * 
 * 
 * Copyright 2014 Taobao.com Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Taobao Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Taobao.com.
 */
package com.taobao.yinggang.curatorexample.zk;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CreateBuilder;
import org.apache.curator.framework.api.ProtectACLCreateModePathAndBytesable;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.common.lang.StringUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 配置信息管理器实现，可以使用zk或者dimond实现
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月4日
 */
public class ZkConfigManager implements ConfigManager {

	private static final Logger logger = LoggerFactory.getLogger(ZkConfigManager.class);

	private CuratorFramework zkClient;

	/**
	 * 调度中心自用的zk配置，注意与tbschedule的zk配置的区别
	 */
	private ZookeeperConfigDO scZkConfig;

	private static final AtomicInteger callBackPoolNumber = new AtomicInteger(1);

	private static final AtomicInteger curatorPoolNumber = new AtomicInteger(1);

	private final ExecutorService callBackExecutor = Executors.newFixedThreadPool(4, new ZkManagerThreadFactory("zkManager-callBack", callBackPoolNumber));

	private final ExecutorService curatorExecutor = Executors.newFixedThreadPool(4, new ZkManagerThreadFactory("curator-Cache", curatorPoolNumber));

	private final ConcurrentMap<String, DataChangeCache> dataChangeMap = Maps.newConcurrentMap();

	private final ConcurrentMap<String, ChildChangeCache> childChangeMap = Maps.newConcurrentMap();

	private final List<ConnectionStatusListener> connectionStatusListenerList = Lists.newCopyOnWriteArrayList();

	public synchronized void init() {
		String zkHosts = scZkConfig.getZkConfigIpData();
		ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(5000, Integer.MAX_VALUE);
		int timeout = Integer.valueOf(scZkConfig.getZkSessionTimeout());
		zkClient = CuratorFrameworkFactory.newClient(zkHosts, timeout, timeout, retryPolicy);
		// addConnectionStatus Listenable
		zkClient.getConnectionStateListenable().addListener(new ConnectionStateListener() {
			@Override
			public void stateChanged(CuratorFramework client, final ConnectionState newState) {
				for (final ConnectionStatusListener listener : connectionStatusListenerList) {
					callBackExecutor.execute(new Runnable() {
						public void run() {
							try {
								listener.handleStateChanged(newState);
							} catch (Exception e) {
								logger.error("initZkConfigManger$Handle Connection State Error! ", e);
							}
						}
					});
				}
			}
		});
		zkClient.start();
	}

	public synchronized void destory() {
		if (null != zkClient) {
			zkClient.close();
			zkClient = null;
		}
		for (DataChangeCache dataChangeCache : dataChangeMap.values()) {
			try {
				dataChangeCache.destory();
			} catch (Throwable e) {
				logger.error("destoryZKConfigManger$Destory DataChangeCache error path:{} ", dataChangeCache.getPath());
			}
		}
		dataChangeMap.clear();
		for (ChildChangeCache childChangeCache : childChangeMap.values()) {
			try {
				childChangeCache.destory();
			} catch (Throwable e) {
				logger.error("destoryZKConfigManger$Destory ChildChangeCache error path: {}", childChangeCache.getPath());
			}
		}
		childChangeMap.clear();
		connectionStatusListenerList.clear();

		callBackExecutor.shutdown();
		curatorExecutor.shutdown();
	}

	public String getData(String path) {
		try {
			try {
				byte[] bytes = zkClient.getData().forPath(path);
				return SerializeUtil.deserialize(bytes);
			} catch (KeeperException.NoNodeException e) {
				return null;
			}
		} catch (Throwable e) {
			throw new RuntimeException("Get Zk Data Error! ", e);
		}
	}

	public Stat getStat(String path) {
		try {
			Stat stat = new Stat();
			zkClient.getData().storingStatIn(stat).forPath(path);
			return stat;
		} catch (Throwable e) {
			throw new RuntimeException("Get Zk Stat Error! ", e);
		}
	}

	public String getDataCache(String path) {
		DataChangeCache dataChangeCache = getDataChangeCache(path);
		try {
			return dataChangeCache.getCurrentData();
		} catch (Throwable e) {
			throw new RuntimeException("Get Zk Data From Cache Error! ", e);
		}
	}

	public Map<String, String> getChildDatas(String path) {
		try {
			try {
				List<String> childNames = zkClient.getChildren().forPath(path);
				Map<String, String> dataMap = new HashMap<String, String>(childNames.size());
				for (String element : childNames) {
					if (StringUtil.isNotBlank(element)) {
						dataMap.put(element, StringUtil.defaultIfBlank(this.getData(path + "/" + element), StringUtil.EMPTY_STRING));
					}
				}
				return dataMap;
			} catch (KeeperException.NoNodeException e) {
				return Collections.emptyMap();
			}
		} catch (Throwable e) {
			throw new RuntimeException("Get getChildDatas Data Error! ", e);
		}
	}

	public List<String> getChildCache(String path) {
		ChildChangeCache childChangeCache = getChildChangeCache(path);
		try {
			return childChangeCache.getCurrentChildren();
		} catch (Throwable e) {
			throw new RuntimeException("Get Zk ChildData From Cache Error! ", e);
		}
	}

	public boolean exists(String path) {
		try {
			Stat stat = this.zkClient.checkExists().forPath(path);
			return null != stat ? true : false;
		} catch (Exception e) {
			throw new RuntimeException("Check Exist Zk Data Error! ", e);
		}
	}

	public void delete(String path) throws Exception {
		try {
			this.zkClient.delete().deletingChildrenIfNeeded().forPath(path);
		} catch (KeeperException.NoNodeException e) {
			logger.warn("delete path not exists path: " + path);
		}
	}

	public void publishData(String path, String data, boolean isPersistent) throws Exception {
		publishData(path, data, isPersistent ? CreateMode.PERSISTENT : CreateMode.EPHEMERAL);
	}

	public String publishDataSequential(String path, String data, boolean isPersistent) throws Exception {
		return publishData(path, data, isPersistent ? CreateMode.PERSISTENT_SEQUENTIAL : CreateMode.EPHEMERAL_SEQUENTIAL);
	}

	private String publishData(String path, String data, CreateMode createMode) throws Exception {
		String result;
		ProtectACLCreateModePathAndBytesable<String> protectAble = this.zkClient.create().creatingParentsIfNeeded();
		CreateBuilder builder = (CreateBuilder) protectAble.withMode(createMode);
		if (null != data) {
			result = builder.forPath(path, SerializeUtil.serialize(data));
		} else {
			result = builder.forPath(path);
		}
		return result;
	}

	public void updateData(String path, String data) throws Exception {
		if (null != data) {
			this.zkClient.setData().forPath(path, SerializeUtil.serialize(data));
		} else {
			this.zkClient.setData().forPath(path);
		}
	}

	public void publishOrUpdateData(String path, String data, boolean isPersistent) throws Exception {
		if (!exists(path)) {
			publishData(path, data, isPersistent);
		} else {
			updateData(path, data);
		}
	}

	public void addDataListener(String path, DataChangeListener listener) {
		DataChangeCache context = getDataChangeCache(path);
		context.addConfigDataListener(listener);
	}

	private DataChangeCache getDataChangeCache(String path) {
		DataChangeCache context = dataChangeMap.get(path);
		if (null == context) {
			context = dataChangeMap.putIfAbsent(path, new DataChangeCache(path, new NodeCache(this.zkClient, path), callBackExecutor));
			if (null == context) {
				context = dataChangeMap.get(path).init();
			}
		}
		return context;
	}

	public void removeDataListener(String path, DataChangeListener listener) {
		DataChangeCache context = dataChangeMap.get(path);
		if (null != context) {
			context.removeConfigDataListener(listener);
		}
	}

	public void addChildChangesListener(String path, ChildChangeListener childChangeListener) {
		ChildChangeCache context = getChildChangeCache(path);
		context.addChildChangesListener(childChangeListener);
	}

	public void clearDataListeners(String path) {
		DataChangeCache context = dataChangeMap.get(path);
		if (null != context) {
			context.clearConfigDataListener();
		}
	}

	public void clearChildChangeListeners(String path) {
		ChildChangeCache context = childChangeMap.get(path);
		if (null != context) {
			context.clearChildChangesListener();
		}
	}

	private ChildChangeCache getChildChangeCache(String path) {
		ChildChangeCache context = childChangeMap.get(path);
		if (null == context) {
			context = childChangeMap.putIfAbsent(path, new ChildChangeCache(path, new PathChildrenCache(this.zkClient, path, false, false, curatorExecutor),
					callBackExecutor));
			if (null == context) {
				context = childChangeMap.get(path).init();
			}
		}
		return context;
	}

	public void removeChildChangesListener(String path, ChildChangeListener childChangeListener) {
		ChildChangeCache context = childChangeMap.get(path);
		if (null != context) {
			context.removeChildChangesListener(childChangeListener);
		}
	}

	public void addConnectionStatusListener(ConnectionStatusListener connectionStatusListener) {
		this.connectionStatusListenerList.add(connectionStatusListener);
	}

	public void removeConnectionStatusListener(ConnectionStatusListener connectionStatusListener) {
		this.connectionStatusListenerList.remove(connectionStatusListener);
	}

	public ZookeeperConfigDO getScZkConfig() {
		return scZkConfig;
	}

	public void setScZkConfig(ZookeeperConfigDO scZkConfig) {
		this.scZkConfig = scZkConfig;
	}

}
