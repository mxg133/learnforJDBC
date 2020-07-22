package JDBC_1;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 孟享广
 * @create 2020-07-22 11:06 上午
 */
public class myConnectionTest {
    @Test
    public void test1() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();

        String url = "jdbc:mysql://localhost:3306/test";

        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "mklmklmkl");

        Connection conn = driver.connect(url, info);

        System.out.println(conn);
    }
}

    @Test
    public void test2(){


    }