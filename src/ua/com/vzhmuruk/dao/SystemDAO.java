package ua.com.vzhmuruk.dao;


import ua.com.vzhmuruk.data.Systems;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SystemDAO implements AbstractDAO<Systems> {

    private static final String TABLE_NAME = "operation_system";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TYPE = "type";
    private Connection connection;
    private Statement statement;

    public SystemDAO(Statement statement, Connection connection) {
        this.connection = connection;
        this.statement = statement;
    }


    @Override
    public boolean add(Systems item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_TYPE +
                    ") VALUES (\"" + item.getOsType() +"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Systems edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_TYPE + " = '" + edited.getOsType() + "' "
                    + " WHERE "
                    + COLUMN_ID + " = " + edited.getId() + ";");
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try {
            statement.executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = " + id + ";" );
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Systems findById(long objid) {
        Systems sys = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String type = resultSet.getString(COLUMN_TYPE);
                sys = new Systems(id, type);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sys;
    }

    @Override
    public boolean addAll(Collection<Systems> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_TYPE + ")"
                + " VALUES ( ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (Systems systems : collection) {
                statement.setString(1, systems.getOsType());
                statement.executeUpdate();
            }

            connection.commit();
            connection.setAutoCommit(true);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Systems> loadAll() {
        List<Systems> systems = new ArrayList<Systems>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String type = resultSet.getString(COLUMN_TYPE);

                systems.add(new Systems(id, type));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return systems;
    }
}
