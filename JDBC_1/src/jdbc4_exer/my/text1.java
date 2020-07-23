package jdbc4_exer.my;

import jdbc3.preparedstatement.crud.PreparedStatementUpdateTest;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author 孟享广
 * @create 2020-07-23 3:14 下午
 */
public class text1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入名字：");
        String name = scanner.next();
        System.out.print("请输入邮箱：");
        String email = scanner.next();
        System.out.print("请输入生日：");
        String birthday = scanner.next();


        PreparedStatementUpdateTest preparedStatementUpdateTest = new PreparedStatementUpdateTest();
        String sql = "insert into customers(name, email, birth) values(?, ?, ?)";
        int i = preparedStatementUpdateTest.update1(sql, name, email, birthday);

        System.out.println(i);


        System.out.println("good!");
    }
    @Test
    public void test1(){




    }
}
