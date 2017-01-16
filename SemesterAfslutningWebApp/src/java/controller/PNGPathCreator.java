package controller;

import java.util.ArrayList;

public class PNGPathCreator {

    LinkCollectionSort linksort;

    public PNGPathCreator() {
        linksort = new LinkCollectionSort();
    }

    public String pathCreator(ArrayList<Link> link) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Link> temp = linksort.sortedByDirection(link);

        for (int i = 0; i < temp.size(); i++) {

            sb.append(temp.get(i).getDirection().substring(0, 1));
        }
        return sb.toString();
    }

    public boolean ValidMove(String direction, ArrayList<Link> links) {
        for (int i = 0; i < links.size(); i++) {
            if (direction.equals(links.get(i).getDirection())) {
                return true;
            }
        }
        return false;
    }
}
