package semesterafslutning;

import controller.Controller;
import controller.PNGPathCreator;
import controller.Player;
import DataBase.DAO;
import DataBase.DBConnector;
import DataBase.Monster;
import DataBase.MonsterType;
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
                //Request from website
                String name = request.getParameter("playerName");
                int currentroomId = Integer.parseInt(request.getParameter("room"));
                int playerId = Integer.parseInt(request.getParameter("playerId"));
                String direction = request.getParameter("direction");
                String action = request.getParameter("action");
                
                
                DBConnector connector = new DBConnector();
                DAO dao = new DAO(connector);
                JsonResponse jResponse = new JsonResponse();
                Player player;
                // finder det næste rooms ID
                if (action.equals("UPDATE")) {
                    player = dao.getPlayer(playerId);
                    jResponse.response(player, dao, png, response, action);
                } else if(action.equals("PICKUP")){
                   player = dao.getPlayer(playerId);
                    int itemId =Integer.parseInt(request.getParameter("itemId"));
                    dao.removeItem(itemId);
                    System.out.println("itemID "+itemId);
                    jResponse.response(player, dao, png, response, action);
                } else if (action.equals("ATTACK")){
                    Controller controller = new Controller();
                    player = dao.getPlayer(playerId);
                    int monsterId = Integer.parseInt(request.getParameter("monsterId"));
                    MonsterType monsterType = dao.getMonsterType(monsterId);
                    Monster monster = dao.getMonster(monsterId);
                    if (monster.getHealth() == -1){
                        monster.setHealth(monsterType.getHealth());
                        monster.setAttack(monsterType.getAttack());
                    }
                    controller.fight(monster, player);
                    
                    
                    jResponse.response(player, dao, png, response, action);
                }
                else{
                if (playerId == 0)
                {
                    playerId = dao.createUniquePlayerId();
                    int playRoomId = ctrl.createPlayerRoomId();
                    System.out.println(playerId);
                    player = dao.createUser(playerId, name, playRoomId);

                } else
                {
                    player = dao.getPlayer(playerId);
                    int nextRoomId = dao.currentRoomId(currentroomId, direction);
                    player.setRoomId(nextRoomId);
                    dao.updateUser(nextRoomId, playerId);
                }
                    jResponse.response(player, dao, png, response, action);
                }
                
                
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
