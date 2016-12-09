package com.kpi.service;

import com.kpi.model.LoadType;
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
public class LoadTypeService {
    @Autowired
    Environment environment;
    @Autowired
    Connection connection;

    public List<LoadType> getAll(){
        List<LoadType> loadTypes = new ArrayList<LoadType>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from c_load_type");
            while (rs.next()) {
                LoadType loadType = new LoadType();
                loadType.setId(Integer.parseInt(rs.getString("load_type_id")));
                loadType.setTitle(rs.getString("title"));
                loadTypes.add(loadType);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return loadTypes;
    }

    public LoadType getById(Integer loadTypeId) {
        LoadType loadType = new LoadType();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "select * from c_load_type where load_type_id=?");
            preparedStatement.setString(1, loadTypeId.toString());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                loadType.setId(Integer.parseInt(rs.getString("load_type_id")));
                loadType.setTitle(rs.getString("title"));
            }
        }catch (SQLException e){
            e.getStackTrace();
        }

        return loadType;
    }

    public void update(LoadType loadType) throws SQLException{

        PreparedStatement preparedStatement = connection.prepareStatement(
                "update c_load_type set title =? where load_type_id=?");

        preparedStatement.setString(1, loadType.getTitle());
        preparedStatement.setInt(2, loadType.getId());
        preparedStatement.executeUpdate();
    }

    public void insert(LoadType loadType)  throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO c_load_type(load_type_id, title) " +
                        "VALUES (?,?)");

        preparedStatement.setInt(1, loadType.getId());
        preparedStatement.setString(2, loadType.getTitle());
        preparedStatement.executeUpdate();
    }

    public void delete(Integer id) throws SQLException{
        PreparedStatement preparedStatement =
                connection.prepareStatement("delete from c_load_type where load_type_id=?");
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
    }

    public Integer getMaxId() throws SQLException {
        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT MAX(load_type_id) FROM c_load_type");
        ResultSet rs = preparedStatement.executeQuery();
        if(rs.next())
            return rs.getInt("MAX(load_type_id)");
        else
            return null;
    }
}
