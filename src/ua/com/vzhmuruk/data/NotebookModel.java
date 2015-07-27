package ua.com.vzhmuruk.data;


public class NotebookModel {

    private final int id;
    private String modelFullName;
    private String modelShortName;
    private int producerId;
    private Accumulator accumulator;
    private Camera camera;
    private Cores cores;
    private Friquency friquency;
    private HDDValue hddValue;
    private Network network;
    private Processors processors;
    private Producer producer;
    private RamValue ramValue;
    private RamType ramType;
    private RamFriquency ramFriquency;
    private ScreenDiagonal screenDiagonal;
    private ScreenMatrix screenMatrix;
    private ScreenResolution screenResolution;
    private Systems systems;
    private VideoCardModel videoCardModel;
    private VideoCardValue videoCardValue;
    private VideoCardProd videoCardProd;

    public NotebookModel(int id, String modelFullName, String modelShortName, Accumulator accumulator,
                         Camera camera, Cores cores, Friquency friquency, HDDValue hddValue, Network network, Processors processors,
                         Producer producer, RamValue ramValue, RamType ramType, RamFriquency ramFriquency, ScreenDiagonal screenDiagonal,
                         ScreenMatrix screenMatrix, ScreenResolution screenResolution, Systems systems, VideoCardModel videoCardModel,
                         VideoCardValue videoCardValue, VideoCardProd videoCardProd) {
        this.id = id;
        this.modelFullName = modelFullName;
        this.modelShortName = modelShortName;
        this.producerId = producerId;
        this.accumulator = accumulator;
        this.camera = camera;
        this.cores = cores;
        this.friquency = friquency;
        this.hddValue = hddValue;
        this.network = network;
        this.processors = processors;
        this.producer = producer;
        this.ramValue = ramValue;
        this.ramType = ramType;
        this.ramFriquency = ramFriquency;
        this.screenDiagonal = screenDiagonal;
        this.screenMatrix = screenMatrix;
        this.screenResolution = screenResolution;
        this.systems = systems;
        this.videoCardModel = videoCardModel;
        this.videoCardValue = videoCardValue;
        this.videoCardProd = videoCardProd;
    }

    public NotebookModel(String modelFullName, String modelShortName, Accumulator accumulator, Camera camera,
                         Cores cores, Friquency friquency, HDDValue hddValue, Network network, Processors processors,
                         Producer producer, RamValue ramValue, RamType ramType, RamFriquency ramFriquency,
                         ScreenDiagonal screenDiagonal, ScreenMatrix screenMatrix, ScreenResolution screenResolution,
                         Systems systems, VideoCardModel videoCardModel, VideoCardValue videoCardValue,
                         VideoCardProd videoCardProd) {
        this.id = 0;
        this.modelFullName = modelFullName;
        this.modelShortName = modelShortName;
        this.producerId = producerId;
        this.accumulator = accumulator;
        this.camera = camera;
        this.cores = cores;
        this.friquency = friquency;
        this.hddValue = hddValue;
        this.network = network;
        this.processors = processors;
        this.producer = producer;
        this.ramValue = ramValue;
        this.ramType = ramType;
        this.ramFriquency = ramFriquency;
        this.screenDiagonal = screenDiagonal;
        this.screenMatrix = screenMatrix;
        this.screenResolution = screenResolution;
        this.systems = systems;
        this.videoCardModel = videoCardModel;
        this.videoCardValue = videoCardValue;
        this.videoCardProd = videoCardProd;
    }

    public int getId() {
        return id;
    }

    public String getModelFullName() {
        return modelFullName;
    }

    public void setModelFullName(String modelFullName) {
        this.modelFullName = modelFullName;
    }

    public String getModelShortName() {
        return modelShortName;
    }

    public void setModelShortName(String modelShortName) {
        this.modelShortName = modelShortName;
    }

    public long getProducerId() {
        return producerId;
    }

    public void setProducerId(int producerId) {
        this.producerId = producerId;
    }

    public Accumulator getAccumulator() {
        return accumulator;
    }

