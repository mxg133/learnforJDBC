package jdbc5.dbutils;

import jdbc2_DAO1.bean.Customer;
import jdbc4.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @author 孟享广
 * @create 2020-07-26 11:12 上午
 */
public class myQueryRunnerTest {
    @Test
    public void test1() throws Exception {
        Connection conn = JDBCUtils.getConnection3Druid();
        QueryRunner runner = new QueryRunner();

        String sql = "INSERT INTO customers(`NAME`, email, birth) VALUES(?,?,?);";
        int insert = runner.update(conn, sql, "刘艺璇", "48502945234@qqcom", "1997-8-17");

        System.out.println(insert);

        JDBCUtils.closeResource(conn, null);

    }

    @Test
    public void test2() throws Exception {
        Connection conn = JDBCUtils.getConnection3Druid();
        QueryRunner runner = new QueryRunner();

        String sql = "SELECT min(birth) FROM customers;";
        ScalarHandler handler1 = new ScalarHandler();

        Date date = (Date) runner.query(conn, sql, handler1);

        System.out.println(date);
        JDBCUtils.closeResource(conn, null);

    }
}
