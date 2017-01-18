/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import database.DAO;
import database.DBConnector;
import database.Monster;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import controller.Controller;
import database.Item;
import database.Link;
import database.Player;

/**
 *
 * @author MartinLodahl
 */
public class DAOtest {

    DBConnector connector;
    DAO dao;

    public DAOtest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        connector = new DBConnector();
        dao = new DAO(connector);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void getDirectionTest() {
        ArrayList<Link> links = dao.getDirection(1);
        //east 2 & north 3
        assertEquals(links.get(0).getTo(), 2);
        assertEquals(links.get(1).getDirection(), "WEST");

    }
//    @Test
//    public void getRoom(){
//        Room room = dao.getRoom(1);
//        Room room2 = dao.getRoom(2);
//        
//        assertEquals(room.getDescription(), "Der var en stor stor trold");
//        assertEquals(room2.getDescription(), "Per");
//    }

    //You need to make sure you've got 0 people in your database, players.
    @Test
    public void createUniquePlayerId() {
        Controller ctrl = new Controller(dao);
        dao.createUser(dao.createUniquePlayerId(), "bob", ctrl.createPlayerRoomId());
        int check = dao.createUniquePlayerId();
        assertEquals(check, 2);

    }

    @Test
    public void getRoomItems() {
        ArrayList<Item> list = dao.getRoomItems(0, 1);

        assertEquals(list.size(), 1);
        dao.removeItem(7);
        list = dao.getRoomItems(0, 1);
        assertEquals(list.size(), 0);
    }

    @Test
    public void updateMonster() {
        Monster monster = dao.getMonster(0, 1);
        monster.setHealth(3);
        dao.updateMonster(monster);

        assertEquals(monster.getHealth(), 3);

    }

    //You need atleast 1 player with the id of 1 (will get created in test for uniqueId), for this test to work.
    @Test
    public void removePlayer() {

        dao.removePlayer(1);

    }
}
