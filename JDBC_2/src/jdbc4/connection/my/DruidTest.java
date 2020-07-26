package jdbc4.connection.my;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * @author 孟享广
 * @create 2020-07-26 10:18 上午
 */
public class DruidTest {
    @Test
    public void test1() throws Exception {
//        Properties pros = new Properties();
//
//        InputStream is = ClassLoader.getPlatformClassLoader().getResourceAsStream("druid.properties");
//        pros.load(is);
//        DataSource source = DruidDataSourceFactory.createDataSource(pros);
//
//        Connection conn = source.getConnection();
//
//        System.out.println(conn);


        Properties pros = new Properties();

        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");

        pros.load(is);

        DataSource source = DruidDataSourceFactory.createDataSource(pros);
        Connection conn = source.getConnection();
        System.out.println(conn);

    }
}
