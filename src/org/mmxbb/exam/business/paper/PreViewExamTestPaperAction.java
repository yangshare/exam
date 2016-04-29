package org.mmxbb.exam.business.paper;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.bean.QuestionInTest;
import org.mmxbb.exam.business.LogonBean;
import org.mmxbb.exam.dao.ExamDAO;
import org.mmxbb.exam.util.Filter;
import org.mmxbb.exam.util.Transformer;



public class PreViewExamTestPaperAction extends Action {
  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    PreViewExamTestPaperForm preViewExamTestPaperForm = (PreViewExamTestPaperForm) actionForm;
    String e_id = httpServletRequest.getParameter("e_id");
   
    ArrayList QuestionSet = null;
    ArrayList singleQ = new ArrayList();
    ArrayList multiQ = new ArrayList();
    ArrayList fitinQ = new ArrayList();
    ArrayList answerQ = new ArrayList();
    QuestionInTest qInTest = new QuestionInTest();
    
    ExamDAO examDAO = new ExamDAO();
    preViewExamTestPaperForm.setExamPaper(examDAO.getExamPaper(e_id));


    examDAO = new ExamDAO();
    QuestionSet = (ArrayList) examDAO.getPreExamQuestion(e_id);

   
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
    
    preViewExamTestPaperForm.setSingleQ(singleQ);
    preViewExamTestPaperForm.setMultiQ(multiQ);
    preViewExamTestPaperForm.setFitinQ(fitinQ);
    preViewExamTestPaperForm.setAnswerQ(answerQ);

    return actionMapping.findForward("previewexamtestpaper");

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
