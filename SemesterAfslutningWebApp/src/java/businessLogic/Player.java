package businessLogic;

public class Player implements ICharacter
{
    private int id;
    private String name;
    private int health, roomId, attackDmg, gold, defense;
    
    public Player(int id, String name, int health,int attack,int defense, int roomId, int gold) {
        this.id = id;
        this.name = name;
        this.health = health;
        this.defense = defense;
        this.attackDmg = attack;
        this.roomId = roomId;
        this.gold = gold;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getDefense()
    {
        return defense;
    }

    public void setDefense(int defense)
    {
        this.defense = defense;
    }
    

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
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
        this.health = Math.min(Math.max(0, health), 100);
    }

    @Override
    public void setRoomId(int roomId)
    {
        this.roomId=roomId;
    }

    @Override
    public String toString() {
        return "Player{" + "name=" + name + ", health=" + health + ", roomId=" + roomId + ", id=" + id + ", attackDmg=" + attackDmg + '}';
    }

    @Override
    public int getRoomId()
    {
        return roomId;
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

