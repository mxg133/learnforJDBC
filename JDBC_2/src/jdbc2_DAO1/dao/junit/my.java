package jdbc2_DAO1.dao.junit;


import jdbc1_transaction.util.JDBCUtils;
import jdbc3_DAO2.dao.CustomerDAOImpl;
import org.junit.Test;

import java.sql.Connection;

/**
 * @author 孟享广
 * @create 2020-07-24 4:43 下午
 */
public class my {
    private CustomerDAOImpl dao = new CustomerDAOImpl();

    @Test
    public void test1() throws Exception {
        Connection connection = JDBCUtils.getConnection();

//        Customer customer = new Customer(1, "小风", "1234543@qq.com", new Date(12343234342L));
//        dao.insert(connection, customer);
//        dao.deleteById(connection, 13);
//        dao.update(connection, new Customer(18, "方媛", "23234322@qq.com", new Date(234234432L)));
//        Customer id19 = dao.getCustomerById(connection, 18);
//        System.out.println(id19);
//        List<Customer> all = dao.getAll(connection);
//        all.forEach(System.out::println);
//        Long count = dao.getCount(connection);
//        System.out.println(count);

//        Date birth = dao.getMaxBirth(connection);
//        System.out.println(birth);


        JDBCUtils.closeResource(connection, null);

    }

}
