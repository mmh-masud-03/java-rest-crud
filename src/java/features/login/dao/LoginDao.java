/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.login.dao;

import features.login.dto.req.LoginReq;
import features.login.dto.res.LoginRes;
import features.login.dto.req.UserDetailsReq;
import features.login.dto.res.UserDetailsRes;

/**
 *
 * @author mobileapps1
 */
public class LoginDao {

    public LoginRes doLogin(LoginReq req) {
        LoginRes res = new LoginRes();
        try {

            if (req.getUserName().equals("Wasit")) {
                if (req.getPassword().equals("12345")) {
                    res.setToken("321"); 
                    res.setOutCode("0");
                    res.setOutMessage("Success!");
                } else {
                    res.setOutCode("1");
                    res.setOutMessage("Invalid Password!");
                }
            } else {
                res.setOutCode("1");
                res.setOutMessage("Invalid Usedr ID!");
            }
        } catch (Exception e) {
            res.setOutCode("1");
            res.setOutMessage("Login Problem!" + e.toString());
        }
        return res;
    }

    public UserDetailsRes userDetails(UserDetailsReq reqModel) {
      UserDetailsRes res = new UserDetailsRes();
      try {

            if (reqModel.getToken().equals("321")) {
                res.setName("Wasit");
                res.setMobile("987324089");
                res.setNid("4309858");
                res.setOutMessage("Success");
                res.setOutCode("0");
            } else {
                res.setOutCode("1");
                res.setOutMessage("Autheraization Faliled!");
            }
        } catch (Exception e) {
            res.setOutCode("1");
            res.setOutMessage("User Details Fetching problem!" + e.toString());
        }
      
      return res;
    }

    
    
 
}
