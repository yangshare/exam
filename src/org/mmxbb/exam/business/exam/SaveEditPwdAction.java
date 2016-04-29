package org.mmxbb.exam.business.exam;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.business.LogonBean;
import org.mmxbb.exam.dao.ExamineeDAO;



public class SaveEditPwdAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
    ExamineeDAO eDAO = null;
    String logon = null;
    String passwordOld = null;
    String passwordNew = null;
    String passwordNew2 = null;
    String passwordPre = null;

    LogonBean logonBean = new LogonBean();
    passwordOld = httpServletRequest.getParameter("passwordOld");
    passwordNew = httpServletRequest.getParameter("passwordNew");
    passwordNew2 = httpServletRequest.getParameter("passwordNew2");
    passwordPre = httpServletRequest.getParameter("httpServletRequest");

    HttpSession session = httpServletRequest.getSession();
    if ( (session != null) && (session.getAttribute("logon") != null)) {
      logonBean = (LogonBean) session.getAttribute("logon");
      logon = logonBean.getLogon();
      if (passwordNew.equals(passwordNew2)) {
        eDAO = new ExamineeDAO();
        try {
          eDAO.updatePwd(logon, passwordOld, passwordNew);
        } catch (SQLException ex) {
          return actionMapping.findForward("error");
        }
      }
    } else {
      return actionMapping.findForward("logon");
    }
    return actionMapping.findForward("sucesssaveexamineepwd");
  }
}
