package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 17.07.2015.
 */
public class Network {

    @SerializedName("id")
    private final int id;
    @SerializedName("wifi")
    private boolean wifi;
    @SerializedName("wifiStandrts")
    private String wifiStandarts;
    @SerializedName("ethernet")
    private boolean ethernet;
    @SerializedName("ethSpeed")
    private String ethSpeed;

    public Network(int id, boolean wifi, String wifiStandarts, boolean ethernet, String ethSpeed) {
        this.id = id;
        this.wifi = wifi;
        this.wifiStandarts = wifiStandarts;
        this.ethernet = ethernet;
        this.ethSpeed = ethSpeed;
    }

    public Network(boolean wifi, String wifiStandarts, boolean ethernet, String ethSpeed) {
        this.id = 0;
        this.wifi = wifi;
        this.wifiStandarts = wifiStandarts;
        this.ethernet = ethernet;
        this.ethSpeed = ethSpeed;
    }

    public Network(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String getWifiStandarts() {
        return wifiStandarts;
    }

    public void setWifiStandarts(String wifiStandarts) {
        this.wifiStandarts = wifiStandarts;
    }

    public boolean isEthernet() {
        return ethernet;
    }

    public void setEthernet(boolean ethernet) {
        this.ethernet = ethernet;
    }

    public String getEthSpeed() {
        return ethSpeed;
    }

    public void setEthSpeed(String ethSpeed) {
        this.ethSpeed = ethSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Network)) return false;

        Network network = (Network) o;

        if (ethernet != network.ethernet) return false;
        if (id != network.id) return false;
        if (wifi != network.wifi) return false;
        if (!ethSpeed.equals(network.ethSpeed)) return false;
        if (!wifiStandarts.equals(network.wifiStandarts)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (wifi ? 1 : 0);
        result = 31 * result + wifiStandarts.hashCode();
        result = 31 * result + (ethernet ? 1 : 0);
        result = 31 * result + ethSpeed.hashCode();
        return result;
    }
}
