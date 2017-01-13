package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Pravien
 */
public class Player implements ICharacter
        
{
    private String name;
    private int health,roomId,playerId;
    
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
    public void setRoomID(int roomId)
    {
        this.roomId=roomId;
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
    
}

