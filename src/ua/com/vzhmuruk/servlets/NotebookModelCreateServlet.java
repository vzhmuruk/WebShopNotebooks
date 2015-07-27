package ua.com.vzhmuruk.servlets;

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
 * Created by dare.kh on 27.07.2015.
 */
@WebServlet(name = "NotebookModelCreateServlet")
public class NotebookModelCreateServlet extends HttpServlet {
    protected void createModelMethod(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        FacadeDAO facade = new FacadeDAO();
        List<NotebookModel> notebookModels = facade.getNotebookModelDAO().loadAll();
        List<Accumulator> accumulatorList = facade.getAccumulatorsDAO().loadAll();
        List<Camera> cameraList = facade.getCameraDAO().loadAll();
        List<Friquency> friquencyList = facade.getFriquencyDAO().loadAll();
        List<Network> networkList = facade.getNetworkDAO().loadAll();
        List<Systems> systemsList = facade.getSystemDAO().loadAll();
        List<Processors> processorList = facade.getProcessorsDAO().loadAll();
        List<Cores> coresList = facade.getCoresDAO().loadAll();
        List<Producer> producerList = facade.getProducerDAO().loadAll();
        List<RamFriquency> ramFriquencyList = facade.getRamFriquencyDAO().loadAll();
        List<RamType> ramTypeList = facade.getRamTypeDAO().loadAll();
        List<RamValue> ramValueList = facade.getRamValueDAO().loadAll();
        List<ScreenDiagonal> screenDiagonalList = facade.getScreenDiagonalDAO().loadAll();
        List<ScreenMatrix> screenMatrixList = facade.getScreenMatrixDAO().loadAll();
        List<ScreenResolution> screenResolutionList = facade.getScreenResolutionDAO().loadAll();
        List<VideoCardModel> videoCardModelList = facade.getVideoCardModelDAO().loadAll();
        List<VideoCardProd> videoCardProdList = facade.getVideoCardProducerDAO().loadAll();
        List<VideoCardValue> videoCardValueList = facade.getVideoCardValueDAO().loadAll();
        List<HDDValue> hddValueList = facade.getHDDValueDAO().loadAll();
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List of Accumulators</title>");
            out.println("</head>");
            out.println("<body>");
            out.print("<form action=\"notebookmodels\" method=\"GET\">");
            out.print("<input type=\"hidden\" name=\"method\" value=\"create\"/>");
            out.println("<h1>All models of notebooks</h1>");
            out.println("<table border=\"1\" style=\"width:75%\">");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Producer</th>");
            out.println("<th>Model full name</th>");
            out.println("<th>Model short name</th>");
            out.println("<th>Camera</th>");
            out.println("<th>Accumulator[Capacity|Sections]</th>");
            out.println("<th>Processor[Cores|Friquency]</th>");
            out.println("<th>Network [Wi-Fi allow|Wi-Fi Standarts|Ethernet|Ethernet Speed]</th>");
            out.println("<th>Operation System</th>");
            out.println("<th>RAM [Friquency|Type|Value]</th>");
            out.println("<th>Screen[Diagonal|Matrix|Resolution]</th>");
            out.println("<th>Videocard[Producer|Model|Value]</th>");
            out.println("<th>HDD</th>");
            out.println("</tr>");

            out.println("<tr>");
            out.println("<td>");
            out.print("N/A");
            out.println("</td>");
                out.print("<td>");
                out.println("<select name=\"producers\">");
                for(Producer prod : producerList) {
                    out.println("<option>" + prod.getName() +"</option>");
                }
                out.println("</select>");
                out.print("</td>");
            out.print("<td>");
            out.print("<input name=\"model_full_name\">");
            out.print("</td>");
            out.print("<td>");
            out.print("<input name=\"model_short_name\">");
            out.print("</td>");
            out.print("<td>");
            out.print("<select name=\"pixels\">");
            for(Camera cam : cameraList) {
                out.print("<option>" + cam.getPixels() + "</option>");
            }
            out.print("</select>");
            out.print("</td>");
            out.print("<td>");
            out.print("<select name=\"capacity\">");
            for(Accumulator accum : accumulatorList) {
                out.print("<option>" + accum.getCapacity() + "</option>");
            }
            out.println("</select>");
            out.print("<select name=\"sections\">");
            for(Accumulator accum : accumulatorList) {
                out.print("<option>" + accum.getSections() + "</option>");
            }
            out.println("</select>");
            out.print("</td>");
            out.print("<td>");
            out.print("<select name=\"producer\">");
            for(Processors proc : processorList) {
                out.print("<option>" +proc.getProducer()+ "</option>");
            }
            out.print("</select>");
            out.print("<select name=\"model\">");
            for(Processors proc : processorList) {
                out.print("<option>" +proc.getModel()+ "</option>");
            }
            out.print("</select>");
            out.print("<select name=\"num_cores\">");
            for(Cores core : coresList) {
                out.print("<option>" +core.getNumCores() + "</option>");
            }
            out.print("</select>");
            out.print("<select name=\"friquencies\">");
            for(Friquency friq : friquencyList) {
                out.print("<option>" + friq.getFriquencys() + "</option>");
            }
            out.print("</select>");
            out.print("<select name=\"identity\">");
            for(Friquency friq : friquencyList) {
                out.print("<option>" + friq.getIdentity() + "</option>");
            }
            out.print("</select>");
            out.print("</td>");
            out.print("<td>");
            out.print("<select name=\"wifi\">");
            for(Network wifi : networkList) {
                out.print("<option>" + wifi.isWifi() + "</option>");
            }
            out.print("</select>");
            out.print("<select name=\"wifi_standarts\">");
            for(Network wifistand : networkList) {
                out.print("<option>" + wifistand.getWifiStandarts() + "</option>");
            }
            out.print("</select>");
            out.print("<select name=\"ethernet\">");
            for(Network eth : networkList) {
                out.print("<option>" + eth.isEthernet() + "</option>");
            }
            out.print("</select>");
            out.print("<select name=\"eth_speed\">");
            for(Network eths : networkList) {
                out.print("<option>" + eths.getEthSpeed() + "</option>");
            }
            out.print("</select>");
            out.print("</td>");
            out.print("<td>");
            out.print("<select name=\"type\">");
            for(Systems sys : systemsList) {
                out.println("<option>" + sys.getOsType() +"</option>");
            }
            out.print("</select>");
            out.print("</td>");
            out.print("<td>");
            out.print("<select name=\"friquency\">");
            for(RamFriquency ramFriq : ramFriquencyList) {
                out.println("<option>" + ramFriq.getFriquency() +"</option>");
            }
            out.print("</select>");
            out.print("<select name=\"identity\">");
            for(RamFriquency ramFriqId : ramFriquencyList) {
                out.println("<option>" + ramFriqId.getIdentity() +"</option>");
            }
            out.print("</select>");
            out.print("<select name=\"type\">");
            for(RamType ramType : ramTypeList) {
                out.print("<option>" + ramType.getRamType() + "</option>");
            }
            out.print("</select>");
            out.print("<select name=\"memory_size\">");
            for(RamValue ramValue : ramValueList) {
                out.print("<option>" + ramValue.getValue() + "</option>");
            }
            out.print("</select>");
            out.print("</td>");
            out.print("<td>");
            out.println("<select name=\"diagonal\">");
            for(ScreenDiagonal screenDi : screenDiagonalList) {
                out.println("<option>" + screenDi.getDiagonal() +"</option>");            }
            out.println("</select>");
            out.println("<select name=\"type_of_matrix\">");
            for(ScreenMatrix screenMatrix : screenMatrixList) {
                out.println("<option>" + screenMatrix.getType() +"</option>");            }
            out.println("</select>");
            out.println("<select name=\"width\">");
            for(ScreenResolution screenResolution : screenResolutionList) {
                out.println("<option>" + screenResolution.getWidth() +"</option>");
            }
            out.println("</select>");
            out.println("<select name=\"height\">");
            for(ScreenResolution screenResolution : screenResolutionList) {
                out.println("<option>" + screenResolution.getHeight() +"</option>");
            }
            out.println("</select>");
            out.print("</td>");
            out.print("<td>");
            out.println("<select name=\"name\">");
            for(VideoCardProd videoCardProd : videoCardProdList) {
                out.println("<option>" + videoCardProd.getProdName() +"</option>");
            }
            out.println("</select>");
            out.println("<select name=\"model\">");
            for(VideoCardModel videoCardModel : videoCardModelList) {
                out.println("<option>" + videoCardModel.getMoidel() +"</option>");
            }
            out.println("</select>");
            out.println("<select name=\"size\">");
            for(VideoCardValue videoCardValue : videoCardValueList) {
                out.println("<option>" + videoCardValue.getSize() +"</option>");            }
            out.println("</select>");
            out.print("</td>");
            out.print("<td>");
            out.println("<select name=\"value\">");
            for(HDDValue hddValue : hddValueList) {
                out.println("<option>" + hddValue.getValue() +"</option>");
            }
            out.println("<select name=\"identity\">");
            for(HDDValue hddIdent : hddValueList) {
                out.println("<option>" + hddIdent.getHddIdentity() +"</option>");            }
            out.println("</select>");
            out.print("</td>");
            out.println("</tr>");

            out.println("</table><br>");
            out.print("<input type=\"submit\" value=\"Save\">");
            out.print("</form>");
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
            createModelMethod(request, response);
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
