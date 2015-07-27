package ua.com.vzhmuruk.servlets;

import ua.com.vzhmuruk.dao.FacadeDAO;
import ua.com.vzhmuruk.data.Friquency;
import ua.com.vzhmuruk.data.RamFriquency;

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
 * Created by dare.kh on 22.07.2015.
 */
@WebServlet(name = "RamFriquencyViewServlet")
public class RamFriquencyViewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        FacadeDAO facade = new FacadeDAO();
        List<RamFriquency> friquencyList = facade.getRamFriquencyDAO().loadAll();
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of Friquencies for RAM</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Friquencies for RAM</h1>");
            out.println("<table border=\"1\" style=\"width:45%\">");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Friquencies</th>");
            out.println("<th>Identity</th>");
            out.println("<th>DELETE</th>");
            out.println("</tr>");
            for (RamFriquency friquency : friquencyList) {
                out.println("<tr>");
                out.println("<td>" + friquency.getId() + "</td>");
                out.println("<td>" + friquency.getFriquency() + "</td>");
                out.println("<td>" + friquency.getIdentity() + "</td>");
                out.println("<td><a href=\"ramfriquencies?method=delete&id=" + friquency.getId() + "\"><button>Delete</button></a></td>");
                out.println("</tr>");
            }
            out.println("</table><br>");
            out.println("<a href=\"create_ramfriquencies.html\"><button>Create</button></a>");
            out.println("<br><br><br><a href=\"index.html\">На главную!</a>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
