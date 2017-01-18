package dungeonlinkgenerator;

/*
 @author UlrikB
 */
public class DungeonOnlineGenerator
{

    public static void main(String[] args)
    {
        System.out.println("/*Horizontal*/");
        
        int[] c11 = {11,21,31};
        int[] c12 = {51,61,71};
        
        int[] c21 = {22,32,42,52};
        int[] c22 = {62,72};
        
        int[] c31 = {13,23,33};
        int[] c32 = {43,53};
        
        int[] c41 = {14,24,34,44};
        int[] c42 = {54,64};
        
        int[] c51 = {35,45,55};
        int[] c52 = {65,75};
        
        doubleConnectRoomsWE(c11);
        doubleConnectRoomsWE(c12);
        doubleConnectRoomsWE(c21);
        doubleConnectRoomsWE(c22);
        doubleConnectRoomsWE(c31);
        doubleConnectRoomsWE(c32);
        doubleConnectRoomsWE(c41);
        doubleConnectRoomsWE(c42);
        doubleConnectRoomsWE(c51);
        doubleConnectRoomsWE(c52);
        
        System.out.println("/*Vertical*/");
        
        int[] x11 = {11,12,13};
        int[] x12 = {14,15};
        
        int[] x21 = {24,25};
        
        int[] x31 = {31,32,33,34,35};
        
        int[] x41 = {41,42};
        int[] x42 = {44,45};
        
        int[] x51 = {52,53,54};
        
        int[] x61 = {62,63,64,65};
        
        int[] x71 = {71,72,73,74};
        
        doubleConnectRoomsSN(x11);
        doubleConnectRoomsSN(x12);
        doubleConnectRoomsSN(x21);
        doubleConnectRoomsSN(x31);
        doubleConnectRoomsSN(x41);
        doubleConnectRoomsSN(x42);
        doubleConnectRoomsSN(x51);
        doubleConnectRoomsSN(x61);
        doubleConnectRoomsSN(x71);
    }

    private static void doubleConnectRoomsSN(int[] connections)
    {
        for(int i = 0; i < connections.length-1; i++)
        {
            doubleConnectRoomsSN(connections[i], connections[i+1]);
        }
    }
    
    private static void doubleConnectRoomsWE(int[] connections)
    {
        for(int i = 0; i < connections.length-1; i++)
        {
            doubleConnectRoomsWE(connections[i], connections[i+1]);
        }
    }

    private static void doubleConnectRoomsSN(int s, int n)
    {
        System.out.println("("+s+",'NORTH',"+n+"),");
        System.out.println("("+n+",'SOUTH',"+s+"),");
    }
    
    private static void doubleConnectRoomsWE(int w, int e)
    {
        System.out.println("("+w+",'EAST',"+e+"),");
        System.out.println("("+e+",'WEST',"+w+"),");
    }

}
