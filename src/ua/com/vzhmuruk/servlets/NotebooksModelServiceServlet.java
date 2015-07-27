package ua.com.vzhmuruk.servlets;

import com.google.gson.Gson;
import ua.com.vzhmuruk.dao.FacadeDAO;
import ua.com.vzhmuruk.data.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by dare.kh on 24.07.2015.
 */
@WebServlet(name = "NotebooksModelServiceServlet")
public class NotebooksModelServiceServlet extends HttpServlet {
    private static final String PARAMETR_FULL_NAME = "model_full_name";
    private static final String PARAMETR_SHORT_NAME = "model_short_name";
    private static final String PARAMETR_PROD_MOD_ID = "prod_id";
    private static final String PARAMETR_METHOD = "method";
    private static final String PARAMETR_ID = "id";
    private static final String PARAMETR_ID_ACC = "id";
    private static final String PARAMETR_CAPACITY = "capacity";
    private static final String PARAMETR_SECTIONS = "sections";
    private static final String PARAMETR_ID_CAM = "id";
    private static final String PARAMETR_PIXELS = "pixels";
    private static final String PARAMETR_ID_CORE = "id";
    private static final String PARAMETR_NUMCORES = "num_cores";
    private static final String PARAMETR_ID_FRIQ = "id";
    private static final String PARAMETR_FRIQUENCIES = "friquencies";
    private static final String PARAMETR_IDENTITY = "identity";
    private static final String PARAMETR_ID_HDD = "id";
    private static final String PARAMETR_VALUE_HDD = "memory_size";
    private static final String PARAMETR_IDENTITY_HDD = "identity";
    private static final String PARAMETR_ID_NET = "id";
    private static final String PARAMETR_WIFI = "wifi";
    private static final String PARAMETR_WIFISTANDARTS = "wifi_standarts";
    private static final String PARAMETR_ETHERNET = "ethernet";
    private static final String PARAMETR_ETHSPEED = "eth_speed";
    private static final String PARAMETR_ID_PROC = "id";
    private static final String PARAMETR_PRODUCER = "producer";
    private static final String PARAMETR_MODEL = "model";
    private static final String PARAMETR_ID_PROD = "id";
    private static final String PARAMETR_PROD_NAME = "name";
    private static final String PARAMETR_ID_RAMFR = "id";
    private static final String PARAMETR_RAM_FRIQUENCY = "friquency";
    private static final String PARAMETR_IDENTITY_RAMFR = "identity";
    private static final String PARAMETR_ID_RAMTYPE = "id";
    private static final String PARAMETR_RAM_TYPE = "type";
    private static final String PARAMETR_ID_RAMVAL = "id";
    private static final String PARAMETR_VALUE_RAM = "memory_size";
    private static final String PARAMETR_ID_SD = "id";
    private static final String PARAMETR_DIAGONAL = "diagonal";
    private static final String PARAMETR_ID_SM = "id";
    private static final String PARAMETR_TYPE = "type_of_matrix";
    private static final String PARAMETR_ID_SR = "id";
    private static final String PARAMETR_WIDTH = "width";
    private static final String PARAMETR_HEIGHT = "height";
    private static final String PARAMETR_ID_SYS = "id";
    private static final String PARAMETR_TYPE_OS = "type";
    private static final String PARAMETR_ID_VCMOD = "id";
    private static final String PARAMETR_MODEL_VCMOD = "model";
    private static final String PARAMETR_ID_VCPROD = "id";
    private static final String PARAMETR_NAME_VCPROD = "name";
    private static final String PARAMETR_ID_VCV= "id";
    private static final String PARAMETR_SIZE = "size";

