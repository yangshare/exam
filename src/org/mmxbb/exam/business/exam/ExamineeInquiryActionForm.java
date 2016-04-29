package org.mmxbb.exam.business.exam;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class ExamineeInquiryActionForm
    extends ActionForm {
  private String pagestr;

  private int page;
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
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
  }
}
