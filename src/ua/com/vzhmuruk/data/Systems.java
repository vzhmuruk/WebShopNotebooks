package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 17.07.2015.
 */
public class Systems {

    @SerializedName("id")
    private final int id;
    @SerializedName("type")
    private String type;

    public Systems(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public Systems(String type) {
        this.id = 0;
        this.type = type;
    }

    public Systems(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getOsType() {
        return type;
    }

    public void setOsType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Systems)) return false;

        Systems systems = (Systems) o;

        if (id != systems.id) return false;
        if (!type.equals(systems.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + type.hashCode();
        return result;
    }
}

