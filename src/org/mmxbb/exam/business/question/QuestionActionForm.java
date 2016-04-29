package org.mmxbb.exam.business.question;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.upload.*;
import org.mmxbb.exam.bean.*;

public class QuestionActionForm
    extends ActionForm {
  public QuestionActionForm() {
    try {
      jbInit();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  private FormFile file;
  private Question question=new Question();
  private String textfield1;
  private String textfield2;
  private String textfield3;
  private String textfield4;
  private String textfield5;
  private String stringMultibox[] = new String[5];
  private String pagestr;
  private int page;
  private String action;
  private long e_id;
  private String submit_reset;


  public String[] getStringMultibox() {
       return (this.stringMultibox);
   }

   public void setStringMultibox(String stringMultibox[]) {
       this.stringMultibox = stringMultibox;
   }

  public FormFile getFile() {
    return file;
  }

  public void setFile(FormFile file) {
    this.file = file;
  }

  public void setTextfield5(String textfield5) {
    this.textfield5 = textfield5;
  }

  public void setTextfield4(String textfield4) {
    this.textfield4 = textfield4;
  }

  public void setTextfield3(String textfield3) {
    this.textfield3 = textfield3;
  }

  public void setTextfield2(String textfield2) {
    this.textfield2 = textfield2;
  }

  public void setTextfield1(String textfield1) {
    this.textfield1 = textfield1;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public void setPagestr(String pagestr) {
    this.pagestr = pagestr;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public Question getQuestion() {
    return question;
  }

  public String getTextfield1() {
    return textfield1;
  }

  public String getTextfield2() {
    return textfield2;
  }

  public String getTextfield3() {
    return textfield3;
  }

  public String getTextfield4() {
    return textfield4;
  }

  public String getTextfield5() {
    return textfield5;
  }

  public String getAction() {
    return action;
  }

  public String getPagestr() {
    return pagestr;
  }

  public int getPage() {
    return page;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    ActionErrors errors = new ActionErrors();
    if(!(action == null)||!(action.equals(""))){
      if(action.equals("single")){
      if((textfield1 == null||textfield1.equals(""))&&(textfield2 == null||textfield2.equals(""))&&(textfield3 == null||textfield3.equals(""))&&(textfield4 == null||textfield4.equals(""))){
        errors.add("error.standardRequired",new ActionError("error.standardRequired"));
      }
      else if((question.getQ_standard() == null)||(question.getQ_standard().equals(""))){
        errors.add("error.standardRequired",new ActionError("error.standardRequired"));
      }
    }
    else if(action.equals("multi")){
      if((textfield1 == null||textfield1.equals(""))&&(textfield2 == null||textfield2.equals(""))&&(textfield3 == null||textfield3.equals(""))&&(textfield4 == null||textfield4.equals(""))&&(textfield5 == null||textfield5.equals(""))){
        errors.add("error.standardRequired",new ActionError("error.standardRequired"));
      }
      else if((stringMultibox[0] == null)||(stringMultibox[0].equals(""))){
        errors.add("error.standardRequired",new ActionError("error.standardRequired"));
      }
    }
    else if(action.equals("fitin")){
      if((question.getQ_standard() == null)||(question.getQ_standard().equals(""))){
        errors.add("error.standardRequired",new ActionError("error.standardRequired"));
      }
    }
    else if(action.equals("answer")){
      if((question.getQ_standard() == null)||(question.getQ_standard().equals(""))){
        errors.add("error.standardRequired",new ActionError("error.standardRequired"));
      }
    }


    }


    /** @todo: finish this method, this is just the skeleton.*/
    return errors;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest servletRequest) {
  }

  private void jbInit() throws Exception {
  }
  public long getE_id() {
    return e_id;
  }
  public void setE_id(long e_id) {
    this.e_id = e_id;
  }
  public String getSubmit_reset() {
    return submit_reset;
  }
  public void setSubmit_reset(String submit_reset) {
    this.submit_reset = submit_reset;
  }
}
