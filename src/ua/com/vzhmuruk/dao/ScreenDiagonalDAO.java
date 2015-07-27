package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.ScreenDiagonal;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class ScreenDiagonalDAO implements AbstractDAO<ScreenDiagonal>{

    private static final String TABLE_NAME = "screen_diagonal";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DIAGONAL = "diagonal";
    private Statement statement;
    private Connection connection;

    public ScreenDiagonalDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }


    @Override
    public boolean add(ScreenDiagonal item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_DIAGONAL +
                    ") VALUES (" + item.getDiagonal() +")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(ScreenDiagonal edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_DIAGONAL + " = '" + edited.getDiagonal() + "' "
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
    public ScreenDiagonal findById(long objid) {
        ScreenDiagonal screenDiagonal = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                double diagonal = resultSet.getDouble(COLUMN_DIAGONAL);
                screenDiagonal = new ScreenDiagonal(id, diagonal);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return screenDiagonal;
    }

    @Override
    public boolean addAll(Collection<ScreenDiagonal> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_DIAGONAL + ")"
                + " VALUES ( ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (ScreenDiagonal screenDiagonal : collection) {
                statement.setDouble(1, screenDiagonal.getDiagonal());
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
    public List<ScreenDiagonal> loadAll() {
        List<ScreenDiagonal> screenDiagonals = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                double diagonal = resultSet.getDouble(COLUMN_DIAGONAL);

                screenDiagonals.add(new ScreenDiagonal(id, diagonal));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return screenDiagonals;
    }
}
