package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.*;

import com.example.model.StudentModel;

@Mapper
public interface StudentMapper
{
    @Select("select npm, name, gpa from student where npm = #{npm}")
    StudentModel selectStudent (@Param("npm") String npm);

    @Select("select npm, name, gpa from student")
    List<StudentModel> selectAllStudents ();

    @Insert("INSERT INTO student (npm, name, gpa) VALUES (#{npm}, #{name}, #{gpa})")
    void addStudent (StudentModel student);

    @Delete("Delete from student where npm = #{npm}")
    void deleteStudent (@Param("npm") String npm);

    @Update("Update student set name = #{student.name}, gpa = #{student.gpa} where npm = #{student.npm}")
    void updateStudent(@Param("student") StudentModel student);
}
