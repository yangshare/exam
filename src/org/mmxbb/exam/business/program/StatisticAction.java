package org.mmxbb.exam.business.program;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.TestPaperDAO;

public class StatisticAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest request,
                               HttpServletResponse response) {
    TestPaperDAO tDao = new TestPaperDAO();
    long e_id = Long.parseLong(request.getParameter("E_ID"));
    float e_total = Float.parseFloat(request.getParameter("E_TOTAL"));
    float e_passvalue = Float.parseFloat(request.getParameter("E_PASSVALUE"));
    tDao.setLength(15);
    int ipage = 1; //present page
    try {
      String page = request.getParameter("page").toString(); //requested page
      ipage = java.lang.Integer.parseInt(page, 10);
    } catch (Exception e) {

      e.printStackTrace();
    }
    if (ipage < 1) {
      ipage = 1;
    }
    ArrayList testPaperList = new ArrayList();
    try {
      //get the paperList the t_total>0
      testPaperList = (ArrayList) tDao.sorting(e_id, ipage);
      int size = testPaperList.size();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    String conStr = tDao.getConditionStr() + "&E_ID=" + e_id + "&E_TOTAL=" +
        e_total + "&E_PASSVALUE=" + e_passvalue;
    tDao.setConditionStr(conStr);
    String pagestr = tDao.getPagestr(ipage, "statisticAction");
    request.setAttribute("pagestr", pagestr);
    request.setAttribute("testPaperList", testPaperList);

    TestPaperDAO tDao1 = new TestPaperDAO();
    ArrayList scoreList = new ArrayList();
    try {
      scoreList = (ArrayList) tDao1.statistic(e_id, e_total, e_passvalue);
      int size = scoreList.size();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    request.setAttribute("scoreList", scoreList);

    return (actionMapping.findForward("e_statistic"));
  }

}
