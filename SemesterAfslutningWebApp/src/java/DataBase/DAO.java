/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import semesterafslutning.Link;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author MartinLodahl
 */
public class DAO {

    public ArrayList<Link> getDirection(int currentRoom) {
        ArrayList<Link> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM link WHERE room_id = ?;";
            PreparedStatement stmt = (PreparedStatement) new DBConnector().getConnection().prepareStatement(query);
            stmt.setInt(1, currentRoom);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                String direction = res.getString("direction");
                int to = res.getInt("goto");
                Link way = new Link(currentRoom, direction, to);
                list.add(way);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
