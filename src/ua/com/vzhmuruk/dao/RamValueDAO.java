package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.RamValue;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class RamValueDAO implements AbstractDAO<RamValue> {

    private static final String TABLE_NAME = "ram_value";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_VALUE = "memory_size";
    private Statement statement;
    private Connection connection;

    public RamValueDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }


    @Override
    public boolean add(RamValue item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_VALUE +
                    ") VALUES (\"" + item.getValue() +"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(RamValue edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_VALUE+ " = '" + edited.getValue() + "' "
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
    public RamValue findById(long objid) {
        RamValue ramValue = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String value = resultSet.getString(COLUMN_VALUE);
                ramValue = new RamValue(id, value);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ramValue;
    }

    @Override
    public boolean addAll(Collection<RamValue> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_VALUE + ")"
                + " VALUES ( ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (RamValue ramValue : collection) {
                statement.setString(1, ramValue.getValue());
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
    public List<RamValue> loadAll() {
        List<RamValue> ramValues = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String value = resultSet.getString(COLUMN_VALUE);

                ramValues.add(new RamValue(id, value));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ramValues;
    }
}
