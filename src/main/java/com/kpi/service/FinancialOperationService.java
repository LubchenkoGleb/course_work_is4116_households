package com.kpi.service;

import com.kpi.model.FinancialOperation;
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
public class FinancialOperationService {

    @Autowired
    Environment environment;

    @Autowired
    Connection connection;

    public List<FinancialOperation> getAll(){
        List<FinancialOperation> familyMembers = new ArrayList<FinancialOperation>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from c_financial_operation");
            while (rs.next()) {
                FinancialOperation familyMember = new FinancialOperation();

                familyMember.setId(Integer.parseInt(rs.getString("operation_id")));
                familyMember.setTitle(rs.getString("title"));
                familyMember.setOperationType(rs.getString("operation_type"));

                familyMembers.add(familyMember);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return familyMembers;
    }

    public FinancialOperation getById(Integer teacherId) {
        FinancialOperation familyMember = new FinancialOperation();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from c_financial_operation where operation_id=?");
            preparedStatement.setString(1, teacherId.toString());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                familyMember.setId(Integer.parseInt(rs.getString("operation_id")));
                familyMember.setTitle(rs.getString("title"));
                familyMember.setOperationType(rs.getString("operation_type"));
            }
        }catch (SQLException e){
            e.getStackTrace();
        }

        return familyMember;
    }

    public void update(FinancialOperation familyMember) throws SQLException{

        PreparedStatement preparedStatement = connection.prepareStatement(
                "update c_financial_operation set title=?, operation_type=? where operation_id=?");

        preparedStatement.setString(1, familyMember.getTitle());
        preparedStatement.setString(2, familyMember.getOperationType());
        preparedStatement.setInt(3, familyMember.getId());

        preparedStatement.executeUpdate();
    }

    public void insert(FinancialOperation familyMember)  throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO c_financial_operation(operation_id, title, operation_type) " +
                        "VALUES (?,?,?)");

        preparedStatement.setInt(1, familyMember.getId());
        preparedStatement.setString(2, familyMember.getTitle());
        preparedStatement.setString(3, familyMember.getOperationType());
        preparedStatement.executeUpdate();
    }

    public void delete(Integer id) throws SQLException{
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from c_financial_operation where operation_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Integer getMaxId() throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT MAX(operation_id) FROM c_financial_operation");
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next())
            return rs.getInt("MAX(operation_id)");
        else
            return null;
    }
}
