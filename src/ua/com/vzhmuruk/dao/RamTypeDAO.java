package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.RamType;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class RamTypeDAO implements AbstractDAO<RamType> {

    private static final String TABLE_NAME = "ram_type";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TYPE = "type";
    private Statement statement;
    private Connection connection;

    public RamTypeDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }


    @Override
    public boolean add(RamType item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_TYPE +
                    ") VALUES (\"" + item.getRamType() +"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(RamType edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_TYPE+ " = '" + edited.getRamType() + "' "
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
    public RamType findById(long objid) {
        RamType ramType = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String type = resultSet.getString(COLUMN_TYPE);
                ramType = new RamType(id, type);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ramType;
    }

    @Override
    public boolean addAll(Collection<RamType> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_TYPE + ")"
                + " VALUES ( ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (RamType type : collection) {
                statement.setString(1, type.getRamType());
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
    public List<RamType> loadAll() {
        List<RamType> types = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String type = resultSet.getString(COLUMN_TYPE);

                types.add(new RamType(id, type));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }
}
