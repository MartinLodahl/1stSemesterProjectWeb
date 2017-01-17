/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import controller.ItemType;
import controller.LinkCollectionSort;
import java.sql.SQLException;

/**
 *
 * @author MartinLodahl
 */
public class DAO {

    private final DBConnector connector;
    LinkCollectionSort linkSort;

    public DAO(DBConnector connector) {
        this.connector = connector;
        linkSort = new LinkCollectionSort();
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

    public int currentRoomId(int currentRoomId, String direction) {
        try {
            String query = "SELECT * FROM link WHERE room_id = ? and direction= ?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, currentRoomId);
            stmt.setString(2, direction);
            ResultSet res = stmt.executeQuery();
            int goTo = currentRoomId;
            while (res.next()) {
                goTo = res.getInt("goto");

            }
            return goTo;
        } catch (Exception ex) {
            ex.printStackTrace();
            return currentRoomId;
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

    public Player createUser(int playerId, String name, int roomId) {
        try {
            int health = 100;
            int attack = 5;
            int gold = 0;
            int defense = 5;
            String query = "INSERT INTO players(playerId ,name, health,attackDmg,defense, roomId, gold) VALUES (?, ?, ?, ?, ? , ?, ?);";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.setString(2, name);
            stmt.setInt(3, health);
            stmt.setInt(4, attack);
            stmt.setInt(5, defense);
            stmt.setInt(6, roomId);
            stmt.setInt(7, gold);
            stmt.executeUpdate();
            Player player = new Player(playerId, name, health, attack,defense, roomId, gold);
            return player;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void updateUser(int roomId, int playerId) {
        try {
            String query = "UPDATE players SET roomId = ? WHERE playerId=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, roomId);
            stmt.setInt(2, playerId);
            stmt.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void updateUser(Player player) {
        try {
            String query = "UPDATE players SET name = ?, health =?, attackDmg = ?,defense = ?, roomId = ?, gold =? WHERE playerId=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getHealth());
            stmt.setInt(3, player.getAttackDmg());
            stmt.setInt(4, player.getDefense());
            stmt.setInt(5, player.getRoomId());
            stmt.setInt(6, player.getGold());
            stmt.setInt(7, player.getId());
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
            int attack = res.getInt("attackDmg");
            int defense = res.getInt("defense");
            int room = res.getInt("roomId");
            int gold = res.getInt("gold");
            Player player = new Player(playerId, name, health, attack,defense, room, gold);
            return player;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public int getItemTypeInt(int itemId){
        try {
            
            String query = "SELECT * FROM items WHERE itemId =?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, itemId);
            ResultSet res = stmt.executeQuery();
            res.next();

            int itemType = res.getInt("type");
            return itemType;
        } catch (Exception ex) {

        }
        return -1;        
    }
    
    public ItemType getItemType(int type){
        try {
            String query = "SELECT * FROM itemtypes WHERE type=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, type);
            ResultSet res = stmt.executeQuery();
            ItemType itemType;
            res.next(); 
            int stat = res.getInt("stat");
            int modifier= res.getInt("modify");
            String note = res.getString("note");
            itemType = new ItemType(stat,modifier,note);

            return itemType;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    } 
    
    public int createUniquePlayerId() {

        try {
            String query = "SELECT count(*) AS NUMBER FROM players;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            res.next();

            return (res.getInt("NUMBER") + 1);

        } catch (Exception ex) {

        }
        return 5;
    }

    public ArrayList<Item> getRoomItems(int roomId) {

        ArrayList<Item> temp = new ArrayList();

        try {
            String query = "SELECT * FROM items WHERE roomId =?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, roomId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {

                int itemId = res.getInt("itemId");
                int type = res.getInt("type");
                int x = res.getInt("x");
                int y = res.getInt("y");

                temp.add(new Item(itemId, type, roomId, x, y));
            }

        } catch (Exception ex) {

        }
        return temp;
    }

    public void removeItem(int itemId) {
        try {
            String query = "DELETE FROM items WHERE itemId =?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        } catch (Exception ex) {

        }

    }

    public String getItemPicture(int itemType) throws SQLException {
        try {
            String query = "SELECT * FROM itemtypes WHERE type =?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, itemType);
            ResultSet res = stmt.executeQuery();
            res.next();

            String pictureName = res.getString("picture");
            return pictureName;
        } catch (Exception ex) {

        }
        return null;
    }

    public ArrayList<Monster> getRoomMonsters(int roomId) {
        ArrayList<Monster> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM monster WHERE roomId=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, roomId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                int type = res.getInt("type");
                int health = res.getInt("health");
                int attack = res.getInt("attack");
                int x = res.getInt("x");
                int y = res.getInt("y");
                list.add(new Monster(id, type, roomId, health, attack, x, y));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Monster getMonster(int id) {
        try {
            String query = "SELECT * FROM monster WHERE id=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            Monster monster;
            res.next(); 
            int type = res.getInt("type");
            int roomId = res.getInt("roomId");
            int health = res.getInt("health");
            int attack = res.getInt("attack");
            int x = res.getInt("x");
            int y = res.getInt("y");
            monster = new Monster(id, type, roomId, health, attack, x, y);

            return monster;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updateMonster(Monster monster) {
        try {
            String query = "UPDATE monster SET roomId=?, health = ?, attack =? WHERE id=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, monster.getRoomId());
            stmt.setInt(2, monster.getHealth());
            stmt.setInt(3, monster.getAttack());
            stmt.setInt(4, monster.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public MonsterType getMonsterType(int type) {
        try {
            String query = "SELECT * FROM monstertype WHERE type=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, type);
            ResultSet res = stmt.executeQuery();
            MonsterType monsterType = null;
            while (res.next()) {
                String picture = res.getString("picture");
                String description = res.getString("description");
                int health = res.getInt("health");
                int attack = res.getInt("attack");
                monsterType = new MonsterType(type, picture, description, health, attack);
            }
            return monsterType;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void updateMonsterType(MonsterType monsterType) {
        try {
            String query = "UPDATE monster SET picture=?, description=?, health=?, attack=? WHERE type=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setString(1, monsterType.getPicture());
            stmt.setString(2, monsterType.getDescription());
            stmt.setInt(3, monsterType.getHealth());
            stmt.setInt(4, monsterType.getAttack());
            stmt.setInt(5, monsterType.getType());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeMonster(Monster monster) {
         try {
            String query = "DELETE FROM monster WHERE ID =?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, monster.getId());
            stmt.executeUpdate();
        } catch (Exception ex) {

        }

    }
}
