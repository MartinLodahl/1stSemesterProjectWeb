package controller;

public class ItemType {
    
    private int stat;
    private int modifier;
    private String note;
    private String picture;

    public ItemType(int stat, int modifier, String note, String picture) {
        this.stat = stat;
        this.modifier = modifier;
        this.note = note;
        this.picture = picture;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int modifier) {
        this.modifier = modifier;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