    private static final String GET_ALL_METHOD = "get";
    private static final String CREATE_METHOD = "create";
    private static final String DELETE_METHOD = "delete";
    private static final String UPDATE_METHOD = "update";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final String queryMethod = request.getParameter(PARAMETR_METHOD);
        System.out.println("method " + queryMethod);
        response.setContentType("application/json;charset=UTF-8");
        FacadeDAO facadeDAO = null;
        try {
            facadeDAO = new FacadeDAO();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        if (GET_ALL_METHOD.equalsIgnoreCase(queryMethod)) {
            List<NotebookModel> notebookModels = facadeDAO.getNotebookModelDAO().loadAll();
            try {
                PrintWriter out = response.getWriter();
                Gson gson = new Gson();
                gson.toJson(notebookModels, out);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(CREATE_METHOD.equalsIgnoreCase(queryMethod)){
            String modelFull = request.getParameter(PARAMETR_FULL_NAME);
            String modelShort = request.getParameter(PARAMETR_SHORT_NAME);

            String idString = request.getParameter(PARAMETR_ID);
            String capacityString = request.getParameter(PARAMETR_CAPACITY);
            String sectionsString = request.getParameter(PARAMETR_SECTIONS);
            String idCamString = request.getParameter(PARAMETR_ID_CAM);
            String camPixelsString = request.getParameter(PARAMETR_PIXELS);
            String idCoreString = request.getParameter(PARAMETR_ID_CORE);
            String numCoresString = request.getParameter(PARAMETR_NUMCORES);
            String idFriqString = request.getParameter(PARAMETR_ID_FRIQ);
            String friquenciesString = request.getParameter(PARAMETR_FRIQUENCIES);
            String friquIdentString = request.getParameter(PARAMETR_IDENTITY);
            String idHddString = request.getParameter(PARAMETR_ID_HDD);
            String hddValueString = request.getParameter(PARAMETR_VALUE_HDD);
            String hddIdentString = request.getParameter(PARAMETR_IDENTITY_HDD);
            String idNetString = request.getParameter(PARAMETR_ID_NET);
            String wifiString = request.getParameter(PARAMETR_WIFI);
            String wifiStandrtsString = request.getParameter(PARAMETR_WIFISTANDARTS);
            String ethrnetString = request.getParameter(PARAMETR_ETHERNET);
            String ethSpeedString = request.getParameter(PARAMETR_ETHSPEED);
            String idProcString = request.getParameter(PARAMETR_ID_PROC);
            String producerString = request.getParameter(PARAMETR_PRODUCER);
            String modelStrinng = request.getParameter(PARAMETR_MODEL);
            String idProdString = request.getParameter(PARAMETR_ID_PROD);
            String prodNameString = request.getParameter(PARAMETR_PROD_NAME);
            String idRamFriqString = request.getParameter(PARAMETR_ID_RAMFR);
            String ramFriqString = request.getParameter(PARAMETR_RAM_FRIQUENCY);
            String ramFriqIdentString = request.getParameter(PARAMETR_IDENTITY_RAMFR);
            String idRamTypeString = request.getParameter(PARAMETR_ID_RAMTYPE);
            String ramTypeString = request.getParameter(PARAMETR_RAM_TYPE);
            String idRamVal = request.getParameter(PARAMETR_ID_RAMVAL);
            String ramValString = request.getParameter(PARAMETR_VALUE_RAM);
            String idScreenDiagString = request.getParameter(PARAMETR_ID_SD);
            String screenDiagString = request.getParameter(PARAMETR_DIAGONAL);
            String idScreenMatrString = request.getParameter(PARAMETR_ID_SM);
            String screenMatrString = request.getParameter(PARAMETR_TYPE);
            String idScreenResString = request.getParameter(PARAMETR_ID_SR);
            String widthString = request.getParameter(PARAMETR_WIDTH);
            String heightStrong = request.getParameter(PARAMETR_HEIGHT);
            String idOsString = request.getParameter(PARAMETR_ID_SYS);
            String typeOsString = request.getParameter(PARAMETR_TYPE_OS);
            String idVideoMod = request.getParameter(PARAMETR_ID_VCMOD);
            String vidMod = request.getParameter(PARAMETR_MODEL_VCMOD);
            String idVideoProd = request.getParameter(PARAMETR_ID_VCPROD);
            String videoProdString = request.getParameter(PARAMETR_NAME_VCPROD);
            String idVideoValString = request.getParameter(PARAMETR_ID_VCV);
            String videoSizeString = request.getParameter(PARAMETR_SIZE);


            int capacity = Integer.parseInt(capacityString);
            int sections = Integer.parseInt(sectionsString);
            int pixels = Integer.parseInt(camPixelsString);
            int core = Integer.parseInt(numCoresString);
            int friquencies = Integer.parseInt(friquenciesString);
            boolean wifi = Boolean.parseBoolean(wifiString);
            boolean ethernet = Boolean.parseBoolean(ethrnetString);
            int ramVal = Integer.parseInt(ramValString);
            double screenDiag = Double.parseDouble(screenDiagString);
            int screenResolWidth = Integer.parseInt(widthString);
            int screenResolHeight = Integer.parseInt(heightStrong);
            int videoValue = Integer.parseInt(videoSizeString);
            int hddVal = Integer.parseInt(hddValueString);

            NotebookModel notebookModel = new NotebookModel(modelFull, modelShort, (new Accumulator(capacity, sections)), (new Camera(pixels)),
                    (new Cores(core)), (new Friquency(friquencies, friquIdentString)), (new HDDValue(hddVal, hddIdentString)),
                    (new Network(wifi, wifiStandrtsString, ethernet, ethSpeedString)), (new Processors(producerString, modelStrinng)),
                    (new Producer(prodNameString)), (new RamValue(ramVal)), (new RamType(ramTypeString)), (new RamFriquency(ramFriqString, ramFriqIdentString)),
                    (new ScreenDiagonal(screenDiag)), (new ScreenMatrix(screenMatrString)), (new ScreenResolution(screenResolWidth, screenResolHeight)),
                    (new Systems(typeOsString)), (new VideoCardModel(vidMod)), (new VideoCardValue(videoValue)),
                    (new VideoCardProd(videoProdString)));
            boolean created = facadeDAO.getNotebookModelDAO().add(notebookModel);
            try{
                PrintWriter out = response.getWriter();
                if(created){
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("МОДЕЛЬ НОУТБУКА СОЗДАНА");
                }else{
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("ОШИБКА при создании МОДЕЛИ НОУТБУКА");
                }
            }catch(Exception e){
                e.printStackTrace();
            }

    }else if(DELETE_METHOD.equalsIgnoreCase(queryMethod)) {
            String idString = request.getParameter(PARAMETR_ID);
            int id = Integer.parseInt(idString);
            boolean deleted = facadeDAO.getNotebookModelDAO().delete(id);
            try {
                PrintWriter out = response.getWriter();
                if (deleted) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("Параметр УДАЛЕН успешно!");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("ОШИБКА при УДАЛЕНИИ параметра ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}