package jdbc4.connection.my;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;

/**
 * @author 孟享广
 * @create 2020-07-25 2:02 下午
 */
public class C3P0Test {
    @Test
    public void test1() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
        cpds.setUser("root");
        cpds.setPassword("mklmklmkl");

    }
}
