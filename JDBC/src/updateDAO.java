import util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 孟享广
 * @create 2020-07-24 1:13 下午
 */
public abstract class updateDAO {
    //通用的增删改操作
    public void update(String sql,Object ...args){//sql中占位符的个数与可变形参的长度相同！
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);//小心参数声明错误！！
            }
            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //5.资源的关闭
            JDBCUtils.closeResource(conn, ps);
        }
    }

    //通用的增删改操作1   ----对行数有诉求
    public int update1(String sql,Object ...args){//sql中占位符的个数与可变形参的长度相同！
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库的连接
            conn = JDBCUtils.getConnection();
            //2.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for(int i = 0;i < args.length;i++){
                ps.setObject(i + 1, args[i]);//小心参数声明错误！！
            }
            //4.执行
//			ps.execute();execute
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //5.资源的关闭
            JDBCUtils.closeResource(conn, ps);
        }
        return 0;
    }

    // 通用的增删改操作---version 2.0 （考虑上事务）
    public int update2(Connection conn,String sql, Object... args) {// sql中占位符的个数与可变形参的长度相同！
        PreparedStatement ps = null;
        try {
            // 1.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            // 2.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);// 小心参数声明错误！！
            }
            // 3.执行
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //修改其为自动提交数据
            //主要针对于使用数据库连接池的使用
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 4.资源的关闭
            JDBCUtils.closeResource(null, ps);

        }
        return 0;

    }
}
