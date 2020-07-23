package jdbc5_blob.my;

import jdbc3.bean.Customer;
import jdbc3.util.JDBCUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;

/**
 * @author 孟享广
 * @create 2020-07-23 5:41 下午
 */
public class BlobText {
    @Test
    public void test() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "INSERT INTO customers(name, email, birth, photo) VALUES(?,?,?,?);";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1, "高佳好");
        ps.setObject(2, "gaojiahao@gmail.com");
        ps.setObject(3, "1997-12-5");
        FileInputStream is = new FileInputStream("girl.jpg");
        ps.setBlob(4,is );
        ps.execute();
        JDBCUtils.closeResource(connection, ps);
    }

    @Test
    public void test2()throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "select id,name,email,birth,photo from customers where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setObject(1,22);
        ResultSet resultSet = ps.executeQuery();

        if (resultSet.next()){
            int anInt = resultSet.getInt(1);
            String string = resultSet.getString(2);
            String string1 = resultSet.getString(3);
            Date date = resultSet.getDate(4);

            Customer customer = new Customer(anInt, string, string1, date);
            System.out.println(customer);

            Blob blob = resultSet.getBlob(5);
            InputStream is = blob.getBinaryStream();
            FileOutputStream fos = new FileOutputStream("playgirl1.jpg");

            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1){
                fos.write(buffer, 0, len);
            }

            is.close();
            fos.close();
            JDBCUtils.closeResource(connection, ps, resultSet);
        }

    }
}
