package org.mmxbb.exam.business.paper;


import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.*;
import org.mmxbb.exam.bean.*;
import org.mmxbb.exam.util.Selector;

import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AddExaminationPaperForm
    extends ValidatorForm {
  private ExaminationPaper examinationPaper = new ExaminationPaper();
  private int singleTotalCount;
  private int singleSimpleCount;
  private int singleMidCount;
  private int singleDifficultyCount;
  private int multiSimpleCount;
  private int multiMidCount;
  private int multiDifficultyCount;
  private int multiTotalCount;
  private int fitinTotalCount;
  private int fitinSimpleCount;
  private int fitinMidCount;
  private int fitinDifficultyCount;
  private int answerTotalCount;
  private int answerSimpleCount;
  private int answerMidCount;
  private int answerDifficultyCount;
  private float singleTotalValue;
  private float multiTotalValue;
  private float fitinTotalValue;
  private float answerTotalValue;
  private Question question;
  private String singleQ_idList;
  private String multiQ_idList;
  private String fitinQ_idList;
  private String answerQ_idList;
  private int page;
  private String pagestr;
  private TestPaper testpaper = new TestPaper();
  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    ActionErrors errors = new ActionErrors();

    Date date = null;
    Date currentDate = null;
    Date e_beginDate = null;
    Date e_endDate = null;
    Timestamp ts = null;
    String dateStr = null;
    SimpleDateFormat sdf = null;


  date = new Date();
  dateStr = new Timestamp(date.getTime()).toString().substring(0,10);
  int begin_end = this.examinationPaper.getE_begin().compareTo(this.examinationPaper.getE_end());
  int begin_current = this.examinationPaper.getE_begin().compareTo(dateStr);

    if(begin_current < 0){
      errors.add("error.beginTimeBeforeCurrentTime",new ActionError("error.beginTimeBeforeCurrentTime"));
    }
    else if(begin_end > 0){
      errors.add("error.beginTimeAfterEndTime",new ActionError("error.beginTimeAfterEndTime"));
    }
    else if(begin_end == 0){
      errors.add("error.beginTimeEquelEndTime",new ActionError("error.beginTimeEquelEndTime"));
    }
    else if(examinationPaper.getE_total() < examinationPaper.getE_passvalue()){
      errors.add("error.totalLessThanPass",new ActionError("error.totalLessThanPass"));
    }
    else if("@@0@@0@@0@@0".equals(examinationPaper.getE_idlist())){
      errors.add("error.nullQuestion",new ActionError("error.nullQuestion"));
    }


    //init selector!!
    Selector selector = null;
    ArrayList e_typeOpts = new ArrayList();
    try {
      selector = new Selector();
      e_typeOpts = selector.getOptions("e_type");
      selector.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    httpServletRequest.setAttribute("e_typeOpts", e_typeOpts);

 /** @todo: finish this method, this is just the skeleton.*/
    return errors;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest servletRequest) {

  }

  public void setExaminationPaper(ExaminationPaper examinationPaper) {
    this.examinationPaper = examinationPaper;
  }

  public void setSingleTotalCount(int singleTotalCount) {
    this.singleTotalCount = singleTotalCount;
  }

  public void setSingleSimpleCount(int singleSimpleCount) {
    this.singleSimpleCount = singleSimpleCount;
  }

  public void setSingleMidCount(int singleMidCount) {
    this.singleMidCount = singleMidCount;
  }

  public void setSingleDifficultyCount(int singleDifficultyCount) {
    this.singleDifficultyCount = singleDifficultyCount;
  }

  public void setMultiSimpleCount(int multiSimpleCount) {
    this.multiSimpleCount = multiSimpleCount;
  }

  public void setMultiMidCount(int multiMidCount) {
    this.multiMidCount = multiMidCount;
  }

  public void setMultiDifficultyCount(int multiDifficultyCount) {
    this.multiDifficultyCount = multiDifficultyCount;
  }

  public void setMultiTotalCount(int multiTotalCount) {
    this.multiTotalCount = multiTotalCount;
  }

  public void setFitinTotalCount(int fitinTotalCount) {
    this.fitinTotalCount = fitinTotalCount;
  }

  public void setFitinSimpleCount(int fitinSimpleCount) {
    this.fitinSimpleCount = fitinSimpleCount;
  }

  public void setFitinMidCount(int fitinMidCount) {
    this.fitinMidCount = fitinMidCount;
  }

  public void setFitinDifficultyCount(int fitinDifficultyCount) {
    this.fitinDifficultyCount = fitinDifficultyCount;
  }

  public void setAnswerTotalCount(int answerTotalCount) {
    this.answerTotalCount = answerTotalCount;
  }

  public void setAnswerSimpleCount(int answerSimpleCount) {
    this.answerSimpleCount = answerSimpleCount;
  }

  public void setAnswerMidCount(int answerMidCount) {
    this.answerMidCount = answerMidCount;
  }

  public void setAnswerDifficultyCount(int answerDifficultyCount) {
    this.answerDifficultyCount = answerDifficultyCount;
  }

  public void setSingleTotalValue(float singleTotalValue) {
    this.singleTotalValue = singleTotalValue;
  }

  public void setMultiTotalValue(float multiTotalValue) {
    this.multiTotalValue = multiTotalValue;
  }

  public void setFitinTotalValue(float fitinTotalValue) {
    this.fitinTotalValue = fitinTotalValue;
  }

  public void setAnswerTotalValue(float answerTotalValue) {
    this.answerTotalValue = answerTotalValue;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public void setSingleQ_idList(String singleQ_idList) {
    this.singleQ_idList = singleQ_idList;
  }

  public void setMultiQ_idList(String multiQ_idList) {
    this.multiQ_idList = multiQ_idList;
  }

  public void setFitinQ_idList(String fitinQ_idList) {
    this.fitinQ_idList = fitinQ_idList;
  }

  public void setAnswerQ_idList(String answerQ_idList) {
    this.answerQ_idList = answerQ_idList;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public void setPagestr(String pagestr) {

    this.pagestr = pagestr;
  }

  public void setTestpaper(TestPaper testpaper) {
    this.testpaper = testpaper;
  }

  public ExaminationPaper getExaminationPaper() {
    return examinationPaper;
  }

  public int getSingleTotalCount() {
    return singleTotalCount;
  }

  public int getSingleSimpleCount() {
    return singleSimpleCount;
  }

  public int getSingleMidCount() {
    return singleMidCount;
  }

  public int getSingleDifficultyCount() {
    return singleDifficultyCount;
  }

  public int getMultiSimpleCount() {
    return multiSimpleCount;
  }

  public int getMultiMidCount() {
    return multiMidCount;
  }

  public int getMultiDifficultyCount() {
    return multiDifficultyCount;
  }

  public int getMultiTotalCount() {
    return multiTotalCount;
  }

  public int getFitinTotalCount() {
    return fitinTotalCount;
  }

  public int getFitinSimpleCount() {
    return fitinSimpleCount;
  }

  public int getFitinMidCount() {
    return fitinMidCount;
  }

  public int getFitinDifficultyCount() {
    return fitinDifficultyCount;
  }

  public int getAnswerTotalCount() {
    return answerTotalCount;
  }

  public int getAnswerSimpleCount() {
    return answerSimpleCount;
  }

  public int getAnswerMidCount() {
    return answerMidCount;
  }

  public int getAnswerDifficultyCount() {
    return answerDifficultyCount;
  }

  public float getSingleTotalValue() {
    return singleTotalValue;
  }

  public float getMultiTotalValue() {
    return multiTotalValue;
  }

  public float getFitinTotalValue() {
    return fitinTotalValue;
  }

  public float getAnswerTotalValue() {
    return answerTotalValue;
  }

  public Question getQuestion() {
    return question;
  }

  public String getSingleQ_idList() {
    return singleQ_idList;
  }

  public String getMultiQ_idList() {
    return multiQ_idList;
  }

  public String getFitinQ_idList() {
    return fitinQ_idList;
  }

  public String getAnswerQ_idList() {
    return answerQ_idList;
  }

  public int getPage() {
    return page;
  }

  public String getPagestr() {

    return pagestr;
  }

  public TestPaper getTestpaper() {
    return testpaper;
  }
}
