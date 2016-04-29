package org.mmxbb.exam.business.exam;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.business.LogonBean;
import org.mmxbb.exam.dao.ExamineeInquiryDAO;


public class ExamineeInquiryAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
    ExamineeInquiryActionForm examineeInquiryActionForm = (
        ExamineeInquiryActionForm) actionForm;
    ExamineeInquiryDAO examineeInquiryDAO = new ExamineeInquiryDAO();
    ArrayList testerPaperSet = new ArrayList();
    
    String examinee_id = null;

    HttpSession s = httpServletRequest.getSession();
    LogonBean logon = (LogonBean) s.getAttribute("logon");
    examinee_id = logon.getLogon();

    
    examineeInquiryDAO.setLength(15);
    int ipage;
    try {
      String page = httpServletRequest.getParameter("page"); //requested page
      ipage = java.lang.Integer.parseInt(page, 10);

    } catch (Exception e) {

      ipage = examineeInquiryActionForm.getPage();
    }
    if (ipage < 1) {
      ipage = 1;
    }

    try {
      testerPaperSet = (ArrayList) examineeInquiryDAO.getSearch(examinee_id,
          ipage);

    } catch (Exception ex1) {
      ex1.printStackTrace();
    }
    String pagestr = examineeInquiryDAO.getPagestr(ipage);

      examineeInquiryActionForm.setPagestr(pagestr); 

    httpServletRequest.setAttribute("testerPaperSet", testerPaperSet);

    return actionMapping.findForward("success");


  }
}
