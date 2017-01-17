/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterafslutning;

import controller.PNGPathCreator;
import database.Player;
import database.Item;
import database.DAO;
import database.Link;
import database.Monster;
import database.MonsterType;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pravien
 */
public class JsonResponse
{
    public static boolean isMoveValid(String direction, ArrayList<Link> links) {
        for (int i = 0; i < links.size(); i++) {
            if (direction.equals(links.get(i).getDirection())) {
                return true;
            }
        }
        return false;
    }

    public void response(Player player, DAO dao, PNGPathCreator png, HttpServletResponse response, String action) throws SQLException
    {
        PrintWriter out = null;
        try
        {
            out = response.getWriter();
            response.setContentType("application/json");
            ArrayList<Link> links = dao.getDirection(player.getRoomId());
            out.print("{\"room\":" + player.getRoomId()
                    + ",\"picture\": \"PicturesRooms/" + png.createPath(links) + ".png\"");
            
            // Directions
            out.print(",\"north\":" + isMoveValid("NORTH", links)
                    + ",\"south\": " + isMoveValid("SOUTH", links)
                    + ",\"east\": " + isMoveValid("EAST", links)
                    + ",\"west\": " + isMoveValid("WEST", links));

            // Items
            out.print(",\"items\": [");
            ArrayList<Item> itemList = dao.getRoomItems(player.getRoomId());
            for (int i = 0; i < itemList.size(); i++)
            {
                out.print("{\"id\":" + itemList.get(i).getId()
                        + ", \"picture\":\"PicturesItems/" + dao.getItemPicture(itemList.get(i).getType()) + ".png\""
                        + ", \"x\":" + itemList.get(i).getX()
                        + ", \"y\":" + itemList.get(i).getY() + "}");
                if (itemList.size() - 1 > i)
                {
                    out.print(",");
                }
            }
            out.print("],");
            
            // Monsters
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
            out.print("],");

            // Player
            out.print("\"playerId\":" + player.getId() + ",");
            out.print("\"player\":");
            out.print("{\"id\":"+player.getId());
            out.print(",\"health\":"+player.getHealth());
            out.print(",\"name\":\""+player.getName()+"\"");
            out.print(",\"attack\":"+player.getAttackDmg());
            out.print(",\"defense\":"+player.getDefense());
            out.print(",\"gold\":"+player.getGold());
            out.print("}");
            
            out.print("}");
        } catch (IOException ex)
        {
            Logger.getLogger(JsonResponse.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
