package org.mmxbb.exam.business.baseinfo;

import javax.servlet.http.*;
import org.apache.struts.action.*;
import org.mmxbb.exam.dao.baseInfoDAO;

public class ManiAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
    /**@todo: complete the business logic here, this is just a skeleton.*/
    BaseInfoActionForm baseInfoActionForm = (BaseInfoActionForm) actionForm;
    String subClassID = "";
    String parClassID = "";
    String manipulate = "";
    String new_subClassValue = "";
    baseInfoDAO baseInfoDAO = new baseInfoDAO();
    parClassID = baseInfoActionForm.getParClassID();
    subClassID = httpServletRequest.getParameter("subClassID");
    manipulate = httpServletRequest.getParameter("manipulate");
    new_subClassValue = httpServletRequest.getParameter("new_subClassValue");

    if (manipulate.equals("delete")) {
      baseInfoDAO.del_ex_baseinfo(subClassID);
    }
    else {
      baseInfoDAO.manipulate_ex_baseinfo(manipulate, parClassID, subClassID,
                                         new_subClassValue);
    }
    return actionMapping.findForward("baseinfoaction");

  }
}
