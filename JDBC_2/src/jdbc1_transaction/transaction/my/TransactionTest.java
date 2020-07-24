package jdbc1_transaction.transaction.my;

import jdbc1_transaction.transaction.User;
import jdbc1_transaction.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author 孟享广
 * @create 2020-07-24 10:46 上午
 */
public class TransactionTest {

    //2.o 增删改 针对事务
    public int update1(Connection conn, String sql,Object ...args){//sql中占位符的个数与可变形参的长度相同！
//        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //0.获取数据库的连接
//            conn = JDBCUtils.getConnection();
            //1.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);//小心参数声明错误！！
            }
            //3.执行
//			ps.execute();execute
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //4.资源的关闭
            try {
                conn.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            JDBCUtils.closeResource(null, ps);

        }
        return 0;
    }

    @Test
    public void test1() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);
            System.out.println(conn.getAutoCommit());

            String sql1 = "UPDATE user_table SET balance = balance - 100 WHERE `user`  = ?;";
            String sql2 = "UPDATE user_table SET balance = balance + 100 WHERE `user`  = ?;";

            int aa = update1(conn, sql1, "AA");

        System.out.println(1/0);

            int bb = update1(conn, sql2, "BB");

            System.out.println( "ok");

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            JDBCUtils.closeResource(conn, null);

        }

    }
        @Test
        public void test2() throws Exception {
            Connection connection = JDBCUtils.getConnection();

//            System.out.println(connection.getTransactionIsolation());
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
            System.out.println(connection.getTransactionIsolation());
            connection.setAutoCommit(false);

            String sql = "select user,passxword,balance from user_table where user = ?";
            User cc = getInstanceOneIns(connection, User.class, sql, "CC");
            System.out.println(cc);

        }
        @Test
        public void test3()throws Exception {
            Connection connection = JDBCUtils.getConnection();
            connection.setAutoCommit(false);

            String sql = "update user_table set balance = ? where user = ?";
            update1(connection, sql, 5000, "CC");

            Thread.sleep(15000);

            System.out.println("修改结束");


        }

    public <T> T getInstanceOneIns(Connection conn, Class<T> clazz,String sql, Object... args) {
//        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
//            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            // 获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            // 通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();

            if (rs.next()) {
                T t = clazz.newInstance();
                // 处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值
                    Object columValue = rs.getObject(i + 1);

                    // 获取每个列的列名
                    // String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 给t对象指定的columnName属性，赋值为columValue：通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(null, ps, rs);

        }

        return null;
    }

}
