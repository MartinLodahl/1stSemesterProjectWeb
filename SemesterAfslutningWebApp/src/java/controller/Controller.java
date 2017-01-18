package controller;

import businessLogic.ItemType;
import businessLogic.Player;
import database.DAO;
import businessLogic.Monster;

public class Controller {

    DAO dao;

    public Controller(DAO dao) {
        this.dao = dao;
    }

    public int createPlayerRoomId() {
        return (int) Math.floor((Math.random() * 5) + 1);
    }
    
    public int damageCalculator (int incommingDamage, int defence){
        
        return (int) ((100.0/(100.0+defence))*incommingDamage);
    }

    public void fight(Monster monster, Player player) {
        
       int monsterDmg = damageCalculator(monster.getAttack(),player.getDefense());
       player.setHealth(player.getHealth() - monsterDmg);
        
        monster.setHealth(monster.getHealth() - player.getAttackDmg());
        dao.updateMonster(monster);
        dao.updateUser(player);

    }
    
    public void applyItem (Player player, int itemId){
       
                    int itemTypeInt = dao.getItem(player.getId(), itemId).getType();
                    ItemType itemType = dao.getItemType(itemTypeInt);
                    if(itemType.getStat() == 1){
                        player.setGold(player.getGold()+itemType.getModifier());
                    } else if (itemType.getStat() == 2){
                       player.setDefence(player.getDefense()+itemType.getModifier());
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
