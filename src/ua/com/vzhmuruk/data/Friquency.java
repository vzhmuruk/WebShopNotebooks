package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 17.07.2015.
 */
public class Friquency {

    @SerializedName("id")
    private final int id;
    @SerializedName("friquencies")
    private int friquencies;
    @SerializedName("identity")
    private String identity;

    public Friquency(int id, int friquencies, String identity) {
        this.id = id;
        this.friquencies = friquencies;
        this.identity = identity;
    }

    public Friquency(int friquencies, String identity) {
        this.id = 0;
        this.friquencies = friquencies;
        this.identity = identity;
    }

    public Friquency(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getFriquencys() {
        return friquencies;
    }

    public void setFriquencys(int friquencys) {
        this.friquencies = friquencys;
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
        if (!(o instanceof Friquency)) return false;

        Friquency friquency = (Friquency) o;

        if (friquencies != friquency.friquencies) return false;
        if (id != friquency.id) return false;
        if (!identity.equals(friquency.identity)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + friquencies;
        result = 31 * result + identity.hashCode();
        return result;
    }
}
