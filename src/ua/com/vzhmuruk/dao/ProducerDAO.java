package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.Camera;
import ua.com.vzhmuruk.data.Producer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class ProducerDAO implements AbstractDAO<Producer> {

    private static final String TABLE_NAME = "producers";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private Statement statement;
    private Connection connection;

    public ProducerDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }

    @Override
    public boolean add(Producer item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_NAME +
                    ") VALUES (\"" + item.getName() +"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Producer edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_NAME+ " = '" + edited.getName() + "' "
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
    public Producer findById(long objid) {
        Producer producer = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String name = resultSet.getString(COLUMN_NAME);
                producer = new Producer(id, name);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producer;
    }

    @Override
    public boolean addAll(Collection<Producer> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (\"" + COLUMN_NAME + "\")"
                + " VALUES ( ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (Producer producer : collection) {
                statement.setString(1, producer.getName());
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
    public List<Producer> loadAll() {
        List<Producer> producers = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String name = resultSet.getString(COLUMN_NAME);

                producers.add(new Producer(id, name));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producers;
    }
}
