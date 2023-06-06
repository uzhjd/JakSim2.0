package com.twinkle.JakSim;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class JakSimApplicationTests {


	@Test
	void contextLoads() throws SQLException {
		Connection conn = ds.getConnection();
		if(conn != null)
			System.out.println("DB connection Success");
		else
			System.out.println("Driver Load Fail");
	}

}
