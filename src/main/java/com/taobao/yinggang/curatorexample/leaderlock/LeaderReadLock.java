package com.taobao.yinggang.curatorexample.leaderlock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * һ������Ķ�������Ϊ�˷�ֹ����ĳЩ����ʱ�����ݷ����仯���ṩ���¹��ܣ�<br/>
 * <ul>
 * <li>ֻ��leader��ִ��lock��release����</li>
 * <li>��leaderֻ���ж��Ƿ�����ס������ִ�������������ܻ����</li>
 * <li>��leader�����ʱ���κ��ˣ�����leader���ڣ���������д��</li>
 * 
 * @author <a href="mailto:yinggang.zg@taobao.com">yinggang.zg</a>
 * @version 1.0
 * @since 2014��9��17��
 */
public class LeaderReadLock extends InterProcessMutex {

	private CuratorFramework client;

	private String path;

	/**
	 * @param client
	 * @param path Ҫ������·��
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
