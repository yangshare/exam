package org.mmxbb.exam.business.question;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.util.Selector;

public class InitialQuestionAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    String action = servletRequest.getParameter("action");
    //initialize select
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
    servletRequest.setAttribute("q_classOpts", q_class);
    servletRequest.setAttribute("q_knowledgeOpts", q_knowledge);
    servletRequest.setAttribute("q_typeOpts", q_type);
    servletRequest.setAttribute("q_difficultyOpts", q_difficulty);

    switch (action.charAt(0)) {
      case 's':
        return (actionMapping.findForward("single"));

      case 'm':
        return (actionMapping.findForward("multi"));

      case 'f':
        return (actionMapping.findForward("fitin"));

      case 'a':
        return (actionMapping.findForward("answer"));

    }
    return (actionMapping.findForward("error"));

    /* throw new java.lang.UnsupportedOperationException(
         "Method $execute() not yet implemented.");*/
  }
}
