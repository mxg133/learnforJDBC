package jdbc1.transaction.my;

import jdbc1.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

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

}
