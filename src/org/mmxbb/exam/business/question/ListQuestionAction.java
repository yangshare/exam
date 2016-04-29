package org.mmxbb.exam.business.question;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.bean.Question;
import org.mmxbb.exam.dao.QuestionDAO;
import org.mmxbb.exam.util.Selector;


public class ListQuestionAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    QuestionActionForm questionActionForm = (QuestionActionForm) actionForm;
    Question q_condition = questionActionForm.getQuestion();
    QuestionDAO questionDAO = new QuestionDAO();
    ArrayList questionSet = new ArrayList();
    Selector selector = null;
    ArrayList q_class = null;
    ArrayList q_knowledge = null;
    ArrayList q_type = null;
    ArrayList q_difficulty = null;

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
    questionActionForm.setQuestion(q_condition);
    servletRequest.setAttribute("q_classOpts", q_class);
    servletRequest.setAttribute("q_knowledgeOpts", q_knowledge);
    servletRequest.setAttribute("q_typeOpts", q_type);
    servletRequest.setAttribute("q_difficultyOpts", q_difficulty);

    questionDAO.setLength(100);
    int ipage; //present page
    try {
      String page = servletRequest.getParameter("page"); //requested page
      ipage = java.lang.Integer.parseInt(page, 10);
    } catch (Exception e) {
      ipage = questionActionForm.getPage();
    }
    if (ipage < 1) {
      ipage = 1;
    }

    String Q_class = q_condition.getQ_class();
    String Q_knowledge = q_condition.getQ_knowledge();
    float Q_value = q_condition.getQ_value();
    String Q_type = q_condition.getQ_type();
    String Q_difficulty = q_condition.getQ_difficulty();

    try {
      questionSet = (ArrayList) questionDAO.getSearch(questionActionForm, ipage);
    } catch (Exception ex1) {
      ex1.printStackTrace();
    }

    String pagestr = questionDAO.getPagestr(ipage);
    questionActionForm.setPagestr(pagestr);
    servletRequest.setAttribute("questionSet", questionSet);

    int offsetI = questionDAO.getLength() * (ipage - 1);
    String offset = Integer.toString(offsetI);
    servletRequest.setAttribute("offset", offset);

    return actionMapping.findForward("success");
    /* throw new java.lang.UnsupportedOperationException(
         "Method $execute() not yet implemented.");*/
  }
}
