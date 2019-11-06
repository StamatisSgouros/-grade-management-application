package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.model.Student;
import com.util.DbUtil;

public class StudentDao {

    private Connection connection;

    public StudentDao() {
        connection = DbUtil.getConnection();
    }

    public void addStudent(Student Student) {
        try {
            PreparedStatement preparedStatement = connection.
            		prepareStatement("INSERT INTO STUDENTS (RegistrationNumber, Name, Surname, Department) VALUES (?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setInt(1, Student.getId());
            preparedStatement.setString(2, Student.getName());
            preparedStatement.setString(3, Student.getSurname());
            preparedStatement.setString(4, Student.getDepartment());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int RegistrationNumber) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from Students where RegistrationNumber=?");
            // Parameters start with 1
            preparedStatement.setInt(1, RegistrationNumber);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student Student) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update Students set  Name=?, Surname=?, Department=?" +
                            "where RegistrationNumber=?");
            // Parameters start with 1
            
            preparedStatement.setString(1, Student.getName());
            preparedStatement.setString(2, Student.getSurname());
            preparedStatement.setString(3, Student.getDepartment());
            preparedStatement.setInt(4, Student.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> Students = new ArrayList<Student>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from Students");
            while (rs.next()) {
                Student Student = new Student();
                Student.setId(rs.getInt("RegistrationNumber"));
                Student.setName(rs.getString("Name"));
                Student.setSurname(rs.getString("Surname"));
                Student.setDepartment(rs.getString("Department"));
                Students.add(Student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Students;
    }

    public Student getStudentByRegNumber(int RegistrationNumber) {
        Student Student = new Student();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from Students where RegistrationNumber=?");
            preparedStatement.setInt(1, RegistrationNumber);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
            	Student.setId(rs.getInt("RegistrationNumber"));
                Student.setName(rs.getString("Name"));
                Student.setSurname(rs.getString("Surname"));
                Student.setDepartment(rs.getString("Department"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Student;
    }
}
