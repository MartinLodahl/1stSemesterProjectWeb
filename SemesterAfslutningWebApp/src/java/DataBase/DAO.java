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
import semesterafslutning.LinkCollectionSort;
import semesterafslutning.Room;

/**
 *
 * @author MartinLodahl
 */
public class DAO {

    private final DBConnector connector;
    LinkCollectionSort linkSort ;

    public DAO(DBConnector connector) {
        this.connector = connector;
        linkSort= new LinkCollectionSort();
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
            
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    
    public int currentRoomId (int currentRoomId, String direction){
        try {
            String query = "SELECT * FROM link WHERE room_id = ? and direction= ?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, currentRoomId);
            stmt.setString(2,direction);
            ResultSet res = stmt.executeQuery();
            int goTo=0;
            while (res.next()) {
                goTo = res.getInt("goto");
               
            }
            return goTo;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    
    
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

    public void createUser(int playerId , String name,int roomId) {
        try {
            String query = "INSERT INTO dungonsonline.players(playerId ,name, health, gold, room) VALUES (?, ?, 10, 0, ?);";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.setString(2, name);
            stmt.setInt(3, roomId);
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

    public int createUniquePlayerId(){
        
        try {
            String query = "SELECT count(*) AS NUMBER FROM players;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            res.next();
            
            return res.getInt("NUMBER");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 5;
    }
}