    public void setAccumulator(Accumulator accumulator) {
        this.accumulator = accumulator;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Cores getCores() {
        return cores;
    }

    public void setCores(Cores cores) {
        this.cores = cores;
    }

    public Friquency getFriquency() {
        return friquency;
    }

    public void setFriquency(Friquency friquency) {
        this.friquency = friquency;
    }

    public HDDValue getHddValue() {
        return hddValue;
    }

    public void setHddValue(HDDValue hddValue) {
        this.hddValue = hddValue;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Processors getProcessors() {
        return processors;
    }

    public void setProcessors(Processors processors) {
        this.processors = processors;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public RamValue getRamValue() {
        return ramValue;
    }

    public void setRamValue(RamValue ramValue) {
        this.ramValue = ramValue;
    }

    public RamType getRamType() {
        return ramType;
    }

    public void setRamType(RamType ramType) {
        this.ramType = ramType;
    }

    public RamFriquency getRamFriquency() {
        return ramFriquency;
    }

    public void setRamFriquency(RamFriquency ramFriquency) {
        this.ramFriquency = ramFriquency;
    }

    public ScreenDiagonal getScreenDiagonal() {
        return screenDiagonal;
    }

    public void setScreenDiagonal(ScreenDiagonal screenDiagonal) {
        this.screenDiagonal = screenDiagonal;
    }

    public ScreenMatrix getScreenMatrix() {
        return screenMatrix;
    }

    public void setScreenMatrix(ScreenMatrix screenMatrix) {
        this.screenMatrix = screenMatrix;
    }

    public ScreenResolution getScreenResolution() {
        return screenResolution;
    }

    public void setScreenResolution(ScreenResolution screenResolution) {
        this.screenResolution = screenResolution;
    }

    public Systems getSystems() {
        return systems;
    }

    public void setSystems(Systems systems) {
        this.systems = systems;
    }

    public VideoCardModel getVideoCardModel() {
        return videoCardModel;
    }

    public void setVideoCardModel(VideoCardModel videoCardModel) {
        this.videoCardModel = videoCardModel;
    }

    public VideoCardValue getVideoCardValue() {
        return videoCardValue;
    }

    public void setVideoCardValue(VideoCardValue videoCardValue) {
        this.videoCardValue = videoCardValue;
    }

    public VideoCardProd getVideoCardProd() {
        return videoCardProd;
    }

    public void setVideoCardProd(VideoCardProd videoCardProd) {
        this.videoCardProd = videoCardProd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NotebookModel)) return false;

        NotebookModel that = (NotebookModel) o;

        if (id != that.id) return false;
        if (producerId != that.producerId) return false;
        if (!accumulator.equals(that.accumulator)) return false;
        if (!camera.equals(that.camera)) return false;
        if (!cores.equals(that.cores)) return false;
        if (!friquency.equals(that.friquency)) return false;
        if (!hddValue.equals(that.hddValue)) return false;
        if (!modelFullName.equals(that.modelFullName)) return false;
        if (!modelShortName.equals(that.modelShortName)) return false;
        if (!network.equals(that.network)) return false;
        if (!processors.equals(that.processors)) return false;
        if (!producer.equals(that.producer)) return false;
        if (!ramFriquency.equals(that.ramFriquency)) return false;
        if (!ramType.equals(that.ramType)) return false;
        if (!ramValue.equals(that.ramValue)) return false;
        if (!screenDiagonal.equals(that.screenDiagonal)) return false;
        if (!screenMatrix.equals(that.screenMatrix)) return false;
        if (!screenResolution.equals(that.screenResolution)) return false;
        if (!systems.equals(that.systems)) return false;
        if (!videoCardModel.equals(that.videoCardModel)) return false;
        if (!videoCardProd.equals(that.videoCardProd)) return false;
        if (!videoCardValue.equals(that.videoCardValue)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + modelFullName.hashCode();
        result = 31 * result + modelShortName.hashCode();
        result = 31 * result + (int) (producerId ^ (producerId >>> 32));
        result = 31 * result + accumulator.hashCode();
        result = 31 * result + camera.hashCode();
        result = 31 * result + cores.hashCode();
        result = 31 * result + friquency.hashCode();
        result = 31 * result + hddValue.hashCode();
        result = 31 * result + network.hashCode();
        result = 31 * result + processors.hashCode();
        result = 31 * result + producer.hashCode();
        result = 31 * result + ramValue.hashCode();
        result = 31 * result + ramType.hashCode();
        result = 31 * result + ramFriquency.hashCode();
        result = 31 * result + screenDiagonal.hashCode();
        result = 31 * result + screenMatrix.hashCode();
        result = 31 * result + screenResolution.hashCode();
        result = 31 * result + systems.hashCode();
        result = 31 * result + videoCardModel.hashCode();
        result = 31 * result + videoCardValue.hashCode();
        result = 31 * result + videoCardProd.hashCode();
        return result;
    }
}
