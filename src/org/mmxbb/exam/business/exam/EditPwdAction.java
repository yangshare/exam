package org.mmxbb.exam.business.exam;

import org.apache.struts.action.*;
import org.mmxbb.exam.business.*;
import org.mmxbb.exam.dao.ExamineeDAO;

import javax.servlet.http.*;
import java.sql.*;

public class EditPwdAction extends Action {
  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    /**@todo: complete the business logic here, this is just a skeleton.*/
    EditAdminPwdActionForm editAdminPwdActionForm = (EditAdminPwdActionForm) actionForm;
    ExamineeDAO examineeDAO = null;
    String passwordPre = null;

    
    String examinee_id = null;
    HttpSession s = httpServletRequest.getSession();
    LogonBean logon = (LogonBean) s.getAttribute("logon");
    examinee_id = new String();
    examinee_id = logon.getLogon();
   
    passwordPre = new String();
    examineeDAO = new ExamineeDAO();
    try {
      passwordPre = examineeDAO.findByKey(examinee_id).getPassword();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
   
    String isAdmin = "false";

    editAdminPwdActionForm.setPasswordPre(passwordPre);
    editAdminPwdActionForm.setIsAdmin(isAdmin);
    return actionMapping.findForward("editpwd");
  }
}
