package com.taobao.yinggang.curatorexample.leaderlock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * 一种特殊的读锁，是为了防止进行某些操作时，数据发生变化，提供如下功能：<br/>
 * <ul>
 * <li>只有leader能执行lock和release操作</li>
 * <li>非leader只能判断是否已锁住，不能执行锁操作，不能获得锁</li>
 * <li>当leader获得锁时，任何人（包括leader在内），都不能写入</li>
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014年9月17日
 */
public class LeaderReadLock extends InterProcessMutex {

	private CuratorFramework client;

	private String path;

	/**
	 * @param client
	 * @param path 要加锁的路径
	 */
	public LeaderReadLock(CuratorFramework client, String path) {
		super(client, path);
		this.client = client;
		this.path = path;
	}

	public void lock(boolean isLeader) throws Exception {
		if (isLeader) {
			acquire();
		} else {

		}
	}

	public void release() throws Exception {
		release();
	}
}
