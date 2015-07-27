package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class ScreenMatrix {

    @SerializedName("id")
    private final int id;
    @SerializedName("type")
    private String type;

    public ScreenMatrix(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public ScreenMatrix(String type) {
        this.id = 0;
        this.type = type;
    }

    public ScreenMatrix(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScreenMatrix)) return false;

        ScreenMatrix that = (ScreenMatrix) o;

        if (id != that.id) return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + type.hashCode();
        return result;
    }
}
