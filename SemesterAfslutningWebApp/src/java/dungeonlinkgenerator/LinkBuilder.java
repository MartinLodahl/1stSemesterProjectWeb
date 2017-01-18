package dungeonlinkgenerator;

/**
 @author Nazzyc
 */
public class LinkBuilder
{

    private StringBuilder sb;

    public LinkBuilder()
    {
    }

    public String create()
    {
        sb = new StringBuilder();
        sb.append("INSERT INTO link(room_id, direction, goto) VALUES\n");

        sb.append("/*Horizontal*/\n");

        int[] c11 = {11, 21, 31};
        int[] c12 = {51, 61, 71};

        int[] c21 = {22, 32, 42, 52};
        int[] c22 = {62, 72};

        int[] c31 = {13, 23, 33};
        int[] c32 = {43, 53};

        int[] c41 = {14, 24, 34, 44};
        int[] c42 = {54, 64};

        int[] c51 = {35, 45, 55};
        int[] c52 = {65, 75};

        int[][] c = {c11, c12, c21, c22, c31, c32, c41, c42, c51, c52};
        for(int[] i : c) {
            doubleConnectRoomsWE(i);
        }

        sb.append("/*Vertical*/\n");

        int[] x11 = {11, 12, 13};
        int[] x12 = {14, 15};

        int[] x21 = {24, 25};

        int[] x31 = {31, 32, 33, 34, 35};

        int[] x41 = {41, 42};
        int[] x42 = {44, 45};

        int[] x51 = {52, 53, 54};

        int[] x61 = {62, 63, 64, 65};

        int[] x71 = {71, 72, 73, 74};

        int[][] x = {x11,x12,x21,x31,x41,x42,x51,x61,x71};
        for(int[] i : x) {
            doubleConnectRoomsSN(i);
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append(";");

        return sb.toString();
    }

    private void doubleConnectRoomsSN(int[] connections)
    {
        for(int i = 0; i < connections.length - 1; i++) {
            doubleConnectRoomsSN(connections[i], connections[i + 1]);
        }
    }

    private void doubleConnectRoomsWE(int[] connections)
    {
        for(int i = 0; i < connections.length - 1; i++) {
            doubleConnectRoomsWE(connections[i], connections[i + 1]);
        }
    }

    private void doubleConnectRoomsSN(int s, int n)
    {
        sb.append("(").append(s).append(",'NORTH',").append(n).append("),");
        sb.append("(").append(n).append(",'SOUTH',").append(s).append("),\n");
    }

    private void doubleConnectRoomsWE(int w, int e)
    {
        sb.append("(").append(w).append(",'EAST',").append(e).append("),");
        sb.append("(").append(e).append(",'WEST',").append(w).append("),\n");
    }
}
