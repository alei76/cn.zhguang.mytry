/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * ChildChangeCache.java File Created at 下午3:46:17
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.utils.ZKPaths;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

/**
 * 对于PathChildrenCache的封装，用于监控子节点的变化
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月4日
 */
public class ChildChangeCache {
	private static final Logger logger = LoggerFactory.getLogger(ChildChangeCache.class);

	private final String path;

	private final PathChildrenCache childrenCache;

	private final Executor executor;

	private final CopyOnWriteArrayList<ChildChangeListener> childrenChangeListeners = Lists.newCopyOnWriteArrayList();

	private final PathChildrenCacheListener childrenCacheListener = new PathChildrenCacheListener() {

		public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
			final PathChildrenCacheEvent.Type type = event.getType();
			switch (type) {
				case CONNECTION_RECONNECTED:
					childrenCache.rebuild();
					break;
				case CHILD_ADDED:
				case CHILD_REMOVED:
					final String childPath = event.getData().getPath();
					for (final ChildChangeListener listener : childrenChangeListeners) {
						if (null != executor) {
							executor.execute(new Runnable() {
								public void run() {
									try {
										listener.handleChild(path, childPath, type);
									} catch (Throwable e) {
										logger.error("Handle ChildChangeCache Listener Error! path: " + path, e);
									}
								}
							});
						} else {
							try {
								listener.handleChild(path, childPath, type);
							} catch (Throwable e) {
								logger.error("Handle ChildChangeCache Listener Error! path: " + path, e);
								continue;
							}
						}
					}
				default:
					break;
			}
		}
	};

	public ChildChangeCache(String path, PathChildrenCache childrenCache, Executor executor) {
		this.path = path;
		this.childrenCache = childrenCache;
		this.executor = executor;
	}

	public ChildChangeCache init() {
		try {
			this.childrenCache.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);
			if (null != this.executor) {
				this.childrenCache.getListenable().addListener(this.childrenCacheListener, this.executor);
			} else {
				this.childrenCache.getListenable().addListener(this.childrenCacheListener);
			}
		} catch (Throwable e) {
			logger.error("DataChangeContext Start ChildrenCache Error!: Parent Path: " + path, e);
		}
		return this;
	}

	public void addChildChangesListener(ChildChangeListener childChangeListener) {
		this.childrenChangeListeners.addIfAbsent(childChangeListener);
	}

	public void removeChildChangesListener(ChildChangeListener childChangeListener) {
		this.childrenChangeListeners.remove(childChangeListener);
	}

	public void clearChildChangesListener() {
		this.childrenChangeListeners.clear();
	}

	public String getPath() {
		return path;
	}

	public List<String> getCurrentChildren() throws UnsupportedEncodingException {
		List<ChildData> childrenDatas = this.childrenCache.getCurrentData();
		List<String> childrenList = new ArrayList<String>(childrenDatas.size());

		for (ChildData childData : childrenDatas) {
			String childrenPath = ZKPaths.getNodeFromPath(childData.getPath());
			childrenList.add(childrenPath);
		}
		return childrenList;
	}

	public ChildChangeCache destory() throws IOException {
		this.childrenCache.getListenable().removeListener(this.childrenCacheListener);
		this.childrenCache.close();
		return this;
	}
}
