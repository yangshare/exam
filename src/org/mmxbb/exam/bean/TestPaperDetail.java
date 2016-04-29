package org.mmxbb.exam.bean;

public class TestPaperDetail {
  private String t_answer;
  private float t_value;
  private long q_ID;
  private long t_ID;
  public TestPaperDetail() {
  }

  public long getT_ID() {
    return t_ID;
  }

  public void setT_ID(long t_ID) {
    this.t_ID = t_ID;
  }

  public long getQ_ID() {
    return q_ID;
  }

  public void setQ_ID(long q_ID) {
    this.q_ID = q_ID;
  }

  public String getT_answer() {

    return t_answer;
  }

  public void setT_answer(String t_answer) {

    this.t_answer = t_answer;
  }

  public float getT_value() {

    return t_value;
  }

  public void setT_value(float t_value) {

    this.t_value = t_value;
  }
}
