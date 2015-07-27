package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.Accumulator;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AccumulatorsDAO implements AbstractDAO<Accumulator>{
    private static final String DB_NAME = "notebooks";
    private static final String TABLE_NAME = "accumulator";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_SECTIONS = "sections";
    private Statement statement;
    private Connection connection;

    public AccumulatorsDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;

    }


    @Override
    public boolean add(Accumulator item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_CAPACITY + ", " + COLUMN_SECTIONS +
                    ") VALUES (" + item.getCapacity() +", " +item.getSections() +")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Accumulator edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_CAPACITY + " = '" + edited.getCapacity() + "' "
                    + COLUMN_SECTIONS + "= '" + edited.getSections() + "' WHERE "
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
    public Accumulator findById(long objid) {
        Accumulator accumulator = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int capacity = resultSet.getInt(COLUMN_CAPACITY);
                short sections = resultSet.getShort(COLUMN_SECTIONS);
                accumulator = new Accumulator(id, capacity, sections);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accumulator;
    }

    @Override
    public boolean addAll(Collection<Accumulator> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_CAPACITY + ", " + COLUMN_SECTIONS + ")"
                + " VALUES ( ? , ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (Accumulator accumulator : collection) {
                statement.setInt(1, accumulator.getCapacity());
                statement.setInt(2, accumulator.getSections());
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
    public List<Accumulator> loadAll() {
        List<Accumulator> accumulator = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + DB_NAME + "." + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int capacity = resultSet.getInt(COLUMN_CAPACITY);
                int sections = resultSet.getInt(COLUMN_SECTIONS);
                accumulator.add(new Accumulator(id, capacity, sections));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accumulator;
    }
}
