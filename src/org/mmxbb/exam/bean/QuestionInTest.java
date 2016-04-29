package org.mmxbb.exam.bean;

import java.io.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
public class QuestionInTest

    implements Serializable {
  private long q_id = 0;
  private String q_content = "";
  private float q_value = 0;

  private String q_answer = "";
  private String text1 = "";
  private String text2 = "";
  private String text3 = "";
  private String text4 = "";
  private String text5 = "";
  private String q_picture = "";
  private String q_standard = "";
  private String[] q_multiBox = new String[5];

  private String q_type = "";

  private String t_answer;
  private String[] t_multiBox = new String[5];
  private float t_value = 0;

  public String getQ_answer() {
    return q_answer;
  }

  public void setQ_answer(String q_answer) {
    this.q_answer = q_answer;
  }

  public String getQ_content() {
    return q_content;
  }

  public void setQ_content(String q_content) {
    this.q_content = q_content;
  }

  public long getQ_id() {
    return q_id;
  }

  public void setQ_id(long q_id) {
    this.q_id = q_id;
  }

  public String[] getQ_multiBox() {
    return q_multiBox;
  }

  public void setQ_multiBox(String[] q_multiBox) {
    this.q_multiBox = q_multiBox;
  }

  public String getQ_picture() {
    return q_picture;
  }

  public void setQ_picture(String q_picture) {
    this.q_picture = q_picture;
  }

  public String getQ_standard() {
    return q_standard;
  }

  public void setQ_standard(String q_standard) {
    this.q_standard = q_standard;
  }

  public String getQ_type() {
    return q_type;
  }

  public void setQ_type(String q_type) {
    this.q_type = q_type;
  }

  public float getQ_value() {
    return q_value;
  }

  public void setQ_value(float q_value) {
    this.q_value = q_value;
  }

  public String getText1() {
    return text1;
  }

  public void setText1(String text1) {
    this.text1 = text1;
  }

  public String getText2() {
    return text2;
  }

  public void setText2(String text2) {
    this.text2 = text2;
  }

  public String getText3() {
    return text3;
  }

  public void setText3(String text3) {
    this.text3 = text3;
  }

  public String getText4() {
    return text4;
  }

  public void setText4(String text4) {
    this.text4 = text4;
  }

  public String getText5() {
    return text5;
  }

  public String getT_answer() {
    return t_answer;
  }

  public String[] getT_multiBox() {
    return t_multiBox;
  }

  public float getT_value() {
    return t_value;
  }

  public void setText5(String text5) {
    this.text5 = text5;
  }

  public void setT_answer(String t_answer) {
    this.t_answer = t_answer;
  }

  public void setT_multiBox(String[] t_multiBox) {
    this.t_multiBox = t_multiBox;
  }

  public void setT_value(float t_value) {
    this.t_value = t_value;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    q_answer = null;
    q_content = null;
    q_id = 0;
    q_multiBox = null;
    q_picture = null;
    q_standard = null;
    q_type = null;
    q_value = 0;
    text1 = null;
    text2 = null;
    text3 = null;
    text4 = null;
    text5 = null;
  }

}
