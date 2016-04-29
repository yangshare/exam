package org.mmxbb.exam.business.examinee;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.ExamineeDAO;
import org.mmxbb.exam.util.Selector;


public class ShowExamineeAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {

    ExamineeActionForm examineeActionForm = (ExamineeActionForm) actionForm;

   
    ArrayList examinees = null;
    ExamineeDAO examineeDAO = null;
    examinees = new ArrayList();
    examineeDAO = new ExamineeDAO();

   
    Selector selector = null;
    ArrayList organizationOpts = null;
    ArrayList post_indexOpts = null;
    ArrayList stateOpts = null;
    ArrayList sexOpts = null;
    ArrayList education_indexOpts = null;
    ArrayList operationOpts = null;

    try {
      selector = new Selector();
      organizationOpts = selector.getOptions("organization_id");
      post_indexOpts = selector.getOptions("post_index");
      stateOpts = selector.getOptions("state");
      sexOpts = selector.getOptions("sex");
      education_indexOpts = selector.getOptions("education_index");
      operationOpts = selector.getOptions("operation");
      selector.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    httpServletRequest.setAttribute("organizationOpts", organizationOpts);
    httpServletRequest.setAttribute("post_indexOpts", post_indexOpts);
    httpServletRequest.setAttribute("stateOpts", stateOpts);
    httpServletRequest.setAttribute("sexOpts", sexOpts);
    httpServletRequest.setAttribute("education_indexOpts", education_indexOpts);
    httpServletRequest.setAttribute("operationOpts", operationOpts);

  

    examineeDAO.setLength(100);

    int ipage; 
    try {
      String page = httpServletRequest.getParameter("page");
      ipage = java.lang.Integer.parseInt(page, 10);
    } catch (Exception e) {
      ipage = examineeActionForm.getPage();
    }
    if (ipage < 1) {
      ipage = 1;
    }

    try {
      examinees = (ArrayList) examineeDAO.getSearch(examineeActionForm, ipage);
    } catch (SQLException ex1) {
      ex1.printStackTrace();
    }

    String pagestr = examineeDAO.getPagestr(ipage);
    examineeActionForm.setPagestr(pagestr);

    httpServletRequest.setAttribute("examinees", examinees);

    return (actionMapping.findForward("success"));

  }
}
