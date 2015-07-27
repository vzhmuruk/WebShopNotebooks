package ua.com.vzhmuruk.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ua.com.vzhmuruk.data.*;


public class NotebookModelDAO implements AbstractDAO<NotebookModel>{

    private static final String DB_NAME = "notebooks";

    private static final String TABLE_NAME_ACC = "accumulator";
    private static final String TABLE_NAME_CAM = "camera";
    private static final String TABLE_NAME_CORE = "cores";
    private static final String TABLE_NAME_FRIQ = "friquency";
    private static final String TABLE_NAME_NET = "network";
    private static final String TABLE_NAME_OS = "operation_system";
    private static final String TABLE_NAME_PROC = "processors";
    private static final String TABLE_NAME_PROD = "producers";
    private static final String TABLE_NAME_RAM_FRIQ = "ram_friquency";
    private static final String TABLE_NAME_RAM_TYPE = "ram_type";
    private static final String TABLE_NAME_RAM_VAL = "ram_value";
    private static final String TABLE_NAME_SCREEN_DIAG = "screen_diagonal";
    private static final String TABLE_NAME_SCREEN_MAT = "screen_matrix";
    private static final String TABLE_NAME_SCREEN_RES = "screen_resolution";
    private static final String TABLE_NAME_VC_MODEL = "videocard_model";
    private static final String TABLE_NAME_VC_SIZE = "videocard_size";
    private static final String TABLE_NAME_VC_PRODUCER = "videocard_producer";
    private static final String TABLE_NAME_HDD = "vint_value";
    private static final String TABLE_NAME_NOTEPROD = "note_producer";


    private static final String TABLE_NAME = "model";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PROD_ID = "prod_id";
    private static final String COLUMN_NOTEPROD_NAME = "name_prod";
    private static final String COLUMN_MODEL_FULL = "model_full_name";
    private static final String COLUMN_MODEL_SHORT = "model_short_name";
    private static final String COLUMN_CAMERA = "camera";
    private static final String COLUMN_ACCUM = "accumulator";
    private static final String COLUMN_PROC = "processor";
    private static final String COLUMN_CORES = "cores";
    private static final String COLUMN_NETWORK = "network";
    private static final String COLUMN_OS = "operation_system";
    private static final String COLUMN_PRODUCERS = "producers";
    private static final String COLUMN_RAM_FRIQ = "ram_friquency";
    private static final String COLUMN_RAM_TYPE_M = "ram_type";
    private static final String COLUMN_RAM_VALUE = "ram_value";
    private static final String COLUMN_SCREEN_DIAG = "screen_diagonal";
    private static final String COLUMN_SCREEN_MATR = "screen_matrix";
    private static final String COLUMN_SCREEN_RES = "screen_resolution";
    private static final String COLUMN_VC_MODEL = "videocard_model";
    private static final String COLUMN_VC_SIZE = "videocard_size";
    private static final String COLUMN_VC_PROD = "videocard_producer";
    private static final String COLUMN_HDD_VAL = "hdd_value";
    private static final String COLUMN_FRIQUENCY = "friquency";

    private static final String COLUMN_ID_ACC = "id";
    private static final String COLUMN_CAPACITY = "capacity";
    private static final String COLUMN_SECTIONS = "sections";
    private static final String COLUMN_ID_CAM = "id";
    private static final String COLUMN_PIXELS = "pixels";
    private static final String COLUMN_ID_CORE = "id";
    private static final String COLUMN_NUMCORES = "num_cores";
    private static final String COLUMN_ID_FRIQ = "id";
    private static final String COLUMN_FRIQUENCIES = "friquencies";
    private static final String COLUMN_IDENTITY = "identity";
    private static final String COLUMN_ID_HDD = "id";
    private static final String COLUMN_VALUE_HDD = "memory_size";
    private static final String COLUMN_IDENTITY_HDD = "identity";
    private static final String COLUMN_ID_NET = "id";
    private static final String COLUMN_WIFI = "wifi";
    private static final String COLUMN_WIFISTANDARTS = "wifi_standarts";
    private static final String COLUMN_ETHERNET = "ethernet";
    private static final String COLUMN_ETHSPEED = "eth_speed";
    private static final String COLUMN_ID_PROC = "id";
    private static final String COLUMN_PRODUCER = "producer";
    private static final String COLUMN_MODEL = "model";
    private static final String COLUMN_ID_PROD = "id";
    private static final String COLUMN_PROD_NAME = "name";
    private static final String COLUMN_ID_RAMFR = "id";
    private static final String COLUMN_RAM_FRIQUENCY = "friquency";
    private static final String COLUMN_IDENTITY_RAMFR = "identity";
    private static final String COLUMN_ID_RAMTYPE = "id";
    private static final String COLUMN_RAM_TYPE = "type";
    private static final String COLUMN_ID_RAMVAL = "id";
    private static final String COLUMN_VALUE_RAM = "memory_size";
    private static final String COLUMN_ID_SD = "id";
    private static final String COLUMN_DIAGONAL = "diagonal";
    private static final String COLUMN_ID_SM = "id";
    private static final String COLUMN_TYPE = "type_of_matrix";
    private static final String COLUMN_ID_SR = "id";
    private static final String COLUMN_WIDTH = "width";
    private static final String COLUMN_HEIGHT = "height";
    private static final String COLUMN_ID_SYS = "id";
    private static final String COLUMN_TYPE_OS = "type";
    private static final String COLUMN_ID_VCMOD = "id";
    private static final String COLUMN_MODEL_VCMOD = "model";
    private static final String COLUMN_ID_VCPROD = "id";
    private static final String COLUMN_NAME_VCPROD = "name";
    private static final String COLUMN_ID_VCV= "id";
    private static final String COLUMN_SIZE = "size";


