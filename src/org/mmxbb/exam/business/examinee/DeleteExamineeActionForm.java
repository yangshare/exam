package org.mmxbb.exam.business.examinee;

import org.apache.struts.action.*;
import javax.servlet.http.*;
import java.io.*;

public class DeleteExamineeActionForm
    extends ActionForm
    implements Serializable {

  private String[] examineeList;

  public String[] getExamineeList() {
    return examineeList;
  }

  public void setExamineeList(String[] examineeList) {
    this.examineeList = examineeList;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.examineeList = null;
  }

}
