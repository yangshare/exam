package org.mmxbb.exam.business.program;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.ExaminationPaperDAO;
import org.mmxbb.exam.util.Selector;
import org.mmxbb.exam.util.Transformer;


public class SearchAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest request,
                               HttpServletResponse response) {
    /**@todo: complete the business logic here, this is just a skeleton.*/
    SearchActionForm searchForm = (SearchActionForm) actionForm;

    //for display the result
    ArrayList paperList = null;
    ExaminationPaperDAO eDao = null;
    paperList = new ArrayList();


    //initialize select
    Selector selector = null;
    ArrayList e_typeOpts = null;
    ArrayList e_stateOpts = null;
    ArrayList e_gradeOpts = null;

    try {
      selector = new Selector();
      e_typeOpts = selector.getOptions("e_type");
      e_stateOpts = selector.getOptions("e_state");
      e_gradeOpts = selector.getOptions("e_grade");
      selector.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    request.setAttribute("e_typeOpts", e_typeOpts);
    request.setAttribute("e_stateOpts", e_stateOpts);
    request.setAttribute("e_gradeOpts", e_gradeOpts);
    String state = null;
    state = request.getParameter("state");
    if (state != null) {
      searchForm.reset(actionMapping, request);
    }

    //updateE_state where current date has gone beyond e_end
    eDao = new ExaminationPaperDAO();
    eDao.updateE_stateUnvalidateExamPaper();

    eDao = new ExaminationPaperDAO();
    eDao.setLength(15);

    int ipage; //present page
    try {
      String page = request.getParameter("page"); //requested page
      ipage = java.lang.Integer.parseInt(page, 10);
    } catch (Exception e) {
      ipage = searchForm.getPage();
    }
    if (ipage < 1) {
      ipage = 1;
    }
    String jspName = null;
    if (! (request.getParameter("jsp") == null)) {
      jspName = request.getParameter("jsp").toString();
    } else if (! (request.getAttribute("jsp") == null)) {
      jspName = request.getAttribute("jsp").toString();
    }
    try {
      if (jspName.equals("e_grademain")) {
        Transformer transformer = new Transformer();
        String e_grade = transformer.valueToId("ÊÖ¹¤");
        searchForm.setE_grade(e_grade);

        paperList = (ArrayList) eDao.getSearchCanGrade(searchForm, ipage);
      }
      else if(jspName.equals("e_distribute")){
        paperList = (ArrayList) eDao.getValidateExaminationPaper(searchForm, ipage);
      }
      else {
        paperList = (ArrayList) eDao.getSearch(searchForm, ipage);
      }
    } catch (SQLException ex1) {
      ex1.printStackTrace();
    }
    String condistr = eDao.getConditionStr() + "&jsp=" + jspName;
    eDao.setConditionStr(condistr);

    String pagestr = eDao.getPagestr(ipage);
    searchForm.setPagestr(pagestr);//set pagestr
    searchForm.setPage(ipage);

    request.setAttribute("paperList", paperList);//set paperList

    if (jspName.equals("e_grademain")) {
      /* Transformer transformer = new Transformer();
                String e_state = transformer.valueToId("å·²ç»“æ?");
                transformer = new Transformer();
                String e_grade = transformer.valueToId("æ‰‹å·¥");
                searchForm.setE_state(e_state);
                searchForm.setE_grade(e_grade);*/
      return actionMapping.findForward("grade_main");

    }
    if (jspName.equals("e_distribute")) {
      return (actionMapping.findForward("e_distribute"));
    }
    if (jspName.equals("e_manage")) {
      return (actionMapping.findForward("e_manage"));
    }
    if (jspName.equals("e_list")) {
      return (actionMapping.findForward("e_list"));
    }

    return (actionMapping.findForward("error"));

    //throw new java.lang.UnsupportedOperationException("Method perform() not yet implemented.");
  }
}
