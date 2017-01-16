package controller;

import java.util.ArrayList;
import java.util.Comparator;

public class LinkCollectionSort
{
    public ArrayList<Link> sortedByDirection(ArrayList collectionOfLinks)
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
