package org.mmxbb.exam.business.question;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.mmxbb.exam.bean.Question;
import org.mmxbb.exam.dao.QuestionDAO;
import org.mmxbb.exam.util.Filter;
import org.mmxbb.exam.util.Transformer;
import org.mmxbb.exam.util.UpLoad;


public class AUD_QuestionAction
    extends DispatchAction {
  public ActionForward deleteQuestion(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest servletRequest,
                                      HttpServletResponse servletResponse) {
    QuestionActionForm questionActionForm = (QuestionActionForm) actionForm;
    QuestionDAO questionDAO = null;
    questionDAO = new QuestionDAO();
    String[] IDList = null;
    IDList = questionActionForm.getStringMultibox();
    if (IDList != null) {
      try {
        questionDAO.removeQuestion(IDList);
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
    return (actionMapping.findForward("listquestionaction"));

  }

  public ActionForward addQuestion(ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest servletRequest,
                                   HttpServletResponse servletResponse) {
    QuestionActionForm questionActionForm = (QuestionActionForm) actionForm;
    Question question = questionActionForm.getQuestion();

    // step 1: upload file  and set q_picture if file is not null
    FormFile file = questionActionForm.getFile();
    if (file.getFileName() != null) {
      String workPath = servletRequest.getRealPath("/"); //contextPath);
      UpLoad upLoadFile = new UpLoad(workPath);
      String fileName = upLoadFile.UpLoad(file);
      question.setQ_picture(fileName);
    }

    //  step 2: set q_type of question and  , accoring to q_type
    //         set q_answer&q_standard in processSingle()&processMulti()
    String action = questionActionForm.getAction();
    String typeLabel = "";
    switch (action.charAt(0)) {
      case 's':
        typeLabel = "单选题";
        processSingle(questionActionForm, question);
        break;
      case 'm':
        typeLabel = "多选题";
        processMulti(questionActionForm, question);
        break;
      case 'f':
        typeLabel = "填空题";
        break;
      case 'a':
        typeLabel = "简答题";
        break;
    }
    Transformer T = new Transformer();
    question.setQ_type(T.valueToId(typeLabel));

    // step 3 :add question to DataBase
    QuestionDAO questionDAO = new QuestionDAO();
    try {
      questionDAO.addQuestion(question);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return actionMapping.findForward("listquestionaction");

  }

  public ActionForward updateQuestion(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest servletRequest,
                                      HttpServletResponse servletResponse) {
    QuestionActionForm questionActionForm = (QuestionActionForm) actionForm;
    Question question = questionActionForm.getQuestion();
    QuestionDAO qDAO = null;

    //  step 1: set q_id so as to update the right record

    // step 2: if q_picture has been changed,set q_picture and upload file
    FormFile file = questionActionForm.getFile();

    if((file.getFileName() == null)||("".equals(file.getFileName()))){
      qDAO = new QuestionDAO();
      String fileName = null;
      try {
        fileName = qDAO.findByKey(question.getQ_id()).getQ_picture();
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
      question.setQ_picture(fileName);
    }
    else if (file != null) {
      String workPath = servletRequest.getRealPath("/"); //contextPath);
      UpLoad upLoadFile = new UpLoad(workPath);
      String fileName = upLoadFile.UpLoad(file);
      question.setQ_picture(fileName);
    }

    //  step 3: set q_type of question  and , accoring to q_type
    //         set q_answer&q_standard in processSingle()&processMulti()
    String action = questionActionForm.getAction();
    String typeLabel = "";
    switch (action.charAt(0)) {
      case 's':
        typeLabel = "单选题";
        processSingle(questionActionForm, question);
        break;
      case 'm':
        typeLabel = "多选题";
        processMulti(questionActionForm, question);
        break;
      case 'f':
        typeLabel = "填空题";
        break;
      case 'a':
        typeLabel = "简答题";
        break;
    }
    Transformer T = new Transformer();
    question.setQ_type(T.valueToId(typeLabel));

    // step 4 :add question to DataBase
    QuestionDAO questionDAO = new QuestionDAO();
    try {
      questionDAO.updateQuestion(question);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }

    return actionMapping.findForward("listquestionaction");

  }

  public Question processSingle(QuestionActionForm questionActionForm,
                                Question question) {
    Filter filter = new Filter();
    String[] res = new String[4];
    res[0] = questionActionForm.getTextfield1();
    res[1] = questionActionForm.getTextfield2();
    res[2] = questionActionForm.getTextfield3();
    res[3] = questionActionForm.getTextfield4();
    String answer = filter.connect(res);
    question.setQ_answer(answer);
    return question;
  }

  public Question processMulti(QuestionActionForm questionActionForm,
                               Question question) {
    Filter filter = new Filter();

    String[] standard = questionActionForm.getStringMultibox();
    StringBuffer q_standard = new StringBuffer();
    for (int i = 0; i < standard.length; i++) {
      q_standard.append(standard[i]);
    }
    question.setQ_standard(q_standard.toString());

    String[] answer = new String[5];
    answer[0] = questionActionForm.getTextfield1();
    answer[1] = questionActionForm.getTextfield2();
    answer[2] = questionActionForm.getTextfield3();
    answer[3] = questionActionForm.getTextfield4();
    answer[4] = questionActionForm.getTextfield5();
    String q_answer = filter.connect(answer);
    question.setQ_answer(q_answer);
    return question;
  }

}
