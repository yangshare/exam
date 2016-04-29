package org.mmxbb.exam.business;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.ExamineeDAO;

public class AdminLogonAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {

    LogonForm logonForm = (LogonForm) actionForm;
    ExamineeDAO eDAO =new ExamineeDAO();
       if(eDAO.isAdminUser(logonForm)){
        	return (actionMapping.findForward("admin"));
    }
    return (actionMapping.findForward("failure"));

  }
}
