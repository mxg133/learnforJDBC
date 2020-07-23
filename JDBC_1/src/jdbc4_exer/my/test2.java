package jdbc4_exer.my;

import jdbc3.preparedstatement.crud.PreparedStatementQueryTest;
import jdbc3.preparedstatement.crud.PreparedStatementUpdateTest;
import jdbc4_exer.Student;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * @author 孟享广
 * @create 2020-07-23 3:40 下午
 */
public class test2 {
    @Test
    public void test1(){

        String sql ="insert into examstudent(type,IDCard,examCard,studentName,location,grade)values(?,?,?,?,?,?)";

        int i = new PreparedStatementUpdateTest().update1(sql, 6, "333322221111", "1000000", "王诗雨", "洛阳", 80);
        System.out.println(i);

    }

    @Test
    public void test2(){
        String selection = "200523164754003";
//        String sql = "select FlowID flowID,Type type,IDCard,ExamCard examCard,StudentName name,Location location,Grade grade from examstudent where examCard = ?";
//        Student student = new PreparedStatementQueryTest().getInstanceOneIns(Student.class, sql, selection);
//        if (student == null){
//            System.out.println("nononono");
//        } else {
            String sql1 = "DELETE FROM examstudent WHERE ExamCard = ?;";
            int i = new PreparedStatementUpdateTest().update1(sql1, selection);
            if (i > 0){
                System.out.println("ok!");

            }else {
                System.out.println("nononono");
            }



    }








}
