package org.mmxbb.exam.business.program;

import org.apache.struts.action.*;
import org.mmxbb.exam.dao.ExaminationPaperDAO;
import org.mmxbb.exam.dao.ExaminationPaperDetailDAO;
import org.mmxbb.exam.dao.TestPaperDAO;
import org.mmxbb.exam.dao.TestPaperDetailDAO;

import javax.servlet.http.*;
import java.util.Vector;
import java.sql.*;

public class StopExamAction extends Action {
  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    ExaminationPaperDAO examinationPaperDAO = null;
    ExaminationPaperDetailDAO examinationPaperDetailDAO = null;
    TestPaperDAO testPaperDAO = null;
    TestPaperDetailDAO testPaperDetailDAO = null;
    Vector t_idVector = null;

    long e_id = java.lang.Long.parseLong(httpServletRequest.getParameter("e_id"));
    long t_id = 0;

    //update ExaminationPaper
    examinationPaperDAO = new ExaminationPaperDAO();
    examinationPaperDAO.updateE_state(e_id,"038");
    examinationPaperDAO = new ExaminationPaperDAO();
    examinationPaperDAO.removeExaminee_list(e_id);

    //removeExaminationPaperDetail_byE_id
    examinationPaperDetailDAO = new ExaminationPaperDetailDAO();
    examinationPaperDetailDAO.removeExaminationPaperDetail_byE_id(e_id);

    //removeTestPaper_byE_id
    t_idVector = new Vector();
    try {
      testPaperDAO = new TestPaperDAO();
      t_idVector = testPaperDAO.getT_idList(e_id);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    testPaperDAO = new TestPaperDAO();
    testPaperDAO.removeTestPaper_byE_id(e_id);

    //removetestPaperDetailByT_id
      int i = 0;
      while(i < t_idVector.size()){
        t_id = java.lang.Long.parseLong(t_idVector.elementAt(i).toString());
        testPaperDetailDAO = new TestPaperDetailDAO();
        testPaperDetailDAO.removeByT_id(t_id);
        i++;
      }

      String jsp = "e_distribute";
      httpServletRequest.setAttribute("jsp",jsp);
    /**@todo: complete the business logic here, this is just a skeleton.*/
    return actionMapping.findForward("searchaction");
  }
}
