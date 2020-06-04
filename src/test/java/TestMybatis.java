import com.devin.dao.StudentDAO;
import com.devin.dao.UserDAO;
import com.devin.entity.Student;
import com.devin.entity.User;
import com.devin.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMybatis {
    
    /*
    *  用于测试: Mybatis动态代理
    * */
    @Test
    public void test(){
        /*使用的是Mybatis的一个叫做动态代理的机制, 使用SqlSession.getMapper
        * getMapper接口能够获得的是实现类对象。
        *
        * */
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        StudentDAO dao = sqlSession.getMapper(StudentDAO.class);

        // 调用dao的方法执行数据库的操作

        List<Student> students = dao.getStudents();

        for (Student student : students) {
            System.out.println("student = " + student);
        }
    }

    /*
    *  用于测试:ParameterType
    * */
    @Test
    public void test1(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        StudentDAO dao = sqlSession.getMapper(StudentDAO.class);

        Student student = dao.selectStudentByStudentId(1);

        System.out.println("student = " + student);
    }

    /*
    *  用于测试:多个参数
    * */
    @Test
    public void test2(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDAO dao = sqlSession.getMapper(StudentDAO.class);
        List<Student> students = dao.selectMultiParams("郭峻溪", "1");
        for (Student student : students) {
            System.out.println("student = " + student);
        }
        sqlSession.close();
    }
    
    /*
    *  用于测试:MyBatisCon
    * */
    @Test
    public void test3(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();

        UserDAO dao = sqlSession.getMapper(UserDAO.class);

        User user = new User();
        user.setName("郭巨侠");
        user.setPassword("123");
        int save = dao.save(user);
        System.out.println("save = " + save);
        sqlSession.commit();
        sqlSession.close();
    }
    
}
