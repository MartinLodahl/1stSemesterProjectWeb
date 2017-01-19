/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import exceptions.DontExistException;
import database.DAO;
import database.DBConnector;
import businessLogic.Monster;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import controller.Controller;
import businessLogic.Item;
import businessLogic.Link;
import businessLogic.Player;
import java.sql.SQLException;

/**
 *
 * @author MartinLodahl
 */
public class DAOtest {

    private DBConnector connector;
    private DAO dao;

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
        ArrayList<Link> links = dao.getDirection(41);
        //east 2 & north 3
        assertEquals(links.get(0).getTo(), 42);
        assertEquals(links.get(0).getDirection(), "NORTH");

    }

    @Test
    public void createUniquePlayerId() {
        Controller ctrl = new Controller(dao);
        Player bob = dao.createUser(dao.createUniquePlayerId(), "bob", ctrl.createPlayerRoomId());
        int check = dao.createUniquePlayerId();
        assertEquals(check, bob.getId()+1);

    }
    
    @Test
    public void createPlayer() throws SQLException, DontExistException{
        Controller ctrl = new Controller(dao);
        int id  =dao.createUniquePlayerId();
        dao.createUser(id, "bob", ctrl.createPlayerRoomId());
        Player newPlayer = dao.getPlayer(id);
        assertEquals(newPlayer.getName(),"bob");
        assertEquals(newPlayer.getHealth(),100);
        assertEquals(newPlayer.getId(),id);
               
    }
    
    @Test
    public void applyItem() throws SQLException, DontExistException{
       Controller ctrl = new Controller(dao);
       int id  =dao.createUniquePlayerId();
       dao.createUser(id, "bob", ctrl.createPlayerRoomId());
       Player newPlayer = dao.getPlayer(id);
       dao.copyItems(id);
       dao.copyMonsters(id);
       ctrl.applyItem(newPlayer, 2);
       newPlayer = dao.getPlayer(id);
       assertEquals(newPlayer.getGold(),20);
       
    }
    
    @ Test(expected=DontExistException.class)
         public void testDontExistException () throws DontExistException, SQLException{
             
             Player newPlayer = dao.getPlayer(888);
        
    }
//
    
//

//
//    //You need atleast 1 player with the id of 1, for this test to work.
   
}
