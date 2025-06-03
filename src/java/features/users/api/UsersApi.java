package features.users.api;
import core.utility.JsonDecoder;
import features.users.dao.UserDaoImpl;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import features.users.dao.UserDao;
import features.users.dto.UserDto;
import java.io.BufferedReader;
import java.util.List;
import java.util.UUID;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author macpc3
 */
@WebServlet(name = "UsersApi", urlPatterns = {"/v1/users", "/v1/users/*"})
public class UsersApi extends HttpServlet {
    
    private UserDao dao;
    
    public UsersApi(){
        this.dao = new UserDaoImpl();
    }

    /**
     * Helper method to read request body as string
     */
    private String getRequestBody(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject result = new JSONObject();
        
        try {
            String pathInfo = request.getPathInfo();
            if(pathInfo != null && pathInfo.length() > 1){
                // Return a single user info
                String userId = pathInfo.substring(1);
                UserDto user = dao.findById(userId);
                if (user != null) {
                    JSONObject userJson = new JSONObject();
                    userJson.put("id", user.getUSER_ID());
                    userJson.put("name", user.getNAME());
                    userJson.put("email", user.getEMAIL());
                    userJson.put("dob", user.getDOB());
                    userJson.put("createDate", user.getCREATE_DATE());
                    userJson.put("createBy", user.getCREATE_BY());
                    
                    result.put("status", "success");
                    result.put("data", userJson);
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    result.put("status", "error");
                    result.put("message", "User not found");
                }
            } else {
                // Return all users
                List<UserDto> users = dao.findAll();
                JSONArray usersArray = new JSONArray();
                
                for (UserDto user : users) {
                    JSONObject userJson = new JSONObject();
                    userJson.put("id", user.getUSER_ID());
                    userJson.put("name", user.getNAME());
                    userJson.put("email", user.getEMAIL());
                    userJson.put("dob", user.getDOB());
                    userJson.put("createDate", user.getCREATE_DATE());
                    userJson.put("createBy", user.getCREATE_BY());
                    usersArray.add(userJson);
                }
                
                result.put("status", "success");
                result.put("data", usersArray);
            }
            
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result.put("status", "error");
            result.put("message", "Internal server error: " + e.getMessage());
            e.printStackTrace();
        }
        
        response.getWriter().write(result.toString());
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        JSONObject result = new JSONObject();
        
        try {
            String requestBody = getRequestBody(request);
            JSONObject jsonData = JsonDecoder.getJson(requestBody);
            
            // Validate required fields
            String name = JsonDecoder.getJsonValue(jsonData, "name");
            String email = JsonDecoder.getJsonValue(jsonData, "email");
            String dob = JsonDecoder.getJsonValue(jsonData, "dob");
            
            if (name.isEmpty() || email.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                result.put("status", "error");
                result.put("message", "Name and email are required fields");
                response.getWriter().write(result.toString());
                return;
            }
            
            // Create new user
            UserDto user = new UserDto();
            user.setUSER_ID(UUID.randomUUID().toString());
            user.setNAME(name);
            user.setEMAIL(email);
            user.setDOB(dob.isEmpty() ? null : dob);
            user.setCREATE_DATE(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            user.setCREATE_BY("SYSTEM"); // You might want to get this from session or request
            
            int rowsAffected = dao.insert(user);
            
            if (rowsAffected > 0) {
                JSONObject userJson = new JSONObject();
                userJson.put("id", user.getUSER_ID());
                userJson.put("name", user.getNAME());
                userJson.put("email", user.getEMAIL());
                userJson.put("dob", user.getDOB());
                userJson.put("createDate", user.getCREATE_DATE());
                userJson.put("createBy", user.getCREATE_BY());
                
                response.setStatus(HttpServletResponse.SC_CREATED);
                result.put("status", "success");
                result.put("message", "User created successfully");
                result.put("data", userJson);
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                result.put("status", "error");
                result.put("message", "Failed to create user");
            }
            
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result.put("status", "error");
            result.put("message", "Internal server error: " + e.getMessage());
            e.printStackTrace();
        }
        
        response.getWriter().write(result.toString());
    }

    /**
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Users API for managing user data";
    }
}