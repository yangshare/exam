package org.mmxbb.exam.business.examinee;

import javax.servlet.http.*;

import org.apache.struts.action.*;

public class ExamineeTestDetailsActionForm
    extends ActionForm {
  private String pagestr;
  private int page;
  private String examinee_name;
  public void setPagestr(String pagestr) {

    this.pagestr = pagestr;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public String getPagestr() {

    return pagestr;
  }

  public int getPage() {
    return page;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
      /** @todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest servletRequest) {

  }
  public String getExaminee_name() {
    return examinee_name;
  }
  public void setExaminee_name(String examinee_name) {
    this.examinee_name = examinee_name;
  }
}
