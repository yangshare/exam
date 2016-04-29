package org.mmxbb.exam.business.examinee;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.bean.Examinee;
import org.mmxbb.exam.dao.ExamineeDAO;


public class SaveAddExamineeAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {

    ExamineeActionForm form = (ExamineeActionForm) actionForm;
    Examinee examinee = null;
    ExamineeDAO examineeDAO = null;

    examinee = new Examinee();
    examineeDAO = new ExamineeDAO();

    examinee.setExaminee_id(form.getExaminee_id());
    examinee.setPassword(form.getPassword());
    examinee.setName(form.getName());
    examinee.setSex(form.getSex());
    examinee.setAge(form.getAge());
    examinee.setOrganization_id(form.getOrganization_id());
    examinee.setPost_index(form.getPost_index());
    examinee.setState(form.getState());
    examinee.setOperation(form.getOperation());
    examinee.setEducation_index(form.getEducation_index());
    examinee.setAddress(form.getAddress());
    examinee.setPhone(form.getPhone());
    examinee.setEmail(form.getEmail());
    examinee.setRemark(form.getRemark());

    try {
      examineeDAO.addExaminee(examinee);
    } catch (SQLException ex1) {
      ex1.printStackTrace();
      return actionMapping.getInputForward();
    }

    form.reset();

    return actionMapping.findForward("success");
  }
}
