package jdbc1_Connection;

import com.mysql.jdbc.Driver;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
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


    @Test
    public void test2() throws Exception {
        Class Clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) Clazz.newInstance();

        String url = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "mklmklmkl");

        Connection conn = driver.connect(url, info);

        System.out.println(conn);
    }

    @Test
    public void test3()throws Exception{
        Class Clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) Clazz.newInstance();

        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "mklmklmkl";

        DriverManager.registerDriver(driver);
        Connection conn = DriverManager.getConnection(url, user, password);

        System.out.println(conn);
    }

    @Test
    public void test4() throws Exception {
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "mklmklmkl";

        Class.forName("com.mysql.jdbc.Driver");//mysql可以省略！
//        Driver driver = (Driver) Clazz.newInstance();
//
//        DriverManager.registerDriver(driver);
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    @Test
    public void test5() throws Exception {
        Properties pros = new Properties();
        InputStream is = myConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

        pros.load(is);

        String url = pros.getProperty("url");
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String driverClass = pros.getProperty("driverClass");
        System.out.println("user = " + user + ",password = " + password);

        Class.forName(driverClass);

        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

    }





}