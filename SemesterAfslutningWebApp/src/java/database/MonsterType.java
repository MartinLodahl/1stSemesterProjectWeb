package database;

public class MonsterType {
    private int type;
    private String picture;
    private String description;
    private int health;
    private int attack;

    public MonsterType(int type, String picture, String description, int health, int attack) {
        this.type = type;
        this.picture = picture;
        this.description = description;
        this.health = health;
        this.attack = attack;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }
}
