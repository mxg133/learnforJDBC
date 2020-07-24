package jdbc1_transaction.transaction.my;

import jdbc1_transaction.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author 孟享广
 * @create 2020-07-24 10:20 上午
 */
public class ConnectionTest {
    @Test
    public void test() throws Exception {

        Connection connection = JDBCUtils.getConnection();
        System.out.println(connection);
    }
}
