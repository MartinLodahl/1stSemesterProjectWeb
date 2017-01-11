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
    public void DAOtest() {
        ArrayList<Link> links = dao.getDirection(1);
        //east 2 & north 3
        assertEquals(links.get(0).getTo(), 2);
        assertEquals(links.get(1).getDirection(), "north");
                
    }

}
