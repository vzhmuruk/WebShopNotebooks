package ua.com.vzhmuruk.dao;


import ua.com.vzhmuruk.data.Processors;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProcessorsDAO implements AbstractDAO<Processors>{

    private static final String TABLE_NAME = "processors";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PRODUCER = "producer";
    private static final String COLUMN_MODEL = "model";
    private Statement statement;
    private Connection connection;

    public ProcessorsDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;

    }

    @Override
    public boolean add(Processors item) {

        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                     COLUMN_PRODUCER + ", " + COLUMN_MODEL +
                    ") VALUES (\"" + item.getProducer() +"\", \"" +item.getModel() +"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Processors edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_PRODUCER + " = '" + edited.getProducer() + "' "
                    + COLUMN_MODEL + "= '" + edited.getModel() + "' WHERE "
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
    public Processors findById(long objid) {
        Processors proc = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String producer = resultSet.getString(COLUMN_PRODUCER);
                String model = resultSet.getString(COLUMN_MODEL);
                proc = new Processors(id, producer, model);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return proc;
    }

    @Override
    public boolean addAll(Collection<Processors> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_PRODUCER + ", " + COLUMN_MODEL + ")"
                + " VALUES ( ? , ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (Processors proc : collection) {
                statement.setString(1, proc.getProducer());
                statement.setString(2, proc.getModel());
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
    public List<Processors> loadAll() {
        List<Processors> proc = new ArrayList<Processors>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String producer = resultSet.getString(COLUMN_PRODUCER);
                String model = resultSet.getString(COLUMN_MODEL);
                proc.add(new Processors(id, producer, model));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proc;
    }
}