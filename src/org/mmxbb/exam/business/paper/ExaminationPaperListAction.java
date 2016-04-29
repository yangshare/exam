package org.mmxbb.exam.business.paper;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.business.LogonBean;
import org.mmxbb.exam.dao.ExaminationPaperDAO;


public class ExaminationPaperListAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    AddExaminationPaperForm addExaminationPaperForm = (AddExaminationPaperForm)
        actionForm;
    ArrayList examinationPaperSet = new ArrayList();
    ExaminationPaperDAO examinationPaperDAO = null;

    LogonBean logonBean = new LogonBean();
    HttpSession session = servletRequest.getSession();
    logonBean = (LogonBean) session.getAttribute("logon");
    String examinee_id = logonBean.getLogon();
    if ( (null == examinee_id) || ("".equals(examinee_id))) {
      return actionMapping.findForward("error");
    }

    
    examinationPaperDAO = new ExaminationPaperDAO();
    examinationPaperDAO.setLength(15);
    int ipage = 0;
    try {
      String page = servletRequest.getParameter("page"); 
      ipage = java.lang.Integer.parseInt(page, 10);
    } catch (Exception e) {
      ipage = addExaminationPaperForm.getPage();
    }
    if (ipage < 1) {
      ipage = 1;
    }

    try {
      examinationPaperSet = (ArrayList) examinationPaperDAO.getSearch(
          examinee_id, ipage);
    } catch (Exception ex1) {
      ex1.printStackTrace();
    }

    String pagestr = examinationPaperDAO.getPagestr_(ipage);
    addExaminationPaperForm.setPagestr(pagestr); 

    servletRequest.setAttribute("examinationPaperSet", examinationPaperSet);

    return actionMapping.findForward("attendtest");
  }
}
