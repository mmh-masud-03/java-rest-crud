/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package features.login.api;

import features.login.dao.LoginDao;
import features.login.dto.req.LoginReq;
import features.login.dto.res.LoginRes;
import features.login.dto.req.UserDetailsReq;
import features.login.dto.res.UserDetailsRes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author mobileapps1
 */
@WebServlet(name = "LoginApi", urlPatterns = {"/v1/login"})
public class LoginApi extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Welcome to LoginApi</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginApi at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String reqCode = request.getParameter("reqCode");
        System.out.println("reqCode = " + reqCode);

        if (reqCode.equals("1")) {

            String userName = request.getParameter("userName");
            String password = request.getParameter("password");

            LoginDao dao = new LoginDao();
            LoginReq reqModel = new LoginReq();
            reqModel.setUserName(userName);
            reqModel.setPassword(password);
            
            System.out.println("LoginReq Mdoel: "+reqModel.toString());

            LoginRes res = dao.doLogin(reqModel);
            
            JSONObject json = new JSONObject();
            json.put("outCode", res.getOutCode());
            json.put("outMessage", res.getOutMessage());
            
            response.getWriter().write(json.toString()); 
            
        }else if (reqCode.equals("2")) {

            String token = request.getParameter("token");
            System.out.println("token = " + token);

            LoginDao dao = new LoginDao();
            
            UserDetailsReq reqModel = new UserDetailsReq();
            reqModel.setToken(token);
            

            UserDetailsRes res = dao.userDetails(reqModel);
            
            JSONObject json = new JSONObject();
            json.put("outCode", res.getOutCode());
            json.put("outMessage", res.getOutMessage());
            json.put("naem", res.getName());
            json.put("nid", res.getNid());
            json.put("address", "Dhak");
            json.put("mobile", res.getMobile());
            
            response.getWriter().write(json.toString()); 
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
