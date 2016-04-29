package org.mmxbb.exam.business.paper;

import java.util.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.mmxbb.exam.bean.*;


public class PreViewExamTestPaperForm extends ActionForm {
  private ArrayList singleQ = new ArrayList();
  private ArrayList multiQ = new ArrayList();
  private ArrayList fitinQ = new ArrayList();
  private ArrayList answerQ = new ArrayList();
  private ExaminationPaper examPaper = new ExaminationPaper();
  private String t_id = "";
  private String t_begin = "";
  private String t_end = "";
  private float e_manual = 0;
  private float e_auto = 0;
  private float t_total = 0;
  private String examinee_name;
  private String[] t_answer = null; 
  private String[][] multi_answer = null;
  private String e_id;
  public void setT_answerLen(int len) {
    this.t_answer = new String[len];
  }

  public void setMultiLen(int len) {
    this.multi_answer = new String[len][5];
  }

  public String getT_answer(int i) {
    return t_answer[i];
  }

  public void setT_answer(int i, String t_answer) {
    this.t_answer[i] = t_answer;
  }

  public String getMulti_answer(int i) {
    StringBuffer T_answer = new StringBuffer();
    String[] answer = multi_answer[i];
    for (int j = 0; j < answer.length; j++) {
      T_answer.append(answer[j]);
    }
    return T_answer.toString();
  }

  public void setMulti_answer(int i, String answer[]) {

    this.multi_answer[i] = answer;
  }

  public static void main(String[] argus) {
    String[] q = new String[5];
    q[0] = "A";
    q[1] = "B";
    q[3] = "C";
    q[2] = "D";
    q[4] = "E";
    PreViewExamTestPaperForm e = new PreViewExamTestPaperForm();
    for (int i = 0; i < 3; i++) {
      e.setMulti_answer(i, q);

    }
  }


  public ArrayList getAnswerQ() {
    return answerQ;
  }

  public void setAnswerQ(ArrayList answerQ) {
    this.answerQ = answerQ;
  }

  public void setSingleQ(ArrayList singleQ) {
    this.singleQ = singleQ;
  }

  public void setMultiQ(ArrayList multiQ) {
    this.multiQ = multiQ;
  }

  public void setFitinQ(ArrayList fitinQ) {
    this.fitinQ = fitinQ;
  }

  public void setExamPaper(ExaminationPaper examPaper) {
    this.examPaper = examPaper;
  }

  public void setT_id(String t_id) {
    this.t_id = t_id;
  }

  public void setT_begin(String t_begin) {
    this.t_begin = t_begin;
  }

  public void setT_end(String t_end) {
    this.t_end = t_end;
  }

  public void setE_manual(float e_manual) {
    this.e_manual = e_manual;
  }

  public void setE_auto(float e_auto) {
    this.e_auto = e_auto;
  }

  public void setT_total(float t_total) {
    this.t_total = t_total;
  }

  public void setExaminee_name(String examinee_name) {
    this.examinee_name = examinee_name;
  }

  public void setT_answer(String[] t_answer) {
    this.t_answer = t_answer;
  }

  public void setMulti_answer(String[][] multi_answer) {
    this.multi_answer = multi_answer;
  }

  public ArrayList getFitinQ() {
    return fitinQ;
  }

  public ArrayList getMultiQ() {
    return multiQ;
  }

  public ArrayList getSingleQ() {
    return singleQ;
  }

  public ExaminationPaper getExamPaper() {
    return examPaper;
  }

  public String getT_id() {
    return t_id;
  }

  public String getT_begin() {
    return t_begin;
  }

  public String getT_end() {
    return t_end;
  }

  public float getE_manual() {
    return e_manual;
  }

  public float getE_auto() {
    return e_auto;
  }

  public float getT_total() {
    return t_total;
  }

  public String getExaminee_name() {
    return examinee_name;
  }

  public String[] getT_answer() {
    return t_answer;
  }

  public String[][] getMulti_answer() {
    return multi_answer;
  }

  public String getE_id() {
    return e_id;
  }

  public void setE_id(String e_id) {
    this.e_id = e_id;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    /** @todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest servletRequest) {
  }

}
