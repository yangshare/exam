package org.mmxbb.exam.util;

import java.sql.SQLException;
import java.util.StringTokenizer;

import org.mmxbb.exam.bean.Question;
import org.mmxbb.exam.dao.QuestionDAO;


public class GetQuestionCount {
  Question question = new Question();
  int singleSimpleCount = 0;
  int singleMidCount = 0;
  int singleDifficultyCount = 0;
  int singleValue = 0;

  int multiSimpleCount = 0;
  int multiMidCount = 0;
  int multiDifficultyCounty = 0;
  int multiValue = 0;

  int fitinSimpleCount = 0;
  int fitinMidCount = 0;
  int fitinDifficultyCount = 0;
  int fitinValue = 0;

  int answerSimpleCount = 0;
  int answerMidCount = 0;
  int answerDifficultyCount = 0;
  int answerValue = 0;

  //add the new string to old string!!
  public String stringOldToNew(String finalString, String addString,
                               String action) {
    GetQuestionCount gqc = new GetQuestionCount();
    String[] question_type = new String[4];
    StringTokenizer tokenizer = new StringTokenizer(finalString, "@@");
    int i = 0;
    while (tokenizer.hasMoreTokens()) {
      question_type[i] = tokenizer.nextToken();
      i++;
    }
    if (action.equals("single")) {
      question_type[0] = "@@" +
          gqc.addWithoutDuplicate(question_type[0], addString);
      question_type[1] = "@@" + question_type[1];
      question_type[2] = "@@" + question_type[2];
      question_type[3] = "@@" + question_type[3];
    } else if (action.equals("multi")) {
      question_type[1] = "@@" +
          gqc.addWithoutDuplicate(question_type[1], addString);
      question_type[0] = "@@" + question_type[0];
      question_type[2] = "@@" + question_type[2];
      question_type[3] = "@@" + question_type[3];
    } else if (action.equals("fitin")) {
      question_type[2] = "@@" +
          gqc.addWithoutDuplicate(question_type[2], addString);
      question_type[0] = "@@" + question_type[0];
      question_type[1] = "@@" + question_type[1];
      question_type[3] = "@@" + question_type[3];
    } else if (action.equals("answer")) {
      question_type[3] = "@@" +
          gqc.addWithoutDuplicate(question_type[3], addString);
      question_type[0] = "@@" + question_type[0];
      question_type[1] = "@@" + question_type[1];
      question_type[2] = "@@" + question_type[2];
    }

    finalString = question_type[0] + question_type[1] + question_type[2] +
        question_type[3];
    return finalString;
  }

  public String addWithoutDuplicate(String oldString, String addString) {
    int i = 0;
    StringTokenizer questionTypeTokenizer1 = new StringTokenizer(oldString,
        "##");
    while (questionTypeTokenizer1.hasMoreTokens()) {
      String aa = questionTypeTokenizer1.nextToken();
      i++;
    }
    String[] questionTypes = new String[i];

    int k = 0;
    StringTokenizer questionTypeTokenizer2 = new StringTokenizer(oldString,
        "##");
    while (questionTypeTokenizer2.hasMoreTokens()) {
      questionTypes[k] = questionTypeTokenizer2.nextToken();
      k++;
    }

    int p = 0;
    StringTokenizer addTokenizer1 = new StringTokenizer(addString, "##");
    while (addTokenizer1.hasMoreTokens()) {
      String bb = addTokenizer1.nextToken();
      p++;
    }

    String[] addStrings = new String[p];
    int q = 0;
    StringTokenizer addTokenizer2 = new StringTokenizer(addString, "##");
    while (addTokenizer2.hasMoreTokens()) {
      addStrings[q] = addTokenizer2.nextToken();
      q++;
    }

    int h = 0;
    for (int j = 0; j < addStrings.length; j++) {
      for (h = 0; h < questionTypes.length; h++) {
        if (questionTypes[h].equals(addStrings[j])) {
          break;
        }
      }
      if (h == questionTypes.length) {
        oldString += "##" + addStrings[j];
      }

    }
    return oldString;
  }

