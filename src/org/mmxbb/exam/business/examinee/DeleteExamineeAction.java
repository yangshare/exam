package org.mmxbb.exam.business.examinee;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.ExamineeDAO;



public class DeleteExamineeAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
    ExamineeActionForm deleteExamineeActionForm = (ExamineeActionForm)
        actionForm;

    ExamineeDAO examineeDAO = null;
    examineeDAO = new ExamineeDAO();
    String[] list = null;
    list = deleteExamineeActionForm.getExamineeList();
    if (list != null) {

      try {
        examineeDAO.removeExaminee(list);
      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }

    return (actionMapping.findForward("success"));

  }
}
