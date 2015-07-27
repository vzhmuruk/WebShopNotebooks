package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.VideoCardProd;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class VideoCardProdDAO implements AbstractDAO<VideoCardProd> {

    private static final String TABLE_NAME = "videocard_producer";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private Statement statement;
    private Connection connection;

    public VideoCardProdDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }


    @Override
    public boolean add(VideoCardProd item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_NAME +
                    ") VALUES (\"" + item.getProdName() +"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(VideoCardProd edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_NAME+ " = '" + edited.getProdName() + "' "
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
    public VideoCardProd findById(long objid) {
        VideoCardProd producer = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String name = resultSet.getString(COLUMN_NAME);
                producer = new VideoCardProd(id, name);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return producer;
    }

    @Override
    public boolean addAll(Collection<VideoCardProd> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_NAME + ")"
                + " VALUES ( ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (VideoCardProd producer : collection) {
                statement.setString(1, producer.getProdName());
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
    public List<VideoCardProd> loadAll() {
        List<VideoCardProd> producers = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String name = resultSet.getString(COLUMN_NAME);

                producers.add(new VideoCardProd(id, name));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producers;
    }
}
