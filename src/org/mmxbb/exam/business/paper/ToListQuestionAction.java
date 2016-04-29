package org.mmxbb.exam.business.paper;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.bean.ExaminationPaper;
import org.mmxbb.exam.bean.Question;
import org.mmxbb.exam.business.question.QuestionActionForm;
import org.mmxbb.exam.dao.ExaminationPaperDAO;
import org.mmxbb.exam.dao.QuestionDAO;
import org.mmxbb.exam.util.Filter;
import org.mmxbb.exam.util.Selector;


public class ToListQuestionAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    QuestionActionForm questionActionForm = (QuestionActionForm) actionForm;
    String action = questionActionForm.getAction();
    Question q_condition = questionActionForm.getQuestion();
    ExaminationPaper examinationPaper = new ExaminationPaper();
    QuestionDAO questionDAO = null;
    ExaminationPaperDAO examinationPaperDAO = null;
    ArrayList questionSet = new ArrayList();
    Selector selector = null;
    ArrayList q_class = null;
    ArrayList q_knowledge = null;
    ArrayList q_type = null;
    ArrayList q_difficulty = null;
    String[] stringMultibox = null;
    String e_idList = new String();
    Filter filter = new Filter();

    String e_id = new String();
    e_id = servletRequest.getParameter("e_id");

   
    examinationPaperDAO = new ExaminationPaperDAO();
    examinationPaper = examinationPaperDAO.getByKeyWhenE_stateNull(java.lang.
        Long.parseLong(e_id));

    e_idList = examinationPaper.getE_idlist();
    stringMultibox = filter.SplitE_idList(e_idList);
    questionActionForm.setStringMultibox(stringMultibox); 
    questionActionForm.setE_id(java.lang.Long.parseLong(e_id));

   
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

   
    questionDAO = new QuestionDAO();
    questionDAO.setLength(100);
    int ipage;
    try {
      String page = servletRequest.getParameter("page"); 
      ipage = java.lang.Integer.parseInt(page, 10);
    } catch (Exception e) {
      ipage = questionActionForm.getPage();
    }
    if (ipage < 1) {
      ipage = 1;
    }

    try {
      questionSet = (ArrayList) questionDAO.getSearch(questionActionForm, ipage);
    } catch (Exception ex1) {
      ex1.printStackTrace();
    }

    
    String conditionStr = questionDAO.getConditionStr();
    if (q_condition.getQ_type().equals("047")) {
      conditionStr += "&action=single";
    } else if (q_condition.getQ_type().equals("048")) {
      conditionStr += "&action=multi";
    } else if (q_condition.getQ_type().equals("049")) {
      conditionStr += "&action=fitin";
    } else if (q_condition.getQ_type().equals("050")) {
      conditionStr += "&action=answer";
    }
    conditionStr += "&e_id=" + e_id;
    questionDAO.setConditionStr(conditionStr);
    //---init action in fenye!!!

    String pagestr = questionDAO.getPagestr_paper(ipage);

    questionActionForm.setPagestr(pagestr); //set questionActionForm!!!!

    servletRequest.setAttribute("questionSet", questionSet);

    if (action.equals("single")) {
      return actionMapping.findForward("addquestion");
    } else if (action.equals("multi")) {
      return actionMapping.findForward("addmultiquestion");
    } else if (action.equals("fitin")) {
      return actionMapping.findForward("addfitinquestion");
    }
    return actionMapping.findForward("addanswerquestion");
  }
}
