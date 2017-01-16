/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author MartinLodahl
 */
public class ItemType {
    
    int stat;
    int modifier;
    String note;

    public ItemType(int stat, int modifier, String note) {
        this.stat = stat;
        this.modifier = modifier;
        this.note = note;
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
            
    
}
