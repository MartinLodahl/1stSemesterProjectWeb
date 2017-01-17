package database;

public interface ICharacter {

    public void setAttackDmg(int attackDmg);

    public int getAttackDmg();

    public String getName();

    public void setName(String name);

    public int getHealth();

    public void setHealth(int health);

    public void setRoomId(int roomId);

    public int getRoomId();
}
