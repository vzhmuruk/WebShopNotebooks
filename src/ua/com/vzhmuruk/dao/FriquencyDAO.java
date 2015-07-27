package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.Friquency;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class FriquencyDAO implements AbstractDAO<Friquency> {

    private static final String TABLE_NAME = "friquency";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FRIQUENCIES = "friquencies";
    private static final String COLUMN_IDENTITY = "identity";

    private Statement statement;
    private Connection connection;

    public FriquencyDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }

    @Override
    public boolean add(Friquency item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " ( " +
                    COLUMN_FRIQUENCIES + ", " + COLUMN_IDENTITY +
                    ") VALUES (" + item.getFriquencys() +", \"" +item.getIdentity() +"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Friquency edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_FRIQUENCIES + " = '" + edited.getFriquencys() + "' "
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
    public Friquency findById(long objid) {
        Friquency friquency = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int friquencys = resultSet.getInt(COLUMN_FRIQUENCIES);
                String identity = resultSet.getString(COLUMN_IDENTITY);
                friquency = new Friquency(id, friquencys, identity);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friquency;
    }

    @Override
    public boolean addAll(Collection<Friquency> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_FRIQUENCIES +", \"" + COLUMN_IDENTITY + "\")"
                + " VALUES ( ?, ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (Friquency friquency : collection) {
                statement.setInt(1, friquency.getFriquencys());
                statement.setString(2, friquency.getIdentity());
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
    public List<Friquency> loadAll() {
        List<Friquency> friquencies = new ArrayList<Friquency>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int numCores = resultSet.getInt(COLUMN_FRIQUENCIES);
                String identity = resultSet.getString(COLUMN_IDENTITY);
                friquencies.add(new Friquency(id, numCores, identity));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return friquencies;
    }
}
