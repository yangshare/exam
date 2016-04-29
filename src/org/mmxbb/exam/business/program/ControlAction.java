package org.mmxbb.exam.business.program;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.bean.ExaminationPaper;
import org.mmxbb.exam.dao.ExaminationPaperDAO;
import org.mmxbb.exam.dao.TestPaperDAO;


public class ControlAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest request,
                               HttpServletResponse response) {
    long e_id = 0;
    String paramE_id = new String();
    paramE_id = request.getParameter("E_ID");
    if(paramE_id == null){
      e_id = Long.parseLong(request.getAttribute("E_ID").toString());
    }
    else{
      e_id = Long.parseLong(request.getParameter("E_ID"));
    }

    ExaminationPaperDAO eDao = null;
    ExaminationPaper examinationPaper = new ExaminationPaper();

    try {
      eDao = new ExaminationPaperDAO();
      examinationPaper = eDao.findBykey(e_id);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    request.setAttribute("exam", examinationPaper);

    TestPaperDAO tDao = new TestPaperDAO();
    tDao.setLength(15);
    int ipage = 1; 
    try {
    String page = request.getParameter("page"); //requested page
      ipage = java.lang.Integer.parseInt(page, 10);

    } catch (Exception e) {
    }
    if (ipage < 1) {
      ipage = 1;
    }
    ArrayList testPaperList = new ArrayList();
    try {
      testPaperList = (ArrayList) tDao.getByE_id(e_id, ipage);
      int size = testPaperList.size();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    String conStr = tDao.getConditionStr() + "&E_ID=" + e_id;
    tDao.setConditionStr(conStr);
    String pagestr = tDao.getPagestr(ipage, "controlAction");
    request.setAttribute("pagestr", pagestr);

    request.setAttribute("testPaperList", testPaperList);
    return (actionMapping.findForward("e_control"));
  }

}
