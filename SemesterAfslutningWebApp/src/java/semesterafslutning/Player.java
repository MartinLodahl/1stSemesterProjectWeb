package semesterafslutning;

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
    private int health = 80;
    
    public Player (String name){
        this.name = name;
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
}
