package ua.com.vzhmuruk.servlets;

import com.google.gson.Gson;
import ua.com.vzhmuruk.dao.FacadeDAO;
import ua.com.vzhmuruk.data.Accumulator;
import ua.com.vzhmuruk.data.Network;

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
 * Created by dare.kh on 21.07.2015.
 */
@WebServlet(name = "NetworkServiceServlet")
public class NetworkServiceServlet extends HttpServlet {

    private static final String PARAMETR_METHOD = "method";
    private static final String PARAMETR_ID = "id";
    private static final String PARAMETR_WIFI = "wifi";
    private static final String PARAMETR_WIFISTANDARTS = "wifi_standarts";
    private static final String PARAMETR_ETH = "ethernet";
    private static final String PARAMETR_ETHSPEED = "eth_speed";

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
        FacadeDAO facadeDAO  = null;
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

        if(GET_ALL_METHOD.equalsIgnoreCase(queryMethod)) {
            List<Network> networks = facadeDAO.getNetworkDAO().loadAll();
            try {
                PrintWriter out = response.getWriter();
                Gson gson = new Gson();
                gson.toJson(networks, out);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(CREATE_METHOD.equalsIgnoreCase(queryMethod)){
            //String idString=request.getParameter(PARAMETR_ID);
            String wifiString = request.getParameter(PARAMETR_WIFI);
            String wifiStandartsString = request.getParameter(PARAMETR_WIFISTANDARTS);
            String ethString = request.getParameter(PARAMETR_ETH);
            String ethSpeedString = request.getParameter(PARAMETR_ETHSPEED);

            boolean wifi = Boolean.parseBoolean(wifiString);
            String wifiStandarts = wifiStandartsString;
            boolean eth = Boolean.parseBoolean(ethString);
            String ethSpeed = ethSpeedString;
            Network network = new Network(wifi, wifiStandarts, eth, ethSpeed);
            boolean created = facadeDAO.getNetworkDAO().add(network);
            try{
                PrintWriter out = response.getWriter();
                if(created){
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("Параметр СЕТЬ создан\"");
                }else{
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("Ошибка при создании параметра СЕТЬ");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(UPDATE_METHOD.equalsIgnoreCase(queryMethod)) {
            String wifiString = request.getParameter(PARAMETR_WIFI);
            String wifiStandartsString = request.getParameter(PARAMETR_WIFISTANDARTS);
            String ethString = request.getParameter(PARAMETR_ETH);
            String ethSpeedString = request.getParameter(PARAMETR_ETHSPEED);

            boolean wifi = Boolean.parseBoolean(wifiString);
            String wifiStandarts = wifiStandartsString;
            boolean eth = Boolean.parseBoolean(ethString);
            String ethSpeed = ethSpeedString;
            Network network = new Network(wifi, wifiStandarts, eth, ethSpeed);

            boolean updated = facadeDAO.getNetworkDAO().update(network);
            try {
                PrintWriter out = response.getWriter();
                if (updated) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("Параметр СЕТЬ ОБНОВЛЕН успешно!");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("ОШИБКА при ОБНОВЛЕНИИ параметра СЕТЬ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(DELETE_METHOD.equalsIgnoreCase(queryMethod)) {
            String idString = request.getParameter(PARAMETR_ID);
            int id = Integer.parseInt(idString);
            boolean deleted = facadeDAO.getNetworkDAO().delete(id);
            try {
                PrintWriter out = response.getWriter();
                if (deleted) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("Параметр СЕТЬ УДАЛЕН успешно!");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("ОШИБКА при УДАЛЕНИИ параметра СЕТЬ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
