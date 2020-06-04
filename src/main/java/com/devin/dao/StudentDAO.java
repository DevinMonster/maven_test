package com.devin.dao;

import com.devin.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentDAO {
    List<Student> getStudents();

    int insertStudent(Student student);

    Student selectStudentByStudentId(Integer id);

    //  多个参数

    List<Student> selectMultiParams(@Param("name") String name, @Param("studentId") String studentID);
}
