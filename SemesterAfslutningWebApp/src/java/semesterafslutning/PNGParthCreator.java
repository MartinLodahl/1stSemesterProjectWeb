/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semesterafslutning;

import java.util.ArrayList;

/**
 *
 * @author Pravien
 */
public class PNGParthCreator
{
    LinkCollectionSort linksort;
    
    public PNGParthCreator(){
        linksort = new LinkCollectionSort();
    }
    
    public String parthCreator(ArrayList<Link> link){
        StringBuilder sb = new StringBuilder();
        ArrayList<Link> temp = linksort.sortedByDirection(link);
        
        for (int i = 0; i < temp.size(); i++)
        {
            
            sb.append(temp.get(i).getDirection().substring(0,1));
            
        }
        
        return sb.toString();
    }
    
}
