package org.mmxbb.exam.business.program;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.mmxbb.exam.bean.*;

public class GradeActionForm
    extends ActionForm {
  private TestPaperDetail testPaperDetail = new TestPaperDetail();
  private String q_content;
  private String q_standard;
  private float q_value;
  private String title;

  public String getQ_content() {
    return q_content;
  }

  public void setQ_content(String q_content) {
    this.q_content = q_content;
  }

  public String getQ_standard() {
    return q_standard;
  }

  public void setQ_standard(String q_standard) {
    this.q_standard = q_standard;
  }

  public float getQ_value() {
    return q_value;
  }

  public void setQ_value(float q_value) {
    this.q_value = q_value;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    ActionErrors errors = new ActionErrors();
    float t_value = testPaperDetail.getT_value();
    if (t_value > this.q_value) {
      errors.add("error.t_valueLagerThanQ_value",
                 new ActionError("error.t_valueLagerThanQ_value"));
    }

    /**@todo: finish this method, this is just the skeleton.*/
    return errors;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
  }

  public org.mmxbb.exam.bean.TestPaperDetail getTestPaperDetail() {
    return testPaperDetail;
  }

  public String getTitle() {
    return title;
  }

  public void setTestPaperDetail(org.mmxbb.exam.bean.TestPaperDetail
                                 testPaperDetail) {
    this.testPaperDetail = testPaperDetail;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
