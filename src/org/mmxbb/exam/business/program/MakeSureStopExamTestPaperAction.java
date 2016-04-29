package org.mmxbb.exam.business.program;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class MakeSureStopExamTestPaperAction extends Action {
  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    MakeSureStopExamTestPaperActionForm makeSureStopExamTestPaperActionForm = (MakeSureStopExamTestPaperActionForm) actionForm;

    String e_id = httpServletRequest.getParameter("e_id");
    makeSureStopExamTestPaperActionForm.setE_id(e_id);
    /**@todo: complete the business logic here, this is just a skeleton.*/
    return actionMapping.findForward("makesurestopexamtestpaper");
  }
}
