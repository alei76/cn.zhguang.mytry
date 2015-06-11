/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * ConfigManager.java File Created at 下午3:25:41
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

import java.util.List;
import java.util.Map;

import org.apache.zookeeper.data.Stat;

/**
 * 配置信息管理器，可以使用zk或者dimond实现
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年6月4日
 */
public interface ConfigManager {
	/**
	 * 指定zk的path获取数据
	 * <p/>
	 * 从CACHE中获取，会存在一段时间与服务器数据不一致的情况
	 * 
	 * @param path
	 * @return
	 */
	public String getDataCache(String path);

	/**
	 * 指定zk的path获取数据
	 * 
	 * @param path
	 * @return
	 */
	public String getData(String path);

	/**
	 * 获取该路径下的各子节点的信息 从CACHE中获取，会存在段时间与服务器数据不一致的情况
	 * 
	 * @param path 父节点PATH
	 * @return List<childName>
	 */
	public List<String> getChildCache(String path);

	/**
	 * 获取该路径下的各子节点的信息
	 * 
	 * @param path 父节点PATH
	 * @return Map<childName,dataString>
	 */
	public Map<String, String> getChildDatas(String path);

	/**
	 * 判断指定zk的path是否存在 <br/>
	 * 注：是指判断指定zk的path是否存在而非数据的存在
	 * 
	 * @param path
	 * @return
	 */
	public boolean exists(String path);

	/**
	 * 获取stat
	 * 
	 * @version 1.0
	 * @param path
	 * @return
	 */
	public Stat getStat(String path);

	/**
	 * 发布数据
	 * 
	 * @param path 路径
	 * @param data 字符串数据
	 * @param isPersistent 是否是持久数据
	 * @throws Exception
	 */
	public void publishData(String path, String data, boolean isPersistent) throws Exception;

	/**
	 * 更新指定PATH数据
	 * 
	 * @param path 路径
	 * @param data 字符串数据
	 * @throws Exception
	 */
	public void updateData(String path, String data) throws Exception;

	/**
	 * 删除指定PATH下的数据
	 * 
	 * @param path
	 * @throws Exception
	 */
	public void delete(String path) throws Exception;

	/**
	 * 发布或者更新指定zk的path或者dataId的数据 注：由于多调用了exists会多一次网络交互
	 * 
	 * @param path 路径
	 * @param data 字符串数据
	 * @param isPersistent 是否是持久数据
	 * @throws Exception
	 */
	public void publishOrUpdateData(String path, String data, boolean isPersistent) throws Exception;

	/**
	 * 发布带序列的数据
	 * 
	 * @param path 路径
	 * @param data 字符串数据
	 * @param isPersistent 是否是持久数据
	 * @throws Exception
	 */
	public String publishDataSequential(String path, String data, boolean isPersistent) throws Exception;

	/**
	 * 添加zk的data的数据监听器
	 * 
	 * @param path
	 * @param listener
	 */
	public void addDataListener(String path, final DataChangeListener listener);

	/**
	 * 移除zk的data的数据监听器
	 * 
	 * @param path
	 * @param listener
	 */
	public void removeDataListener(String path, final DataChangeListener listener);

	/**
	 * 添加指定父节点下的子节点监听
	 * 
	 * @param path
	 * @param childChangeListener
	 */
	public void addChildChangesListener(String path, final ChildChangeListener childChangeListener);

	/**
	 * 移除添加指定父节点下的子节点监听
	 * 
	 * @param path
	 * @param childChangeListener
	 */
	public void removeChildChangesListener(String path, final ChildChangeListener childChangeListener);

	/**
	 * 设置Session监听器
	 * 
	 * @param connectionStateListener
	 */
	public void addConnectionStatusListener(final ConnectionStatusListener connectionStatusListener);

	/**
	 * 移除Session监听器
	 * 
	 * @param connectionStateListener
	 */
	public void removeConnectionStatusListener(ConnectionStatusListener connectionStatusListener);

	/**
	 * 清楚数据变化监听器
	 * 
	 * @version 1.0
	 * @param path
	 */
	public void clearDataListeners(String path);

	/**
	 * 清楚节点变化监听器
	 * 
	 * @version 1.0
	 * @param path
	 */
	public void clearChildChangeListeners(String path);

	public void init();

	public void destory();

	public ZookeeperConfigDO getScZkConfig();

	public void setScZkConfig(ZookeeperConfigDO scZkConfig);

}