  //Get question count!!!
  public int[] GetCount(String e_idList) {
    Filter filter = new Filter();
    String[] multiString = new String[0];
    multiString = filter.SplitE_idList(e_idList);

    int multiStringLen = multiString.length;
    for (int i = 0; i < multiStringLen; i++) {
      try {
        QuestionDAO qdao = new QuestionDAO();
        question = qdao.findByKey(java.lang.Long.parseLong(
            multiString[i]));

        if (question.getQ_id() == 0) {
          continue;
        }

        if (question.getQ_type().equals("047")) {
          if (question.getQ_difficulty().equals("087")) {
            this.singleSimpleCount++;
            this.singleValue += question.getQ_value();
          } else if (question.getQ_difficulty().equals("086")) {
            this.singleMidCount++;
            this.singleValue += question.getQ_value();
          } else if (question.getQ_difficulty().equals("085")) {
            this.singleDifficultyCount++;
            this.singleValue += question.getQ_value();
          }
        } else if (question.getQ_type().equals("048")) {
          if (question.getQ_difficulty().equals("087")) {
            this.multiSimpleCount++;
            this.multiValue += question.getQ_value();
          } else if (question.getQ_difficulty().equals("086")) {
            this.multiMidCount++;
            this.multiValue += question.getQ_value();
          } else if (question.getQ_difficulty().equals("085")) {
            this.multiDifficultyCounty++;
            this.multiValue += question.getQ_value();
          }
        } else if (question.getQ_type().equals("049")) {
          if (question.getQ_difficulty().equals("087")) {
            this.fitinSimpleCount++;
            this.fitinValue += question.getQ_value();
          } else if (question.getQ_difficulty().equals("086")) {
            this.fitinMidCount++;
            this.fitinValue += question.getQ_value();
          } else if (question.getQ_difficulty().equals("085")) {
            this.fitinDifficultyCount++;
            this.fitinValue += question.getQ_value();
          }
        } else if (question.getQ_type().equals("050")) {
          if (question.getQ_difficulty().equals("087")) {
            this.answerSimpleCount++;
            this.answerValue += question.getQ_value();
          } else if (question.getQ_difficulty().equals("086")) {
            this.answerMidCount++;
            this.answerValue += question.getQ_value();
          } else if (question.getQ_difficulty().equals("085")) {
            this.answerDifficultyCount++;
            this.answerValue += question.getQ_value();
          }
        }

      } catch (SQLException ex) {
        ex.printStackTrace();
      }
    }
    int[] getCounts = {
        singleSimpleCount, singleMidCount, singleDifficultyCount, singleValue,
        multiSimpleCount, multiMidCount, multiDifficultyCounty, multiValue,
        fitinSimpleCount, fitinMidCount, fitinDifficultyCount, fitinValue,
        answerSimpleCount, answerMidCount, answerDifficultyCount, answerValue};
    return getCounts;
  }

  //get 4 TypeIdList!!!!!!!!!!
  public String[] GetTypeIdList(String e_idList) {

    StringTokenizer tokenizer = new StringTokenizer(e_idList, "@@");
    String[] typeIdList = new String[4];
    int i = 0;
    while (tokenizer.hasMoreTokens()) {
      typeIdList[i] = tokenizer.nextToken();
      typeIdList[i] = typeIdList[i].replaceAll("##", ",");
      i++;
    }

    return typeIdList;
  }

  public String[] GetTypeIdListWithoutDecorate(String e_idList) {

    StringTokenizer tokenizer = new StringTokenizer(e_idList, "@@");
    String[] typeIdList = new String[4];
    int i = 0;
    while (tokenizer.hasMoreTokens()) {
      typeIdList[i] = tokenizer.nextToken();
      i++;
    }

    return typeIdList;
  }
}
