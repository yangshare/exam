package org.mmxbb.exam.bean;

import java.io.Serializable;
public class TestPaper
    implements Serializable {
  public TestPaper() {
    try {
      jbInit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private long t_id;
  private String examinee_id;
  private long e_id;
  private String t_begin;
  private String t_end;
  private float t_passvalue;
  private float e_autovalue;
  private float e_manualvalue;
  private float t_total;
  private String t_state;
  private String e_name;
  private String name;
  private String score_area;
  private String e_percent;
  private int subSum;
  private String t_stateName;
  private String e_typeName;
  private int e_timer;
  private String e_gradeName;
  private String e_stateName;
  private float e_total;
  private String e_state;
  public long getT_id() {
    return t_id;
  }

  public String getExaminee_id() {
    return examinee_id;
  }

  public long getE_id() {
    return e_id;
  }

  public String getT_begin() {
    return t_begin;
  }

  public String getT_end() {
    return t_end;
  }

  public float getT_passvalue() {
    return t_passvalue;
  }

  public float getE_autovalue() {
    return e_autovalue;
  }

  public float getE_manualvalue() {
    return e_manualvalue;
  }

  public float getT_total() {
    return t_total;
  }

  public String getT_state() {
    return t_state;
  }

  public String getE_name() {
    return e_name;
  }

  public String getName() {
    return name;
  }

  public String getScore_area() {
    return score_area;
  }

  public String getE_percent() {
    return e_percent;
  }

  public int getSubSum() {
    return subSum;
  }

  public String getT_stateName() {
    return t_stateName;
  }

  public void setT_id(long t_id) {
    this.t_id = t_id;
  }

  public void setExaminee_id(String examinee_id) {
    this.examinee_id = examinee_id;
  }

  public void setE_id(long e_id) {
    this.e_id = e_id;
  }

  public void setT_begin(String t_begin) {
    this.t_begin = t_begin;
  }

  public void setT_end(String t_end) {
    this.t_end = t_end;
  }

  public void setT_passvalue(float t_passvalue) {
    this.t_passvalue = t_passvalue;
  }

  public void setE_autovalue(float e_autovalue) {
    this.e_autovalue = e_autovalue;
  }

  public void setE_manualvalue(float e_manualvalue) {
    this.e_manualvalue = e_manualvalue;
  }

  public void setT_total(float t_total) {
    this.t_total = t_total;
  }

  public void setT_state(String t_state) {
    this.t_state = t_state;
  }

  public void setE_name(String e_name) {
    this.e_name = e_name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setScore_area(String score_area) {
    this.score_area = score_area;
  }

  public void setE_percent(String e_percent) {
    this.e_percent = e_percent;
  }

  public void setSubSum(int subSum) {
    this.subSum = subSum;
  }

  public void setT_stateName(String t_stateName) {
    this.t_stateName = t_stateName;
  }

  private void jbInit() throws Exception {
  }

  public String getE_typeName() {
    return e_typeName;
  }

  public void setE_typeName(String e_typeName) {
    this.e_typeName = e_typeName;
  }

  public int getE_timer() {
    return e_timer;
  }

  public void setE_timer(int e_timer) {
    this.e_timer = e_timer;
  }

  public String getE_gradeName() {
    return e_gradeName;
  }

  public void setE_gradeName(String e_gradeName) {
    this.e_gradeName = e_gradeName;
  }

  public String getE_stateName() {
    return e_stateName;
  }

  public void setE_stateName(String e_stateName) {
    this.e_stateName = e_stateName;
  }
  public float getE_total() {
    return e_total;
  }
  public void setE_total(float e_total) {
    this.e_total = e_total;
  }
  public String getE_state() {
    return e_state;
  }
  public void setE_state(String e_state) {
    this.e_state = e_state;
  }

}
