/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterafslutning;

import controller.PNGPathCreator;
import controller.Player;
import controller.Item;
import DataBase.DAO;
import DataBase.Monster;
import DataBase.MonsterType;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pravien
 */
public class JsonResponse
{

    public void response(Player player, DAO dao, PNGPathCreator png, HttpServletResponse response, String action) throws SQLException
    {
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            response.setContentType("application/json");
            out.print("{"
                    + "\"room\":" + player.getRoomId() + ","
                    + "\"player\": [" 
                    + "\"playerId\":" + player.getPlayerId() + ","
                    + "\"playerHealth\":" + player.getHealth()+ ","
                    + "\"playerDamage\":" + player.getAttackDmg()+ ","
                    + "],"
                    + "\"picture\": \"PicturesRooms/" + png.pathCreator(dao.getDirection(player.getRoomId())) + ".png\","
                    + "\"north\":" + png.ValidMove("NORTH", dao.getDirection(player.getRoomId())) + ","
                    + "\"south\": " + png.ValidMove("SOUTH", dao.getDirection(player.getRoomId())) + ","
                    + "\"east\": " + png.ValidMove("EAST", dao.getDirection(player.getRoomId())) + ","
                    + "\"west\": " + png.ValidMove("WEST", dao.getDirection(player.getRoomId())) + ","
                    + "\"items\": [");
            ArrayList<Item> itemList = dao.getRoomItems(player.getRoomId());
            for (int i = 0; i < itemList.size(); i++)
            {
                out.print("{\"id\":" + itemList.get(i).getItemId()
                        + ", \"picture\":\"PicturesItems/" + dao.getItemPicture(itemList.get(i).getItemType()) + ".png\","
                        + " \"x\":" + itemList.get(i).getX()
                        + ", \"y\":" + itemList.get(i).getY() + "}");
                if (itemList.size() - 1 > i)
                {
                    out.print(",");
                }
            }
            out.print("],");
            
            out.print("\"monsters\":[");
            ArrayList<Monster> monsters = dao.getRoomMonsters(player.getRoomId());
            for (int i = 0; i < monsters.size(); i++) {
                Monster monster = monsters.get(i);
                MonsterType monsterType = dao.getMonsterType(monster.getType());
                
                out.print("{\"id\":" + monster.getId()
                        + ", \"picture\":\"PicturesItems/" + monsterType.getPicture() + ".png\""
                        + ", \"description\":\"" + monsterType.getDescription() + "\""
                        + ", \"x\":" + monster.getX()
                        + ", \"y\":" + monster.getY() + "}");
                if (monsters.size() - 1 > i) {
                    out.print(",");
                }
            }
            out.print(
                    // close monster
                    "]"
                    + "}");
        } catch (IOException ex)
        {
            Logger.getLogger(JsonResponse.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
