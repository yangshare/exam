package org.mmxbb.exam.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.mmxbb.exam.bean.ExaminationPaper;
import org.mmxbb.exam.bean.Question;
import org.mmxbb.exam.bean.QuestionInTest;
import org.mmxbb.exam.dao.ExaminationPaperDAO;
import org.mmxbb.exam.dao.QuestionDAO;
import org.mmxbb.exam.util.DBConn;
import org.mmxbb.exam.util.Filter;


public class ExamDAO {
  DBConn dbconn = null;
  private Connection conn = null;

  private static final String GET_EXAM_QUESTION =
      "SELECT * FROM EX_QUESTION WHERE Q_ID IN ( SELECT Q_ID FROM EX_EXAMINATIONPAPER_DETAIL WHERE E_ID = ? )";
  private static final String GET_INQURY_QUESTION =
      "SELECT EX_QUESTION.Q_ID AS Q_ID,EX_QUESTION.Q_CONTENT AS Q_CONTENT,EX_QUESTION.Q_ANSWER AS Q_ANSWER,EX_QUESTION.Q_STANDARD AS Q_STANDARD,EX_QUESTION.Q_VALUE AS Q_VALUE,EX_QUESTION.Q_PICTURE AS Q_PICTURE,EX_QUESTION.Q_TYPE AS Q_TYPE,EX_TESTPAPERDETAIL.T_ANSWER AS T_ANSWER,EX_TESTPAPERDETAIL.T_VALUE AS T_VALUE FROM EX_QUESTION,EX_TESTPAPERDETAIL  WHERE EX_QUESTION.Q_ID IN ( SELECT Q_ID FROM EX_EXAMINATIONPAPER_DETAIL WHERE EX_EXAMINATIONPAPER_DETAIL.E_ID = ? ) AND EX_QUESTION.Q_ID=EX_TESTPAPERDETAIL.Q_ID AND EX_TESTPAPERDETAIL.T_ID=?";
  private static final String GET_EXAM_PAPER =
      "SELECT E.E_ID E_ID,E.E_NAME E_NAME,E.E_TIMER E_TIMER,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,E.E_TYPE E_TYPE,B1.B_VALUE E_TYPENAME,E.E_STATE E_STATE,B2.B_VALUE E_STATENAME FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE B1.B_ID = E.E_TYPE AND B2.B_ID = E.E_STATE AND E_ID = ?";

  public ExamDAO() {
    try {
      dbconn = new DBConn();
      conn = dbconn.getConnection();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public Collection getExamQuestion(String e_id) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    QuestionInTest qTest = null;
    Collection list = null;
    list = new ArrayList();
    try {
      pstmt = conn.prepareStatement(GET_EXAM_QUESTION);
      pstmt.setString(1, e_id);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        qTest = new QuestionInTest();
        qTest.setQ_id(rs.getLong("Q_ID"));
        qTest.setQ_type(rs.getString("Q_TYPE"));
        qTest.setQ_content(rs.getString("Q_CONTENT"));
        qTest.setQ_value(rs.getFloat("Q_VALUE"));
        qTest.setQ_answer(rs.getString("Q_ANSWER"));
        qTest.setQ_picture(rs.getString("Q_PICTURE"));
        qTest.setQ_standard(rs.getString("Q_STANDARD"));

        list.add(qTest);
      }
      
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        rs.close();
        rs = null;
        pstmt.close();
        pstmt = null;
        conn.close();
        conn = null;
       
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
      
    
    }
    return list;
  }

  public Collection getPreExamQuestion(String e_id) {
    ExaminationPaperDAO examPaperDAO = null;
    QuestionDAO questionDAO = null;
    String[] q_idStrs = null;
    String e_idlist = null;
    QuestionInTest qTest = null;
    Collection list = null;
    list = new ArrayList();
    Filter filter = new Filter();
    Question question = null;

    examPaperDAO = new ExaminationPaperDAO();
    e_idlist = examPaperDAO.findBykey(java.lang.Long.parseLong(e_id)).getE_idlist();
    q_idStrs = filter.SplitE_idList(e_idlist);

    for(int i = 0;i < q_idStrs.length;i++){
      questionDAO = new QuestionDAO();
      question = new Question();
      try {
        question = questionDAO.findByKey(java.lang.Long.parseLong(q_idStrs[i]));

        qTest = new QuestionInTest();
        qTest.setQ_id(question.getQ_id());
        qTest.setQ_type(question.getQ_type());
        qTest.setQ_content(question.getQ_content());
        qTest.setQ_value(question.getQ_value());
        qTest.setQ_answer(question.getQ_answer());
        qTest.setQ_picture(question.getQ_picture());
        qTest.setQ_standard(question.getQ_standard());

        list.add(qTest);
      } catch (NumberFormatException ex2) {
        ex2.printStackTrace();
      } catch (SQLException ex2) {
        ex2.printStackTrace();
      }
    }
    return list;
  }


  public Collection getInquryQuestion(String e_id, String t_id) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    QuestionInTest qTest = null;
    Collection list = null;
    list = new ArrayList();
    try {
      pstmt = conn.prepareStatement(GET_INQURY_QUESTION);
      pstmt.setString(1, e_id);
      pstmt.setString(2, t_id);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        qTest = new QuestionInTest();
        qTest.setQ_id(rs.getLong("Q_ID"));
        qTest.setQ_type(rs.getString("Q_TYPE"));
        qTest.setQ_content(rs.getString("Q_CONTENT"));
        qTest.setQ_value(rs.getFloat("Q_VALUE"));
        qTest.setQ_answer(rs.getString("Q_ANSWER"));
        qTest.setQ_picture(rs.getString("Q_PICTURE"));
        qTest.setQ_standard(rs.getString("Q_STANDARD"));
        qTest.setT_answer(rs.getString("T_ANSWER"));
        qTest.setT_value(rs.getFloat("T_VALUE"));

        list.add(qTest);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        rs.close();
        rs = null;
        pstmt.close();
        pstmt = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }

      
    }
    return list;
  }

  public ExaminationPaper getExamPaper(String e_id) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ExaminationPaper paper = null;
    paper = new ExaminationPaper();
    try {
      pstmt = conn.prepareStatement(GET_EXAM_PAPER);
      pstmt.setString(1, e_id);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        paper.setE_id(rs.getLong("E_ID"));
        paper.setE_name(rs.getString("E_NAME"));
        paper.setE_timer(rs.getInt("E_TIMER"));
        paper.setE_begin(rs.getString("E_BEGIN"));
        paper.setE_end(rs.getString("E_END"));
        paper.setE_passvalue(rs.getFloat("E_PASSVALUE"));
        paper.setE_total(rs.getFloat("E_TOTAL"));
        paper.setE_type(rs.getString("E_TYPE"));
        paper.setE_type(rs.getString("E_TYPENAME"));
        paper.setE_state(rs.getString("E_STATE"));
        paper.setE_state(rs.getString("E_STATENAME"));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        rs.close();
        rs = null;
        pstmt.close();
        pstmt = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }

     
    }
    return paper;
  }
}
