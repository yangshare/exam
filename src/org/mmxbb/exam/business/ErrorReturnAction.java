package org.mmxbb.exam.business;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class ErrorReturnAction extends Action {
  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    String action = new String();

    action = httpServletRequest.getParameter("action");
    /**@todo: complete the business logic here, this is just a skeleton.*/
    return actionMapping.findForward("initialquestionaction");
  }
}
