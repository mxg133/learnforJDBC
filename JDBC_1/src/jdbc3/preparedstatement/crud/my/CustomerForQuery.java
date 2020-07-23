package jdbc3.preparedstatement.crud.my;

import jdbc3.bean.Customer;
import jdbc3.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author 孟享广
 * @create 2020-07-23 10:28 上午
 */
public class CustomerForQuery {
    public Customer query_for_Customer(String sql, Object ... args){

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = JDBCUtils.getConnection();

            ps = conn.prepareStatement(sql);

            for (int i = 0; i < args.length; i++){
                ps.setObject(i + 1, args[i]);
            }

            resultSet = ps.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();


            if (resultSet.next()){
                Customer customer = new Customer();

                //处理每一行
                for (int i = 0; i < columnCount; i++){

                    Object value = resultSet.getObject(i + 1);
                    String columnName = resultSetMetaData.getColumnName(i + 1);

                    Class<Customer> clazz = Customer.class;
                    Field field = clazz.getDeclaredField(columnName);

                    field.setAccessible(true);

                    field.set(customer, value);
                }
                return customer;
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
        String sql = "select id, name, birth, email from customers where id = ?";
        String sql1 = "select name, email from customers where name = ?";
        Customer customer = query_for_Customer(sql, 13);
        Customer customer1 = query_for_Customer(sql1, "周杰伦");
        System.out.println(customer);
        System.out.println(customer1);

    }
}
