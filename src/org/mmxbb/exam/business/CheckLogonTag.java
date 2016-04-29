package org.mmxbb.exam.business;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.mmxbb.exam.dao.ExamineeDAO;

public class CheckLogonTag
    extends TagSupport {

  private LogonBean logonBean = new LogonBean();
  private String logon = "logon";
  private String page = "/index.jsp";
  private String role = "";
  private String display = "false";
  private String logonInfo = "";

  public String getLogon() {
    return logon;
  }

  public void setLogon(String logon) {
    this.logon = logon;
  }

  public String getPage() {
    return page;
  }

  public void setPage(String page) {
    this.page = page;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public int doStartTag() throws JspException {
    return (SKIP_BODY);
  }

  public int doEndTag() throws JspException {
    ExamineeDAO eDAO = null;
    String examinee_id = null;
    String examinee_name = null;
    boolean valid = false;
    HttpSession session = pageContext.getSession();
    if ( (session != null) && (session.getAttribute(logon) != null)) {
      try {
        logonBean = (LogonBean) session.getAttribute(logon);
        examinee_id = logonBean.getLogon();
        eDAO = new ExamineeDAO();
        examinee_name = eDAO.findByKey(examinee_id).getName();
        this.logonInfo = examinee_id + "  ¿¼ÉúÐÕÃû£º" + examinee_name + " ";
        if (CheckRole(role, logonBean)) {
          valid = true;
        }
      } catch (Exception e) {
        valid = false;
      }
    }

    // Forward control based on the results
    if (valid) {
      return (EVAL_PAGE);
    } else {
      try {
        pageContext.forward(page);
      } catch (Exception e) {
        throw new JspException(e.toString());
      }
      return (SKIP_PAGE);
    }
  }

  public boolean CheckRole(String role, LogonBean logonBean) {
    if ( (role.equals("admin") && logonBean.isAuthority()) ||
        ( (role.equals("user")) && (logonBean.getLogon() != null) ||
         (! ("".equals(logonBean.getLogon()))))) {
      if ("true".equals(display)) {
        print(logonInfo);
      }
      return true;
    } else {
      return false;
    }
  }

  private void print(String text) {
    JspWriter writer = pageContext.getOut();
    try {
      writer.print(text);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Release any acquired resources.
   */
  public void release() {
    super.release();
    this.logon = "logon";
    this.page = "/index.jsp";
  }
  public String getLogonInfo() {
    return logonInfo;
  }
  public void setLogonInfo(String logonInfo) {
    this.logonInfo = logonInfo;
  }
}
