package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.Processors;
import ua.com.vzhmuruk.data.ScreenResolution;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class ScreenResolutionDAO implements AbstractDAO<ScreenResolution> {

    private static final String TABLE_NAME = "screen_resolution";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WIDTH = "width";
    private static final String COLUMN_HEIGHT = "height";
    private Statement statement;
    private Connection connection;

    public ScreenResolutionDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }


    @Override
    public boolean add(ScreenResolution item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_WIDTH + ", " + COLUMN_HEIGHT +
                    ") VALUES (" + item.getWidth() +", " +item.getHeight() +")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(ScreenResolution edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_WIDTH + " = '" + edited.getWidth() + "' "
                    + COLUMN_HEIGHT + "= '" + edited.getHeight() + "' WHERE "
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
    public ScreenResolution findById(long objid) {
        ScreenResolution screenResolution = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int width = resultSet.getInt(COLUMN_WIDTH);
                int height = resultSet.getInt(COLUMN_HEIGHT);
                screenResolution = new ScreenResolution(id, width, height);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return screenResolution;
    }

    @Override
    public boolean addAll(Collection<ScreenResolution> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_WIDTH + ", " + COLUMN_HEIGHT + ")"
                + " VALUES ( ? , ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (ScreenResolution screenResolution : collection) {
                statement.setInt(1, screenResolution.getWidth());
                statement.setInt(2, screenResolution.getHeight());
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
    public List<ScreenResolution> loadAll() {
        List<ScreenResolution> screenResolutions = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int width = resultSet.getInt(COLUMN_WIDTH);
                int heigth = resultSet.getInt(COLUMN_HEIGHT);
                screenResolutions.add(new ScreenResolution(id, width, heigth));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return screenResolutions;
    }
}
