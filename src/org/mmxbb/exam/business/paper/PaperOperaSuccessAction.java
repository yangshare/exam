package org.mmxbb.exam.business.paper;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class PaperOperaSuccessAction extends Action {
  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    return actionMapping.findForward("paperoperasuccess");
  }
}
