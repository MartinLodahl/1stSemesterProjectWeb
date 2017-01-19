package server;

import controller.Controller;
import controller.PNGPathCreator;
import businessLogic.Player;
import database.DAO;
import database.DBConnector;
import businessLogic.Monster;
import businessLogic.MonsterType;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                //Request from website

                int currentroomId = Integer.parseInt(request.getParameter("room"));
                int playerId = Integer.parseInt(request.getParameter("playerId"));

                String action = request.getParameter("action");

                DBConnector connector = new DBConnector();
                DAO dao = new DAO(connector);
                Controller ctrl = new Controller(dao);
                JsonResponse jResponse = new JsonResponse();
                Player player;
                // finder det næste rooms ID
                switch (action) {
                    case "UPDATE":
                        player = dao.getPlayer(playerId);
                        jResponse.response(player, dao, png, response, action);
                        break;
                    case "PICKUP":
                        player = dao.getPlayer(playerId);
                        int itemId = Integer.parseInt(request.getParameter("itemId"));
                        ctrl.applyItem(player, itemId);
                        jResponse.response(player, dao, png, response, action);
                        break;
                    case "ATTACK":
                        player = dao.getPlayer(playerId);
                        int monsterId = Integer.parseInt(request.getParameter("monsterId"));
                        Monster monster = dao.getMonster(playerId, monsterId);
                        MonsterType monsterType = dao.getMonsterType(monster.getType());
                        boolean isAlive = true;
                        if (monster.getHealth() == -1) {
                            monster.setHealth(monsterType.getHealth());
                            monster.setAttack(monsterType.getAttack());
                        }
                        ctrl.fight(monster, player);
                        if (monster.getHealth() == 0) {
                            int type = ctrl.getDropType();
                            dao.createItem(player.getId(), player.getRoomId(), monster.getX(), monster.getY(), type);
                            dao.removeMonster(monster);
                        }
                        if (player.getHealth() == 0) {
                            dao.addScore(player);
                            dao.removePlayer(player.getId());
                        }
                        jResponse.response(player, dao, png, response, action);
                        break;
                    case "START":
                        String name = request.getParameter("playerName");
                        playerId = dao.createUniquePlayerId();
                        int playRoomId = ctrl.createPlayerRoomId();
                        player = dao.createUser(playerId, name, playRoomId);
                        dao.copyItems(playerId);
                        dao.copyMonsters(playerId);
                        jResponse.response(player, dao, png, response, action);
                        break;
                    case "GOTO":
                        String direction = request.getParameter("direction");
                        player = dao.getPlayer(playerId);
                        int nextRoomId = dao.currentRoomId(currentroomId, direction);
                        player.setRoomId(nextRoomId);
                        dao.updateUser(nextRoomId, playerId);
                        jResponse.response(player, dao, png, response, action);
                    default:
                        break;
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
