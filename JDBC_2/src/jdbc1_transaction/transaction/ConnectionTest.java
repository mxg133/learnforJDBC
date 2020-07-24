package jdbc1_transaction.transaction;

import java.sql.Connection;

import org.junit.Test;

import jdbc1_transaction.util.JDBCUtils;

public class ConnectionTest {
	
	@Test
	public void testGetConnection() throws Exception{
		Connection conn = JDBCUtils.getConnection();
		System.out.println(conn);
	}
}
