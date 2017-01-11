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
    
    public ArrayList<Double> getPriceValue(String frame) {
        ArrayList<Double> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM price WHERE frame='" + frame + "';";
            Statement stmt = new DBConnector().getConnection().createStatement();
            ResultSet res = stmt.executeQuery(query);
            while (res.next()) {
                Double FramePrice = Double.parseDouble(res.getString("FramePrice"));
                list.add(0, FramePrice);
                Double GlassPrice = Double.parseDouble(res.getString("GlassPrice"));
                list.add(1, GlassPrice);
            }
            return list;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
}
