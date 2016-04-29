package org.mmxbb.exam.business.program;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.TestPaperDAO;

public class AbsenceAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest request,
                               HttpServletResponse response) {
    DistributeActionForm disForm = (DistributeActionForm) actionForm;
    String e_id1 = request.getParameter("e_id");
    long e_id = Long.parseLong(e_id1);
    TestPaperDAO tDao = new TestPaperDAO();
    ArrayList testPaperList = new ArrayList();
    try {
      testPaperList = (ArrayList) tDao.findAbsence(e_id);
      int size = testPaperList.size();

    } catch (Exception ex) {
      ex.printStackTrace();
    }
    request.setAttribute("testPaperList", testPaperList);
    return (actionMapping.findForward("e_absence"));
  }

}
