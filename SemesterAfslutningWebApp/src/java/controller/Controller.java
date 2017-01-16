package controller;

import DataBase.DAO;
import DataBase.DBConnector;
import DataBase.Monster;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public void fight(Monster monster, Player player){
    player.setHealth(monster.getAttack());
    monster.setHealth(player.getAttackDmg());
    
    dao.updateMonster(monster);
    dao.updateUser(player);
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
