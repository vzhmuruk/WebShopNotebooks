package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.VideoCardModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class VideoCardModelDAO implements AbstractDAO<VideoCardModel> {

    private static final String TABLE_NAME = "videocard_model";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MODEL = "model";
    private Statement statement;
    private Connection connection;

    public VideoCardModelDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }

    @Override
    public boolean add(VideoCardModel item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_MODEL +
                    ") VALUES (\"" + item.getMoidel() +"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(VideoCardModel edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_MODEL+ " = '" + edited.getMoidel() + "' "
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
    public VideoCardModel findById(long objid) {
        VideoCardModel videoCardModel = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String model = resultSet.getString(COLUMN_MODEL);
                videoCardModel = new VideoCardModel(id, model);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return videoCardModel;
    }

    @Override
    public boolean addAll(Collection<VideoCardModel> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_MODEL + ")"
                + " VALUES ( ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (VideoCardModel videoCardModel : collection) {
                statement.setString(1, videoCardModel.getMoidel());
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
    public List<VideoCardModel> loadAll() {
        List<VideoCardModel> videoCardModels = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String model = resultSet.getString(COLUMN_MODEL);

                videoCardModels.add(new VideoCardModel(id, model));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return videoCardModels;
    }
}
