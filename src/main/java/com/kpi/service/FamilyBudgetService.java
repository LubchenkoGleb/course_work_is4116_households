package com.kpi.service;

import com.kpi.model.FamilyBudget;
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
public class FamilyBudgetService {
    @Autowired
    Environment environment;
    @Autowired
    Connection connection;

    public List<FamilyBudget> getAll(){
        List<FamilyBudget> FamilyBudgets = new ArrayList<FamilyBudget>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from v_family_budget");
            while (rs.next()) {
                FamilyBudget FamilyBudget = new FamilyBudget();

                FamilyBudget.setId(Integer.parseInt(rs.getString("budget_id")));
                FamilyBudget.setAmountOfMoney(Integer.parseInt(rs.getString("amount_of_money")));
                FamilyBudget.setDateOfOperation(rs.getDate("date_of_operation"));
                FamilyBudget.setFamilyMemberId(Integer.parseInt(rs.getString("family_member_id")));
                FamilyBudget.setOperationId(Integer.parseInt(rs.getString("operation_id")));

                FamilyBudgets.add(FamilyBudget);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return FamilyBudgets;
    }

    public FamilyBudget getById(Integer loadTypeId) {
        FamilyBudget FamilyBudget = new FamilyBudget();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from v_family_budget where budget_id=?");
            preparedStatement.setString(1, loadTypeId.toString());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                FamilyBudget.setId(Integer.parseInt(rs.getString("budget_id")));
                FamilyBudget.setAmountOfMoney(Integer.parseInt(rs.getString("amount_of_money")));
                FamilyBudget.setDateOfOperation(rs.getDate("date_of_operation"));
                FamilyBudget.setFamilyMemberId(Integer.parseInt(rs.getString("family_member_id")));
                FamilyBudget.setOperationId(Integer.parseInt(rs.getString("operation_id")));
            }
        }catch (SQLException e){
            e.getStackTrace();
        }
        return FamilyBudget;
    }

    public void update(FamilyBudget FamilyBudget) throws SQLException{

        PreparedStatement preparedStatement = connection.prepareStatement(
                "update v_family_budget set amount_of_money=?, " +
                        "date_of_operation=?, family_member_id=?, operation_id=? where budget_id=?");

        preparedStatement.setInt(1, FamilyBudget.getAmountOfMoney());
        preparedStatement.setDate(2, FamilyBudget.getDateOfOperation());
        preparedStatement.setInt(3, FamilyBudget.getFamilyMemberId());
        preparedStatement.setInt(4, FamilyBudget.getOperationId());
        preparedStatement.setInt(5, FamilyBudget.getId());
        preparedStatement.executeUpdate();
    }

    public void insert(FamilyBudget FamilyBudget)  throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO v_family_budget(budget_id, amount_of_money, date_of_operation, family_member_id, operation_id) " +
                        "VALUES (?,?,?,?,?)");

        preparedStatement.setInt(1, FamilyBudget.getId());
        preparedStatement.setInt(2, FamilyBudget.getAmountOfMoney());
        preparedStatement.setDate(3, FamilyBudget.getDateOfOperation());
        preparedStatement.setInt(4, FamilyBudget.getFamilyMemberId());
        preparedStatement.setInt(5, FamilyBudget.getOperationId());
        preparedStatement.executeUpdate();
    }

    public void delete(Integer id) throws SQLException{
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from v_family_budget where budget_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Integer getMaxId() throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT MAX(budget_id) FROM v_family_budget");
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next())
            return rs.getInt("MAX(budget_id)");
        else
            return null;
    }
}
