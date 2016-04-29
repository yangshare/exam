package org.mmxbb.exam.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class EditAdminPwdAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
    String logon = null;
    String passwordOld = null;
    String passwordNew = null;
    String passwordNew2 = null;
    LogonBean logonBean = null;
    UserDatabase uDB = null;
    passwordOld = httpServletRequest.getParameter("passwordOld");
    passwordNew = httpServletRequest.getParameter("passwordNew");
    passwordNew2 = httpServletRequest.getParameter("passwordNew2");

    HttpSession session = httpServletRequest.getSession();
    if ( (session != null) && (session.getAttribute("logon") != null)) {
      logonBean = new LogonBean();
      logonBean = (LogonBean) session.getAttribute("logon");
      logon = logonBean.getLogon();
      if (passwordNew.equals(passwordNew2)) {
        try {
          uDB = new UserDatabase();
          uDB.update(logon, passwordOld, passwordNew);
        } catch (Exception ex) {
          return actionMapping.findForward("error");
        }
      }
    } else {
      return actionMapping.findForward("logon");
    }

    return actionMapping.findForward("successsaveeditpwd");
  }
}
