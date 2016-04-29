package org.mmxbb.exam.business.exam;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.Action;
import org.mmxbb.exam.bean.QuestionInTest;
import org.mmxbb.exam.bean.TestPaper;
import org.mmxbb.exam.dao.TestPaperDAO;
import org.mmxbb.exam.dao.TestPaperDetailDAO;
import org.mmxbb.exam.util.Transformer;

import java.util.ArrayList;
import java.sql.*;

public class SaveTestPaperAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    ExamTestActionForm examTestActionForm = (ExamTestActionForm) actionForm;
   
    ArrayList singleQ = examTestActionForm.getSingleQ();
    ArrayList multiQ = examTestActionForm.getMultiQ();
    ArrayList fitinQ = examTestActionForm.getFitinQ();
    ArrayList answerQ = examTestActionForm.getAnswerQ();
   
    String[] answer1 = examTestActionForm.getT_answer();

    String[] answer2 = new String[multiQ.size()];
    String name = "";
    String[] temp;
    StringBuffer S = null;
    for (int i = 0; i < multiQ.size(); i++) {
      S = new StringBuffer();
      name = "multi_answer[" + i + "]";
      temp = servletRequest.getParameterValues(name);
      if(temp != null){
        for (int j = 0; j < temp.length; j++) {
        S.append(temp[j]);
      }
      answer2[i] = S.toString();
      }
    }


    String t_idS = examTestActionForm.getT_id();
    long t_id = Long.parseLong(t_idS);

    QuestionInTest qInTest = null;
    TestPaperDetailDAO tpdDAO = null;

   
    int pos = 0;
    int i = 0;
    float total = 0;
    for (i = 0; i < singleQ.size(); i++) {

      qInTest = (QuestionInTest) singleQ.get(i);

      float t_value = 0;

      if (answer1[i] != null) {
        if (qInTest.getQ_standard().equals(answer1[i])) {
          t_value = qInTest.getQ_value();
        }
      }

      qInTest.setT_value(t_value);
      total += t_value;

      tpdDAO = new TestPaperDetailDAO();
      tpdDAO.updateTestPaperDetail(t_id, qInTest.getQ_id(), answer1[i],
                                   qInTest.getT_value());
    }

    for (i = 0; i < multiQ.size(); i++) {
      qInTest = (QuestionInTest) multiQ.get(i);
     
      if(answer2[i] != null){
        float t_value = (answer2[i].equals(qInTest.getQ_standard()) ?
                       qInTest.getQ_value() : 0);
      qInTest.setT_value(t_value);
      total += t_value;
      tpdDAO = new TestPaperDetailDAO();
      tpdDAO.updateTestPaperDetail(t_id, qInTest.getQ_id(), answer2[i],
                                   qInTest.getT_value());
      }
      
      else{
        tpdDAO = new TestPaperDetailDAO();
        tpdDAO.updateTestPaperDetail(t_id,qInTest.getQ_id(),"",
                                   0);
      }
    }
   
    pos = singleQ.size();
    for (i = 0; i < fitinQ.size(); i++) {
      qInTest = (QuestionInTest) fitinQ.get(i);
      
      float t_value = 0;
      qInTest.setT_value(t_value);
      total += t_value;

      tpdDAO.updateTestPaperDetail(t_id, qInTest.getQ_id(), answer1[pos + i],
                                   qInTest.getT_value());
    }
  
    pos = singleQ.size() + fitinQ.size();
    for (i = 0; i < answerQ.size(); i++) {
      qInTest = (QuestionInTest) answerQ.get(i);
      
     float t_value = 0;
      qInTest.setT_value(t_value);
      total += t_value;

      tpdDAO.updateTestPaperDetail(t_id, qInTest.getQ_id(), answer1[pos + i],
                                   qInTest.getT_value());
    }

    tpdDAO.close();

    Transformer transformer = new Transformer();
    String t_state1 = transformer.valueToId("已考完未评分");
    transformer = new Transformer();
    String t_state2 = transformer.valueToId("已评分");

    TestPaperDAO tDAO = null;

    tDAO = new TestPaperDAO();
    TestPaper testPaper = tDAO.findByKey(t_id);
    testPaper.setE_autovalue(total);

    if (fitinQ.size() == 0 & answerQ.size() == 0) {
      testPaper.setT_state(t_state2);
      testPaper.setT_total(total);
    } else {
      testPaper.setT_state(t_state1);
    }
    tDAO = new TestPaperDAO();
    try {
      tDAO.gradeTestPaper(testPaper);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return actionMapping.findForward("suceesssubmitexamtest");

  }
}
