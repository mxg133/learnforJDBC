package jdbc3.preparedstatement.crud.my;

import jdbc3.bean.Customer;
import jdbc3.bean.Order;
import jdbc3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

/**
 * @author 孟享广
 * @create 2020-07-23 1:11 下午
 */
public class PreparedStatementQueryTest {
    public <T>T getInstance(Class<T> clazz, String sql, Object... args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            resultSet = ps.executeQuery();
            //获取结果集的元数据 :ResultSetMetaData
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = resultSetMetaData.getColumnCount();

            if (resultSet.next()) {
                Customer customer = new Customer();
                T t = clazz.newInstance();
                //处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object columValue = resultSet.getObject(i + 1);

                    //获取每个列的列名
//					String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);

                    //给cust对象指定的columnName属性，赋值为columValue：通过反射

                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t, columValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps, resultSet);

        }

        return null;
    }
    @Test
    public void test(){

        String sql = "select id,name,email from customers where id < ?";
        Customer customer = getInstance(Customer.class, sql, 12);
        System.out.println(customer);

        String sql1 = "select order_id orderId,order_name orderName from `order` where order_id = ?";
        Order order = getInstance(Order.class, sql1, 1);
        System.out.println(order);

//        List<Order> orderList = getForList(Order.class, sql1);
    }
}
