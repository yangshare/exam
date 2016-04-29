package org.mmxbb.exam.business.program;

import org.apache.struts.action.*;
import org.mmxbb.exam.bean.ExaminationPaper;
import org.mmxbb.exam.dao.ExaminationPaperDAO;
import org.mmxbb.exam.dao.ExamineeChooserDAO;
import org.mmxbb.exam.util.GetQuestionCount;
import org.mmxbb.exam.util.Selector;

import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DoRandomDistributeAction extends Action {
  public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
    /**@todo: complete the business logic here, this is just a skeleton.*/
    RandomDistributeExamPaperForm randomDistributeExamPaperForm = (RandomDistributeExamPaperForm) actionForm;
    String [] examineeList = null;
    String [] examinationPaperList = null;
    String distributedE_idListStr = null;
    ArrayList paperList = null;
    String [] distributedE_idList = null;
    StringTokenizer st = null;
    ExaminationPaper examPaper = null;
    ExaminationPaperDAO eDAO = null;
    examineeList = randomDistributeExamPaperForm.getExamineeList();
    examinationPaperList = randomDistributeExamPaperForm.getExaminationPaperList();

   
    distributedE_idListStr = this.doRandomDistribute(examineeList,examinationPaperList);

    
    st = new StringTokenizer(distributedE_idListStr,"##");
    int t = 0;

    while(st.hasMoreTokens()){
    st.nextToken();
      t++;
    }
    distributedE_idList = new String[t];
    st = new StringTokenizer(distributedE_idListStr,"##");
    t = 0;
    while(st.hasMoreTokens()){
    distributedE_idList[t] = st.nextToken();
      t++;
    }

    paperList = new ArrayList();
    for(int i = 0;i < distributedE_idList.length;i++){
      eDAO = new ExaminationPaperDAO();
      examPaper = eDAO.findBykey(Long.parseLong(distributedE_idList[i]));
      paperList.add(examPaper);
    }

    httpServletRequest.setAttribute("paperList",paperList);

   
    Selector selector = null;
    ArrayList e_typeOpts = null;
    ArrayList e_stateOpts = null;
    ArrayList e_gradeOpts = null;

    try {
      selector = new Selector();
      e_typeOpts = selector.getOptions("e_type");
      e_stateOpts = selector.getOptions("e_state");
      e_gradeOpts = selector.getOptions("e_grade");
      selector.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }

    httpServletRequest.setAttribute("e_typeOpts", e_typeOpts);
    httpServletRequest.setAttribute("e_stateOpts", e_stateOpts);
    httpServletRequest.setAttribute("e_gradeOpts", e_gradeOpts);

    return actionMapping.findForward("randomdistributedlist");
  }

  public String doRandomDistribute(String[] examineeList,String[] examinationPaperList){
    ExaminationPaperDAO eDAO = null;
    ExamineeChooserDAO examineeChooserDAO = null;
    GetQuestionCount gqc = null;
    String distributedE_idListStr = "";

    gqc = new GetQuestionCount();
    int k = 0;
   
    while(k < examineeList.length){
      int j = 0;
      while(true){
        int ra = DoRandomDistributeAction.myRandom(3,0);
        if(ra == 0){
         
          long e_id = Long.parseLong(examinationPaperList[j]);
          if(!(distributedE_idListStr == null)){
            distributedE_idListStr = gqc.addWithoutDuplicate(distributedE_idListStr,examinationPaperList[j]);
          }
          else{
            distributedE_idListStr = examinationPaperList[j];
          }
         
          eDAO = new ExaminationPaperDAO();
          eDAO.updateE_state(e_id,"039");
        
          eDAO = new ExaminationPaperDAO();
          String examineeListInDb = eDAO.findBykey(e_id).getE_examineeList();
        
          if(!(examineeListInDb == null)){
          examineeChooserDAO = new ExamineeChooserDAO();
          examineeChooserDAO.updateList("##" + examineeList[k] + examineeListInDb,e_id);
          }
          else{
          examineeChooserDAO = new ExamineeChooserDAO();
          examineeChooserDAO.updateList("##" + examineeList[k],e_id);
          }
          break;
        }
        j++;
        if(j == examinationPaperList.length){
          j = 0;
        }
      }
      k++;
    }
    return distributedE_idListStr;
  }
 
  public static int myRandom(int upLimit, int downLimit)
 {
    return (int)(Math.random()*(upLimit-downLimit))+downLimit;
 }


}
