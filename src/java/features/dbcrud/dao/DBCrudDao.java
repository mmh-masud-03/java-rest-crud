/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.dbcrud.dao;

import core.db.DBConnection;
import java.sql.Connection;
import java.sql.Statement;

/**
 *
 * @author mobileapps1
 */
public class DBCrudDao {

    public String insertData() {

        String res = "False";

        try ( Connection con = new DBConnection().getOracleConnection()) {
            String sql = "INSERT INTO APPS_PRACTISE (\n"
                    + "   USER_ID, NAME, EMAIL, DOB, CREATE_DATE, CREATE_BY\n"
                    + ") VALUES (\n"
                    + "   'U001', 'John Doe', 'john.doe@example.com', '1990-01-15', '2025-06-03', 'admin'\n"
                    + ")";

            Statement st = con.createStatement();
            boolean r = st.execute(sql);
            if(r){
                res = "Success";
            }

        } catch (Exception e) {
            System.out.println("Err: "+e.toString());
        }
        /*Connection con;
        try{
            
            con = new DBConnection().getOracleConnection();
            
        } catch (Exception e) {
        }finally{
            if(null != con){
                con.close();
            }
        }*/

        return res;
    }
}
