package org.mmxbb.exam.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.ExamineeDAO;



public class LogonAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {

    LogonForm logonForm = (LogonForm) actionForm;
    HttpSession session = httpServletRequest.getSession();
    ExamineeDAO eDAO =new ExamineeDAO();
    LogonBean logonBean = new LogonBean();
     if(eDAO.isUser(logonForm)){
    	logonBean.setLogon(logonForm.getUserName());
    	logonBean.setAuthority(false);
    	session.setAttribute("logon", logonBean);
    	return (actionMapping.findForward("ks"));
    }
    return (actionMapping.findForward("failure"));

  }
}
