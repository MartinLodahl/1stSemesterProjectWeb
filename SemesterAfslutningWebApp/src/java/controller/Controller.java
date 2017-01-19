package controller;

import exceptions.DontExistException;
import businessLogic.ItemType;
import businessLogic.Player;
import database.DAO;
import businessLogic.Monster;
import java.sql.SQLException;

public class Controller {

    private final DAO dao;

    public Controller(DAO dao) {
        this.dao = dao;
    }

    public int createPlayerRoomId() {
        return 41;
    }

    public int damageCalculator(int incommingDamage, int defence) {

        return (int) Math.ceil((50.0 / (50.0 + defence)) * incommingDamage);
    }

    public void fight(Monster monster, Player player) {

        double dicePlayer = Math.random();
        double diceMonster = Math.random();

        if (dicePlayer > 0.2) {
            monster.setHealth(monster.getHealth() - player.getAttackDmg());
        } else if (dicePlayer < 0.2 && dicePlayer > 0.1) {
        } else if (dicePlayer < 0.1) {
            monster.setHealth(monster.getHealth() - player.getAttackDmg() * 3);
        }
        if (diceMonster > 0.2) {
            int monsterDmg = damageCalculator(monster.getAttack(), player.getDefense());
            player.setHealth(player.getHealth() - monsterDmg);
        } else if (diceMonster < 0.2 && diceMonster > 0.1) {

        } else if (diceMonster < 0.1) {
            int monsterDmg = damageCalculator(monster.getAttack(), player.getDefense());
            player.setHealth(player.getHealth() - monsterDmg*3);
        }

        dao.updateMonster(monster);
        dao.updateUser(player);

    }

    public int getDropType() {
        int type = 1 + (int)Math.floor(Math.random() * 8);
        return type;
    }
    
    public void applyItem(Player player, int itemId) throws SQLException, DontExistException {
        int itemTypeInt = dao.getItem(player.getId(), itemId).getType();
        ItemType itemType = dao.getItemType(itemTypeInt);
        switch (itemType.getStat()) {
            case 1:
                player.setGold(player.getGold() + itemType.getModifier());
                break;
            case 2:
                player.setAttackDmg(player.getAttackDmg() + itemType.getModifier());
                break;
            case 3:
                player.setDefense(player.getDefense() + itemType.getModifier());
                break;
            case 4:
                player.setHealth(player.getHealth() + itemType.getModifier());
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
