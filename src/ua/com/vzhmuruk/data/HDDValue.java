package ua.com.vzhmuruk.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dare.kh on 19.07.2015.
 */
public class HDDValue {

    @SerializedName("id")
    private final int id;
    @SerializedName("value")
    private int value;
    @SerializedName("identity")
    private String identity;

    public HDDValue(int id, int value, String identity) {
        this.id = id;
        this.value = value;
        this.identity = identity;
    }

    public HDDValue(int value, String identity) {
        this.id = 0;
        this.value = value;
        this.identity = identity;
    }

    public HDDValue(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getHddIdentity() {
        return identity;
    }

    public void setHddIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HDDValue)) return false;

        HDDValue hddValue = (HDDValue) o;

        if (id != hddValue.id) return false;
        if (value != hddValue.value) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + value;
        return result;
    }
}
