package controller;

import database.Player;
import database.DAO;
import database.DBConnector;
import database.Monster;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    DAO dao;

    public Controller(DAO dao) {
        this.dao = dao;
    }

    public int createPlayerRoomId() {
        return (int) Math.floor((Math.random() * 5) + 1);
    }
    
    public int damageCalculator (int incommingDamage, int defence){
        return (100/(100+defence))*incommingDamage;
    }

    public void fight(Monster monster, Player player) {
        
        
        
        player.setHealth(player.getHealth() - damageCalculator(monster.getAttack(),player.getDefense()));
        monster.setHealth(monster.getHealth() - player.getAttackDmg());
        dao.updateMonster(monster);
        dao.updateUser(player);

    }
    
    public void applyItem (Player player, int itemId){
       
                    int itemTypeInt = dao.getItemTypeInt(itemId);
                    ItemType itemType = dao.getItemType(itemTypeInt);
                    if(itemType.getStat() == 1){
                        player.setGold(player.getGold()+itemType.getModifier());
                    } else if (itemType.getStat() == 2){
                        player.setHealth(player.getHealth()+itemType.getModifier());
                    } else if (itemType.getStat()==3){
                        player.setAttackDmg(player.getAttackDmg() + itemType.getModifier());
                    }
                    dao.updateUser(player);
                    dao.removeItem(itemId);
    }

//    public int createPlayer()
//    {
//        int uniqPlayerId = dao.createUniquePlayerId();
//        int playRoomId = createPlayerRoomId();
//        dao.createUser(uniqPlayerId, "marton", playRoomId);
//        
//        return uniqPlayerId;
//    }
}
