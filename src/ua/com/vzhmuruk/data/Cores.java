package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;


public class Cores {

    @SerializedName("id")
    private final int id;
    @SerializedName("numCores")
    private int numCores;

    public Cores(int id, int numCores) {
        this.id = id;
        this.numCores = numCores;
    }

    public Cores(int numCores) {
        this.id = 0;
        this.numCores = numCores;
    }

    public int getId() {
        return id;
    }

    public int getNumCores() {
        return numCores;
    }

    public void setNumCores(int numCores) {
        this.numCores = numCores;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cores)) return false;

        Cores cores = (Cores) o;

        if (id != cores.id) return false;
        if (numCores != cores.numCores) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + numCores;
        return result;
    }
}
