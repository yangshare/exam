package org.mmxbb.exam.business.paper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.bean.ExaminationPaper;
import org.mmxbb.exam.dao.ExaminationPaperDAO;


public class AddExaminationPaperAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    AddExaminationPaperForm examinationPaperForm = (AddExaminationPaperForm)
        actionForm;
    ExaminationPaperDAO examinationPaperDAO = null;
    ExaminationPaper examinationPaper = new ExaminationPaper();

    examinationPaper = examinationPaperForm.getExaminationPaper();
    examinationPaper.setE_state("038");

    examinationPaperDAO = new ExaminationPaperDAO();
    examinationPaperDAO.updateExaminationPaper(examinationPaper);

    return (actionMapping.findForward("paperoperasuccessaction"));
  }
}
