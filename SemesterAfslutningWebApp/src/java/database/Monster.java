package database;

public class Monster {
    private int id;
    private int type;
    private int roomId;
    private int health;
    private int attack;
    private int x;
    private int y;

    public Monster(int id, int type, int roomId, int health, int attack, int x, int y) {
        this.id = id;
        this.type = type;
        this.roomId = roomId;
        this.health = health;
        this.attack = attack;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Monster{" + "id=" + id + ", type=" + type + ", roomId=" + roomId + ", health=" + health + ", attack=" + attack + ", x=" + x + ", y=" + y + '}';
    }

    public int getId() {
        return id;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
        if (health <0){
            this.health = 0;
        }
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
