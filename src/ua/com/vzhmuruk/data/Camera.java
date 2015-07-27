package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

public class Camera {
    @SerializedName("id")
    private final int id;
    @SerializedName("pixels")
    private int pixels;

    public Camera(int id, int pixels) {
        this.id = id;
        this.pixels = pixels;
    }

    public Camera(int pixels) {
        this.id = 0;
        this.pixels = pixels;

    }

    public int getId() {
        return id;
    }

    public int getPixels() {
        return pixels;
    }

    public void setPixels(int capacity) {
        this.pixels = pixels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Camera)) return false;

        Camera camera = (Camera) o;

        if (id != camera.id) return false;
        if (pixels != camera.pixels) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + pixels;
        return result;
    }
}
