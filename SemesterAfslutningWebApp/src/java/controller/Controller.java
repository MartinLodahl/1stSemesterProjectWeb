package controller;

import businessLogic.ItemType;
import businessLogic.Player;
import database.DAO;
import businessLogic.Monster;

public class Controller {

    private DAO dao;

    public Controller(DAO dao) {
        this.dao = dao;
    }

    public int createPlayerRoomId() {
        return 41;
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
        switch (itemType.getStat()) {
            case 1:
                player.setGold(player.getGold()+itemType.getModifier());
                break;
            case 2:
                player.setDefense(player.getDefense()+itemType.getModifier());
                break;
            case 3:
                player.setAttackDmg(player.getAttackDmg() + itemType.getModifier());
                break;
            default:
                break;
        }
        dao.updateUser(player);
        dao.removeItem(player.getId(), itemId);
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
