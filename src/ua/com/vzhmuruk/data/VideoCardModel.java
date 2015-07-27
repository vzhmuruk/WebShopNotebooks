package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class VideoCardModel {

    @SerializedName("id")
    private final int id;
    @SerializedName("model")
    private String model;

    public VideoCardModel(int id, String model) {
        this.id = id;
        this.model = model;
    }

    public VideoCardModel(String model) {
        this.id = 0;
        this.model = model;
    }

    public VideoCardModel(int id) {
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public String getMoidel() {
        return model;
    }

    public void setMoidel(String moidel) {
        this.model = moidel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoCardModel)) return false;

        VideoCardModel that = (VideoCardModel) o;

        if (id != that.id) return false;
        if (!model.equals(that.model)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + model.hashCode();
        return result;
    }
}
