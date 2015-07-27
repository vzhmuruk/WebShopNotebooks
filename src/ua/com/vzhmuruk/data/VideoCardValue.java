package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class VideoCardValue {

    @SerializedName("id")
    private final int id;
    @SerializedName("size")
    private int size;

    public VideoCardValue(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public VideoCardValue(int size) {
        this.id = 0;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoCardValue)) return false;

        VideoCardValue that = (VideoCardValue) o;

        if (id != that.id) return false;
        if (size != that.size) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + size;
        return result;
    }
}