    private Statement statement;
    private Connection connection;

    public NotebookModelDAO(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;
    }

    @Override
    public List<NotebookModel> loadAll() {
        List<NotebookModel> notebookModels = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT " + TABLE_NAME + "." + COLUMN_ID + ", " + TABLE_NAME_ACC + "." + COLUMN_CAPACITY + ", " +
            TABLE_NAME_ACC + "." + COLUMN_SECTIONS + ", " + TABLE_NAME_CAM + "." + COLUMN_PIXELS + ", " + TABLE_NAME_CORE + "." + COLUMN_NUMCORES + ", " +
            TABLE_NAME_FRIQ + "." + COLUMN_FRIQUENCIES + ", " +TABLE_NAME_FRIQ + "." + COLUMN_IDENTITY + ", " +TABLE_NAME_NET + "." + COLUMN_WIFI + ", " +
            TABLE_NAME_NET + "." + COLUMN_WIFISTANDARTS + ", " + TABLE_NAME_NET + "." + COLUMN_ETHERNET + ", " + TABLE_NAME_NET + "." + COLUMN_ETHSPEED + ", " +
            TABLE_NAME_FRIQ + "." + COLUMN_FRIQUENCIES + ", " + TABLE_NAME_OS + "." + COLUMN_TYPE_OS + ", " + TABLE_NAME_PROC + "." + COLUMN_PRODUCER + ", " +
            TABLE_NAME_PROC + "." + COLUMN_MODEL + ", " + TABLE_NAME_PROD + "." + COLUMN_PROD_NAME + ", " + TABLE_NAME_RAM_FRIQ + "." + COLUMN_RAM_FRIQUENCY + ", " +
            TABLE_NAME_RAM_FRIQ + "." + COLUMN_IDENTITY_RAMFR + ", " + TABLE_NAME_RAM_TYPE + "." + COLUMN_RAM_TYPE + ", " + TABLE_NAME_RAM_VAL + "." + COLUMN_VALUE_RAM + ", " +
            TABLE_NAME_SCREEN_DIAG + "." + COLUMN_DIAGONAL + ", " + TABLE_NAME_SCREEN_MAT + "." + COLUMN_TYPE + ", " + TABLE_NAME_SCREEN_RES + "." + COLUMN_WIDTH + ", " +
            TABLE_NAME_SCREEN_RES + "." + COLUMN_HEIGHT + ", " + TABLE_NAME_VC_MODEL + "." + COLUMN_MODEL_VCMOD + ", " + TABLE_NAME_VC_PRODUCER + "." + COLUMN_NAME_VCPROD + ", " +
            TABLE_NAME_VC_SIZE + "." + COLUMN_SIZE + ", " + TABLE_NAME_HDD + "." + COLUMN_VALUE_HDD + ", " + TABLE_NAME_HDD + "." + COLUMN_IDENTITY_HDD + ", " + TABLE_NAME + "." + COLUMN_MODEL_FULL +
            ", " + COLUMN_MODEL_SHORT +  " FROM " + TABLE_NAME + " LEFT JOIN " + TABLE_NAME_ACC + " ON (" + TABLE_NAME_ACC + "." + COLUMN_ID_ACC + " = " + TABLE_NAME + "." + COLUMN_ACCUM + ") "+
            " LEFT JOIN " + TABLE_NAME_CAM + " ON (" + TABLE_NAME_CAM + "." + COLUMN_ID_CAM + " = " + TABLE_NAME + "." + COLUMN_CAMERA + ") "+
            " LEFT JOIN " + TABLE_NAME_CORE + " ON (" + TABLE_NAME_CORE + "." + COLUMN_ID_CORE + " = " + TABLE_NAME + "." + COLUMN_CORES + ") "+
            " LEFT JOIN " + TABLE_NAME_FRIQ + " ON (" + TABLE_NAME_FRIQ + "." + COLUMN_ID_FRIQ + " = " + TABLE_NAME + "." + COLUMN_FRIQUENCY + ") " +
            " LEFT JOIN " + TABLE_NAME_PROC + " ON (" + TABLE_NAME_PROC + "." + COLUMN_ID_PROC + " = " + TABLE_NAME + "." + COLUMN_PROC + ") "+
            " LEFT JOIN " + TABLE_NAME_NET + " ON (" + TABLE_NAME_NET + "." + COLUMN_ID_NET + " = " + TABLE_NAME + "." + COLUMN_NETWORK + ") "+
            " LEFT JOIN " + TABLE_NAME_OS + " ON (" + TABLE_NAME_OS + "." + COLUMN_ID_SYS + " = " + TABLE_NAME + "." + COLUMN_OS + ") "+
            " LEFT JOIN " + TABLE_NAME_PROD + " ON (" + TABLE_NAME_PROD + "." + COLUMN_ID_PROD + " = " + TABLE_NAME + "." + COLUMN_PRODUCERS + ") "+
            " LEFT JOIN " + TABLE_NAME_RAM_FRIQ + " ON (" + TABLE_NAME_RAM_FRIQ + "." + COLUMN_ID_RAMFR + " = " + TABLE_NAME + "." + COLUMN_RAM_FRIQ + ") "+
            " LEFT JOIN " + TABLE_NAME_RAM_TYPE + " ON (" + TABLE_NAME_RAM_TYPE + "." + COLUMN_ID_RAMTYPE + " = " + TABLE_NAME + "." + COLUMN_RAM_TYPE_M + ") "+
            " LEFT JOIN " + TABLE_NAME_RAM_VAL + " ON (" + TABLE_NAME_RAM_VAL + "." + COLUMN_ID_RAMVAL + " = " + TABLE_NAME + "." + COLUMN_RAM_VALUE + ") "+
            " LEFT JOIN " + TABLE_NAME_SCREEN_DIAG + " ON (" + TABLE_NAME_SCREEN_DIAG + "." + COLUMN_ID_SD + " = " + TABLE_NAME + "." + COLUMN_SCREEN_DIAG + ") "+
            " LEFT JOIN " + TABLE_NAME_SCREEN_MAT + " ON (" + TABLE_NAME_SCREEN_MAT + "." + COLUMN_ID_SM + " = " + TABLE_NAME + "." + COLUMN_SCREEN_MATR + ") "+
            " LEFT JOIN " + TABLE_NAME_SCREEN_RES + " ON (" + TABLE_NAME_SCREEN_RES + "." + COLUMN_ID_SR + " = " + TABLE_NAME + "." + COLUMN_SCREEN_RES + ") "+
            " LEFT JOIN " + TABLE_NAME_VC_MODEL + " ON (" + TABLE_NAME_VC_MODEL + "." + COLUMN_ID_VCMOD + " = " + TABLE_NAME + "." + COLUMN_VC_MODEL + ") "+
            " LEFT JOIN " + TABLE_NAME_VC_PRODUCER + " ON (" + TABLE_NAME_VC_PRODUCER + "." + COLUMN_ID_VCPROD + " = " + TABLE_NAME + "." + COLUMN_VC_PROD + ") "+
            " LEFT JOIN " + TABLE_NAME_VC_SIZE + " ON (" + TABLE_NAME_VC_SIZE + "." + COLUMN_ID_VCV + " = " + TABLE_NAME + "." + COLUMN_VC_SIZE + ") "+
            " LEFT JOIN " + TABLE_NAME_HDD + " ON (" + TABLE_NAME_HDD + "." + COLUMN_ID_HDD + " = " + TABLE_NAME + "." + COLUMN_HDD_VAL + ");");

