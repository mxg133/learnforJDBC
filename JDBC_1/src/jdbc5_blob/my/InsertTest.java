package jdbc5_blob.my;

import jdbc3.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author 孟享广
 * @create 2020-07-24 9:21 上午
 */
public class InsertTest {
    @Test
    public void test3() throws Exception {
        //
        long start = System.currentTimeMillis();
        Connection connection = JDBCUtils.getConnection();
        connection.setAutoCommit(false);

        String sql = "INSERT INTO goods(`NAME`) VALUES(?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 100000; i++){
            preparedStatement.setObject(1, "name_" + i);
            preparedStatement.addBatch();
            if (i % 500 == 0){
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }

        }
        connection.commit();
        JDBCUtils.closeResource(connection, preparedStatement);
        long end = System.currentTimeMillis();

        System.out.println("花费的时间为：" + (end - start));
    }

    @Test
    public void test2() throws Exception {
        //
        Connection connection = JDBCUtils.getConnection();

        String sql = "INSERT INTO goods(`NAME`) VALUES(?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 1000000; i++){
            preparedStatement.setObject(1, "name_" + i);
            preparedStatement.addBatch();
            if (i % 5 == 0){
                preparedStatement.executeBatch();
                preparedStatement.clearBatch();
            }

        }
        JDBCUtils.closeResource(connection, preparedStatement);
    }

    @Test
    public void test1() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "INSERT INTO goods(`NAME`) VALUES(?);";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < 20; i++){
            preparedStatement.setObject(1, "name_" + i);
            preparedStatement.execute();
        }
        JDBCUtils.closeResource(connection, preparedStatement);

    }
}
