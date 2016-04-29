package org.mmxbb.exam.business;

import java.io.*;
import java.sql.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;
import org.mmxbb.exam.dao.*;


public class LogonForm
    extends ValidatorForm
    implements Serializable {
  private String userName;
  private String password;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
  }

public ActionErrors validate(ActionMapping actionMapping,
                                HttpServletRequest httpServletRequest){
  UserDatabase ud = new UserDatabase();
  ActionErrors errors = new ActionErrors();

  String adminUserName = ud.getUserName();

  if(((userName == null)||("".equals(userName)))&&((password == null)||("".equals(password)))){
    errors.add("user and password",new ActionError("error.required.userName.password"));
  }
  else if((userName == null)||("".equals(userName))){
    errors.add("userName",new ActionError("error.required.userName"));
  }
  else if((password == null)||("".equals(password))){
    errors.add("password",new ActionError("error.required.password"));
  }
  else if(adminUserName.equals(this.userName)){
    String adminPassword = null;
    adminPassword = ud.getPassword();
    if(!(adminPassword.equals(this.password))){
      errors.add("unvalidate adminPassword",new ActionError("error.uncorrectpassword"));
}
  }
    else if(!(adminUserName.equals(this.userName))){
      String examineePassword = null;
      try {
        ExamineeDAO examineeDAO = null;
        examineeDAO = new ExamineeDAO();
        examineePassword = examineeDAO.findByKey(userName).getPassword();
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
      if(examineePassword == null||!(examineePassword.equals(this.password))){
        errors.add("unvalidate examineePassword",new ActionError("error.uncorrectpassword"));
      }
    }

  return errors;
}

}
