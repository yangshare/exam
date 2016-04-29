package org.mmxbb.exam.business;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import org.apache.struts.validator.*;
import org.mmxbb.exam.business.UserDatabase;


public class EditAdminPwdActionForm
    extends ValidatorForm {

  private String passwordNew;
  private String passwordNew2;
  private String passwordOld;
  private String passwordPre;
  private String isAdmin;

  public String getPasswordNew() {
    return passwordNew;
  }

  public void setPasswordNew(String passwordNew) {
    this.passwordNew = passwordNew;
  }

  public String getPasswordNew2() {
    return passwordNew2;
  }

  public void setPasswordNew2(String passwordNew2) {
    this.passwordNew2 = passwordNew2;
  }

  public String getPasswordOld() {
    return passwordOld;
  }

  public void setPasswordOld(String passwordOld) {
    this.passwordOld = passwordOld;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    ActionErrors errors = new ActionErrors();
    //get password pre!!
    if((isAdmin == null)||(isAdmin != "false")){
      UserDatabase ud = null;
    ud = new UserDatabase();
    this.passwordPre = ud.getPassword();
    }


    if((passwordOld != null)&&(passwordPre != null)&&!(passwordPre.equals(passwordOld))){
      errors.add("error.passwordPreUnmatchpasswordOld",new ActionError("error.passwordPreUnmatchpasswordOld"));
    }
    else if(!(passwordNew == null)&&!(passwordNew2 == null)){
      if(!(passwordNew.equals(passwordNew2))){
      errors.add("error.Password1UnmatchPassword2",new ActionError("error.Password1UnmatchPassword2"));
    }
    }
    /**@todo: finish this method, this is just the skeleton.*/
    return errors;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    passwordNew = null;
    passwordNew2 = null;
    passwordOld = null;
  }
  public String getPasswordPre() {
    return passwordPre;
  }
  public void setPasswordPre(String passwordPre) {
    this.passwordPre = passwordPre;
  }
  public String getIsAdmin() {
    return isAdmin;
  }
  public void setIsAdmin(String isAdmin) {
    this.isAdmin = isAdmin;
  }
}
