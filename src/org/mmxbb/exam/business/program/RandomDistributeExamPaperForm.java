package org.mmxbb.exam.business.program;

import org.apache.struts.action.*;
import javax.servlet.http.*;

public class RandomDistributeExamPaperForm extends ActionForm {
  //information of examinee
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
  //information of examinationPaper
  private long e_id;
  private String e_type;
  private String e_name;
  private String e_begin;
  private String e_end;
  private String e_state;
  private String[] examinationPaperList;

  public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    ActionErrors errors = new ActionErrors();
    if((this.examineeList == null)||(this.examinationPaperList == null)){
      errors.add("error.nullExamineeOrExaminationPaper",new ActionError("error.nullExamineeOrExaminationPaper"));
    }
    /**@todo: finish this method, this is just the skeleton.*/
    return errors;
  }
  public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
    this.examineeList = null;
    this.examinationPaperList = null;
  }
  public String getAction() {
    return action;
  }
  public void setAction(String action) {
    this.action = action;
  }
  public void setAddress(String address) {
    this.address = address;
  }
  public String getAddress() {
    return address;
  }
  public String getAge() {
    return age;
  }
  public void setAge(String age) {
    this.age = age;
  }
  public void setEducation_index(String education_index) {
    this.education_index = education_index;
  }
  public String getEducation_index() {
    return education_index;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setExaminee_id(String examinee_id) {
    this.examinee_id = examinee_id;
  }
  public void setExamineeList(String[] examineeList) {
    this.examineeList = examineeList;
  }
  public void setName(String name) {
    this.name = name;
  }
  public void setOperation(String operation) {
    this.operation = operation;
  }
  public void setOrganization_id(String organization_id) {
    this.organization_id = organization_id;
  }
  public void setPage(int page) {
    this.page = page;
  }
  public void setPagestr(String pagestr) {
    this.pagestr = pagestr;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public void setPhone(String phone) {
    this.phone = phone;
  }
  public void setPost_index(String post_index) {
    this.post_index = post_index;
  }
  public void setRemark(String remark) {
    this.remark = remark;
  }
  public void setSex(String sex) {
    this.sex = sex;
  }
  public void setState(String state) {
    this.state = state;
  }
  public String getState() {
    return state;
  }
  public String getSex() {
    return sex;
  }
  public String getRemark() {
    return remark;
  }
  public String getPost_index() {
    return post_index;
  }
  public String getPhone() {
    return phone;
  }
  public String getPassword() {
    return password;
  }
  public String getPagestr() {
    return pagestr;
  }
  public int getPage() {
    return page;
  }
  public String getOrganization_id() {
    return organization_id;
  }
  public String getOperation() {
    return operation;
  }
  public String getName() {
    return name;
  }
  public String[] getExamineeList() {
    return examineeList;
  }
  public String getExaminee_id() {
    return examinee_id;
  }
  public String getE_begin() {
    return e_begin;
  }
  public void setE_begin(String e_begin) {
    this.e_begin = e_begin;
  }
  public void setE_end(String e_end) {
    this.e_end = e_end;
  }
  public String getE_end() {
    return e_end;
  }
  public long getE_id() {
    return e_id;
  }
  public String getE_name() {
    return e_name;
  }
  public String getE_state() {
    return e_state;
  }
  public String getE_type() {
    return e_type;
  }
  public void setE_type(String e_type) {
    this.e_type = e_type;
  }
  public void setE_state(String e_state) {
    this.e_state = e_state;
  }
  public void setE_name(String e_name) {
    this.e_name = e_name;
  }
  public void setE_id(long e_id) {
    this.e_id = e_id;
  }
  public String[] getExaminationPaperList() {
    return examinationPaperList;
  }
  public void setExaminationPaperList(String[] examinationPaperList) {
    this.examinationPaperList = examinationPaperList;
  }
}
