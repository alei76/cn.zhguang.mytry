/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * DataChangeCache.java File Created at 下午2:35:30
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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * 对于NodeCache的封装，可设置listener监听nodecache的变化
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月4日
 */
public class DataChangeCache {
	private static final Logger logger = LoggerFactory.getLogger(DataChangeCache.class);

	private final String path;

	private final NodeCache nodeCache;

	private final Executor executor;

	private final CopyOnWriteArrayList<DataChangeListener> dataChangeListeners = Lists.newCopyOnWriteArrayList();

	private final NodeCacheListener nodeCacheListener = new NodeCacheListener() {

		public void nodeChanged() throws Exception {
			ChildData childData = nodeCache.getCurrentData();
			final String data = null != childData ? SerializeUtil.deserialize(childData.getData()) : null;
			for (final DataChangeListener listener : dataChangeListeners) {
				if (null != executor) {
					executor.execute(new Runnable() {
						public void run() {
							try {
								listener.handleData(path, data);
							} catch (Throwable e) {
								logger.error("asyncHandleDataChange$Handle DataChange Listener Error! path:{} ",
										new Object[] { path, e });
							}
						}
					});
				} else {
					try {
						listener.handleData(path, data);
					} catch (Throwable e) {
						logger.error("handleDataChange$Handle DataChange Listener Error! path:{} ", new Object[] {
								path, e });
						continue;
					}
				}
			}
		}
	};

	public DataChangeCache(String path, NodeCache nodeCache, Executor executor) {
		this.path = path;
		this.nodeCache = nodeCache;
		this.executor = executor;
	}

	public DataChangeCache init() {
		try {
			this.nodeCache.start(true);
			if (null != this.executor) {
				this.nodeCache.getListenable().addListener(this.nodeCacheListener, executor);
			} else {
				this.nodeCache.getListenable().addListener(this.nodeCacheListener);
			}
		} catch (Throwable e) {
			logger.error("startNodeCache$DataChangeContext Start NodeCache Error! path:{} ", new Object[] {
					nodeCache.getCurrentData().getPath(), e });
		}
		return this;
	}

	public DataChangeCache destory() throws IOException {
		this.nodeCache.getListenable().removeListener(this.nodeCacheListener);
		this.nodeCache.close();
		return this;
	}

	public void addConfigDataListener(DataChangeListener listener) {
		this.dataChangeListeners.addIfAbsent(listener);
	}

	public void removeConfigDataListener(DataChangeListener listener) {
		this.dataChangeListeners.remove(listener);
	}

	public void clearConfigDataListener() {
		this.dataChangeListeners.clear();
	}

	public String getPath() {
		return path;
	}

	public String getCurrentData() throws UnsupportedEncodingException {
		ChildData childData = this.nodeCache.getCurrentData();
		if (null != childData) {
			return SerializeUtil.deserialize(this.nodeCache.getCurrentData().getData());

		}
		return null;
	}

}
