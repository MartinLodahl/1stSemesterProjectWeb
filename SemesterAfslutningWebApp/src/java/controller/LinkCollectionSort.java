/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.Link;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Pravien
 */
public class LinkCollectionSort
{
      public ArrayList<Link> sortedByDirection(ArrayList collectionOfLinks )
    {

        collectionOfLinks.sort(linkComparator);
        return collectionOfLinks;
    }
    
    public static Comparator<Link> linkComparator
            = new Comparator<Link>()
    {

        @Override
        public int compare(Link link1, Link link2)
        {
            String linkdirec1 = link1.getDirection().toUpperCase();
            String linkdirec2 = link2.getDirection().toUpperCase();
            return linkdirec1.compareTo(linkdirec2);
        }

    };
    
   
    
    
}
