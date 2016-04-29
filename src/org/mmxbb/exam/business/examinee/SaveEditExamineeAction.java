package org.mmxbb.exam.business.examinee;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.ExamineeDAO;


public class SaveEditExamineeAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
    ExamineeDAO examineeDAO = null;
    examineeDAO = new ExamineeDAO();

    String reInitPwd = null;
    reInitPwd = httpServletRequest.getParameter("reInitPwd");
    if (reInitPwd != null) {
      try {
        examineeDAO.reInitPwd(reInitPwd);
      } catch (SQLException ex) {
      }
      return (actionMapping.findForward("showSuccess"));
    }

    ExamineeActionForm examineeActionForm = (ExamineeActionForm) actionForm;

    try {
      examineeDAO.updateExaminee(examineeActionForm);
    } catch (SQLException ex) {
      ex.printStackTrace();
      return (actionMapping.findForward("failure"));
    }
    examineeActionForm.reset();
    return (actionMapping.findForward("success"));

  }
}
