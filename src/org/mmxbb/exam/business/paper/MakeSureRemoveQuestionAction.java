package org.mmxbb.exam.business.paper;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class MakeSureRemoveQuestionAction extends Action {
  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    MakeSureRemoveQuestionForm makeSureRemoveQuestionForm = (MakeSureRemoveQuestionForm)
        actionForm;

    String e_id = null;
    e_id = httpServletRequest.getParameter("e_id");
    makeSureRemoveQuestionForm.setE_id(e_id);
    /**@todo: complete the business logic here, this is just a skeleton.*/
    return actionMapping.findForward("makesureremovequestion");
  }
}
