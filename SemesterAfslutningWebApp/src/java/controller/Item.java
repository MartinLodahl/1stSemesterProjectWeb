package controller;

public class Item
{
 int itemId,x,y,roomId;
 String itemName;

    public Item(int itemId, int x, int y, int roomId, String itemName)
    {
        this.itemId = itemId;
        this.x = x;
        this.y = y;
        this.roomId = roomId;
        this.itemName = itemName;
    }

    public int getItemId()
    {
        return itemId;
    }

    public void setItemId(int itemId)
    {
        this.itemId = itemId;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getRoomId()
    {
        return roomId;
    }

    public void setRoomId(int roomId)
    {
        this.roomId = roomId;
    }

    public String getItemName()
    {
        return itemName;
    }

    public void setItemName(String itemName)
    {
        this.itemName = itemName;
    }
    
}
