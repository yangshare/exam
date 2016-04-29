package org.mmxbb.exam.business.paper;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class MakeSureRemoveQuestionForm extends ActionForm {
  private String e_id;
  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }
  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
  }
  public String getE_id() {
    return e_id;
  }
  public void setE_id(String e_id) {
    this.e_id = e_id;
  }
}