            while(resultSet.next()){
                int idMod = resultSet.getInt(COLUMN_ID);
                String modelFull = resultSet.getString(COLUMN_MODEL_FULL);
                String modelShort = resultSet.getString(COLUMN_MODEL_SHORT);

                int capacity =resultSet.getInt(COLUMN_CAPACITY);
                int sections = resultSet.getInt(COLUMN_SECTIONS);
                int pixels = resultSet.getInt(COLUMN_PIXELS);
                int core = resultSet.getInt(COLUMN_NUMCORES);
                int friquencies = resultSet.getInt(COLUMN_FRIQUENCIES);
                String friqIdentity = resultSet.getString(COLUMN_IDENTITY);
                boolean wifi = resultSet.getBoolean(COLUMN_WIFI);
                String wifiStandarts = resultSet.getString(COLUMN_WIFISTANDARTS);
                boolean ethernet = resultSet.getBoolean(COLUMN_ETHERNET);
                String ethSpeed = resultSet.getString(COLUMN_ETHSPEED);
                String operSystem = resultSet.getString(COLUMN_TYPE_OS);
                String procProducer = resultSet.getString(COLUMN_PRODUCER);
                String procModel = resultSet.getString(COLUMN_MODEL);
                String prodName = resultSet.getString(COLUMN_PROD_NAME);
                String ramFriq = resultSet.getString(COLUMN_RAM_FRIQUENCY);
                String ramFriqIdentity = resultSet.getString(COLUMN_IDENTITY_RAMFR);
                String ramTypes = resultSet.getString(COLUMN_RAM_TYPE);
                String ramVal = resultSet.getString(COLUMN_VALUE_RAM);
                double screenDiag = resultSet.getDouble(COLUMN_DIAGONAL);
                String screenMatr = resultSet.getString(COLUMN_TYPE);
                int screenResolWidth = resultSet.getInt(COLUMN_WIDTH);
                int screenResolHeight = resultSet.getInt(COLUMN_HEIGHT);
                String videoModel = resultSet.getString(COLUMN_MODEL_VCMOD);
                String videoProd = resultSet.getString(COLUMN_NAME_VCPROD);
                int videoValue = resultSet.getInt(COLUMN_SIZE);
                int hddVal = resultSet.getInt(COLUMN_VALUE_HDD);
                String hddIdent = resultSet.getString(COLUMN_IDENTITY_HDD);

                notebookModels.add(new NotebookModel(idMod, modelFull, modelShort, (new Accumulator(capacity, sections)), (new Camera(pixels)),
                        (new Cores(core)), (new Friquency(friquencies, friqIdentity)), (new HDDValue(hddVal, hddIdent)),
                        (new Network(wifi, wifiStandarts, ethernet, ethSpeed)), (new Processors(procProducer, procModel)),
                        (new Producer(prodName)), (new RamValue(ramVal)), (new RamType(ramTypes)), (new RamFriquency(ramFriq, ramFriqIdentity)),
                        (new ScreenDiagonal(screenDiag)),(new ScreenMatrix(screenMatr)), (new ScreenResolution(screenResolWidth, screenResolHeight)),
                        (new Systems(operSystem)), (new VideoCardModel(videoModel)), (new VideoCardValue(videoValue)),
                        (new VideoCardProd(videoProd))));


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return notebookModels;
    }

