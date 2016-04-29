package org.mmxbb.exam.bean;

import java.io.*;

public class Examinee
    implements Serializable {
  private String examinee_id = null;
  private String password = null;
  private String name = null;
  private String sex = null;
  private String organization_id = null;
  private String state = null;
  private String operation = null;
  private String address = null;
  private String phone = null;
  private String mobile = null;
  private String email = null;
  private String remark = null;
  private String education_index = null;
  private String post_index = null;
  private String educationName = null;
  private String operationName = null;
  private String organizationName = null;
  private String postName = null;
  private String sexName = null;
  private String stateName = null;
  private String age;

  public String getExaminee_id() {
    return this.examinee_id;
  }

  public void setExaminee_id(String examinee_id) {
    this.examinee_id = examinee_id;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSex() {
    return this.sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getAge() {
    return this.age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public String getOrganization_id() {
    return this.organization_id;
  }

  public void setOrganization_id(String organization_id) {
    this.organization_id = organization_id;
  }

  public String getPost_index() {
    return this.post_index;
  }

  public void setPost_index(String post_index) {
    this.post_index = post_index;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public String getEducation_index() {
    return this.education_index;
  }

  public void setEducation_index(String education_index) {
    this.education_index = education_index;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRemark() {
    return this.remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getEducationName() {
    return educationName;
  }

  public void setEducationName(String educationName) {
    this.educationName = educationName;
  }

  public String getOperationName() {
    return operationName;
  }

  public void setOperationName(String operationName) {
    this.operationName = operationName;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(String organizationName) {
    this.organizationName = organizationName;
  }

  public String getPostName() {
    return postName;
  }

  public void setPostName(String postName) {
    this.postName = postName;
  }

  public String getSexName() {
    return sexName;
  }

  public void setSexName(String sexName) {
    this.sexName = sexName;
  }

  public String getStateName() {
    return stateName;
  }

  public void setStateName(String stateName) {
    this.stateName = stateName;
  }
}
