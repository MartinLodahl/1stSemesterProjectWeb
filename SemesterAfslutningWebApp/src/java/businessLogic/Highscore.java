/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businessLogic;

/**
 *
 * @author MartinLodahl
 */
public class Highscore {

    private int score;
    private String name;

    public Highscore(int score, String name) {
        this.score = score;
        this.name = name;
    }
    
    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

}
