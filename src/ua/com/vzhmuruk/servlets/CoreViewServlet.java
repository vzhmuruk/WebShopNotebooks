package ua.com.vzhmuruk.servlets;

import ua.com.vzhmuruk.dao.FacadeDAO;
import ua.com.vzhmuruk.data.Camera;
import ua.com.vzhmuruk.data.Cores;

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
@WebServlet(name = "CoreViewServlet")
public class CoreViewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        FacadeDAO facade = new FacadeDAO();
        List<Cores> coresList = facade.getCoresDAO().loadAll();
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of Cores</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Num cores in processors</h1>");
            out.println("<table border=\"1\" style=\"width:45%\">");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Num of cores</th>");
            out.println("<th>DELETE</th>");
            out.println("</tr>");
            for (Cores cores : coresList) {
                out.println("<tr>");
                out.println("<td>" + cores.getId() + "</td>");
                out.println("<td>" + cores.getNumCores() + "</td>");

                out.println("<td><a href=\"cores?method=delete&id=" + cores.getId() + "\"><button>Delete</button></a></td>");
                out.println("</tr>");
            }
            out.println("</table><br>");
            out.println("<a href=\"create_cores.html\"><button>Create</button></a>");
            out.println("<br><br><br><a href=\"index.html\">На главную!</a>");
            out.println("</body>");
            out.println("</html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
