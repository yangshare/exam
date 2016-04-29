package org.mmxbb.exam.business.baseinfo;

import java.util.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;

public class BaseInfoActionForm
    extends ActionForm {
  private Collection beanCollection;
  private String singleSelect;
  private String[] beanCollectionSelect;
  private String parClassName;
  private String parClassID; 
  private String subI;
  private String subJ;
  private String subClassID; 
  private String subClassName; 
  private String new_subClassID; 
  private String new_subClassName; 

  public Collection getBeanCollection() {
    return beanCollection;
  }

  public void setBeanCollection(Collection beanCollection) {
    this.beanCollection = beanCollection;
  }

  public String getSingleSelect() {
    return singleSelect;
  }

  public void setSingleSelect(String singleSelect) {
    this.singleSelect = singleSelect;
  }

  public String[] getBeanCollectionSelect() {
    return beanCollectionSelect;
  }

  public void setBeanCollectionSelect(String[] beanCollectionSelect) {
    this.beanCollectionSelect = beanCollectionSelect;
  }

  public String getParClassName() {
    return parClassName;
  }

  public void setParClassName(String parClassName) {
    this.parClassName = parClassName;
  }

  public String getParClassID() {
    return parClassID;
  }

  public void setParClassID(String parClassID) {
    this.parClassID = parClassID;
  }

  public String getSubI() {
    return subI;
  }

  public void setSubI(String subI) {
    this.subI = subI;
  }

  public String getSubJ() {
    return subJ;
  }

  public void setSubJ(String subJ) {
    this.subJ = subJ;
  }

  public String getSubClassID() {
    return subClassID;
  }

  public void setSubClassID(String subClassID) {
    this.subClassID = subClassID;
  }

  public String getSubClassName() {
    return subClassName;
  }

  public void setSubClassName(String subClassName) {
    this.subClassName = subClassName;
  }

  public String getNew_subClassID() {
    return new_subClassID;
  }

  public void setNew_subClassID(String new_subClassID) {
    this.new_subClassID = new_subClassID;
  }

  public String getNew_subClassName() {
    return new_subClassName;
  }

  public void setNew_subClassName(String new_subClassName) {
    this.new_subClassName = new_subClassName;
  }

  public ActionErrors validate(ActionMapping actionMapping,
                               HttpServletRequest httpServletRequest) {
    /**@todo: finish this method, this is just the skeleton.*/
    return null;
  }

  public void reset(ActionMapping actionMapping,
                    HttpServletRequest httpServletRequest) {
  }


}
