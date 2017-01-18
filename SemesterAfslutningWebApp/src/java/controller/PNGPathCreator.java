package controller;

import businessLogic.Link;
import java.util.ArrayList;

public class PNGPathCreator {

    public String createPath(ArrayList<Link> links) {
        LinkCollectionSort linksort = new LinkCollectionSort();
        StringBuilder sb = new StringBuilder();
        ArrayList<Link> temp = linksort.sortedByDirection(links);

        for (int i = 0; i < temp.size(); i++) {

            sb.append(temp.get(i).getDirection().substring(0, 1));
        }
        return sb.toString();
    }
}
