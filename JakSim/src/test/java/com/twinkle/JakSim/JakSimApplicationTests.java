package com.twinkle.JakSim;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
class JakSimApplicationTests {
	@Autowired
	private DataSource ds;


	@Test
	void contextLoads() throws SQLException {
		Connection conn = ds.getConnection();
		if(conn != null)
			System.out.println("DB connection Success");
		else
			System.out.println("Driver Load Fail");
	}

	@Test
	void ShowAll() throws SQLException {
		Connection conn = ds.getConnection();
		String sql = "select * from user_info where user_id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "wkdgyfla97");
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println(rs.getString("user_id"));
			System.out.println(rs.getString("user_name"));
			System.out.println(rs.getString("user_c_dt"));
			System.out.println(rs.getString("user_birth"));
		}
		if(!conn.isClosed())
			conn.close();
	}
}
