package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.Accumulator;
import ua.com.vzhmuruk.data.Camera;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CameraDAO implements AbstractDAO<Camera> {
    private static final String DB_NAME = "notebooks";
    private static final String TABLE_NAME = "camera";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PIXELS = "pixels";
    private Statement statement;
    private Connection connection;

    public CameraDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }

    @Override
    public boolean add(Camera item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_PIXELS +
                    ") VALUES (" + item.getPixels() +")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Camera edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_PIXELS+ " = '" + edited.getPixels() + "' "
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
    public Camera findById(long objid) {
        Camera camera = null;
        try {
            ResultSet resultSet = statement.executeQuery("SLELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int pixels = resultSet.getInt(COLUMN_PIXELS);
                camera = new Camera(id, pixels);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return camera;
    }

    @Override
    public boolean addAll(Collection<Camera> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_PIXELS + ")"
                + " VALUES ( ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (Camera camera : collection) {
                statement.setInt(1, camera.getPixels());
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
    public List<Camera> loadAll() {
        List<Camera> camera = new ArrayList<Camera>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + DB_NAME + "." + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int pixels = resultSet.getInt(COLUMN_PIXELS);
                camera.add(new Camera(id, pixels));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return camera;
    }
}
