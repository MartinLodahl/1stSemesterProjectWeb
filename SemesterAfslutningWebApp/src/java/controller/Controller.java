/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DataBase.DAO;
import DataBase.DBConnector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pravien
 */
public class Controller
{

    DBConnector connector;
    DAO dao = new DAO(connector);

    public Controller()
    {
        try
        {
            this.connector = new DBConnector();
        } catch (Exception ex)
        {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int createPlayerRoomId()
    {
        System.out.println("room nr generaet");
        return (int) Math.floor((Math.random() * 5) + 1);
    }

//    public int createPlayer()
//    {
//        int uniqPlayerId = dao.createUniquePlayerId();
//        System.out.println("Talk fra unique"+uniqPlayerId);
//        int playRoomId = createPlayerRoomId();
//        dao.createUser(uniqPlayerId, "marton", playRoomId);
//        
//        return uniqPlayerId;
//    }
}