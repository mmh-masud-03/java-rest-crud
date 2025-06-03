/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.users.dao;

import core.db.DBConnection;
import features.users.dto.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macpc3
 */
public class UserDaoImpl implements UsersDao {
    // SQL statements as private constants

    private static final String SQL_INSERT
            = "INSERT INTO APPS_PRACTISE (USER_ID, NAME, EMAIL, DOB, CREATE_DATE, CREATE_BY) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE
            = "UPDATE APPS_PRACTISE SET NAME = ?, EMAIL = ?, DOB = ?, UPDATE_DATE = ?, UPDATE_BY = ? "
            + "WHERE USER_ID = ?";

    private static final String SQL_DELETE
            = "DELETE FROM APPS_PRACTISE WHERE USER_ID = ?";

    private static final String SQL_FIND_BY_ID
            = "SELECT USER_ID, NAME, EMAIL, DOB, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY "
            + "FROM APPS_PRACTISE WHERE USER_ID = ?";

    private static final String SQL_FIND_ALL
            = "SELECT USER_ID, NAME, EMAIL, DOB, CREATE_DATE, CREATE_BY, UPDATE_DATE, UPDATE_BY "
            + "FROM APPS_PRACTISE ORDER BY CREATE_DATE DESC";

    @Override
    public int insert(UserDto user) throws Exception {

        try ( Connection conn = new DBConnection().getOracleConnection();  PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, user.getUSER_ID());
            stmt.setString(2, user.getNAME());
            stmt.setString(3, user.getEMAIL());
            stmt.setString(4, user.getDOB());
            stmt.setString(5, user.getCREATE_DATE());
            stmt.setString(6, user.getCREATE_BY());

            return stmt.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error inserting data" + e.toString());
        }

        return 0;
    }

    @Override
    public UserDto findById(String userId) throws Exception {
        UserDto user = null;
        try ( Connection conn = new DBConnection().getOracleConnection();  PreparedStatement stmt = conn.prepareStatement(SQL_FIND_BY_ID)) {
            stmt.setString(1, userId);

            try ( ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new UserDto();
                    user.setUSER_ID(rs.getString("USER_ID"));
                    user.setNAME(rs.getString("NAME"));
                    user.setEMAIL(rs.getString("EMAIL"));
                    user.setDOB(rs.getString("DOB"));
                    user.setCREATE_DATE(rs.getString("CREATE_DATE"));
                    user.setCREATE_BY(rs.getString("CREATE_BY"));
                }
            }

        } catch (Exception e) {
            System.out.println("Error finding user" + e.toString());

        }
        return user;
    }

    @Override
    public List<UserDto> findAll() throws Exception {
        List<UserDto> users = new ArrayList<>();

        try ( Connection conn = new DBConnection().getOracleConnection();  PreparedStatement stmt = conn.prepareStatement(SQL_FIND_ALL);  ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                UserDto user = new UserDto();
                user.setUSER_ID(rs.getString("USER_ID"));
                user.setNAME(rs.getString("NAME"));
                user.setEMAIL(rs.getString("EMAIL"));
                user.setDOB(rs.getString("DOB"));
                user.setCREATE_DATE(rs.getString("CREATE_DATE"));
                user.setCREATE_BY(rs.getString("CREATE_BY"));

                users.add(user);
            }
        } catch (Exception e) {

            System.out.println("Error finding users" + e.toString());

        }
        return users;
    }

    @Override
    public int update(UserDto user) throws Exception {

        try ( Connection conn = new DBConnection().getOracleConnection();  PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE);  ResultSet rs = stmt.executeQuery();) {
            stmt.setString(1, user.getNAME());
            stmt.setString(2, user.getEMAIL());
            // Handle Date format correctly
            if (user.getDOB() != null) {
                stmt.setString(3, user.getDOB());
            } else {
                stmt.setNull(3, java.sql.Types.CHAR);
            }
            

            // WHERE clause parameter
            stmt.setString(4, user.getUSER_ID());
                    return stmt.executeUpdate();


        } catch (Exception e) {

            System.out.println("Error finding users" + e.toString());

        }
      return 0;
    }
    
    @Override
    public int delete(String id) throws Exception{
    return 0;
    }


}
