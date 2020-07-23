package jdbc3.preparedstatement.crud;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import org.junit.Test;

import jdbc3.bean.Customer;
import jdbc3.util.JDBCUtils;

/**
 * 
 * @Description 针对于Customers表的查询操作
 *
 */
public class CustomerForQuery {
	
	@Test
	public void testQueryForCustomers(){
		String sql = "select id,name,birth,email from customers where id = ?";
		Customer customer = queryForCustomers(sql, 13);
		System.out.println(customer);
		
		sql = "select name,email from customers where name = ?";
		Customer customer1 = queryForCustomers(sql,"周杰伦");
		System.out.println(customer1);
	}
	
	/**
	 * 
	 * @Description 针对于customers表的通用的查询操作
	 * @throws Exception
	 */
	public Customer queryForCustomers(String sql,Object...args){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			conn = JDBCUtils.getConnection();
			
			ps = conn.prepareStatement(sql);
			for(int i = 0;i < args.length;i++){
				ps.setObject(i + 1, args[i]);
			}
			
			resultSet = ps.executeQuery();
			//获取结果集的元数据 :ResultSetMetaData
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			//通过ResultSetMetaData获取结果集中的列数
			int columnCount = resultSetMetaData.getColumnCount();
			
			if(resultSet.next()){
				Customer customer = new Customer();
				//处理结果集一行数据中的每一个列
				for(int i = 0;i <columnCount;i++){
					//获取列值
					Object columValue = resultSet.getObject(i + 1);
					
					//获取每个列的列名
//					String columnName = rsmd.getColumnName(i + 1);
					String columnLabel = resultSetMetaData.getColumnLabel(i + 1);
					
					//给cust对象指定的columnName属性，赋值为columValue：通过反射

					Class<Customer> clazz = Customer.class;
					Field field = clazz.getDeclaredField(columnLabel);
					field.setAccessible(true);
					field.set(customer, columValue);
				}
				return customer;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.closeResource(conn, ps, resultSet);
			
		}
		
		return null;
	}
	
	@Test
	public void testQuery1() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			conn = JDBCUtils.getConnection();
			String sql = "select id,name,email,birth from customers where id = ?";
			ps = conn.prepareStatement(sql);
			ps.setObject(1, 1);
			
			//执行,并返回结果集
			resultSet = ps.executeQuery();
			//处理结果集
			if(resultSet.next()){//next():判断结果集的下一条是否有数据，如果有数据返回true,并指针下移；如果返回false,指针不会下移。
				
				//获取当前这条数据的各个字段值
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String email = resultSet.getString(3);
				Date birth = resultSet.getDate(4);
				
			//方式一：
//			System.out.println("id = " + id + ",name = " + name + ",email = " + email + ",birth = " + birth);
				
			//方式二：
//			Object[] data = new Object[]{id,name,email,birth};

			//方式三：将数据封装为一个对象（推荐）
			Customer customer = new Customer(id, name, email, birth);
			System.out.println(customer);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			//关闭资源
			JDBCUtils.closeResource(conn, ps, resultSet);
			
		}
		
	}
	
}
