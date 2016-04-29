package org.mmxbb.exam.business.baseinfo;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.baseInfoDAO;

public class BaseInfoAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
    /**@todo: complete the business logic here, this is just a skeleton.*/
    BaseInfoActionForm baseInfoActionForm = (BaseInfoActionForm) actionForm;
    baseInfoDAO baseInfoDAO = new baseInfoDAO();
    Collection col = null;
    try {
      col = baseInfoDAO.findInUseForSelect();
      baseInfoActionForm.setBeanCollection(col);
      Collection subCol = baseInfoDAO.findInUseForSubSelect();
      httpServletRequest.setAttribute("subClassList", subCol);

    }
    catch (Exception ex) {
      System.out.println(":::::::::::::::::mutipulate database failly");
      ex.printStackTrace();
    }
    finally {
      baseInfoDAO.dbClose();
    }
    return actionMapping.findForward("baseinfo");
  }
}
