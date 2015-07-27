package ua.com.vzhmuruk.servlets;

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
@WebServlet(name = "NetworkViewServlet")
public class NetworkViewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        FacadeDAO facade = new FacadeDAO();
        List<Network> networkList = facade.getNetworkDAO().loadAll();
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of Networks properties</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Network properties</h1>");
            out.println("<table border=\"1\" style=\"width:45%\">");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>WiFiOnBoard</th>");
            out.println("<th>Wi-Fi Standarts</th>");
            out.println("<th>EthernetOnBoard</th>");
            out.println("<th>Ethernet Speed</th>");
            out.println("<th>DELETE</th>");
            out.println("</tr>");
            for (Network network : networkList) {
                out.println("<tr>");
                out.println("<td>" + network.getId() + "</td>");
                out.println("<td>" + network.isWifi() + "</td>");
                out.println("<td>");
                if(network.isWifi()) {
                    out.println(network.getWifiStandarts());
                }else{
                    out.println("no Wi-Fi");
                }
                out.println("</td>");
                out.println("<td>" + network.isEthernet() + "</td>");
                out.println("<td>");
                if(network.isEthernet()) {
                       out.println(network.getEthSpeed());
                }else{
                    out.println("no Eth");
                }
                out.println("</td>");
                out.println("<td><a href=\"networks?method=delete&id=" + network.getId() + "\"><button>Delete</button></a></td>");
                out.println("</tr>");
            }
            out.println("</table><br>");
            out.println("<a href=\"create_networks.html\"><button>Create</button></a>");
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
