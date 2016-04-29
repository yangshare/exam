package org.mmxbb.exam.business.program;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.dao.ExamineeChooserDAO;
import org.mmxbb.exam.util.Selector;
import org.mmxbb.exam.util.StrTokenConverter;


public class ChooseExamineeAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {

    ChooseExamineeActionForm chooseForm = (ChooseExamineeActionForm) actionForm;

    ArrayList examinees = null;
    String[] list = {};
    String tempList = null;
    long e_id;

    examinees = new ArrayList();
    e_id = chooseForm.getE_id();

  
    iniSelector(httpServletRequest);

    
    int ipage; 
    try {
      String page = httpServletRequest.getParameter("page"); //requested page
      ipage = java.lang.Integer.parseInt(page, 10);
    } catch (Exception e) {
      ipage = chooseForm.getPage();
    }
    if (ipage < 1) {
      ipage = 1;
    }

   
    examinees = getExamineeList(chooseForm, ipage);
    httpServletRequest.setAttribute("examinees", examinees);

   
    String[] examineesList = getExamineeList(examinees);

    int size = examinees.size();
    chooseForm.setLength(size);
    ExamineeChooserDAO ecDAO1 = new ExamineeChooserDAO();
    String pagestr = ecDAO1.getPagestr(ipage);
    chooseForm.setPagestr(pagestr);

   
    tempList = ecDAO1.getList(e_id);
    ecDAO1 = null;
    String newList = null;
    if (tempList.length() != 0) {
      list = StrTokenConverter.convert(tempList);
   
      StringBuffer eList = new StringBuffer(tempList);
      for (int i = 0; i < examineesList.length; i++) {
        int l = eList.indexOf(examineesList[i]);
        if (l >= 0) {
          eList = eList.delete(l - 2, examineesList[i].length() + l);
        }
      }
      newList = eList.toString();
    } else {
      list = new String[0];
      newList = "";
    }
    chooseForm.setExamineeList(list);
    chooseForm.setTempList(newList);

 
    saveRefererPage(httpServletRequest);
    return (actionMapping.findForward("success"));
  }

  private String[] getExamineeList(ArrayList examinees) {
    int listSize = examinees.size();
    String[] examineesList = new String[listSize];
    for (int i = 0; i < listSize; i++) {
      examineesList[i] = ( (ChooseExamineeActionForm) examinees.get(i)).
          getExaminee_id();
    }
    return examineesList;
  }

  private ArrayList getExamineeList(ChooseExamineeActionForm chooseForm,
                                    int ipage) {
    ArrayList examinees;
    ExamineeChooserDAO ecDAO = new ExamineeChooserDAO();
    ecDAO.setLength(100);
    examinees = (ArrayList) ecDAO.getList(chooseForm, ipage);
    ecDAO = null;
    return examinees;
  }

  private void saveRefererPage(HttpServletRequest httpServletRequest) {
    String prePage = null;
    String temp = httpServletRequest.getHeader("Referer");
    if ( (temp != null) && (! ("".equals(temp)))) {
      if (temp.indexOf("chooseExaminee") < 0) {
        prePage = temp;
        HttpSession s = httpServletRequest.getSession();
        s.setAttribute("prePage", prePage);
      }
    }
  }

  private void iniSelector(HttpServletRequest httpServletRequest) {
    Selector selector = null;
    ArrayList organizationOpts = null;
    ArrayList post_indexOpts = null;

    try {
      selector = new Selector();
      organizationOpts = selector.getOptions("organization_id");
      post_indexOpts = selector.getOptions("post_index");
      selector.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    httpServletRequest.setAttribute("organizationOpts", organizationOpts);
    httpServletRequest.setAttribute("post_indexOpts", post_indexOpts);
  }
}
