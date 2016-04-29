package org.mmxbb.exam.bean;

public class ExaminationPaperDetail {
  private long e_id;
  private long q_id;
  private long q_seq;
  private String q_type;
  private float q_value;
  private String q_standard;

  public ExaminationPaperDetail() {
    this.e_id = 0;
    this.q_id = 0;
    this.q_seq = 0;
    this.q_type = " ";
    this.q_standard = " ";
  }

  public void setE_id(long e_id) {
    this.e_id = e_id;
  }

  public void setQ_id(long q_id) {
    this.q_id = q_id;
  }

  public void setQ_seq(long q_seq) {
    this.q_seq = q_seq;
  }

  public void setQ_type(String q_type) {
    this.q_type = q_type;
  }

  public void setQ_value(float q_value) {
    this.q_value = q_value;
  }

  public void setQ_standard(String q_standard) {
    this.q_standard = q_standard;
  }

  public long getE_id() {
    return e_id;
  }

  public long getQ_id() {
    return q_id;
  }

  public long getQ_seq() {
    return q_seq;
  }

  public String getQ_type() {
    return q_type;
  }

  public float getQ_value() {
    return q_value;
  }

  public String getQ_standard() {
    return q_standard;
  }
}
