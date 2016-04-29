package org.mmxbb.exam.business.question;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.mmxbb.exam.bean.Question;
import org.mmxbb.exam.dao.QuestionDAO;
import org.mmxbb.exam.util.Filter;
import org.mmxbb.exam.util.Selector;
import org.mmxbb.exam.util.Transformer;

public class ShowQuestionDetailsAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    QuestionActionForm questionActionForm = (QuestionActionForm) actionForm;
    Selector selector = null;
    ArrayList q_class = null;
    ArrayList q_knowledge = null;
    ArrayList q_type = null;
    ArrayList q_difficulty = null;
    String method_reset = null;

    //delete submit and reset in the page!
    method_reset = questionActionForm.getSubmit_reset();
    questionActionForm.setSubmit_reset(method_reset);

    //init selector
    try {
      selector = new Selector();
      q_class = selector.getOptions("q_class");
      q_knowledge = selector.getOptions("q_knowledge");
      q_type = selector.getOptions("q_type");
      q_difficulty = selector.getOptions("q_difficulty");

      selector.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    servletRequest.setAttribute("q_classOpts", q_class);
    servletRequest.setAttribute("q_knowledgeOpts", q_knowledge);
    servletRequest.setAttribute("q_typeOpts", q_type);
    servletRequest.setAttribute("q_difficultyOpts", q_difficulty);

    Question question = new Question();

    QuestionDAO questionDAO = null;
    questionDAO = new QuestionDAO();

    long q_id = Long.parseLong(servletRequest.getParameter("q_id").toString());

    if (q_id != 0) {
      try {
        question = questionDAO.findByKey(q_id);
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }

    questionActionForm.setQuestion(question);//set question in questionActionForm




    Transformer transformer = new Transformer();
    String type = transformer.idToValue(question.getQ_type());

    if (type.equals("单选题")) {
      processSingle(questionActionForm);
      return actionMapping.findForward("single");
    }
    if (type.equals("多选题")) {
      processMulti(questionActionForm);
      return actionMapping.findForward("multi");
    }
    if (type.equals("填空题")) {

      return actionMapping.findForward("fitin");
    }
    if (type.equals("简答题")) {

      return actionMapping.findForward("answer");
    }

    /*throw new java.lang.UnsupportedOperationException(
        "Method $execute() not yet implemented.");*/
    return actionMapping.findForward("error");
  }

  private void processSingle(QuestionActionForm questionActionForm) {
    String q_answer = questionActionForm.getQuestion().getQ_answer();
    Filter filter = new Filter();
    String[] results = new String[4];
    results = filter.Split(q_answer);
    questionActionForm.setTextfield1(results[0]);
    questionActionForm.setTextfield2(results[1]);
    questionActionForm.setTextfield3(results[2]);
    questionActionForm.setTextfield4(results[3]);
  }

  private void processMulti(QuestionActionForm questionActionForm) {
    String q_answer = questionActionForm.getQuestion().getQ_answer();
    Filter filter = new Filter();
    String[] results = new String[5];
    results = filter.Split(q_answer);
    //set Textfields in questionActionForm
    questionActionForm.setTextfield1(results[0]);
    questionActionForm.setTextfield2(results[1]);
    questionActionForm.setTextfield3(results[2]);
    questionActionForm.setTextfield4(results[3]);
    questionActionForm.setTextfield5(results[4]);

    String q_standard = questionActionForm.getQuestion().getQ_standard();
    for (int i = 0; i < q_standard.length(); i++) {
      results[i] = q_standard.substring(i, i + 1);
    }
    //set results in questionActionForm
    questionActionForm.setStringMultibox(results);
  }

}
