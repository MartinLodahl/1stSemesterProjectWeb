package database;

import database.ICharacter;

public class Player implements ICharacter
{
    private String name;
    private int health,roomId,playerId,attackDmg;
    
    public Player (String name){
        this.name = name;
        this.setHealth(100);
    }

    public Player(String name, int health, int roomId, int playerId) {
        this.name = name;
        this.health = health;
        this.roomId = roomId;
        this.playerId = playerId;
    }
       public Player(String name, int health,int attack, int roomId, int playerId) {
        this.name = name;
        this.health = health;
        this.attackDmg = attack;
        this.roomId = roomId;
        this.playerId = playerId;
    }

   

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public int getHealth()
    {
        return health;
    }

    @Override
    public void setHealth(int health)
    {
        this.health = health;
    }

    @Override
    public void setRoomId(int roomId)
    {
        this.roomId=roomId;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", health=" + health + ", roomId=" + roomId + ", playerId=" + playerId + ", attackDmg=" + attackDmg + '}';
    }

    @Override
    public int getRoomId()
    {
        return roomId;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    @Override
    public void setAttackDmg(int attackDmg)
    {
        this.attackDmg=attackDmg;
    }

    @Override
    public int getAttackDmg()
    {
        return attackDmg;
    }
    
}

