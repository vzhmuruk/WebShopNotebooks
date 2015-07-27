package ua.com.vzhmuruk.dao;

import com.google.gson.annotations.SerializedName;
import ua.com.vzhmuruk.data.Network;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by dare.kh on 17.07.2015.
 */
public class NetworkDAO implements AbstractDAO<Network> {

    private static final String TABLE_NAME = "network";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_WIFI = "wifi";
    private static final String COLUMN_WIFISTANDARTS = "wifi_standarts";
    private static final String COLUMN_ETHERNET = "ethernet";
    private static final String COLUMN_ETHSPEED = "eth_speed";
    private Connection connection;
    private Statement statement;

    public NetworkDAO(Connection connection, Statement statement) {
        this.statement = statement;
        this.connection = connection;
    }

    @Override
    public boolean add(Network item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + "( " +
                    COLUMN_WIFI + ", " + COLUMN_WIFISTANDARTS + ", " + COLUMN_ETHERNET + ", " + COLUMN_ETHSPEED +
                    ") VALUES (" + item.isWifi() +", \"" + item.getWifiStandarts()+"\", " + item.isEthernet() + ", \"" + item.getEthSpeed() +"\")");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean update(Network edited) {
        try{
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_WIFI + " = '" + edited.isWifi() + "', "
                    + COLUMN_WIFISTANDARTS + "= '" + edited.getWifiStandarts()+ "', "
                    + COLUMN_ETHERNET + "= '" + edited.isEthernet() + "', "
                    + COLUMN_ETHSPEED + "= '" + edited.getEthSpeed()
                    + "' WHERE " + COLUMN_ID + " = " + edited.getId() + ";");
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
    public Network findById(long objid) {
        Network network = null;
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + objid +";");
            while (resultSet.next()){
                int id = resultSet.getInt(COLUMN_ID);
                boolean wifi = resultSet.getBoolean(COLUMN_WIFI);
                String wifiStandarts = resultSet.getString(COLUMN_WIFISTANDARTS);
                boolean ethernet = resultSet.getBoolean(COLUMN_ETHERNET);
                String ethSpeed = resultSet.getString(COLUMN_ETHSPEED);
                network = new Network(id, wifi, wifiStandarts, ethernet, ethSpeed);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return network;
    }

    @Override
    public boolean addAll(Collection<Network> collection) {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " ("
                + COLUMN_WIFI + ", " + COLUMN_WIFISTANDARTS + ", "
                + COLUMN_ETHERNET + ", " + COLUMN_ETHSPEED +")"
                + " VALUES ( ? , ? , ?, ?)";

        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            for (Network network : collection) {
                statement.setBoolean(1, network.isWifi());
                statement.setString(2, network.getWifiStandarts());
                statement.setBoolean(3, network.isEthernet());
                statement.setString(4, network.getEthSpeed());
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
    public List<Network> loadAll() {
            List<Network> networks = new ArrayList<Network>();
            try {
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_NAME + ";");
                while (resultSet.next()){
                    int id = resultSet.getInt(COLUMN_ID);
                    boolean wifi = resultSet.getBoolean(COLUMN_WIFI);
                    String wifiStandarts = resultSet.getString(COLUMN_WIFISTANDARTS);
                    boolean ethernet = resultSet.getBoolean(COLUMN_ETHERNET);
                    String ethSpeed = resultSet.getString(COLUMN_ETHSPEED);
                    networks.add(new Network(id, wifi, wifiStandarts, ethernet, ethSpeed));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return networks;
    }
}
