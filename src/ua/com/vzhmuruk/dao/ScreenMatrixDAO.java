package ua.com.vzhmuruk.dao;

import javafx.stage.Screen;
import ua.com.vzhmuruk.data.ScreenMatrix;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ScreenMatrixDAO implements AbstractDAO<ScreenMatrix> {

    private static final String TABLE_NAME = "screen_matrix";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TYPE = "type_of_matrix";
    private Statement statement;
    private Connection connection;

    public ScreenMatrixDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }


    @Override
    public boolean add(ScreenMatrix item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_TYPE +
                    ") VALUES (\"" + item.getType() +"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(ScreenMatrix edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_TYPE + " = '" + edited.getType() + "' "
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
    public ScreenMatrix findById(long objid) {
        ScreenMatrix screenMatrix = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String type = resultSet.getString(COLUMN_TYPE);
                screenMatrix = new ScreenMatrix(id, type);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return screenMatrix;
    }

    @Override
    public boolean addAll(Collection<ScreenMatrix> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " " + " (" + COLUMN_TYPE + ")"
                + " VALUES ( ? )";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (ScreenMatrix type : collection) {
                statement.setString(1, type.getType());
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
    public List<ScreenMatrix> loadAll() {
        List<ScreenMatrix> types = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                String type = resultSet.getString(COLUMN_TYPE);

                types.add(new ScreenMatrix(id, type));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }
}
