package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class ScreenDiagonal {

    @SerializedName("id")
    private final int id;
    @SerializedName("diagonal")
    private double diagonal;

    public ScreenDiagonal(int id, double diagonal) {
        this.id = id;
        this.diagonal = diagonal;
    }

    public ScreenDiagonal(double diagonal) {
        this.id = 0;
        this.diagonal = diagonal;
    }

    public int getId() {
        return id;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(double diagonal) {
        this.diagonal = diagonal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScreenDiagonal)) return false;

        ScreenDiagonal that = (ScreenDiagonal) o;

        if (Double.compare(that.diagonal, diagonal) != 0) return false;
        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(diagonal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
