package com.kpi.service;

import com.kpi.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 06.12.16.
 */
@Service
public class TeacherService {

    @Autowired
    Environment environment;

    @Autowired
    Connection connection;

    public List<Teacher> getAll(){
        List<Teacher> teachers = new ArrayList<Teacher>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from c_teacher");
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(Integer.parseInt(rs.getString("teacher_id")));
                teacher.setFullName(rs.getString("full_name"));
                teacher.setPosition(rs.getString("position"));
                teacher.setEmploymentType(rs.getString("employment_type"));
                teachers.add(teacher);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return teachers;
    }

    public Teacher getById(Integer teacherId) {
        Teacher teacher = new Teacher();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from c_teacher where teacher_id=?");
            preparedStatement.setString(1, teacherId.toString());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                teacher.setId(Integer.parseInt(rs.getString("teacher_id")));
                teacher.setFullName(rs.getString("full_name"));
                teacher.setPosition(rs.getString("position"));
                teacher.setEmploymentType(rs.getString("employment_type"));
            }
        }catch (SQLException e){
            e.getStackTrace();
        }

        return teacher;
    }

    public void update(Teacher teacher) throws SQLException{

        PreparedStatement preparedStatement = connection.prepareStatement(
                "update c_teacher set full_name=?, position =?, employment_type=? where teacher_id=?");

        preparedStatement.setString(1, teacher.getFullName());
        preparedStatement.setString(2, teacher.getPosition());
        preparedStatement.setString(3, teacher.getEmploymentType());
        preparedStatement.setInt(4, teacher.getId());
        System.out.println(teacher.getFullName() + teacher.getPosition() + teacher.getEmploymentType());
        preparedStatement.executeUpdate();
    }

    public void insert(Teacher teacher)  throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO c_teacher(teacher_id, full_name, position, employment_type) " +
                        "VALUES (?,?,?,?)");

        preparedStatement.setInt(1, teacher.getId());
        preparedStatement.setString(2, teacher.getFullName());
        preparedStatement.setString(3, teacher.getPosition());
        preparedStatement.setString(4, teacher.getEmploymentType());
        System.out.println(teacher.getFullName() + teacher.getPosition() + teacher.getEmploymentType());
        preparedStatement.executeUpdate();
    }

    public void delete(Integer id) throws SQLException{
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from c_teacher where teacher_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Integer getMaxId() throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT MAX(teacher_id) FROM c_teacher");
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next())
            return rs.getInt("MAX(teacher_id)");
        else
            return null;
    }
}
