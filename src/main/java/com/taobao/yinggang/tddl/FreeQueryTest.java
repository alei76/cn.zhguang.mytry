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

		// ��ʼ��tddl����Դ
		TDataSource ds = new TDataSource();
		ds.setAppName(appName);
		ds.setDynamicRule(true);
		ds.init();

		// ��ȡ���ֲ���Ϣ ,TDDL�汾>=3.3.2����ֱ�Ӵ�����Դ�л�ȡ����,�Ͱ汾�μӷ���@getAppTopologyFromMetaClient(String appName, String
		// logicTableName)
		// ע�⣬���淽�����ܷ���һ���ն����������������ͽݳ���ϵ
		// AppTopology appTopology = ds.getAppTopology();
		AppTopology appTopology = getAppTopologyFromMetaClient(appName, logicTableName);
		// ��ȡ���߼���ķֲ���Ϣ
		TableTopology topology = appTopology.lookup(logicTableName);
		// key��ʾgroupKey,value��ʾ��group����ñ�ķֲ�
		Map<String, NamePattern> map = topology.map();

		// �������ִ��sql
		for (String groupKey : map.keySet()) {
			NamePattern np = map.get(groupKey);
			System.out.println(groupKey + "=" + np.list());
			for (String realTableName : np.list()) {
				executePerTable(ds, logicTableName, groupKey, realTableName);
			}
		}
	}

	/*
	 * ��MetaClient�л�ȡ���ֲ��ṹ,TDDL�汾<3.3.2��ʹ�÷��� 1.������� <dependency> <groupId>com.taobao.metacenter</groupId>
	 * <artifactId>meta-client</artifactId> <version>2.1.1-SNAPSHOT</version> </dependency>
	 * 2.�״λ�ȡ�󻺴�AppTopology������ÿ��������ȡ��������� 3.����÷����׳��쳣��˵������δ��ȷ�ϣ�����ϵ�ݳ���
	 */
	private static AppTopology getAppTopologyFromMetaClient(String appName, String logicTableName) {
		MetaClient client = MetaCenter.getMetaClient();
		AppTopology appTopology = client.getAppTopology(appName);
		return appTopology;
	}

	/**
	 * ʹ��tddl hintָ������Ŀ��ִ��sql�� 1.JDBC�û�ֱ����sqlǰƴװhint 2.IBATIS�û�����ͨ��##��ռλ���滻��ʽ����hintд��xml�����ļ��� 3.������������ʹ��tddl
	 * 3.3+�汾��Ӧ�ã������°汾hint�����ϰ汾hint˵������μ�@http://gitlab.alibaba-inc.com/middleware/tddl/wikis/home
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

			// ��һ���������߼���������ʾsql�е��������Ҫ�����滻
			ps.setString(1, logicTableName);
			// �ڶ���������groupKey�������������ִ��
			ps.setString(2, groupKey);
			// �����������Ǿ���������߼��������ᱻ�滻�ɸ�����ִ��sql
			ps.setString(3, realTableName);
			// ���ĸ�������sql�е�limitֵ��
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
