package org.mmxbb.exam.business.program;

import java.util.ArrayList;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.bean.ExaminationPaper;
import org.mmxbb.exam.dao.ExaminationPaperDAO;
import org.mmxbb.exam.dao.ExaminationPaperDetailDAO;
import org.mmxbb.exam.dao.TestPaperDAO;
import org.mmxbb.exam.dao.TestPaperDetailDAO;
import org.mmxbb.exam.util.Filter;
import org.mmxbb.exam.util.Transformer;


public class ExamBeginAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest request,
                               HttpServletResponse response) {
    ExaminationPaperDAO eDao = new ExaminationPaperDAO();
    long e_id = Long.parseLong(request.getParameter("E_ID"));
    Transformer transformer = new Transformer();
    String e_state = transformer.valueToId("½øÐÐÖÐ");
    try {
      eDao.updateE_state(e_id, e_state);
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    TestPaperDAO tDao = new TestPaperDAO();
    String e_examineeList = request.getParameter("E_EXAMINEELIST");
    float e_passvalue = Float.parseFloat(request.getParameter("E_PASSVALUE"));
    if (e_examineeList == null) {
      return (actionMapping.findForward("examBegin"));
    } else {
      ArrayList examineeIdList = null;
      Filter filter = new Filter();
      examineeIdList = filter.parseString(e_examineeList);
      try {
        if (examineeIdList == null || "".equals(examineeIdList)) {
          return actionMapping.findForward("examBegin");
        }
        tDao.addMany(examineeIdList, e_id, e_passvalue);
      } catch (Exception ex) {
        ex.printStackTrace();
      }

      Vector t_idList = new Vector();
      TestPaperDAO tDao1 = new TestPaperDAO();
      try {
        t_idList = (Vector) tDao1.getT_idList(e_id);
        for (int i = 0; i < t_idList.size(); i++) {
          Long tempL = (Long) t_idList.get(i);
          long templ = tempL.longValue();
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }

      ExaminationPaper exam = new ExaminationPaper();
      ExaminationPaperDAO eDao1 = new ExaminationPaperDAO();
      String[] q_idList = new String[0];
      try {
        exam = eDao1.findBykey(e_id);
        String e_idList = exam.getE_idlist();
        q_idList = filter.SplitE_idList(e_idList);
        for (int j = 0; j < q_idList.length; j++) {
          int q_idL = Integer.parseInt(q_idList[j]);
          if (q_idL == 0) {
            continue;
          }
          long q_id = (long) q_idL;
        }

      } catch (Exception ex) {
        ex.printStackTrace();
      }

      TestPaperDetailDAO testDatailDao = new TestPaperDetailDAO();
      try {
        testDatailDao.add_TestPaperDetail(t_idList, q_idList);
      } catch (Exception ex) {
        ex.printStackTrace();
      }

      ExaminationPaperDetailDAO examDetailDao = new
          ExaminationPaperDetailDAO();
      try {
        examDetailDao.addExaminationPaperDetail(e_id, q_idList);
      } catch (Exception ex) {
        ex.printStackTrace();
      }

      return (actionMapping.findForward("examBegin"));

    }
  }
}