    @Override
    public boolean add(NotebookModel item)  {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + "( "
                    +  COLUMN_MODEL_FULL + ", " + COLUMN_MODEL_SHORT + ", " + COLUMN_PROD_ID + ", " + COLUMN_CAMERA + ", "
                    + COLUMN_ACCUM + ", " + COLUMN_PROC + ", " + COLUMN_CORES + ", " + COLUMN_NETWORK + ", "
                    + COLUMN_OS + ", "+ COLUMN_PRODUCERS + ", "+ COLUMN_RAM_FRIQ + ", "+ COLUMN_RAM_TYPE_M + ", "
                    + COLUMN_RAM_VALUE + ", "+ COLUMN_SCREEN_DIAG + ", "+ COLUMN_SCREEN_MATR + ", "+ COLUMN_SCREEN_RES + ", "
                    + COLUMN_VC_MODEL + ", "+ COLUMN_VC_SIZE + ", "+ COLUMN_VC_PROD + ", "+ COLUMN_HDD_VAL + ", " + COLUMN_FRIQUENCY +
                    ") VALUES (\"" + item.getModelFullName() + "\", \"" + item.getModelShortName() +"\", "+ item.getProducerId() +", " +
                    item.getCamera().getId() + ", " + item.getAccumulator().getId() + ", " + item.getProcessors().getId() + ", " +
                    item.getCores().getId() + ", " + item.getNetwork().getId() + ", " + item.getSystems().getId() + ", " + item.getProducer().getId() +", " +
                    item.getRamFriquency().getId() + ", " + item.getRamType().getId() + ", " + item.getRamValue().getId()+ ", " +
                    item.getScreenDiagonal().getId() + ", " + item.getScreenMatrix().getId() + ", " + item.getScreenResolution().getId() + ", " +
                    item.getVideoCardModel().getId() + ", " + item.getVideoCardValue().getId() + ", "+ item.getVideoCardProd().getId() + ", " +
                    item.getHddValue().getId() +", " + item.getFriquency().getId() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(NotebookModel edited) {
        return false;
    }

    public boolean delete(int id) {

        return true;
    }

    @Override
    public NotebookModel findById(long objid) {
        return null;
    }

    @Override
    public boolean addAll(Collection<NotebookModel> collection) {
        return false;
    }




}
