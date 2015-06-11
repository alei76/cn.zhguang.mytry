package com.taobao.yinggang.tddl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import com.taobao.metacenter.client.MetaCenter;
import com.taobao.metacenter.client.MetaClient;
import com.taobao.metacenter.common.topology.AppTopology;
import com.taobao.metacenter.common.topology.NamePattern;
import com.taobao.metacenter.common.topology.TableTopology;
import com.taobao.tddl.client.jdbc.TDataSource;

public class FreeQueryTest {

	public static void main(String args[]) throws Exception {

		String appName = "CAINIAO_SCD_APP";
		String logicTableName = "sc_schedule_task";

		// 初始化tddl数据源
		TDataSource ds = new TDataSource();
		ds.setAppName(appName);
		ds.setDynamicRule(true);
		ds.init();

		// 获取库表分布信息 ,TDDL版本>=3.3.2可以直接从数据源中获取拓扑,低版本参加方法@getAppTopologyFromMetaClient(String appName, String
		// logicTableName)
		// 注意，下面方法可能返回一个空对象，遇到这种情况请和捷辰联系
		// AppTopology appTopology = ds.getAppTopology();
		AppTopology appTopology = getAppTopologyFromMetaClient(appName, logicTableName);
		// 获取该逻辑表的分布信息
		TableTopology topology = appTopology.lookup(logicTableName);
		// key表示groupKey,value表示该group下面该表的分布
		Map<String, NamePattern> map = topology.map();

		// 遍历库表执行sql
		for (String groupKey : map.keySet()) {
			NamePattern np = map.get(groupKey);
			System.out.println(groupKey + "=" + np.list());
			for (String realTableName : np.list()) {
				executePerTable(ds, logicTableName, groupKey, realTableName);
			}
		}
	}

	/*
	 * 从MetaClient中获取库表分布结构,TDDL版本<3.3.2的使用方法 1.依赖添加 <dependency> <groupId>com.taobao.metacenter</groupId>
	 * <artifactId>meta-client</artifactId> <version>2.1.1-SNAPSHOT</version> </dependency>
	 * 2.首次获取后缓存AppTopology，避免每次主动获取的网络代价 3.如果该方法抛出异常，说明拓扑未被确认，请联系捷辰。
	 */
	private static AppTopology getAppTopologyFromMetaClient(String appName, String logicTableName) {
		MetaClient client = MetaCenter.getMetaClient();
		AppTopology appTopology = client.getAppTopology(appName);
		return appTopology;
	}

	/**
	 * 使用tddl hint指定具体的库表执行sql。 1.JDBC用户直接在sql前拼装hint 2.IBATIS用户可以通过##的占位符替换方式，将hint写在xml配置文件中 3.该例子适用于使用tddl
	 * 3.3+版本的应用，属于新版本hint，新老版本hint说明具体参见@http://gitlab.alibaba-inc.com/middleware/tddl/wikis/home
	 * 
	 * @param ds
	 * @param groupKey
	 * @param realTableName
	 * @throws SQLException
	 */
	public static void executePerTable(DataSource ds, String logicTableName, String groupKey, String realTableName) throws SQLException {
		String sql = "/*+TDDL({\"type\":\"direct\",\"vtab\":\"?\",\"dbid\":\"?\",\"realtabs\":[\"?\"]})*/ select * from " + logicTableName + " limit ?";

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = ds.getConnection();
			ps = conn.prepareStatement(sql);

			// 第一个参数是逻辑表名，表示sql中的这个表名要进行替换
			ps.setString(1, logicTableName);
			// 第二个参数是groupKey，即在这个库上执行
			ps.setString(2, groupKey);
			// 第三个参数是具体表名，逻辑表名将会被替换成该名字执行sql
			ps.setString(3, realTableName);
			// 第四个参数是sql中的limit值。
			ps.setInt(4, 2);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StringBuilder sb = new StringBuilder();
				int count = rs.getMetaData().getColumnCount();
				for (int i = 1; i <= count; i++) {

					String key = rs.getMetaData().getColumnLabel(i);
					Object val = rs.getObject(i);
					sb.append("[" + rs.getMetaData().getTableName(i) + "." + key + "->" + val + "]");
				}
				System.out.println(sb.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
	}

}
