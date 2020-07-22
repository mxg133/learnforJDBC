package jdbc2_statement.my;

import jdbc1_Connection.ConnectionTest;
import jdbc1_Connection.myConnectionTest;
import jdbc3.util.JDBCUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Properties;

/**
 * @author 孟享广
 * @create 2020-07-22 4:53 下午
 */
public class PreparedStatementTest {
    @Test
    public void test1()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Properties pros = new Properties();
            InputStream is = myConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

            pros.load(is);

            String url = pros.getProperty("url");
            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String driverClass = pros.getProperty("driverClass");
            System.out.println("user = " + user + ",password = " + password);

            Class.forName(driverClass);

            conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn);


            String sql = "INSERT INTO customers (`name`, email, birth)\n" +
                    "VALUES(?, ?, ?);";
            ps = conn.prepareStatement(sql);

            ps.setString(1, "娜扎");
            ps.setString(2,"nazhe@gmail.com");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = sdf.parse("1000-1-1");
            ps.setDate(3,new Date(date.getTime()));

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                ps.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                if (conn != null)
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        }
    }

    @Test
    public void test2() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.getConnection();

            String sql = "UPDATE customers SET `name` = ? WHERE id = ?";
            ps = conn.prepareStatement(sql);

            ps.setObject(1, "莫扎da");
            ps.setObject(2,18);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }


    }
}
