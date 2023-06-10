package com.twinkle.JakSim;

import com.twinkle.JakSim.account.UserDao;
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

	@Autowired
	private UserDao userDao;
}
