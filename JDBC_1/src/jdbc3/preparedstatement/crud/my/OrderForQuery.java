package jdbc3.preparedstatement.crud.my;

import jdbc3.bean.Order;
import jdbc3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author 孟享广
 * @create 2020-07-23 11:23 上午
 */
public class OrderForQuery {

    public Order order_for_query(String sql, Object ...args) throws Exception {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++){
                preparedStatement.setObject(i + 1,args[i]);
            }
            resultSet = preparedStatement.executeQuery();

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();

            if (resultSet.next()){
                Order order = new Order();

                for (int i = 0; i < columnCount; i++) {
                    Object columnValus = resultSet.getObject(i + 1);
                    String columnLabel = resultSetMetaData.getColumnLabel(i + 1);

                    Class<Order> clazz = Order.class;
                    Field field = clazz.getDeclaredField(columnLabel);

                    field.setAccessible(true);

                    field.set(order, columnValus);
                }
                return order;

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(connection, preparedStatement, resultSet);
        }

        return  null;
    }

    @Test
    public void test() throws Exception {
            String sql = "select order_id orderId,order_name orderName,order_date orderDate from `order` where order_id = ?";
        Order order = order_for_query(sql, 1);
        System.out.println(order);


    }
}
