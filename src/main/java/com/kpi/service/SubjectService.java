package com.kpi.service;

import com.kpi.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gleb on 09.12.16.
 */
@Service
public class SubjectService  {
    
    @Autowired
    Environment environment;
    @Autowired
    Connection connection;

    public List<Subject> getAll(){
        List<Subject> subjects = new ArrayList<Subject>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from c_subject");
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(Integer.parseInt(rs.getString("subject_id")));
                subject.setCathedra(rs.getString("cathedra"));
                subject.setTitle(rs.getString("title"));
                subject.setSpeciality(rs.getString("speciality"));
                subjects.add(subject);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return subjects;
    }

    public Subject getById(Integer subjectId) {
        Subject subject = new Subject();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from c_subject where subject_id=?");
            preparedStatement.setString(1, subjectId.toString());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                subject.setId(Integer.parseInt(rs.getString("subject_id")));
                subject.setCathedra(rs.getString("cathedra"));
                subject.setTitle(rs.getString("title"));
                subject.setSpeciality(rs.getString("speciality"));
            }
        }catch (SQLException e){
            e.getStackTrace();
        }

        return subject;
    }

    public void update(Subject subject) throws SQLException{

        PreparedStatement preparedStatement = connection.prepareStatement(
                "update c_subject set speciality=?, title =?, cathedra=? where subject_id=?");

        preparedStatement.setString(1, subject.getSpeciality());
        preparedStatement.setString(2, subject.getTitle());
        preparedStatement.setString(3, subject.getCathedra());
        preparedStatement.setInt(4, subject.getId());
        preparedStatement.executeUpdate();
    }

    public void insert(Subject subject)  throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO c_subject(subject_id, speciality, title, cathedra) " +
                        "VALUES (?,?,?,?)");

        preparedStatement.setInt(1, subject.getId());
        preparedStatement.setString(2, subject.getSpeciality());
        preparedStatement.setString(3, subject.getTitle());
        preparedStatement.setString(4, subject.getCathedra());
        preparedStatement.executeUpdate();
    }

    public void check(Subject subject) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(
                "select subject_id from c_subject where subject_id = " + subject.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            update(subject);
        else
            insert(subject);

    }

    public void delete(Integer id) throws SQLException{
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from c_subject where subject_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Integer getMaxId() throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT MAX(subject_id) FROM c_subject");
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next())
            return rs.getInt("MAX(subject_id)");
        else
            return null;
    }
}
