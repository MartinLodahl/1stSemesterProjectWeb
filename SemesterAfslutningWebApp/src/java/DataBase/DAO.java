/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import controller.Link;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import controller.ICharacter;
import controller.Item;
import controller.LinkCollectionSort;
import controller.Player;
import controller.Room;

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

    public Player createUser(int playerId , String name,int roomId) {
        try {
            String query = "INSERT INTO players(playerId ,name, health, gold, roomId) VALUES (?, ?, 10, 0, ?);";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.setString(2, name);
            stmt.setInt(3, roomId);
            stmt.executeUpdate();
           Player player = new Player ( name,  100, roomId,  playerId);
           return player;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void updateUser(int roomId,int playerId) {
        try {
            String query = "UPDATE players SET roomId = ? WHERE playerId=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, roomId);
            stmt.setInt(2,playerId);
            stmt.executeUpdate();
            

            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

    public Player getPlayer(int playerId) {
        try {
            String query = "SELECT * FROM players WHERE playerId = ?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            ResultSet res = stmt.executeQuery();
            res.next();

            String name = res.getString("name");
            int health = res.getInt("health");
            int gold = res.getInt("gold");
            int room = res.getInt("roomId");
            Player player = new Player(name, health, room,playerId);
            return player;
            

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public int createUniquePlayerId(){
        
        try {
            String query = "SELECT count(*) AS NUMBER FROM players;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            res.next();
            
            return (res.getInt("NUMBER")+1);

        } catch (Exception ex) {
            
        }
        return 5;
    }
    
    public ArrayList<Item> getRoomItems(int roomId){
         
        ArrayList<Item> temp  = new ArrayList();
        
        try {
            String query = "SELECT * FROM items WHERE roomId =?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, roomId);
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                
                int itemId = res.getInt("itemId");
                String itemName = res.getString("itemName");
                int x = res.getInt("x");
                int y = res.getInt("y");
                
                temp.add(new Item(itemId,x,y,roomId,itemName));
            }

        } catch (Exception ex) {
            
        }
        return temp;
    }
    
    public void removeItem (int itemId){
        try {
            String query = "DELETE FROM items WHERE itemId =?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        } catch (Exception ex) {
            
        }
        
    }
}
