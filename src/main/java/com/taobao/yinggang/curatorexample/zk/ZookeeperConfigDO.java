/**
 *   _/          _/  _/        _/_/_/    
 *  _/          _/  _/        _/    _/   
 * _/    _/    _/  _/        _/_/_/      
 *  _/  _/  _/    _/        _/    _/     
 *   _/  _/      _/_/_/_/  _/_/_/     
 * 
 * Project: schedule-core
 * 
 * ZookeeperConfigDO.java File Created at 上午12:08:12
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

/**
 * zk配置信息
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年3月29日
 */
public class ZookeeperConfigDO {
	/**
	 * Schedule使用的ZK根路径
	 */
	private String rootPath;

	/**
	 * Schedule使用的的ZK的用户名
	 */
	private String userName;

	/**
	 * Schedule使用的ZK的密码
	 */
	private String password;

	/**
	 * Schedule使用的ZK的session超时
	 */
	private String zkSessionTimeout;

	/**
	 * Diamond中配置的ZK IP地址
	 */
	private String zkConfigIpData;

	/**
	 * 是否校验父路径
	 */
	private String isCheckParentPath;

	public String getRootPath() {
		return rootPath;
	}

	public void setRootPath(String rootPath) {
		this.rootPath = rootPath;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZkSessionTimeout() {
		return zkSessionTimeout;
	}

	public void setZkSessionTimeout(String zkSessionTimeout) {
		this.zkSessionTimeout = zkSessionTimeout;
	}

	public String getZkConfigIpData() {
		return zkConfigIpData;
	}

	public void setZkConfigIpData(String zkConfigIpData) {
		this.zkConfigIpData = zkConfigIpData;
	}

	public String getIsCheckParentPath() {
		return isCheckParentPath;
	}

	public void setIsCheckParentPath(String isCheckParentPath) {
		this.isCheckParentPath = isCheckParentPath;
	}
}
