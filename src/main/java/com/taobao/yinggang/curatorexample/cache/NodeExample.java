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
package com.taobao.yinggang.curatorexample.cache;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.framework.api.CuratorListener;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicValue;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.test.TestingServer;
import org.apache.curator.utils.CloseableUtils;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * An example of the PathChildrenCache. The example "harness" is a command processor that allows adding/updating/removed
 * nodes in a path. A PathChildrenCache keeps a cache of these changes and outputs when updates occurs.
 */
public class NodeExample {

	private static final String PATH = "/example-test-yinggang/cache";

	public static void main(String[] args) throws Exception {
		final TestingServer server = new TestingServer();
		CuratorFramework client = null;
		try {
			// client = CuratorFrameworkFactory.newClient(server.getConnectString(), new ExponentialBackoffRetry(1000,
			// 3));
			client = CuratorFrameworkFactory.newClient("127.0.0.1", new ExponentialBackoffRetry(1000, 3));
			client.start();
			processCommands(client);
		} finally {
			CloseableUtils.closeQuietly(client);
			CloseableUtils.closeQuietly(server);
		}
	}

	private static void addListener(CuratorFramework client) {
		// a PathChildrenCacheListener is optional. Here, it's used just to log changes
		CuratorListener listener = new CuratorListener() {

			private void printEvent(CuratorEvent event) {
				System.out.println("Node " + event.getType() + ": " + event.getPath() + "=" + ZKPaths.getNodeFromPath(event.getPath()) + "version:"
						+ event.getStat().getVersion() + ",cversion:" + event.getStat().getCversion() + ",aversion:" + event.getStat().getAversion());
			}

			@Override
			public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception {
				System.out.println("Node changed:" + event.getType() + "_" + event.getName() + "_" + event.getStat() + "_" + event.getPath());
				switch (event.getType()) {
					case CREATE: {
						printEvent(event);
						break;
					}

					case SET_DATA: {
						printEvent(event);
						break;
					}

					case DELETE: {
						printEvent(event);
						break;
					}
					default:
						break;
				}
			}
		};
		client.getCuratorListenable().addListener(listener);
	}

	private static void addWatcher(CuratorFramework client) {
		Watcher watcher = new Watcher() {

			@Override
			public void process(WatchedEvent event) {
				System.out.println("Watched Node changed:" + event.getType() + "_" + event.getState() + "_" + event.getPath());
			}

		};
		try {
			// client.getChildren().usingWatcher(watcher).forPath(PATH + "/yz");
			// client.getData().usingWatcher(watcher).forPath(PATH + "/yz");
			client.checkExists().usingWatcher(watcher).forPath(PATH + "/yz");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void processCommands(CuratorFramework client) throws Exception {
		// More scaffolding that does a simple command line processor
		printHelp();
		try {
			addListener(client);
			addWatcher(client);
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
					setValue(client, command, args);
				} else if (operation.equals("remove")) {
					remove(client, command, args);
				} else if (operation.equals("list")) {
					list(client, command, args);
				}

				Thread.sleep(1000); // just to allow the console output to catch up
			}
		} finally {
		}
	}

	private static void list(CuratorFramework client, String command, String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("syntax error (expected list <path>): " + command);
			return;
		}
		getZKAssignVersion(client);
		String path = args[0];
		try {
			String fullPath = PATH;
			if (!path.equals(".")) {
				fullPath += "/" + path;
			}

			System.out.println(fullPath + " = " + new String(client.getData().forPath(fullPath)));
			List<String> children = client.getChildren().forPath(fullPath);

			for (String child : children) {
				String childrenPath = fullPath + "/" + child;
				System.out.println(childrenPath + " = " + new String(client.getData().forPath(childrenPath)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void remove(CuratorFramework client, String command, String[] args) throws Exception {
		if (args.length != 1) {
			System.err.println("syntax error (expected remove <path>): " + command);
			return;
		}

		String name = args[0];
		// if (name.contains("/")) {
		// System.err.println("Invalid node name" + name);
		// return;
		// }
		String path = ZKPaths.makePath(PATH, name);
		refreshZKAssignVersion(client, "aaaa");
		try {
			client.delete().forPath(path);
			System.out.println("delete path" + path);
		} catch (KeeperException.NoNodeException e) {
			e.printStackTrace();
		}
	}

	private static void setValue(CuratorFramework client, String command, String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("syntax error (expected set <path> <value>): " + command);
			return;
		}

		String name = args[0];
		// if (name.contains("/")) {
		// System.err.println("Invalid node name" + name);
		// return;
		// }
		String path = ZKPaths.makePath(PATH, name);
		refreshZKAssignVersion(client, "bbbb");
		byte[] bytes = args[1].getBytes();
		try {
			client.setData().forPath(path, bytes);
			System.out.println("update path " + path + " = " + new String(bytes));
		} catch (KeeperException.NoNodeException e) {
			client.create().creatingParentsIfNeeded().forPath(path, bytes);
			System.out.println("add path" + path + " = " + new String(bytes));
		}
	}

	private static String getZKAssignVersion(CuratorFramework client) throws Exception {
		String path = PATH + "/version";
		DistributedAtomicValue value = new DistributedAtomicValue(client, path, new RetryNTimes(32, 1000));

		AtomicValue<byte[]> rc = value.get();
		byte[] assignVersion = rc.postValue();
		String strValue = new String(assignVersion);
		System.out.println("Current version:" + strValue);
		return strValue;
	}

	private static void refreshZKAssignVersion(CuratorFramework client, String param) throws Exception {
		String path = PATH + "/version";
		DistributedAtomicValue value = new DistributedAtomicValue(client, path, new RetryNTimes(32, 1000));

		AtomicValue<byte[]> rc;
		do {
			rc = value.trySet(param.getBytes());
		} while (!rc.succeeded());
		System.out.println("factory分派完成，分派前版本号：" + new String(rc.preValue()) + ",分派后版本号：" + new String(rc.postValue()));
	}

	private static void printHelp() {
		System.out.println("An example of using Nomal node. This example is driven by entering commands at the prompt:\n");
		System.out.println("set <name> <value>: Adds or updates a node with the given name");
		System.out.println("remove <name>: Deletes the node with the given name");
		System.out.println("list <name> : List the nodes/values with the given name");
		System.out.println("quit: Quit the example");
		System.out.println();
	}
}
