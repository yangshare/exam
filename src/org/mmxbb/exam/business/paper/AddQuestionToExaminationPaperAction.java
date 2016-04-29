package org.mmxbb.exam.business.paper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.bean.ExaminationPaper;
import org.mmxbb.exam.business.question.QuestionActionForm;
import org.mmxbb.exam.dao.ExaminationPaperDAO;
import org.mmxbb.exam.util.Filter;
import org.mmxbb.exam.util.GetQuestionCount;


public class AddQuestionToExaminationPaperAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    QuestionActionForm questionActionForm = (QuestionActionForm) actionForm;
    ExaminationPaperDAO examinationPaperDAO = null;

    ExaminationPaper examinationPaper = null;
    Filter filter = new Filter();
    GetQuestionCount gqc = new GetQuestionCount();
    String action = questionActionForm.getAction();
    String[] stringMultibox = questionActionForm.getStringMultibox();
    String addE_idList = null;

      addE_idList = filter.Connect(stringMultibox);

   
    examinationPaperDAO = new ExaminationPaperDAO();
    examinationPaper = new ExaminationPaper();
    long e_id = questionActionForm.getE_id();
    examinationPaper = examinationPaperDAO.getByKeyWhenE_stateNull(e_id);
    String finalString = examinationPaper.getE_idlist();

    finalString = gqc.stringOldToNew(finalString, addE_idList, action);

    finalString = finalString.replaceAll("@@0##", "@@##");
    examinationPaper.setE_idlist(finalString);

   
    if (examinationPaper.getE_id() == 0) {
      examinationPaperDAO = new ExaminationPaperDAO();
      examinationPaperDAO.addExamintionPaper(examinationPaper);
    } else {
      examinationPaperDAO = new ExaminationPaperDAO();
      examinationPaperDAO.updateExaminationPaper(examinationPaper);
    }
    servletRequest.setAttribute("e_id", java.lang.Long.toString(e_id));

    return actionMapping.findForward("addselectoraction");
  }
}
