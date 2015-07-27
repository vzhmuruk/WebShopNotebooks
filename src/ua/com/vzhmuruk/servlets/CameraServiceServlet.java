package ua.com.vzhmuruk.servlets;

import com.google.gson.Gson;
import ua.com.vzhmuruk.dao.FacadeDAO;
import ua.com.vzhmuruk.data.Accumulator;
import ua.com.vzhmuruk.data.Camera;

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
 * Created by dare.kh on 20.07.2015.
 */
@WebServlet(name = "CameraServiceServlet")
public class CameraServiceServlet extends HttpServlet {

    private static final String PARAMETR_METHOD = "method";
    private static final String PARAMETR_ID = "id";
    private static final String PARAMETR_PIXELS = "pixels";


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
            List<Camera> cameras = facadeDAO.getCameraDAO().loadAll();
            try {
                PrintWriter out = response.getWriter();
                Gson gson = new Gson();
                gson.toJson(cameras, out);
                response.setStatus(HttpServletResponse.SC_OK);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if(CREATE_METHOD.equalsIgnoreCase(queryMethod)){

            String pixelsString = request.getParameter(PARAMETR_PIXELS);

            int pixel = Integer.parseInt(pixelsString);
            Camera camera = new Camera(pixel);
            boolean created = facadeDAO.getCameraDAO().add(camera);
            try{
                PrintWriter out = response.getWriter();
                if(created){
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("\"Ответ\":\"Параметр КАМЕРА создан\"");
                }else{
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("\"Ответ\":\"Ошибка при создании параметра КАМЕРА\"");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else if(UPDATE_METHOD.equalsIgnoreCase(queryMethod)) {
            String idString = request.getParameter(PARAMETR_ID);
            String pixelsString = request.getParameter(PARAMETR_PIXELS);

            int id = Integer.parseInt(idString);
            int pixels = Integer.parseInt(pixelsString);

            Camera camera = new Camera(id, pixels);
            boolean updated = facadeDAO.getCameraDAO().update(camera);
            try {
                PrintWriter out = response.getWriter();
                if (updated) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("\"Ответ\":\"Параметр КАМЕРА ОБНОВЛЕН успешно!\"");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("\"Ответ\":\"ОШИБКА при ОБНОВЛЕНИИ параметра КАМЕРА\"");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if(DELETE_METHOD.equalsIgnoreCase(queryMethod)) {
            String idString = request.getParameter(PARAMETR_ID);
            int id = Integer.parseInt(idString);
            boolean deleted = facadeDAO.getCameraDAO().delete(id);
            try {
                PrintWriter out = response.getWriter();
                if (deleted) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    out.print("\"Ответ\":\"Параметр КАМЕРА УДАЛЕН успешно!\"");
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    out.print("\"Ответ\":\"ОШИБКА при УДАЛЕНИИ параметра КАМЕРА\"");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
