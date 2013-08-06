package com.szwx.yht.util;

import com.szwx.yht.common.Config;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Properties;


public class DBConnection {

	private static HashMap<String, BasicDataSource> map = new HashMap<String, BasicDataSource>();

	public DBConnection() {
	}

	private static void init(String name) {

		BasicDataSource dataSource = null;

		if (dataSource != null) {
			try {
				dataSource.close();
			} catch (Exception e) {

			}
			dataSource = null;
		}

		try {
			Properties p =Config.newInstance().getDb();
			dataSource = (BasicDataSource) BasicDataSourceFactory
					.createDataSource(p);
			map.put(name, dataSource);
		} catch (Exception e) {

		}
	}

	public static synchronized DataSource getDataSource(String name) {
		if (!map.containsKey(name)) {

			init(name);
		}
		return map.get(name);
	}

	public static Connection getConnection(String name) throws SQLException {

		Connection conn = null;

		if (getDataSource(name) != null) {
			conn = getDataSource(name).getConnection();
		}

		return conn;
	}

	public synchronized static void closePool(String name) {
		if (map.containsKey(name)) {
			try {
				map.get(name).close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
}
