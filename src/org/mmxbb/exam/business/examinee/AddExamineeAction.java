package org.mmxbb.exam.business.examinee;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.util.Selector;



public class AddExamineeAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest httpServletRequest,
                               HttpServletResponse httpServletResponse) {
    ExamineeActionForm examineeActionForm = (ExamineeActionForm) actionForm;

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
