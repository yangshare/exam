package org.mmxbb.exam.business.program;

import org.apache.struts.action.*;
import org.mmxbb.exam.dao.ExaminationPaperDAO;
import org.mmxbb.exam.dao.ExamineeDAO;
import org.mmxbb.exam.util.Selector;

import javax.servlet.http.*;
import java.util.ArrayList;
import java.sql.*;

public class InitRandomDistributeAction extends Action {
  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    RandomDistributeExamPaperForm randomDistributeExamPaperForm = (RandomDistributeExamPaperForm)actionForm;
    randomDistributeExamPaperForm.reset(actionMapping,httpServletRequest);
    //***************for examinee**************begin****************
    //for display the result
    ArrayList examinees = null;
    ExamineeDAO examineeDAO = null;

    //initialize select
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



    examineeDAO = new ExamineeDAO();
    //fen ye!
    /*examineeDAO.setLength(15);

    int ipage; //present page
    try {
      String page = httpServletRequest.getParameter("page"); //requested page
      ipage = java.lang.Integer.parseInt(page, 10);
    } catch (Exception e) {
      ipage = randomDistributeExamPaperForm.getPage();
    }
    if (ipage < 1) {
      ipage = 1;
    }
    */

    examinees = new ArrayList();
      try {
        examinees = (ArrayList) examineeDAO.getSearchWithoutPage(randomDistributeExamPaperForm);
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }


    //String pagestr = examineeDAO.getPagestr(ipage);
    //randomDistributeExamPaperForm.setPagestr(pagestr);

    httpServletRequest.setAttribute("examinees", examinees);
    //***************for examinee**************end*****************

    //***************for examinationPaper**************begin*****************
     //for display the result
   ArrayList paperList = null;
   ExaminationPaperDAO eDao = null;
   paperList = new ArrayList();

   //initialize select
   ArrayList e_typeOpts = null;
   ArrayList e_stateOpts = null;

   try {
     selector = new Selector();
     e_typeOpts = selector.getOptions("e_type");
     e_stateOpts = selector.getOptions("e_state");
     selector.close();
   } catch (Exception ex) {
     ex.printStackTrace();
   }

   httpServletRequest.setAttribute("e_typeOpts", e_typeOpts);
   httpServletRequest.setAttribute("e_stateOpts", e_stateOpts);

   //updateE_state where current date has gone beyond e_end
    eDao = new ExaminationPaperDAO();
    eDao.updateE_stateUnvalidateExamPaper();



    eDao = new ExaminationPaperDAO();
    /*//FEN YE
    eDao.setLength(5);

    ipage = 0; //present page
    try {
      String page = httpServletRequest.getParameter("page"); //requested page
      ipage = java.lang.Integer.parseInt(page, 10);
    } catch (Exception e) {
      ipage = randomDistributeExamPaperForm.getPage();
    }
    if (ipage < 1) {
      ipage = 1;
    }*/

    try {
      paperList = (ArrayList) eDao.getValidateExaminationPaper(
          randomDistributeExamPaperForm);
    } catch (SQLException ex2) {
      ex2.printStackTrace();
    }
    String condistr = eDao.getConditionStr();
    eDao.setConditionStr(condistr);
    //pagestr = eDao.getPagestr(ipage);
    //randomDistributeExamPaperForm.setPagestr(pagestr);

    httpServletRequest.setAttribute("paperList", paperList);

    //***************for examinationPaper**************end*****************

    return actionMapping.findForward("randomdistribute");
  }
}
