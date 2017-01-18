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

        int[] c11 =
        {
            11, 21, 31
        };
        int[] c12 =
        {
            51, 61, 71
        };

        int[] c21 =
        {
            22, 32, 42, 52
        };
        int[] c22 =
        {
            62, 72
        };

        int[] c31 =
        {
            13, 23, 33
        };
        int[] c32 =
        {
            43, 53
        };

        int[] c41 =
        {
            14, 24, 34, 44
        };
        int[] c42 =
        {
            54, 64
        };

        int[] c51 =
        {
            35, 45, 55
        };
        int[] c52 =
        {
            65, 75
        };

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

        sb.append("/*Vertical*/\n");

        int[] x11 =
        {
            11, 12, 13
        };
        int[] x12 =
        {
            14, 15
        };

        int[] x21 =
        {
            24, 25
        };

        int[] x31 =
        {
            31, 32, 33, 34, 35
        };

        int[] x41 =
        {
            41, 42
        };
        int[] x42 =
        {
            44, 45
        };

        int[] x51 =
        {
            52, 53, 54
        };

        int[] x61 =
        {
            62, 63, 64, 65
        };

        int[] x71 =
        {
            71, 72, 73, 74
        };

        doubleConnectRoomsSN(x11);
        doubleConnectRoomsSN(x12);
        doubleConnectRoomsSN(x21);
        doubleConnectRoomsSN(x31);
        doubleConnectRoomsSN(x41);
        doubleConnectRoomsSN(x42);
        doubleConnectRoomsSN(x51);
        doubleConnectRoomsSN(x61);
        doubleConnectRoomsSNLast(x71);

        return sb.toString();
    }

    private void doubleConnectRoomsSN(int[] connections)
    {
        for(int i = 0; i < connections.length - 1; i++)
        {
            doubleConnectRoomsSN(connections[i], connections[i + 1]);

            sb.append(",\n");
        }
    }

    private void doubleConnectRoomsSNLast(int[] connections)
    {
        for(int i = 0; i < connections.length - 1; i++)
        {
            doubleConnectRoomsSN(connections[i], connections[i + 1]);

            if(i < connections.length - 2)
            {
                sb.append(",\n");
            }
            else
            {
                sb.append(";");
            }
        }
    }

    private void doubleConnectRoomsWE(int[] connections)
    {
        for(int i = 0; i < connections.length - 1; i++)
        {
            doubleConnectRoomsWE(connections[i], connections[i + 1]);

            sb.append(",\n");
        }
    }
    
    private void doubleConnectRoomsWELast(int[] connections)
    {
        for(int i = 0; i < connections.length - 1; i++)
        {
            doubleConnectRoomsWE(connections[i], connections[i + 1]);

            if(i < connections.length - 2)
            {
                sb.append(",\n");
            }
            else
            {
                sb.append(";");
            }
        }
    }

    private void doubleConnectRoomsSN(int s, int n)
    {
        sb.append("(").append(s).append(",'NORTH',").append(n).append("),");
        sb.append("(").append(n).append(",'SOUTH',").append(s).append(")");
    }

    private void doubleConnectRoomsWE(int w, int e)
    {
        sb.append("(").append(w).append(",'EAST',").append(e).append("),");
        sb.append("(").append(e).append(",'WEST',").append(w).append(")");
    }
}
