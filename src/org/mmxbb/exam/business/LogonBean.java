package org.mmxbb.exam.business;



public class LogonBean {

  private String logon = null;
  private boolean authority = false;

  public String getLogon() {
    return logon;
  }

  public void setLogon(String logon) {
    this.logon = logon;
  }

  public boolean isAuthority() {
    return authority;
  }

  public void setAuthority(boolean authority) {
    this.authority = authority;
  }
}
