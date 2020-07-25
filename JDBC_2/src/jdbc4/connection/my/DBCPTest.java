package jdbc4.connection.my;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 孟享广
 * @create 2020-07-25 3:26 下午
 */
public class DBCPTest {
    @Test
    public void test1() throws SQLException {
        DataSource source = new BasicDataSource();



        Connection conn = source.getConnection();
        System.out.println(conn);
    }
}
