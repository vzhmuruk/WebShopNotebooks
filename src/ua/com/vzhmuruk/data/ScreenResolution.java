package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class ScreenResolution {

    @SerializedName("id")
    private final int id;
    @SerializedName("width")
    private int width;
    @SerializedName("height")
    private int height;

    public ScreenResolution(int id, int width, int height) {
        this.id = id;
        this.width = width;
        this.height = height;
    }

    public ScreenResolution(int width, int height) {
        this.id = 0;
        this.width = width;
        this.height = height;
    }

    public ScreenResolution(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScreenResolution)) return false;

        ScreenResolution that = (ScreenResolution) o;

        if (height != that.height) return false;
        if (id != that.id) return false;
        if (width != that.width) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + width;
        result = 31 * result + height;
        return result;
    }
}
