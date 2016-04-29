package org.mmxbb.exam.business;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class PortalLogonAction extends Action {
  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    UserDatabase ud = new UserDatabase();
    String adminName = ud.getUserName();
    String adminPassword = ud.getPassword();

    String username = httpServletRequest.getParameter("username");
    String password = httpServletRequest.getParameter("password");
   


    if((username != null)&&(username.equals(adminName))&&(password != null)&&(password.equals(adminPassword))){
    return actionMapping.findForward("admin");
    }else{
    return actionMapping.findForward("logoff");
    }
  }
}
