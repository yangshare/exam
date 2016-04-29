package org.mmxbb.exam.business.exam;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.bean.Examinee;
import org.mmxbb.exam.business.LogonBean;
import org.mmxbb.exam.business.examinee.ExamineeActionForm;
import org.mmxbb.exam.dao.ExamineeDAO;
import org.mmxbb.exam.util.Selector;



public class EditExamineeAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
    ExamineeActionForm examineeActionForm = (ExamineeActionForm) actionForm;
    Examinee examinee = null;
    ExamineeDAO examineeDAO = null;
    LogonBean logonBean = new LogonBean();
    String examinee_id = "";

    examineeDAO = new ExamineeDAO();
    HttpSession session = httpServletRequest.getSession();
    logonBean = (LogonBean) session.getAttribute("logon");
    examinee_id = logonBean.getLogon();
    try {
      examinee = examineeDAO.findByKey(examinee_id);
    } catch (SQLException ex1) {
      ex1.printStackTrace();
    }

    examineeActionForm.setExaminee_id(examinee.getExaminee_id());
    examineeActionForm.setPassword(examinee.getPassword());
    examineeActionForm.setName(examinee.getName());
    examineeActionForm.setSex(examinee.getSex());
    examineeActionForm.setAge(String.valueOf(examinee.getAge()));
    examineeActionForm.setOrganization_id(examinee.getOrganization_id());
    examineeActionForm.setPost_index(examinee.getPost_index());
    examineeActionForm.setState(examinee.getState());
    examineeActionForm.setOperation(examinee.getOperation());
    examineeActionForm.setEducation_index(examinee.getEducation_index());
    examineeActionForm.setAddress(examinee.getAddress());
    examineeActionForm.setPhone(examinee.getPhone());
    examineeActionForm.setEmail(examinee.getEmail());
    examineeActionForm.setRemark(examinee.getRemark());

    
    Selector selector = null;
    ArrayList organizationOpts = null;
    ArrayList post_indexOpts = null;
    ArrayList stateOpts = null;
    ArrayList sexOpts = null;
    ArrayList education_indexOpts = null;
    ArrayList operationOpts = null;

    try {
      selector = new Selector();
      organizationOpts = selector.getOptions("organization_id");
      post_indexOpts = selector.getOptions("post_index");
      stateOpts = selector.getOptions("state");
      sexOpts = selector.getOptions("sex");
      education_indexOpts = selector.getOptions("education_index");
      operationOpts = selector.getOptions("operation");
      selector.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    httpServletRequest.setAttribute("organizationOpts", organizationOpts);
    httpServletRequest.setAttribute("post_indexOpts", post_indexOpts);
    httpServletRequest.setAttribute("stateOpts", stateOpts);
    httpServletRequest.setAttribute("sexOpts", sexOpts);
    httpServletRequest.setAttribute("education_indexOpts", education_indexOpts);
    httpServletRequest.setAttribute("operationOpts", operationOpts);

    return (actionMapping.findForward("success"));

  }
}
