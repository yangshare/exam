package org.mmxbb.exam.business.exam;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.bean.QuestionInTest;
import org.mmxbb.exam.bean.TestPaper;
import org.mmxbb.exam.business.LogonBean;
import org.mmxbb.exam.dao.ExamDAO;
import org.mmxbb.exam.dao.ExamineeDAO;
import org.mmxbb.exam.dao.TestPaperDAO;
import org.mmxbb.exam.util.Filter;
import org.mmxbb.exam.util.Transformer;

import java.sql.*;

public class InquryExamAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    ExamTestActionForm examTestActionForm = (ExamTestActionForm) actionForm;
    String e_id = servletRequest.getParameter("e_id");
    String examinee_id = null;
    HttpSession s = servletRequest.getSession();
    LogonBean logon = (LogonBean) s.getAttribute("logon");
    examinee_id = logon.getLogon();

    TestPaperDAO tDAO = null;
    tDAO = new TestPaperDAO();
    String t_idS = tDAO.findT_id(e_id, examinee_id);

    long t_id = Long.parseLong(t_idS);
    
    examTestActionForm.setT_id(t_idS);
    ArrayList QuestionSet = null;
    ArrayList singleQ = new ArrayList();
    ArrayList multiQ = new ArrayList();
    ArrayList fitinQ = new ArrayList();
    ArrayList answerQ = new ArrayList();
    QuestionInTest qInTest = new QuestionInTest();
    
    ExamDAO examDAO = new ExamDAO();
    examTestActionForm.setExamPaper(examDAO.getExamPaper(e_id));

    TestPaper testPaper = new TestPaper();
    try {
      tDAO = new TestPaperDAO();
      testPaper = tDAO.findByKey(t_id);
      ExamineeDAO examineeDAO = null;
      examineeDAO = new ExamineeDAO();
      String examinee_name = examineeDAO.findByKey(testPaper.getExaminee_id()).getName();
      
      examTestActionForm.setExaminee_name(examinee_name);
    
      examTestActionForm.setT_begin(testPaper.getT_begin());
      
      examTestActionForm.setT_end(testPaper.getT_end());
     
      examTestActionForm.setT_total(testPaper.getT_total());
      
      examTestActionForm.setE_auto(testPaper.getE_autovalue());
   
      examTestActionForm.setE_manual(testPaper.getE_manualvalue());
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

  
    examDAO = new ExamDAO();
    QuestionSet = (ArrayList) examDAO.getInquryQuestion(e_id, t_idS);

    
    Transformer transformer = new Transformer();
    String singType = transformer.valueToId("单选题");

    transformer = new Transformer();
    String multiType = transformer.valueToId("多选题");

    transformer = new Transformer();
    String fitinType = transformer.valueToId("填空题");

    transformer = new Transformer();
    String answerType = transformer.valueToId("简答题");

    
    for (int i = 0; i < QuestionSet.size(); i++) {
      qInTest = (QuestionInTest) QuestionSet.get(i);
      if (qInTest.getQ_type().equals(singType)) {
        processSingle(qInTest);
        singleQ.add(qInTest);
      }
      if (qInTest.getQ_type().equals(multiType)) {
        processMulti(qInTest);
        multiQ.add(qInTest);
      }
      if (qInTest.getQ_type().equals(fitinType)) {
        fitinQ.add(qInTest);
      }
      if (qInTest.getQ_type().equals(answerType)) {
        answerQ.add(qInTest);
      }
    }
  
    examTestActionForm.setSingleQ(singleQ);
    examTestActionForm.setMultiQ(multiQ);
    examTestActionForm.setFitinQ(fitinQ);
    examTestActionForm.setAnswerQ(answerQ);

    return actionMapping.findForward("inquryexam");

  }

  public void processSingle(QuestionInTest qInTest) {
    String q_answer = qInTest.getQ_answer();
    Filter filter = new Filter();
    String[] results = new String[5];
    results = filter.Split(q_answer);
    qInTest.setText1(results[0]);
    qInTest.setText2(results[1]);
    qInTest.setText3(results[2]);
    qInTest.setText4(results[3]);
  }

  public void processMulti(QuestionInTest qInTest) {
    String q_answer = qInTest.getQ_answer();
    Filter filter = new Filter();
    String[] results = new String[5];
    results = filter.Split(q_answer);
    qInTest.setText1(results[0]);
    qInTest.setText2(results[1]);
    qInTest.setText3(results[2]);
    qInTest.setText4(results[3]);
    qInTest.setText5(results[4]);
    
  }

}
