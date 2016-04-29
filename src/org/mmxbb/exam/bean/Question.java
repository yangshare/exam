package org.mmxbb.exam.bean;

public class Question {
  private long q_id;
  private String q_class = "";
  private String q_knowledge = "";
  private float q_value;
  private String q_type = "";
  private String q_difficulty = "";
  private String q_content = "";
  private String q_answer = "";
  private String q_standard = "";
  private String q_picture = "";

  public Question() {
    try {
      jbInit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
  }

  public void setQ_id(long q_id) {
    this.q_id = q_id;
  }

  public void setQ_class(String q_class) {
    this.q_class = q_class;
  }

  public void setQ_knowledge(String q_knowledge) {
    this.q_knowledge = q_knowledge;
  }

  public void setQ_value(float q_value) {
    this.q_value = q_value;
  }

  public void setQ_type(String q_type) {
    this.q_type = q_type;
  }

  public void setQ_difficulty(String q_difficulty) {
    this.q_difficulty = q_difficulty;
  }

  public void setQ_content(String q_content) {
    this.q_content = q_content;
  }

  public void setQ_answer(String q_answer) {
    this.q_answer = q_answer;
  }

  public void setQ_standard(String q_standard) {
    this.q_standard = q_standard;
  }

  public void setQ_picture(String q_picture) {
    this.q_picture = q_picture;
  }

  public long getQ_id() {
    return q_id;
  }

  public String getQ_class() {
    return q_class;
  }

  public String getQ_knowledge() {
    return q_knowledge;
  }

  public float getQ_value() {
    return q_value;
  }

  public String getQ_type() {
    return q_type;
  }

  public String getQ_difficulty() {
    return q_difficulty;
  }

  public String getQ_content() {
    return q_content;
  }

  public String getQ_answer() {
    return q_answer;
  }

  public String getQ_standard() {
    return q_standard;
  }

  public String getQ_picture() {
    return q_picture;
  }
}
