/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * ConfigManager.java File Created at ����3:25:41
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
 * ������Ϣ������������ʹ��zk����dimondʵ��
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014��6��4��
 */
public interface ConfigManager {
	/**
	 * ָ��zk��path��ȡ����
	 * <p/>
	 * ��CACHE�л�ȡ�������һ��ʱ������������ݲ�һ�µ����
	 * 
	 * @param path
	 * @return
	 */
	public String getDataCache(String path);

	/**
	 * ָ��zk��path��ȡ����
	 * 
	 * @param path
	 * @return
	 */
	public String getData(String path);

	/**
	 * ��ȡ��·���µĸ��ӽڵ����Ϣ ��CACHE�л�ȡ������ڶ�ʱ������������ݲ�һ�µ����
	 * 
	 * @param path ���ڵ�PATH
	 * @return List<childName>
	 */
	public List<String> getChildCache(String path);

	/**
	 * ��ȡ��·���µĸ��ӽڵ����Ϣ
	 * 
	 * @param path ���ڵ�PATH
	 * @return Map<childName,dataString>
	 */
	public Map<String, String> getChildDatas(String path);

	/**
	 * �ж�ָ��zk��path�Ƿ���� <br/>
	 * ע����ָ�ж�ָ��zk��path�Ƿ���ڶ������ݵĴ���
	 * 
	 * @param path
	 * @return
	 */
	public boolean exists(String path);

	/**
	 * ��ȡstat
	 * 
	 * @version 1.0
	 * @param path
	 * @return
	 */
	public Stat getStat(String path);

	/**
	 * ��������
	 * 
	 * @param path ·��
	 * @param data �ַ�������
	 * @param isPersistent �Ƿ��ǳ־�����
	 * @throws Exception
	 */
	public void publishData(String path, String data, boolean isPersistent) throws Exception;

	/**
	 * ����ָ��PATH����
	 * 
	 * @param path ·��
	 * @param data �ַ�������
	 * @throws Exception
	 */
	public void updateData(String path, String data) throws Exception;

	/**
	 * ɾ��ָ��PATH�µ�����
	 * 
	 * @param path
	 * @throws Exception
	 */
	public void delete(String path) throws Exception;

	/**
	 * �������߸���ָ��zk��path����dataId������ ע�����ڶ������exists���һ�����罻��
	 * 
	 * @param path ·��
	 * @param data �ַ�������
	 * @param isPersistent �Ƿ��ǳ־�����
	 * @throws Exception
	 */
	public void publishOrUpdateData(String path, String data, boolean isPersistent) throws Exception;

	/**
	 * ���������е�����
	 * 
	 * @param path ·��
	 * @param data �ַ�������
	 * @param isPersistent �Ƿ��ǳ־�����
	 * @throws Exception
	 */
	public String publishDataSequential(String path, String data, boolean isPersistent) throws Exception;

	/**
	 * ���zk��data�����ݼ�����
	 * 
	 * @param path
	 * @param listener
	 */
	public void addDataListener(String path, final DataChangeListener listener);

	/**
	 * �Ƴ�zk��data�����ݼ�����
	 * 
	 * @param path
	 * @param listener
	 */
	public void removeDataListener(String path, final DataChangeListener listener);

	/**
	 * ���ָ�����ڵ��µ��ӽڵ����
	 * 
	 * @param path
	 * @param childChangeListener
	 */
	public void addChildChangesListener(String path, final ChildChangeListener childChangeListener);

	/**
	 * �Ƴ����ָ�����ڵ��µ��ӽڵ����
	 * 
	 * @param path
	 * @param childChangeListener
	 */
	public void removeChildChangesListener(String path, final ChildChangeListener childChangeListener);

	/**
	 * ����Session������
	 * 
	 * @param connectionStateListener
	 */
	public void addConnectionStatusListener(final ConnectionStatusListener connectionStatusListener);

	/**
	 * �Ƴ�Session������
	 * 
	 * @param connectionStateListener
	 */
	public void removeConnectionStatusListener(ConnectionStatusListener connectionStatusListener);

	/**
	 * ������ݱ仯������
	 * 
	 * @version 1.0
	 * @param path
	 */
	public void clearDataListeners(String path);

	/**
	 * ����ڵ�仯������
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
