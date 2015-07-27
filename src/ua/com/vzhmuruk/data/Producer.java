package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class Producer {
    @SerializedName("id")
    private final int id;
    @SerializedName("name")
    private String name;

    public Producer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Producer(String name) {
        this.id = 0;
        this.name = name;
    }

    public Producer(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Producer)) return false;

        Producer producer = (Producer) o;

        if (id != producer.id) return false;
        if (!name.equals(producer.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
