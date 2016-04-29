package org.mmxbb.exam.business.program;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.validator.ValidatorForm;
import org.mmxbb.exam.dao.ExamineeChooserDAO;
import org.mmxbb.exam.util.Selector;

import java.util.ArrayList;


public class ChooseExamineeActionForm
    extends ValidatorForm {

  private String[] examineeList;
  private String organization_id;
  private String post_index;
  private String examinee_id;
  private String name;
  private int page;
  private String pagestr;
  private int length;
  private long e_id;
  private String tempList;

  public String[] getExamineeList() {
    return examineeList;
  }

  public void setExamineeList(String[] examineeList) {
    this.examineeList = examineeList;
  }

  public String getOrganization_id() {
    return organization_id;
  }

  public void setOrganization_id(String organization_id) {
    this.organization_id = organization_id;
  }

  public String getPost_index() {
    return post_index;
  }

  public void setPost_index(String post_index) {
    this.post_index = post_index;
  }

  public String getExaminee_id() {
    return examinee_id;
  }

  public void setExaminee_id(String examinee_id) {
    this.examinee_id = examinee_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public String getPagestr() {
    return pagestr;
  }

  public void setPagestr(String pagestr) {
    this.pagestr = pagestr;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public long getE_id() {
    return e_id;
  }

  public void setE_id(long e_id) {
    this.e_id = e_id;
  }

  public String getTempList() {
    return tempList;
  }

  public void setTempList(String tempList) {
    this.tempList = tempList;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    ActionErrors errors = new ActionErrors();
    if((examineeList == null)||(examineeList.length == 0)){
      errors.add("error.missExaminee",new ActionError("error.missExaminee"));
    }
    iniSelector(httpServletRequest);

    //init multi box examineelist and fen ye***********************begin
    ChooseExamineeActionForm chooseForm = new ChooseExamineeActionForm();
    ArrayList examinees = null;
    String[] list = {};
    String tempList = null;
    long e_id;

    examinees = new ArrayList();
    e_id = chooseForm.getE_id();

    //initialize select
    iniSelector(httpServletRequest);

    //fen ye!!
    int ipage; //present page
    try {
      String page = httpServletRequest.getParameter("page"); //requested page
      ipage = java.lang.Integer.parseInt(page, 10);
    } catch (Exception e) {
      ipage = chooseForm.getPage();
    }
    if (ipage < 1) {
      ipage = 1;
    }

    //get all examinee list :: ArrayList
    examinees = getExamineeList(chooseForm, ipage);
    httpServletRequest.setAttribute("examinees", examinees);
    //init multi box examineelist and fen ye***********************begin

    return errors;
  }

  private void iniSelector(HttpServletRequest httpServletRequest) {
    Selector selector = null;
    ArrayList organizationOpts = null;
    ArrayList post_indexOpts = null;

    try {
      selector = new Selector();
      organizationOpts = selector.getOptions("organization_id");
      post_indexOpts = selector.getOptions("post_index");
      selector.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    httpServletRequest.setAttribute("organizationOpts", organizationOpts);
    httpServletRequest.setAttribute("post_indexOpts", post_indexOpts);
  }

  private ArrayList getExamineeList(ChooseExamineeActionForm chooseForm,
                                    int ipage) {
    ArrayList examinees;
    ExamineeChooserDAO ecDAO = new ExamineeChooserDAO();
    ecDAO.setLength(100);
    examinees = (ArrayList) ecDAO.getList(chooseForm, ipage);
    ecDAO = null;
    return examinees;
  }



  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
    examineeList = null;
  }

}
