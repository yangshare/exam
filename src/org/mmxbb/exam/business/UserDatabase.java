package org.mmxbb.exam.business;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.digester.Digester;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.mmxbb.exam.dao.ExamineeDAO;

public class UserDatabase {
  private Users _users;
  private String path = this.getClass().getResource("./").getPath().replaceAll(
      "classes/org/mmxbb/exam/business", "database.xml");

  private File f = new File(path);

  ExamineeDAO eDAO = null;

 


  public UserDatabase() {
    try {
      load(f);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void init(Digester dg) {
    dg.addObjectCreate("users", Users.class);
    dg.addObjectCreate("users/user", User.class);
    dg.addBeanPropertySetter("users/user/userName", "userName");
    dg.addBeanPropertySetter("users/user/password", "password");
    dg.addSetNext("users/user", "addUser");
  }

  /**
   * maps the xml data to java object
   */
  private void load(File in) throws Exception {
    Digester dg = new Digester();
    init(dg);
    try {
      _users = (Users) dg.parse(in);
    } catch (IOException e) {
      throw new Exception("Error occured When loading [database.xml]", e);
    }
  }

  public void update(String userName, String passwordOld, String passwordNew) {
    SAXBuilder sb = new SAXBuilder();
    Document doc = null;
    try {
      doc = sb.build(new FileInputStream(path));
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (JDOMException ex1) {
      ex1.printStackTrace();
    }
    Element root = doc.getRootElement();
    java.util.List users = root.getChildren();
    for (int i = 0; i < users.size(); i++) {
      Element user = (Element) users.get(i);
      if ( ( (user.getChild("userName").getText()).equals(userName)) &&
          (user.getChild("password").getText().equals(passwordOld))) {
        Element pwd = user.getChild("password");
        pwd.setText(passwordNew);
      }
    }

    for (int i = 0; i < users.size(); i++) {
      Element user = (Element) users.get(i);
    }

    XMLOutputter opt = new XMLOutputter();
    try {
      opt.output(doc, new FileOutputStream(path));
    } catch (IOException ex2) {
      ex2.printStackTrace();
    }
  }

  public boolean isUser(LogonForm logonForm) {
    eDAO = new ExamineeDAO();
    return (eDAO.isUser(logonForm));
  }

  /**
   * judge user wehther an admin or not
   * @param userName String
   * @param password String
   * @return boolean
   */
  public boolean isAdmin(LogonForm logonForm) {
    Iterator userList = _users.getUsers().iterator();
    while (userList.hasNext()) {
      User u = (User) userList.next();
      String id = u.getUserName();
      String value = u.getPassword();
      if (logonForm.getUserName().equals(id) &&
          logonForm.getPassword().equals(value)) {
        return true;
      }
    }
    return false;
  }

  public LogonBean logon(LogonForm logonForm) {
    LogonBean logonBean = new LogonBean();
    boolean authority = this.isAdmin(logonForm);
    if (authority) {
      logonBean.setAuthority(authority);
      logonBean.setLogon(logonForm.getUserName());
      return logonBean;
    }

    boolean logon = this.isUser(logonForm);
    if (logon) {
      logonBean.setLogon(logonForm.getUserName());
      return logonBean;
    }
    return logonBean;
  }

  public String getPassword(){
    String password = null;
    SAXBuilder sb = new SAXBuilder();
    Document doc = null;
    try {
      doc = sb.build(new FileInputStream(path));
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (JDOMException ex1) {
      ex1.printStackTrace();
    }
    Element root = doc.getRootElement();
    java.util.List users = root.getChildren();
    for (int i = 0; i < users.size(); i++) {
      Element user = (Element) users.get(i);
      password = user.getChild("password").getText();
    }
    return password;
  }

  public String getUserName(){
    String userName = null;
    SAXBuilder sb = new SAXBuilder();
    Document doc = null;
    try {
      doc = sb.build(new FileInputStream(path));
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (JDOMException ex1) {
      ex1.printStackTrace();
    }
    Element root = doc.getRootElement();
    java.util.List users = root.getChildren();
    for (int i = 0; i < users.size(); i++) {
      Element user = (Element) users.get(i);
      userName = user.getChild("userName").getText();
    }
    return userName;
  }
  


}
