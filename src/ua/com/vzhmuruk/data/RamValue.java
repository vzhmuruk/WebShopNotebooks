package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class RamValue {

    @SerializedName("id")
    private final int id;
    @SerializedName("value")
    private String value;

    public RamValue(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public RamValue(String value) {
        this.id = 0;
        this.value = value;

    }

    public RamValue(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RamValue)) return false;

        RamValue ramValue = (RamValue) o;

        if (id != ramValue.id) return false;
        if (!value.equals(ramValue.value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + value.hashCode();
        return result;
    }
}
