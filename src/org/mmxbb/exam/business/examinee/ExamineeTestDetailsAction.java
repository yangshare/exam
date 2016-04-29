package org.mmxbb.exam.business.examinee;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.ExamineeDAO;
import org.mmxbb.exam.dao.ExamineeTestDetailsDAO;

import java.sql.*;

public class ExamineeTestDetailsAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    ExamineeTestDetailsActionForm examineeTestDetailsActionForm = (
        ExamineeTestDetailsActionForm) actionForm;
    ExamineeTestDetailsDAO examineeTestDetailsDAO = new ExamineeTestDetailsDAO();
    ExamineeDAO examineeDAO = null;
    ArrayList testerPaperSet = new ArrayList();
    String examinee_id = new String();
    examinee_id = servletRequest.getParameter("examinee_id");

    
    examineeDAO = new ExamineeDAO();
    try {
      String examinee_name = examineeDAO.findByKey(examinee_id).getName();
      examineeTestDetailsActionForm.setExaminee_name(examinee_name);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

   
    examineeTestDetailsDAO.setLength(15);
    int ipage; 
    try {
      String page = servletRequest.getParameter("page"); 
      ipage = java.lang.Integer.parseInt(page, 10);
    } catch (Exception e) {
      ipage = examineeTestDetailsActionForm.getPage();
    }
    if (ipage < 1) {
      ipage = 1;
    }

    try {
      testerPaperSet = (ArrayList) examineeTestDetailsDAO.getSearch(examinee_id,
          ipage);
    } catch (Exception ex1) {
      ex1.printStackTrace();
    }
    String pagestr = examineeTestDetailsDAO.getPagestr(ipage);
    examineeTestDetailsActionForm.setPagestr(pagestr); 
    servletRequest.setAttribute("testerPaperSet", testerPaperSet);

    return actionMapping.findForward("success");
  }
}
