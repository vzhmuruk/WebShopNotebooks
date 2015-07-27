package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.RamFriquency;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class RamFriquencyDAO implements AbstractDAO<RamFriquency>{

    private static final String TABLE_NAME = "ram_friquency";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FRIQUENCY = "friquency";
    private static final String COLUMN_IDENTITY = "identity";
    private Statement statement;
    private Connection connection;

    public RamFriquencyDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }


    @Override
    public boolean add(RamFriquency item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " ( " +
                    COLUMN_FRIQUENCY + ", " + COLUMN_IDENTITY +
                    ") VALUES (" + item.getFriquency() +", \"" +item.getIdentity() +"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(RamFriquency edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_FRIQUENCY + " = '" + edited.getFriquency() + "' "
                    + COLUMN_IDENTITY + " = '" + edited.getIdentity() + "' "
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
    public RamFriquency findById(long objid) {
        RamFriquency ramFriquency = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String friquency = resultSet.getString(COLUMN_FRIQUENCY);
                String identity = resultSet.getString(COLUMN_IDENTITY);
                ramFriquency = new RamFriquency(id, friquency, identity);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ramFriquency;
    }

    @Override
    public boolean addAll(Collection<RamFriquency> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_FRIQUENCY +", \"" + COLUMN_IDENTITY + "\")"
                + " VALUES ( ?, ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (RamFriquency ramFriquency : collection) {
                statement.setString(1, ramFriquency.getFriquency());
                statement.setString(2 , ramFriquency.getIdentity());
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
    public List<RamFriquency> loadAll() {
        List<RamFriquency> ramFriquencies = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String friquency = resultSet.getString(COLUMN_FRIQUENCY);
                String identity = resultSet.getString(COLUMN_IDENTITY);
                ramFriquencies.add(new RamFriquency(id, friquency, identity));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ramFriquencies;
    }
}
