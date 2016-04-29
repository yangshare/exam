package org.mmxbb.exam.business.paper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.ExaminationPaperDAO;
import org.mmxbb.exam.dao.ExaminationPaperDetailDAO;
import org.mmxbb.exam.dao.TestPaperDAO;
import org.mmxbb.exam.dao.TestPaperDetailDAO;

import java.util.Vector;
import java.sql.*;



public class RemoveQuestionAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
    long e_id = 0;
    long t_id = 0;
    ExaminationPaperDAO epDAO = null;
    ExaminationPaperDetailDAO examinationPaperDetailDAO = null;
    TestPaperDAO testPaperDAO = null;
    TestPaperDetailDAO testPaperDetailDAO = null;
    Vector t_idVector = null;
   
    e_id = Long.parseLong(httpServletRequest.getParameter("e_id"));
    
    epDAO = new ExaminationPaperDAO();
    epDAO.removeExaminationPaper(e_id);

    examinationPaperDetailDAO = new ExaminationPaperDetailDAO();
    examinationPaperDetailDAO.removeExaminationPaperDetail_byE_id(e_id);

  
    t_idVector = new Vector();
    try {
      testPaperDAO = new TestPaperDAO();
      t_idVector = testPaperDAO.getT_idList(e_id);
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    testPaperDAO = new TestPaperDAO();
    testPaperDAO.removeTestPaper_byE_id(e_id);

      int i = 0;
      while(i < t_idVector.size()){
        t_id = java.lang.Long.parseLong(t_idVector.elementAt(i).toString());
        testPaperDetailDAO = new TestPaperDetailDAO();
        testPaperDetailDAO.removeByT_id(t_id);
        i++;
      }

    String jsp = "e_list";
    httpServletRequest.setAttribute("jsp", jsp);
    return actionMapping.findForward("searchaction");
  }
}
