/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import semesterafslutning.Link;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import semesterafslutning.Room;

/**
 *
 * @author MartinLodahl
 */
public class DAO {

    private final DBConnector connector;

    public DAO(DBConnector connector) {
        this.connector = connector;
    }

    public ArrayList<Link> getDirection(int currentRoom) {
        ArrayList<Link> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM link WHERE room_id = ?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, currentRoom);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                String direction = res.getString("direction");
                int to = res.getInt("goto");
                Link way = new Link(currentRoom, direction, to);
                list.add(way);
            }
            
            return sortedByDirection(list);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Link> sortedByDirection(ArrayList collectionOfLinks )
    {

        collectionOfLinks.sort(linkComparator);
        return collectionOfLinks;
    }
    
    public static Comparator<Link> linkComparator
            = new Comparator<Link>()
    {

        @Override
        public int compare(Link link1, Link link2)
        {
            String linkdirec1 = link1.getDirection().toUpperCase();
            String linkdirec2 = link2.getDirection().toUpperCase();
            return linkdirec1.compareTo(linkdirec2);
        }

    };
    public Room getRoom(int currentRoom) {

        try {
            String query = "SELECT * FROM room WHERE ID = ?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, currentRoom);
            ResultSet res = stmt.executeQuery();
            Room room = null;
            while (res.next()) {
                String description = res.getString("Description");
                room = new Room(currentRoom, description);
            }
            return room;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void createUser(String name) {
        try {
            String query = "INSERT INTO dungeonsonline.players(name, health, gold, room) VALUES (?, 10, 100, 1);";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setString(1, name);
            stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkUser(String name) {
        try {
            String query = "SELECT count(name) AS NUMBER FROM players WHERE name = ?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setString(1, name);
            ResultSet res = stmt.executeQuery();
            res.next();

            if (res.getInt("NUMBER") == 0) {
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

}
