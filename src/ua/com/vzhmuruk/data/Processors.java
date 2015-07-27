package ua.com.vzhmuruk.data;


import com.google.gson.annotations.SerializedName;

public class Processors {
    @SerializedName("id")
    private final int id;
    @SerializedName("producer")
    private String producer;
    @SerializedName("model")
    private String model;

    public Processors(int id, String producer, String model) {
        this.id = id;
        this.producer = producer;
        this.model = model;
    }

    public Processors(String producer, String model) {
        this.id = 0;
        this.producer = producer;
        this.model = model;
    }

    public Processors(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Processors)) return false;

        Processors that = (Processors) o;

        if (id != that.id) return false;
        if (!model.equals(that.model)) return false;
        if (!producer.equals(that.producer)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + producer.hashCode();
        result = 31 * result + model.hashCode();
        return result;
    }
}
