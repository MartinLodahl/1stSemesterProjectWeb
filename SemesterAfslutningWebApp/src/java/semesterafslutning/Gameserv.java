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
import java.util.ArrayList;
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
        =
        {
            "/Gameserv"
        })
public class Gameserv extends HttpServlet
{

    PNGPathCreator png = new PNGPathCreator();
    Controller ctrl = new Controller();

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
            throws ServletException, IOException, Exception
    {
        try (PrintWriter out = response.getWriter())
        {
            try
            {
                String name = request.getParameter("name");
                int currentroomId = Integer.parseInt(request.getParameter("room"));
                int playerId = Integer.parseInt(request.getParameter("playerId"));
                String direction = request.getParameter("direction");
                DBConnector connector = new DBConnector();
                DAO dao = new DAO(connector);
                Player player;
                // finder det næste rooms ID

                if (playerId == 0)
                {
                    playerId = dao.createUniquePlayerId();
                    int playRoomId = ctrl.createPlayerRoomId();
                    System.out.println(playerId);
                    player = dao.createUser(playerId, "marton", playRoomId);

                } else
                {
                    player = dao.getPlayer(playerId);
                    int nextRoomId = dao.currentRoomId(currentroomId, direction);
                    player.setRoomID(nextRoomId);
                    dao.updateUser(nextRoomId, playerId);
                }
                response.setContentType("application/json");
                out.print("{"
                        + "\"room\":" + player.getRoomId() + ","
                        + "\"playerId\":" + playerId + ","
                        + "\"picture\": \"PicturesRooms/" + png.pathCreator(dao.getDirection(player.getRoomId())) + ".png\","
                        + "\"north\":" + png.ValidMove("NORTH", dao.getDirection(player.getRoomId())) + ","
                        + "\"south\": " + png.ValidMove("SOUTH", dao.getDirection(player.getRoomId())) + ","
                        + "\"east\": " + png.ValidMove("EAST", dao.getDirection(player.getRoomId())) + ","
                        + "\"west\": " + png.ValidMove("WEST", dao.getDirection(player.getRoomId())) + ","
                        + "\"items\": [");
                ArrayList<Item> itemList = dao.getRoomItems(player.getRoomId());
                for (int i = 0; i < itemList.size(); i++)
                {
                    out.print("{\"id\":"+itemList.get(i).getItemId()
                            +", \"picture\":\"PicturesItems/"+itemList.get(i).getItemName()+".png\","
                            + " \"x\":"+itemList.get(i).getX()
                            +", \"y\":"+itemList.get(i).getY()+"}");
                    if(itemList.size()-1>i){
                        out.print(",");
                    }
                }

                out.print(
                        // close items
                        "]"
                        + "}");
            } catch (Exception ex)
            {
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
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (Exception ex)
        {
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
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (Exception ex)
        {
            Logger.getLogger(Gameserv.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
