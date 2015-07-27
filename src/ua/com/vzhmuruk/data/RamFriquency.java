package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class RamFriquency {
    @SerializedName("id")
    private final int id;
    @SerializedName("friquency")
    private String friquency;
    @SerializedName("identity")
    private String identity;

    public RamFriquency(int id, String friquency, String identity) {
        this.id = id;
        this.friquency = friquency;
        this.identity = identity;
    }

    public RamFriquency(String friquency, String identity) {
        this.id = 0;
        this.friquency = friquency;
        this.identity = identity;
    }

    public RamFriquency(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFriquency() {
        return friquency;
    }

    public void setFriquency(String friquency) {
        this.friquency = friquency;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RamFriquency)) return false;

        RamFriquency that = (RamFriquency) o;

        if (id != that.id) return false;
        if (!friquency.equals(that.friquency)) return false;
        if (!identity.equals(that.identity)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + friquency.hashCode();
        result = 31 * result + identity.hashCode();
        return result;
    }
}
