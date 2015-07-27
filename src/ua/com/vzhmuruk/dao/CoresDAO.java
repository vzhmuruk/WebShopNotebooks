package ua.com.vzhmuruk.dao;


import ua.com.vzhmuruk.data.Camera;
import ua.com.vzhmuruk.data.Cores;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CoresDAO implements AbstractDAO<Cores>  {

    private static final String TABLE_NAME = "cores";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NUMCORES = "num_cores";
    private Statement statement;
    private Connection connection;

    public CoresDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }


    @Override
    public boolean add(Cores item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_NUMCORES +
                    ") VALUES (" + item.getNumCores() +")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Cores edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_NUMCORES+ " = '" + edited.getNumCores() + "' "
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
    public Cores findById(long objid) {
        Cores cores = null;
        try {
            ResultSet resultSet = statement.executeQuery("SLELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int numCores = resultSet.getInt(COLUMN_NUMCORES);
                cores = new Cores(id, numCores);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cores;
    }

    @Override
    public boolean addAll(Collection<Cores> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_NUMCORES + ")"
                + " VALUES ( ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (Cores cores : collection) {
                statement.setInt(1, cores.getNumCores());
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
    public List<Cores> loadAll() {
        List<Cores> cores = new ArrayList<Cores>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int numCores = resultSet.getInt(COLUMN_NUMCORES);

                cores.add(new Cores(id, numCores));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cores;
    }
}
