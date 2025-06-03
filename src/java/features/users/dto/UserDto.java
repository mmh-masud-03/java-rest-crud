/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package features.users.dto;

/**
 *
 * @author macpc3
 */
public class UserDto {
    public String USER_ID, NAME, EMAIL, DOB, CREATE_DATE, CREATE_BY,UPDATE_DATE,UPDATE_BY;

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getCREATE_DATE() {
        return CREATE_DATE;
    }

    public void setCREATE_DATE(String CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    public String getCREATE_BY() {
        return CREATE_BY;
    }

    public void setCREATE_BY(String CREATE_BY) {
        this.CREATE_BY = CREATE_BY;
    }
   public String getUPDATE_DATE() { return UPDATE_DATE; }
    public void setUPDATE_DATE(String UPDATE_DATE) { this.UPDATE_DATE = UPDATE_DATE; }
    public String getUPDATE_BY() { return UPDATE_BY; }
    public void setUPDATE_BY(String UPDATE_BY) { this.UPDATE_BY = UPDATE_BY; }
    @Override
    public String toString() {
        return "UserDto{" + "USER_ID=" + USER_ID + ", NAME=" + NAME + ", EMAIL=" + EMAIL + ", DOB=" + DOB + ", CREATE_DATE=" + CREATE_DATE + ", CREATE_BY=" + CREATE_BY + '}';
    }
    
}
