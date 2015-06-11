/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.taobao.yinggang.curatorexample.zk;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.data.Stat;

public class ZkConfigMangerTest {
	private static final String PATH = "/example-test-yinggang/cache";

	public static void main(String[] args) throws Exception {
		ZkConfigManager configManager = new ZkConfigManager();

		try {
			ZookeeperConfigDO scZkConfig = new ZookeeperConfigDO();
			scZkConfig.setZkConfigIpData("10.125.193.148:2181,10.125.192.53:2181,10.232.102.191:2181");
			scZkConfig.setRootPath(PATH);
			scZkConfig.setZkSessionTimeout("5000");
			configManager.setScZkConfig(scZkConfig);
			configManager.init();

			processCommands(configManager);
		} finally {
			configManager.destory();
		}
	}

	private static void addListener(final ZkConfigManager zkConfigManager) {
		ConnectionStatusListener connectionStatusListener = new ConnectionStatusListener() {

			@Override
			public void handleStateChanged(ConnectionState state) throws Exception {
				System.out.println("Connection status changed: state=" + state);
			}
		};
		zkConfigManager.addConnectionStatusListener(connectionStatusListener);

		DataChangeListener dataChangeListener = new DataChangeListener() {

			@Override
			public void handleData(String path, String data) {
				System.out.println("Node data changed : path=" + path + ",data=" + data);
				Stat stat = zkConfigManager.getStat(path);
				System.out.println("Node : version:" + path + stat.getVersion() + ",cversion:" + stat.getCversion() + ",aversion:" + stat.getAversion());
			}
		};
		zkConfigManager.addDataListener(PATH + "/yz", dataChangeListener);

		ChildChangeListener childChangeListener = new ChildChangeListener() {

			@Override
			public void handleChild(String parentPath, String childPath, Type type) {
				System.out.println("Child changed: parentPath=" + parentPath + ",childpath= " + childPath + ",type=" + type);
				Stat stat = zkConfigManager.getStat(parentPath);
				System.out.println("Node : version: " + parentPath + stat.getVersion() + ",cversion:" + stat.getCversion() + ",aversion:" + stat.getAversion());
				Stat stat2 = zkConfigManager.getStat(childPath);
				System.out.println("Node : version: " + childPath + stat2.getVersion() + ",cversion:" + stat2.getCversion() + ",aversion:"
						+ stat2.getAversion());
			}
		};
		zkConfigManager.addChildChangesListener(PATH, childChangeListener);
	}

	private static void processCommands(ZkConfigManager zkConfigManager) throws Exception {
		printHelp();

		try {
			addListener(zkConfigManager);

			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			boolean done = false;
			while (!done) {
				System.out.print("> ");

				String command = in.readLine().trim();
				String[] parts = command.split("\\s");
				if (parts.length == 0) {
					continue;
				}
				String operation = parts[0];
				String args[] = Arrays.copyOfRange(parts, 1, parts.length);

				if (operation.equalsIgnoreCase("help") || operation.equalsIgnoreCase("?")) {
					printHelp();
				} else if (operation.equalsIgnoreCase("q") || operation.equalsIgnoreCase("quit")) {
					done = true;
				} else if (operation.equals("set")) {
					setValue(zkConfigManager, command, args);
				} else if (operation.equals("remove")) {
					remove(zkConfigManager, command, args);
				} else if (operation.equals("list")) {
					list(zkConfigManager, command, args);
				}

				Thread.sleep(1000); // just to allow the console output to catch up
			}
		} finally {
		}
	}

	private static void list(ZkConfigManager zkConfigManager, String command, String[] args) {
		if (args.length != 1) {
			System.err.println("syntax error (expected list <path>): " + command);
			return;
		}

		String path = args[0];
		String fullPath = PATH;
		if (!path.equals(".")) {
			fullPath += "/" + path;
		}
		Map<String, String> childrenMap = zkConfigManager.getChildDatas(fullPath);
		for (Map.Entry<String, String> entry : childrenMap.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
			String subPath = fullPath + "/" + entry.getKey();
			if (zkConfigManager.exists(subPath)) {
				Map<String, String> childrenMap2 = zkConfigManager.getChildDatas(subPath);
				for (Map.Entry<String, String> entry2 : childrenMap2.entrySet()) {
					System.out.println(entry2.getKey() + " = " + entry2.getValue());
				}
			}
		}
	}

	private static void remove(ZkConfigManager zkConfigManager, String command, String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("syntax error (expected remove <path>): " + command);
			return;
		}

		String path = args[0];
		String fullPath = ZKPaths.makePath(PATH, path);

		try {
			zkConfigManager.delete(fullPath);
		} catch (KeeperException.NoNodeException e) {
			e.printStackTrace();
		}
	}

	private static void setValue(ZkConfigManager zkConfigManager, String command, String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("syntax error (expected set <path> <value>): " + command);
			return;
		}

		String path = args[0];
		String fullPath = ZKPaths.makePath(PATH, path);

		String data = args[1];
		zkConfigManager.publishOrUpdateData(fullPath, data, true);
	}

	private static void printHelp() {
		System.out.println("An example of using PathChildrenCache. This example is driven by entering commands at the prompt:\n");
		System.out.println("set <name> <value>: Adds or updates a node with the given name");
		System.out.println("remove <name>: Deletes the node with the given name");
		System.out.println("list: List the nodes/values in the cache");
		System.out.println("quit: Quit the example");
		System.out.println();
	}
}
