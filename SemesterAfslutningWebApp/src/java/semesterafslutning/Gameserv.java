/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterafslutning;

import DataBase.DAO;
import DataBase.DBConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pravien
 */
@WebServlet(name = "Gameserv", urlPatterns
        = {
            "/Gameserv"
        })
public class Gameserv extends HttpServlet {
                    
                    
    PNGPathCreator png = new PNGPathCreator();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        try (PrintWriter out = response.getWriter()) {
            try {
                String name = request.getParameter("name");
                int currentroomId = Integer.parseInt(request.getParameter("room"));
                String direction = request.getParameter("direction");
                DBConnector connector = new DBConnector();
                DAO dao = new DAO(connector);
                // finder det næste rooms ID
                int nextRoomId = dao.currentRoomId(currentroomId,direction);
                
                
                if (!dao.checkUser(name)) {
                    dao.createUser(name);
                    
                    out.print("{\"room\":"+ nextRoomId+", \"picture\": \"0001.png\", \"north\":" +png.ValidMove("NORTH", dao.getDirection(nextRoomId))+", \"south\": "+png.ValidMove("SOUTH", dao.getDirection(nextRoomId))+", \"east\": "+png.ValidMove("EAST", dao.getDirection(nextRoomId))+", \"west\": "+png.ValidMove("WEST", dao.getDirection(nextRoomId))+" }");
                    /*
                    out.print(
                        "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "    <title>Dungeon Online</title>\n" +
                        "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                        "    <link rel=\"stylesheet\" href=\"main.css\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <div class=\"outer\">\n" +
                        "        <div class=\"page\" style=\"background-image: url(&quot;0001.png&quot;);\">"
                    );
                    out.print(
                        "           <form action=\"Gameserv\" method=\"post\">\n" +
                        "                <input type=\"hidden\" name=\"room\" value=\"1\">\n" +
                        "                <input type=\"hidden\" name=\"direction\" value=\"north\">\n" +
                        "                <input type=\"submit\" value=\"North\" id=\"north\">\n" +
                        "            </form>\n"
                    );
                    out.println(
                        "        </div>\n" +
                        "    </div>\n" +
                        "</body>\n" +
                        "</html>\n"
                    );
                    */
                    response.setContentType("text/html;charset=UTF-8");
                } else{
                    response.sendRedirect("registration.html");
                }
            } catch (Exception ex) {
                out.println(out.toString());
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Gameserv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Gameserv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
