package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

public class Accumulator {
    @SerializedName("id")
    private final int id;
    @SerializedName("capacity")
    private int capacity;
    @SerializedName("sections")
    private int sections;

    public Accumulator(int id, int capacity, int sections) {
        this.id = id;
        this.capacity = capacity;
        this.sections = sections;
    }

    public Accumulator(int capacity, int sections) {
        this.id = 0;
        this.capacity = capacity;
        this.sections = sections;
    }

    public Accumulator(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getSections() {
        return sections;
    }

    public void setSections(short sections) {
        this.sections = sections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Accumulator)) return false;

        Accumulator that = (Accumulator) o;

        if (capacity != that.capacity) return false;
        if (id != that.id) return false;
        if (sections != that.sections) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + capacity;
        result = 31 * result + (int) sections;
        return result;
    }
}
