/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author mobileapps1
 */
public class DBConnection {

    public Connection getOracleConnection() {
        //jdbc:oracle:thin:@ip:prot:db_service <19c
        //jdbc:oracle:thin:ip:prot/db_service 19c+
        String url = "jdbc:oracle:thin:@192.183.155.12:1535/ibank";
        String user = "IBANKING";
        String pass = "ibanking";
        
        Connection con = null;
        try {
            //Register
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            //Statblish conneciton
            con  = DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            System.out.println("DB Conneciton Problem: " + e.toString());
        }
        return con;
    }
    
    public static void main(String[] args) {
        new DBConnection().getOracleConnection();
    }
}
