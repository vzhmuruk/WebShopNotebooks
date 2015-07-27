package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class RamType {

    @SerializedName("id")
    private final int id;
    @SerializedName("type")
    private String type;

    public RamType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public RamType(String type) {
        this.id = 0;
        this.type = type;
    }

    public RamType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getRamType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RamType)) return false;

        RamType ramType = (RamType) o;

        if (id != ramType.id) return false;
        if (type != null ? !type.equals(ramType.type) : ramType.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
