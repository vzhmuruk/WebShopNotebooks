package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.Camera;
import ua.com.vzhmuruk.data.VideoCardValue;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class VideoCardValueDAO implements AbstractDAO<VideoCardValue> {

    private static final String TABLE_NAME = "videocard_size";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_SIZE = "size";
    private Statement statement;
    private Connection connection;

    public VideoCardValueDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }

    @Override
    public boolean add(VideoCardValue item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_SIZE +
                    ") VALUES (" + item.getSize() +")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(VideoCardValue edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_SIZE+ " = '" + edited.getSize() + "' "
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
    public VideoCardValue findById(long objid) {
        VideoCardValue vcValue = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT  FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int size = resultSet.getInt(COLUMN_SIZE);
                vcValue = new VideoCardValue(id, size);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vcValue;
    }

    @Override
    public boolean addAll(Collection<VideoCardValue> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_SIZE + ")"
                + " VALUES ( ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (VideoCardValue vcValue : collection) {
                statement.setInt(1, vcValue.getSize());
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
    public List<VideoCardValue> loadAll() {
        List<VideoCardValue> vcValue = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                int size = resultSet.getInt(COLUMN_SIZE);

                vcValue.add(new VideoCardValue(id, size));
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vcValue;
    }
}
