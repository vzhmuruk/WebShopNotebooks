package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class VideoCardProd {

    @SerializedName("id")
    private final int id;
    @SerializedName("name")
    private String name;

    public VideoCardProd(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public VideoCardProd(String name) {
        this.id = 0;
        this.name = name;
    }

    public VideoCardProd(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getProdName() {
        return name;
    }

    public void setProdName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VideoCardProd)) return false;

        VideoCardProd that = (VideoCardProd) o;

        if (id != that.id) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
