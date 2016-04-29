package org.mmxbb.exam.business.program;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.mmxbb.exam.bean.Question;
import org.mmxbb.exam.bean.TestPaper;
import org.mmxbb.exam.bean.TestPaperDetail;
import org.mmxbb.exam.dao.QuestionDAO;
import org.mmxbb.exam.dao.TestPaperDAO;
import org.mmxbb.exam.dao.TestPaperDetailDAO;
import org.mmxbb.exam.util.Transformer;

import java.io.*;

public class GradeAction
    extends DispatchAction {
  public ActionForward showExamination(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse) {
    /**@todo: complete the business logic here, this is just a skeleton.*/
    GradeActionForm gradeActionForm = (GradeActionForm) actionForm;
    long e_id = Long.parseLong(httpServletRequest.getParameter("e_id"));
    String e_name = "";
    try {
      e_name = httpServletRequest.getParameter("e_name").toString();
    } catch (Exception ex) {

      e_name = gradeActionForm.getTitle();
    }

    try {
      e_name = new String(e_name.getBytes("GBK"), "UTF-8");
    } catch (UnsupportedEncodingException ex3) {
      ex3.printStackTrace();
    }

    TestPaperDAO tDAO = new TestPaperDAO();
    tDAO.setLength(15);

    int ipage = 1; 
    try {
      String page = httpServletRequest.getParameter("page").toString(); //requested page
      ipage = java.lang.Integer.parseInt(page);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (ipage < 1) {
      ipage = 1;
    }

    ArrayList paperList = new ArrayList();
    try {
      paperList = (ArrayList) tDAO.getNotGrade(e_id, ipage);
    } catch (SQLException ex1) {
      ex1.printStackTrace();
    }

    String otherParam = "&e_id=" + Long.toString(e_id) +
        "&method=showExamination";
    tDAO.setConditionStr(otherParam);
    String pagestr = tDAO.getPagestr(ipage, "gradeAction");
    httpServletRequest.setAttribute("pagestr", pagestr);

    httpServletRequest.setAttribute("testPaperSet", paperList);
    gradeActionForm.setTitle(e_name);

    return (actionMapping.findForward("showExaminationJsp"));

    
  }

  public ActionForward showTest(ActionMapping actionMapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) {
    GradeActionForm gradeActionForm = (GradeActionForm) actionForm;
    String e_name = httpServletRequest.getParameter("e_name").toString();
    String name = httpServletRequest.getParameter("name").toString();
    String t_idS = httpServletRequest.getParameter("t_id").toString();

    long t_id = Long.parseLong(t_idS);

    TestPaperDetailDAO tpdDAO = new TestPaperDetailDAO();
    ArrayList testDedailsSet = null;
    try {
      testDedailsSet = (ArrayList) tpdDAO.findNotGraded(t_id);
    } catch (SQLException ex) {
    }

    httpServletRequest.setAttribute("testDedailsSet", testDedailsSet);

    gradeActionForm.setTitle(e_name + "-----" + name);
    TestPaperDetail testPaperDetail = new TestPaperDetail();
    testPaperDetail.setT_ID(t_id);
    gradeActionForm.setTestPaperDetail(testPaperDetail);

    saveRefererPage(httpServletRequest,"showTest");
    return actionMapping.findForward("showTestJsp");
  }

  public ActionForward showQuestion(ActionMapping actionMapping,
                                    ActionForm actionForm,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) {
    /**@todo: complete the business logic here, this is just a skeleton.*/
    GradeActionForm gradeActionForm = (GradeActionForm) actionForm;

    TestPaperDetail TestPaperDetail = new TestPaperDetail();
    String t_IDS = httpServletRequest.getParameter("t_id").toString();
    String q_IDS = httpServletRequest.getParameter("q_id").toString();
    long t_ID = Long.parseLong(t_IDS);
    long q_ID = Long.parseLong(q_IDS);

    try {
      QuestionDAO questionDAO = new QuestionDAO();
      Question question = questionDAO.findByKey(q_ID);
      gradeActionForm.setQ_content(question.getQ_content());
      gradeActionForm.setQ_standard(question.getQ_standard());
      gradeActionForm.setQ_value(question.getQ_value());

      TestPaperDetailDAO testPaperDetailDAO = new TestPaperDetailDAO();
      TestPaperDetail testPaperDetail = testPaperDetailDAO.findByKey(t_ID, q_ID);

      gradeActionForm.setTestPaperDetail(testPaperDetail);
    } catch (SQLException ex) {
    }

    saveRefererPage(httpServletRequest,"grade");
    return actionMapping.findForward("showQuestionJsp");
    //throw new java.lang.UnsupportedOperationException("Method perform() not yet implemented.");
  }

  public ActionForward saveGrade(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) {
    /**@todo: complete the business logic here, this is just a skeleton.*/
    GradeActionForm gradeActionForm = (GradeActionForm)
        actionForm;
    TestPaperDetail testPaperDetail = new TestPaperDetail();
    testPaperDetail = gradeActionForm.getTestPaperDetail();

    TestPaperDetailDAO testPaperDetailDAO = new TestPaperDetailDAO();
    try {
      testPaperDetailDAO.updateTestPaperDetail(testPaperDetail);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    HttpSession s = httpServletRequest.getSession();
    String prePage = (String) s.getAttribute("grade");
    s.removeAttribute("grade");
    if ( (prePage != null) && (! ("".equals(prePage)))) {
      try {
        httpServletResponse.sendRedirect(prePage);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } else {
      return actionMapping.findForward("showSuccess");
    }

    return null;
  }

  public ActionForward countTotalValue(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse) {
    /**@todo: complete the business logic here, this is just a skeleton.*/
    GradeActionForm gradeActionForm = (GradeActionForm)
        actionForm;
    TestPaperDetail testPaperDetail = gradeActionForm.getTestPaperDetail();
    long t_id = testPaperDetail.getT_ID();

    TestPaperDetailDAO testPaperDetailDAO = new TestPaperDetailDAO();
    float totalValue = testPaperDetailDAO.countTotalValue(t_id);

    if (totalValue >= 0) {
      try {
        TestPaper testPaper = new TestPaper();
        TestPaperDAO testPaperDAO = null;
        testPaperDAO = new TestPaperDAO();
        testPaper = testPaperDAO.findByKey(t_id);

        float t_autoValue = testPaper.getE_autovalue();
        float t_manualValue = totalValue - t_autoValue;

        Transformer transformer = new Transformer();
        String t_state = transformer.valueToId("ÒÑÆÀ·Ö");

        testPaperDAO = new TestPaperDAO();
        testPaperDAO.updateTestPaper(t_manualValue, totalValue, t_state, t_id);
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }

    HttpSession s = httpServletRequest.getSession();
    String prePage = (String) s.getAttribute("showTest");
    s.removeAttribute("showTest");
    if ( (prePage != null) && (! ("".equals(prePage)))) {
      try {
        httpServletResponse.sendRedirect(prePage);
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    } else {
      return actionMapping.findForward("showSuccess");
    }

    return null;
  }

  private void saveRefererPage(HttpServletRequest httpServletRequest,String name) {
    String prePage = null;
    String temp = httpServletRequest.getHeader("Referer");
    if ( (temp != null) && (! ("".equals(temp)))) {
      if (temp.indexOf("showQuestion") < 0) {
        prePage = temp;
        HttpSession s = httpServletRequest.getSession();
        s.setAttribute(name, prePage);
      }
    }
  }
}
