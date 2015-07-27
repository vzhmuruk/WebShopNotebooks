package ua.com.vzhmuruk.servlets;

import ua.com.vzhmuruk.dao.FacadeDAO;
import ua.com.vzhmuruk.data.*;

import javax.annotation.processing.Processor;
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
@WebServlet(name = "NotebookModelViewServlet")
public class NotebookModelViewServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        FacadeDAO facade = new FacadeDAO();
        List<NotebookModel> notebookModelList = facade.getNotebookModelDAO().loadAll();
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of Models</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>All models of notebooks</h1>");
            out.println("<table border=\"1\" style=\"width:100%\">");
            out.println("<tr>");
            out.println("<th>1.ID</th>");
            out.println("<th>2.Producer</th>");
            out.println("<th>3.Model full name</th>");
            out.println("<th>4.Model short name</th>");
            out.println("<th>5.Camera</th>");
            out.println("<th>6.Accumulator[Capacity|Sections]</th>");
            out.println("<th>7.Processor[Cores|Friquency]</th>");
            out.println("<th>8.Network [Wi-Fi allow|Wi-Fi Standarts|Ethernet|Ethernet Speed]</th>");
            out.println("<th>9.Operation System</th>");
            out.println("<th>10.RAM [Friquency|Type|Value]</th>");
            out.println("<th>11.Screen[Diagonal|Matrix|Resolution]</th>");
            out.println("<th>12.Videocard[Producer|Model|Value]</th>");
            out.println("<th>13.HDD</th>");
            out.println("<th>DELETE</th>");
            out.println("</tr>");
            for (NotebookModel note : notebookModelList) {
                out.println("<tr>");
                out.println("<td>" + note.getId() + "</td>");
                out.println("<td>" + note.getProducer().getName() + "</td>");
                out.println("<td>" + note.getModelFullName() + "</td>");
                out.println("<td>" + note.getModelShortName() + "</td>");
                out.println("<td>" + note.getCamera().getPixels() +" Mpx" + "</td>");
                out.println("<td>" + note.getAccumulator().getCapacity()+"|"+ note.getAccumulator().getSections() + "</td>");
                out.println("<td>" + note.getProducer().getName()+ " " +note.getProcessors().getModel() + "|" + note.getCores().getNumCores()  + "|"+
                        note.getFriquency().getFriquencys() + " " + note.getFriquency().getIdentity() + "</td>");
                out.println("<td>" + note.getNetwork().isWifi() + "|" + note.getNetwork().getWifiStandarts() + "|" +
                        note.getNetwork().isEthernet() + "|" + note.getNetwork().getEthSpeed()  + "</td>");
                out.println("<td>" + note.getSystems().getOsType() + "</td>");
                out.println("<td>" + note.getRamFriquency().getFriquency() + " " + note.getRamFriquency().getIdentity()+ "|" +
                        note.getRamType().getRamType() + "|" + note.getRamValue().getValue() +  "</td>");
                out.println("<td>" + note.getScreenDiagonal().getDiagonal()+ "|" + note.getScreenMatrix().getType()+ "|" +
                        note.getScreenResolution().getWidth() +  "x" + note.getScreenResolution().getHeight() + "</td>");
                out.println("<td>" + note.getVideoCardProd().getProdName()+ "|" + note.getVideoCardModel().getMoidel()+ "|" +
                        note.getVideoCardValue().getSize() + "</td>");
                out.println("<td>" + note.getHddValue().getValue()+ " " + note.getHddValue().getHddIdentity() + "</td>");
                out.println("<td><a href=\"notebookmodels?method=delete&id=" + note.getId() + "\"><button>Delete</button></a></td>");
                out.println("</tr>");
            }
            out.println("</table><br>");
            out.println("<a href=\"notebookmodels_create\"><button>Create</button></a>");
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
