package com.kpi.service;

import com.kpi.model.FamilyMember;
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
public class FamilyMemberService {
    
    @Autowired
    Environment environment;
    @Autowired
    Connection connection;

    public List<FamilyMember> getAll(){
        List<FamilyMember> familyMembers = new ArrayList<FamilyMember>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from c_family_member ORDER BY family_member_id");
            while (rs.next()) {
                FamilyMember familyMember = new FamilyMember();

                familyMember.setId(Integer.parseInt(rs.getString("family_member_id")));
                familyMember.setName(rs.getString("name"));
                familyMember.setStatus(rs.getString("status"));
                familyMembers.add(familyMember);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return familyMembers;
    }

    public FamilyMember getById(Integer subjectId) {
        FamilyMember FamilyMember = new FamilyMember();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from c_family_member where family_member_id=?");
            preparedStatement.setString(1, subjectId.toString());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                FamilyMember.setId(Integer.parseInt(rs.getString("family_member_id")));
                FamilyMember.setName(rs.getString("name"));
                FamilyMember.setStatus(rs.getString("status"));
            }
        }catch (SQLException e){
            e.getStackTrace();
        }

        return FamilyMember;
    }

    public void update(FamilyMember FamilyMember) throws SQLException{

        PreparedStatement preparedStatement = connection.prepareStatement(
                "update c_family_member set name=?, status=? where family_member_id=?");

        preparedStatement.setString(1, FamilyMember.getName());
        preparedStatement.setString(2, FamilyMember.getStatus());
        preparedStatement.setInt(3, FamilyMember.getId());
        preparedStatement.executeUpdate();
    }

    public void insert(FamilyMember FamilyMember)  throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO c_family_member(family_member_id, name, status) " +
                        "VALUES (?,?,?)");

        preparedStatement.setInt(1, FamilyMember.getId());
        preparedStatement.setString(2, FamilyMember.getName());
        preparedStatement.setString(3, FamilyMember.getStatus());
        preparedStatement.executeUpdate();
    }

    public void check(FamilyMember FamilyMember) throws SQLException{
        PreparedStatement ps = connection.prepareStatement(
                "select family_member_id from c_family_member where family_member_id = " + FamilyMember.getId());
        ResultSet rs = ps.executeQuery();
        if (rs.next())
            update(FamilyMember);
        else
            insert(FamilyMember);

    }

    public void delete(Integer id) throws SQLException{
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from c_family_member where family_member_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Integer getMaxId() throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT MAX(family_member_id) FROM c_family_member");
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next())
            return rs.getInt("MAX(family_member_id)");
        else
            return null;
    }
}
