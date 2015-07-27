package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.HDDValue;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class HDDValueDAO implements AbstractDAO<HDDValue> {

    private static final String TABLE_NAME = "vint_value";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VALUE = "memory_size";
    private static final String COLUMN_IDENTITY = "identity";
    private Statement statement;
    private Connection connection;

    public HDDValueDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }


    @Override
    public boolean add(HDDValue item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_VALUE + ", " + COLUMN_IDENTITY +
                    ") VALUES (" + item.getValue() + ",\" "+item.getHddIdentity()+"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(HDDValue edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_VALUE+ " = '" + edited.getValue() + "' "
                    + COLUMN_IDENTITY+ " = '" + edited.getHddIdentity() + "' "
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
    public HDDValue findById(long objid) {
        HDDValue hddValue = null;
        try {
            ResultSet resultSet = statement.executeQuery("SLELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int value = resultSet.getInt(COLUMN_VALUE);
                String identity = resultSet.getString(COLUMN_IDENTITY);
                hddValue = new HDDValue(id, value, identity);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hddValue;
    }

    @Override
    public boolean addAll(Collection<HDDValue> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_VALUE + ", \"" + COLUMN_IDENTITY + "\")"
                + " VALUES ( ?, ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (HDDValue value : collection) {
                statement.setInt(1, value.getValue());
                statement.setString(2, value.getHddIdentity());
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
    public List<HDDValue> loadAll() {
        List<HDDValue> values = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int value = resultSet.getInt(COLUMN_VALUE);
                String identity = resultSet.getString(COLUMN_IDENTITY);

                values.add(new HDDValue(id, value, identity));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return values;
    }
}
