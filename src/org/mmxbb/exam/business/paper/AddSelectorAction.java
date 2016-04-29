package org.mmxbb.exam.business.paper;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.mmxbb.exam.bean.ExaminationPaper;
import org.mmxbb.exam.dao.ExaminationPaperDAO;
import org.mmxbb.exam.util.GetQuestionCount;
import org.mmxbb.exam.util.Selector;


public class AddSelectorAction
    extends Action {
  public ActionForward execute(ActionMapping actionMapping,
                               ActionForm actionForm,
                               HttpServletRequest servletRequest,
                               HttpServletResponse servletResponse) {
    AddExaminationPaperForm addExaminationPaperForm = (AddExaminationPaperForm)
        actionForm;

    ExaminationPaperDAO examinationPaperDAO = null;
    ExaminationPaper examinationPaper = new ExaminationPaper();
    GetQuestionCount gqc = new GetQuestionCount();
    String e_idList = new String();
    int[] getCounts = new int[0];
    String[] typeIdList = new String[4];

    String e_id = null;
    if (servletRequest.getParameter("e_id") != null) {
      e_id = servletRequest.getParameter("e_id");
      examinationPaper.setE_id(java.lang.Long.parseLong(e_id));
      examinationPaperDAO = new ExaminationPaperDAO();
      examinationPaper = examinationPaperDAO.getByKeyWhenE_stateNull(java.lang.
          Long.parseLong(e_id));
    } else if (servletRequest.getAttribute("e_id") != null) {
      e_id = (String) servletRequest.getAttribute("e_id");
      examinationPaper.setE_id(java.lang.Long.parseLong(e_id));
      examinationPaperDAO = new ExaminationPaperDAO();
      examinationPaper = examinationPaperDAO.getByKeyWhenE_stateNull(java.lang.
          Long.parseLong(e_id));
    } else {
      examinationPaperDAO = new ExaminationPaperDAO();
      examinationPaper = examinationPaperDAO.findByE_state();
      if (examinationPaper == null || "".equals(examinationPaper)) {
        examinationPaperDAO = new ExaminationPaperDAO();
        examinationPaperDAO.addExamintionPaper(examinationPaper);
        examinationPaperDAO = new ExaminationPaperDAO();
        examinationPaper = examinationPaperDAO.findByE_state();
      }
    }

  
    e_idList = examinationPaper.getE_idlist();
    typeIdList = gqc.GetTypeIdList(e_idList);
    String singleQ_idList = typeIdList[0];
    String multiQ_idList = typeIdList[1];
    String fitinQ_idList = typeIdList[2];
    String answerQ_idList = typeIdList[3];

    addExaminationPaperForm.setSingleQ_idList(singleQ_idList);
    addExaminationPaperForm.setMultiQ_idList(multiQ_idList);
    addExaminationPaperForm.setFitinQ_idList(fitinQ_idList);
    addExaminationPaperForm.setAnswerQ_idList(answerQ_idList);

    getCounts = gqc.GetCount(e_idList);

    int singleSimpleCount = getCounts[0];
    int singleMidCount = getCounts[1];
    int singleDifficultyCount = getCounts[2];
    int singleTotalValue = getCounts[3];

    int multiSimpleCount = getCounts[4];
    int multiMidCount = getCounts[5];
    int multiDifficultyCount = getCounts[6];
    int multiTotalValue = getCounts[7];

    int fitinSimpleCount = getCounts[8];
    int fitinMidCount = getCounts[9];
    int fitinDifficultyCount = getCounts[10];
    int fitinTotalValue = getCounts[11];

    int answerSimpleCount = getCounts[12];
    int answerMidCount = getCounts[13];
    int answerDifficultyCount = getCounts[14];
    int answerTotalValue = getCounts[15];

    addExaminationPaperForm.setSingleSimpleCount(singleSimpleCount);
    addExaminationPaperForm.setSingleMidCount(singleMidCount);
    addExaminationPaperForm.setSingleDifficultyCount(singleDifficultyCount);
    addExaminationPaperForm.setSingleTotalValue(singleTotalValue);

    addExaminationPaperForm.setMultiSimpleCount(multiSimpleCount);
    addExaminationPaperForm.setMultiMidCount(multiMidCount);
    addExaminationPaperForm.setMultiDifficultyCount(multiDifficultyCount);
    addExaminationPaperForm.setMultiTotalValue(multiTotalValue);

    addExaminationPaperForm.setFitinSimpleCount(fitinSimpleCount);
    addExaminationPaperForm.setFitinMidCount(fitinMidCount);
    addExaminationPaperForm.setFitinDifficultyCount(fitinDifficultyCount);
    addExaminationPaperForm.setFitinTotalValue(fitinTotalValue);

    addExaminationPaperForm.setAnswerSimpleCount(answerSimpleCount);
    addExaminationPaperForm.setAnswerMidCount(answerMidCount);
    addExaminationPaperForm.setAnswerDifficultyCount(answerDifficultyCount);
    addExaminationPaperForm.setAnswerTotalValue(answerTotalValue);

    int singleTotalCount = singleSimpleCount + singleMidCount +
        singleDifficultyCount;
    int multiTotalCount = multiSimpleCount + multiMidCount +
        multiDifficultyCount;
    int fitinTotalCount = fitinSimpleCount + fitinMidCount +
        fitinDifficultyCount;
    int answerTotalCount = answerSimpleCount + answerMidCount +
        answerDifficultyCount;

    addExaminationPaperForm.setSingleTotalCount(singleTotalCount);
    addExaminationPaperForm.setMultiTotalCount(multiTotalCount);
    addExaminationPaperForm.setFitinTotalCount(fitinTotalCount);
    addExaminationPaperForm.setAnswerTotalCount(answerTotalCount);

    examinationPaper.setE_total(singleTotalValue + multiTotalValue +
                                fitinTotalValue + answerTotalValue);

    
    addExaminationPaperForm.setExaminationPaper(examinationPaper); //set addExaminationPaperForm!!!!

   
    Selector selector = null;
    ArrayList e_typeOpts = new ArrayList();
    try {
      selector = new Selector();
      e_typeOpts = selector.getOptions("e_type");
      selector.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    servletRequest.setAttribute("e_typeOpts", e_typeOpts);

    return actionMapping.findForward("createexaminationpaper");

  }
}
