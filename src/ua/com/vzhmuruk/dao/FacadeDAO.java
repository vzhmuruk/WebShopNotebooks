package ua.com.vzhmuruk.dao;

import ua.com.vzhmuruk.data.*;
import java.sql.*;
import java.lang.System;

import ua.com.vzhmuruk.data.Accumulator;

import javax.servlet.ServletContext;

public class FacadeDAO{
    private Connection connection = null;
    private Statement statement;

    private AccumulatorsDAO accumulatorsDAO;
    private CameraDAO cameraDAO;
    private CoresDAO coresDAO;
    private FriquencyDAO friquencyDAO;
    private NetworkDAO networkDAO;
    private ProcessorsDAO processorsDAO;
    private SystemDAO systemaDAO;
    private HDDValueDAO hddValueDAO;
    private ProducerDAO producerDAO;
    private RamFriquencyDAO ramFriquencyDAO;
    private RamTypeDAO ramTypeDAO;
    private RamValueDAO ramValueDAO;
    private ScreenDiagonalDAO screenDiagonalDAO;
    private ScreenMatrixDAO screenMatrixDAO;
    private ScreenResolutionDAO screenResolutionDAO;
    private VideoCardModelDAO videoCardModelDAO;
    private VideoCardProdDAO videoCardProdDAO;
    private VideoCardValueDAO videoCardValueDAO;
    private NotebookModelDAO notebookModelDAO;

    public FacadeDAO() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {




        final String sqlUrl = "jdbc:mysql://localhost:3306/notebooks";
        final String user = "webshopuser";
        final String password = "";
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        Connection connection = DriverManager.getConnection(sqlUrl, user, password);

        if (connection == null) {
            System.exit(1);
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }



        accumulatorsDAO = new AccumulatorsDAO(statement, connection);
        cameraDAO = new CameraDAO(statement, connection);
        coresDAO = new CoresDAO(statement, connection);
        friquencyDAO = new FriquencyDAO(statement, connection);
        networkDAO = new NetworkDAO(connection, statement);
        processorsDAO = new ProcessorsDAO(statement, connection);
        systemaDAO = new SystemDAO(statement, connection);
        hddValueDAO = new HDDValueDAO(statement, connection);
        producerDAO = new ProducerDAO(statement, connection);
        ramFriquencyDAO = new RamFriquencyDAO(statement, connection);
        ramTypeDAO = new RamTypeDAO(statement, connection);
        ramValueDAO = new RamValueDAO(statement, connection);
        screenDiagonalDAO = new ScreenDiagonalDAO(statement, connection);
        screenMatrixDAO = new ScreenMatrixDAO(statement, connection);
        screenResolutionDAO = new ScreenResolutionDAO(statement, connection);
        videoCardModelDAO = new VideoCardModelDAO(statement,connection);
        videoCardProdDAO =  new VideoCardProdDAO(statement, connection);
        videoCardValueDAO = new VideoCardValueDAO(statement, connection);
        notebookModelDAO = new NotebookModelDAO(statement, connection);

    }

    public AbstractDAO<Accumulator> getAccumulatorsDAO() {
        return accumulatorsDAO;
    }

    public AbstractDAO<Camera> getCameraDAO(){
        return cameraDAO;
    }

    public AbstractDAO<Cores> getCoresDAO(){
        return coresDAO;
    }

    public AbstractDAO<Friquency> getFriquencyDAO(){
        return friquencyDAO;
    }

    public AbstractDAO<HDDValue> getHDDValueDAO() {
        return hddValueDAO;
    }

    public AbstractDAO<Network> getNetworkDAO(){
        return networkDAO;
    }

    public AbstractDAO<Processors> getProcessorsDAO(){
        return processorsDAO;
    }

    public AbstractDAO<Systems> getSystemDAO(){
        return systemaDAO;
    }

    public AbstractDAO<Producer> getProducerDAO(){
        return producerDAO;
    }

    public AbstractDAO<RamFriquency> getRamFriquencyDAO(){
        return ramFriquencyDAO;
    }

    public AbstractDAO<RamType> getRamTypeDAO(){
        return ramTypeDAO;
    }

    public AbstractDAO<RamValue> getRamValueDAO(){
        return ramValueDAO;
    }

    public AbstractDAO<ScreenDiagonal> getScreenDiagonalDAO(){
        return screenDiagonalDAO;
    }

    public AbstractDAO<ScreenMatrix> getScreenMatrixDAO(){
        return screenMatrixDAO;
    }

    public AbstractDAO<ScreenResolution> getScreenResolutionDAO(){
        return screenResolutionDAO;
    }

    public AbstractDAO<VideoCardModel> getVideoCardModelDAO(){
        return videoCardModelDAO;
    }

    public AbstractDAO<VideoCardProd> getVideoCardProducerDAO(){
        return videoCardProdDAO;
    }

    public AbstractDAO<VideoCardValue> getVideoCardValueDAO(){
        return videoCardValueDAO;
    }

    public AbstractDAO<NotebookModel> getNotebookModelDAO(){
        return notebookModelDAO;
    }


    public void closeSqlConnection() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
