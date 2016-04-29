package org.mmxbb.exam.business.paper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.ExaminationPaperDAO;


public class ClearIdListAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
    String e_id = null;
    String action = null;
    ExaminationPaperDAO epDAO = null;
    e_id = httpServletRequest.getParameter("e_id");
    action = httpServletRequest.getParameter("action");
    if ( (null == e_id) || ("".equals(e_id)) || (null == action) ||
        ("".equals(action))) {
      return actionMapping.getInputForward();
    }
    try {
      epDAO = new ExaminationPaperDAO();
      epDAO.clearIdList(e_id, action);
    } catch (Exception ex) {
      ex.printStackTrace();
      return actionMapping.findForward("error");
    }

    return actionMapping.findForward("success");
  }
}
