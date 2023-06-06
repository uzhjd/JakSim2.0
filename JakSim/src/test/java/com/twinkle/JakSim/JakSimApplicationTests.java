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
		String sql = "select * from question where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, "1");
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			System.out.println(rs.getString("title"));
			System.out.println(rs.getString("content"));
		}
		if(!conn.isClosed())
			conn.close();
	}
}
