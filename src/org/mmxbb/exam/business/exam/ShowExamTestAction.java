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

public class ShowExamTestAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    ExamTestActionForm examTestActionForm = (ExamTestActionForm) actionForm;
    String e_id = null;
    e_id = examTestActionForm.getE_id();
    String examinee_id = null;
    HttpSession s = servletRequest.getSession();
    LogonBean logon = (LogonBean) s.getAttribute("logon");
    examinee_id = logon.getLogon();

    TestPaperDAO testPaperDAO = null;
    ExamineeDAO examineeDAO = null;

   
    examTestActionForm.setExaminee_id(examinee_id);

    
    examineeDAO = new ExamineeDAO();
    try {
      String examinee_name = examineeDAO.findByKey(examinee_id).getName();
      
      examTestActionForm.setExaminee_name(examinee_name);
    } catch (SQLException ex1) {
      ex1.printStackTrace();
    }


    TestPaperDAO tDAO = null;
    tDAO = new TestPaperDAO();
    String t_idS = tDAO.findT_id(e_id, examinee_id);

    examTestActionForm.setT_id(t_idS);
    ArrayList QuestionSet = null;
    ArrayList singleQ = new ArrayList();
    ArrayList multiQ = new ArrayList();
    ArrayList fitinQ = new ArrayList();
    ArrayList answerQ = new ArrayList();
    QuestionInTest qInTest = new QuestionInTest();

    ExamDAO examDAO = null;
    examDAO = new ExamDAO();
    examTestActionForm.setExamPaper(examDAO.getExamPaper(e_id));

    examDAO = new ExamDAO();
    QuestionSet = (ArrayList) examDAO.getExamQuestion(e_id);

    Transformer transformer = new Transformer();
    String singType = transformer.valueToId("单选题");
    transformer = new Transformer();
    String multiType = transformer.valueToId("多选题");
    transformer = new Transformer();
    String fitinType = transformer.valueToId("填空题");
    transformer = new Transformer();
    String answerType = transformer.valueToId("简答题");
    int mLen = 0;
    for (int i = 0; i < QuestionSet.size(); i++) {
      qInTest = (QuestionInTest) QuestionSet.get(i);
      if (qInTest.getQ_type().equals(singType)) {
        processSingle(qInTest);
        singleQ.add(qInTest);
      }
      if (qInTest.getQ_type().equals(multiType)) {
        processMulti(qInTest);
        multiQ.add(qInTest);
        mLen++;
      }
      if (qInTest.getQ_type().equals(fitinType)) {
        fitinQ.add(qInTest);
      }
      if (qInTest.getQ_type().equals(answerType)) {
        answerQ.add(qInTest);
      }
    }

    examTestActionForm.setT_answerLen(QuestionSet.size() - mLen);
    examTestActionForm.setMultiLen(mLen);
    examTestActionForm.setSingleQ(singleQ);
    examTestActionForm.setMultiQ(multiQ);
    examTestActionForm.setFitinQ(fitinQ);
    examTestActionForm.setAnswerQ(answerQ);

    tDAO = new TestPaperDAO();
    long t_id = Long.parseLong(t_idS);

   
    tDAO.setBeginTime(t_id);
    int timer = examTestActionForm.getExamPaper().getE_timer() * 60 * 1000;
    String e_timer = Integer.toString(timer);

   
    testPaperDAO = new TestPaperDAO();
    TestPaper testPaper = new TestPaper();
    testPaper = testPaperDAO.findByKey(t_id);
    testPaper.setT_state("095");
    testPaperDAO = new TestPaperDAO();
    try {
      testPaperDAO.updateTestPaper(testPaper);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    servletRequest.setAttribute("e_timer", e_timer);

    return actionMapping.findForward("examtest");
    
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
