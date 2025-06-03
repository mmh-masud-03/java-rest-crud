package features.users.dao;

import core.db.DBConnection;
import features.users.dto.UserDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author macpc3
 */
public class UserDaoImpl implements UserDao {
    // SQL statements as private constants
    private static final String SQL_INSERT
            = "INSERT INTO APPS_PRACTISE (USER_ID, NAME, EMAIL, DOB, CREATE_DATE, CREATE_BY) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE
            = "UPDATE APPS_PRACTISE SET NAME = ?, EMAIL = ?, DOB = ? "
            + "WHERE USER_ID = ?";

    private static final String SQL_DELETE
            = "DELETE FROM APPS_PRACTISE WHERE USER_ID = ?";

    private static final String SQL_FIND_BY_ID
            = "SELECT USER_ID, NAME, EMAIL, DOB, CREATE_DATE, CREATE_BY "
            + "FROM APPS_PRACTISE WHERE USER_ID = ?";

    private static final String SQL_FIND_ALL
            = "SELECT USER_ID, NAME, EMAIL, DOB, CREATE_DATE, CREATE_BY "
            + "FROM APPS_PRACTISE ORDER BY CREATE_DATE DESC";

    @Override
    public int insert(UserDto user) throws Exception {
        try (Connection conn = new DBConnection().getOracleConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            
            stmt.setString(1, user.getUSER_ID());
            stmt.setString(2, user.getNAME());
            stmt.setString(3, user.getEMAIL());
            stmt.setString(4, user.getDOB());
            stmt.setString(5, user.getCREATE_DATE());
            stmt.setString(6, user.getCREATE_BY());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting user: " + e.getMessage());
            throw new Exception("Failed to insert user", e);
        }
    }

    @Override
    public UserDto findById(String userId) throws Exception {
        UserDto user = null;
        try (Connection conn = new DBConnection().getOracleConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_FIND_BY_ID)) {
            
            stmt.setString(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
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
        } catch (SQLException e) {
            System.err.println("Error finding user by ID: " + e.getMessage());
            throw new Exception("Failed to find user by ID", e);
        }
        return user;
    }

    @Override
    public List<UserDto> findAll() throws Exception {
        List<UserDto> users = new ArrayList<>();

        try (Connection conn = new DBConnection().getOracleConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = stmt.executeQuery()) {
            
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
        } catch (SQLException e) {
            System.err.println("Error finding all users: " + e.getMessage());
            throw new Exception("Failed to find all users", e);
        }
        return users;
    }

    @Override
    public int update(UserDto user) throws Exception {
        try (Connection conn = new DBConnection().getOracleConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {
            
            stmt.setString(1, user.getNAME());
            stmt.setString(2, user.getEMAIL());
            stmt.setString(3, user.getDOB());
            stmt.setString(4, user.getUSER_ID()); // WHERE clause parameter

            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating user: " + e.getMessage());
            throw new Exception("Failed to update user", e);
        }
    }
    
    @Override
    public int delete(String userId) throws Exception {
        try (Connection conn = new DBConnection().getOracleConnection();
             PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {
            
            stmt.setString(1, userId);
            
            return stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
            throw new Exception("Failed to delete user", e);
        }
    }
}