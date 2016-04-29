package org.mmxbb.exam.business.examinee;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;
import javax.servlet.http.*;
import java.io.*;

public class ExamineeActionForm
    extends ValidatorForm
    implements Serializable {
  private String address;
  private String email;
  private String examinee_id;
  private String password;
  private String name;
  private String operation;
  private String organization_id;
  private String phone;
  private String remark;
  private String sex;
  private String state;
  private String action;
  private String education_index;
  private String post_index;
  private String age;
  private String pagestr;
  private int page;
  private String[] examineeList;

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getEducation_index() {
    return education_index;
  }

  public void setEducation_index(String education_index) {
    this.education_index = education_index;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getExaminee_id() {
    return examinee_id;
  }

  public void setExaminee_id(String examinee_id) {
    this.examinee_id = examinee_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getOperation() {
    return operation;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public String getOrganization_id() {
    return organization_id;
  }

  public void setOrganization_id(String organization_id) {
    this.organization_id = organization_id;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPost_index() {
    return post_index;
  }

  public void setPost_index(String post_index) {
    this.post_index = post_index;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public String[] getExamineeList() {
    return examineeList;
  }

  public void setExamineeList(String[] examineeList) {
    this.examineeList = examineeList;
  }

  public String getPagestr() {
    return pagestr;
  }

  public void setPagestr(String pagestr) {
    this.pagestr = pagestr;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset() {
    this.action = null;
    this.address = null;
    this.age = null;
    this.education_index = null;
    this.email = null;
    this.examinee_id = null;
    this.name = null;
    this.operation = null;
    this.organization_id = null;
    this.phone = null;
    this.post_index = null;
    this.remark = null;
    this.sex = null;
    this.state = null;
  }


  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    this.action = null;
    this.address = null;
    this.age = null;
    this.education_index = null;
    this.email = null;
    this.examinee_id = null;
    this.name = null;
    this.operation = null;
    this.organization_id = null;
    this.phone = null;
    this.post_index = null;
    this.remark = null;
    this.sex = null;
    this.state = null;
  }
}
