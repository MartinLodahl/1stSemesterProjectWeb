/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DataBase.DAO;
import DataBase.DBConnector;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import semesterafslutning.Link;
import semesterafslutning.Room;

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
   /* @Test
    public void getDirectionTest() {
        ArrayList<Link> links = dao.getDirection(1);
        //east 2 & north 3
        assertEquals(links.get(0).getTo(), 2);
        assertEquals(links.get(1).getDirection(), "north");
                
    }
    @Test
    public void getRoom(){
        Room room = dao.getRoom(1);
        Room room2 = dao.getRoom(2);
        
        assertEquals(room.getDescription(), "Der var en stor stor trold");
        assertEquals(room2.getDescription(), "Per");
    }*/
  /*  @Test 
    public void checkUser(){
        boolean per = dao.checkUser("Per");
        boolean falsez = dao.checkUser("false");
        assertEquals(per, true);
        assertEquals(falsez, false);
        
    }
    */
    @Test
    public void sortDirec(){
        ArrayList<Link> list = dao.getDirection(1);
        assertEquals(list.get(0).getDirection(), "EAST");
        assertEquals(list.get(1).getDirection(), "WEST");
        
    }

}
