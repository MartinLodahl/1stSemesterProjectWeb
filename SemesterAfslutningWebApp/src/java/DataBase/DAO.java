/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author MartinLodahl
 */
public class DAO {
    
    public ArrayList<Double> getDirection(int currentRoom) {
        ArrayList<Double> list = new ArrayList<>();

        try {
            String query = "SELECT"+ currentRoom + "FROM link ;";
            Statement stmt = new DBConnector().getConnection().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                ;
                list.add( );
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
