/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import businessLogic.Highscore;
import exceptions.DontExistException;
import businessLogic.MonsterType;
import businessLogic.Monster;
import businessLogic.Item;
import businessLogic.Room;
import businessLogic.Link;
import businessLogic.Player;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import businessLogic.ItemType;
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
            stmt.close();
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
            stmt.close();
            return goTo;
        } catch (Exception ex) {
            ex.printStackTrace();
            return currentRoomId;
        }
    }

    public Room getRoom(int currentRoom) throws SQLException, DontExistException {

        String query = "SELECT * FROM room WHERE ID = ?;";
        PreparedStatement stmt = connector.getConnection().prepareStatement(query);
        stmt.setInt(1, currentRoom);
        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            String description = res.getString("Description");
            Room room = new Room(currentRoom, description);
            stmt.close();
            return room;
        }
        throw new DontExistException(currentRoom, "Room");
    }

    public Item createItem(int playerId, int roomId, int x, int y, int type) throws SQLException {
        String query = "SELECT MAX(itemId)+1 AS itemId FROM items;";
        PreparedStatement stmt = connector.getConnection().prepareStatement(query);
        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            int itemId = res.getInt("itemId");

            query = "INSERT INTO items(playerId, itemId, roomId, x, y, type) "
                    + "VALUES (?, ?, ?, ?, ?, ?);";
            stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.setInt(2, itemId);
            stmt.setInt(3, roomId);
            stmt.setInt(4, x);
            stmt.setInt(5, y);
            stmt.setInt(6, type);
            stmt.executeUpdate();
            Item item = new Item(itemId, type, roomId, x, y);
            stmt.close();
            return item;
        }
        return null;
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
            Player player = new Player(playerId, name, health, attack, defense, roomId, gold);
            stmt.close();
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
            stmt.close();
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
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Player getPlayer(int playerId) throws SQLException, DontExistException {

        String query = "SELECT * FROM players WHERE playerId = ?;";
        PreparedStatement stmt = connector.getConnection().prepareStatement(query);
        stmt.setInt(1, playerId);
        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            String name = res.getString("name");
            int health = res.getInt("health");
            int attack = res.getInt("attackDmg");
            int defense = res.getInt("defense");
            int room = res.getInt("roomId");
            int gold = res.getInt("gold");
            Player player = new Player(playerId, name, health, attack, defense, room, gold);
            stmt.close();
            return player;
        }
        throw new DontExistException(playerId, "Player");
    }

    public ItemType getItemType(int type) throws SQLException, DontExistException {

        String query = "SELECT * FROM itemtypes WHERE type=?;";
        PreparedStatement stmt = connector.getConnection().prepareStatement(query);
        stmt.setInt(1, type);
        ResultSet res = stmt.executeQuery();
        ItemType itemType;
        if (res.next()) {
            int stat = res.getInt("stat");
            int modifier = res.getInt("modify");
            String note = res.getString("note");
            String picture = res.getString("picture");
            itemType = new ItemType(stat, modifier, note, picture);
            stmt.close();
            return itemType;
        }
        throw new DontExistException(type, "ItemType");
    }

    public int createUniquePlayerId() {

        try {
            String query = "SELECT max(playerId) as HIGHESTID FROM players;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            res.next();
            stmt.close();
            return (res.getInt("HIGHESTID") + 1);

        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public Item getItem(int playerId, int itemId) throws SQLException, DontExistException {

        String query = "SELECT * FROM items WHERE playerId = ? AND itemId =?;";
        PreparedStatement stmt = connector.getConnection().prepareStatement(query);
        stmt.setInt(1, playerId);
        stmt.setInt(2, itemId);
        ResultSet res = stmt.executeQuery();
        if (res.next()) {
            int type = res.getInt("type");
            int roomId = res.getInt("roomId");
            int x = res.getInt("x");
            int y = res.getInt("y");
            Item item = new Item(itemId, type, roomId, x, y);
            stmt.close();
            return item;
        }
        throw new DontExistException(itemId, "Item");
    }

    public void removePlayer(int playerId) {
        try {
            String query = "DELETE FROM players WHERE playerId =?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.executeUpdate();

            query = "DELETE FROM monster WHERE playerId=?;";
            stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.executeUpdate();

            query = "DELETE FROM items WHERE playerId=?;";
            stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Item> getRoomItems(int playerId, int roomId) {

        ArrayList<Item> temp = new ArrayList();

        try {
            String query = "SELECT * FROM items WHERE playerId = ? AND roomId =?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.setInt(2, roomId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int itemId = res.getInt("itemId");
                int type = res.getInt("type");
                int x = res.getInt("x");
                int y = res.getInt("y");

                temp.add(new Item(itemId, type, roomId, x, y));
            }
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return temp;
    }

    public void copyItems(int playerId) {
        try {
            String query = "INSERT INTO items (playerId, itemId, type, x, y, roomId) "
                    + "SELECT ?, itemId, type, x, y, roomId FROM items WHERE playerId=0;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void copyMonsters(int playerId) {
        try {
            String query = "INSERT INTO monster (playerId, id, type, roomId, health, attack, x, y) "
                    + "SELECT ?, id, type, roomId, health, attack, x, y FROM monster WHERE playerId=0;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeItem(int playerId, int itemId) {
        try {
            String query = "DELETE FROM items WHERE playerId=? AND itemId =?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.setInt(2, itemId);
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Monster> getRoomMonsters(int playerId, int roomId) {
        ArrayList<Monster> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM monster WHERE playerId=? AND roomId=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, playerId);
            stmt.setInt(2, roomId);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                int type = res.getInt("type");
                int health = res.getInt("health");
                int attack = res.getInt("attack");
                int x = res.getInt("x");
                int y = res.getInt("y");
                list.add(new Monster(playerId, id, type, roomId, health, attack, x, y));
            }
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    public Monster getMonster(int playerId, int id) throws SQLException, DontExistException {

        String query = "SELECT * FROM monster WHERE playerId=? AND id=?;";
        PreparedStatement stmt = connector.getConnection().prepareStatement(query);
        stmt.setInt(1, playerId);
        stmt.setInt(2, id);
        ResultSet res = stmt.executeQuery();
        Monster monster;
        if (res.next()) {
            int type = res.getInt("type");
            int roomId = res.getInt("roomId");
            int health = res.getInt("health");
            int attack = res.getInt("attack");
            int x = res.getInt("x");
            int y = res.getInt("y");
            monster = new Monster(playerId, id, type, roomId, health, attack, x, y);
            stmt.close();
            return monster;
        }
        stmt.close();
        throw new DontExistException(id, "Monster");
    }

    public void updateMonster(Monster monster) {
        try {
            String query = "UPDATE monster SET roomId=?, health = ?, attack =? WHERE playerId=? AND id=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, monster.getRoomId());
            stmt.setInt(2, monster.getHealth());
            stmt.setInt(3, monster.getAttack());
            stmt.setInt(4, monster.getPlayerId());
            stmt.setInt(5, monster.getId());
            stmt.executeUpdate();
            stmt.close();
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
            stmt.close();
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
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeMonster(Monster monster) {
        try {
            String query = "DELETE FROM monster WHERE playerId=? AND id=?;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setInt(1, monster.getPlayerId());
            stmt.setInt(2, monster.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addScore(Player player) {
        try {
            String query = "INSERT INTO highscore VALUES (?, ?)";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getGold());
            stmt.executeUpdate();
            stmt.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Highscore> getHighscore() {
        try {
            ArrayList<Highscore> highscore = new ArrayList<>();
            String query = "SELECT * FROM highscore ORDER BY score DESC;";
            PreparedStatement stmt = connector.getConnection().prepareStatement(query);
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int score = res.getInt("score");
                String name = res.getString("name");
                Highscore thisScore = new Highscore(score, name);
                highscore.add(thisScore);
            }
            stmt.close();
            return highscore;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
