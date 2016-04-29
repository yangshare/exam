package org.mmxbb.exam.dao;



import java.sql.*;
import java.util.*;

import org.mmxbb.exam.bean.Question;
import org.mmxbb.exam.business.question.QuestionActionForm;
import org.mmxbb.exam.util.DBConn;

public class QuestionDAO {
  DBConn dbconn = null;
  private Connection conn = null;
  private int rowCount;
  private int pageCount;
  private int length;
  private String pagestr;
  private String conditionStr = "";
  private final static String ADD_QUESTIOIN =
      "INSERT INTO EX_QUESTION VALUES('',?,?,?,?,?,?,?,?,?)";
  private final static String UPDATE_QUESTION =
      "UPDATE ex_question set Q_CLASS=?,Q_KNOWLEDGE=?,Q_VALUE=?,Q_TYPE=?,Q_DIFFICULTY=?,Q_CONTENT=?,Q_ANSWER=?,Q_STANDARD=?,Q_PICTURE=? WHERE Q_ID=?";
  private final static String SELECT_QUESTIOIN_NoInt =
      "select ex_question.q_id as q_id,b1.b_value as q_class,b2.b_value as q_knowledge,ex_question.q_value as q_value,b3.b_value as q_type,b4.b_value as q_difficulty,ex_question.q_content as q_content,ex_question.q_answer as q_answer,ex_question.q_standard,ex_question.q_picture as q_picture from ex_question,ex_baseinfo b1, ex_baseinfo b2,ex_baseinfo b3,ex_baseinfo b4  where ex_question.q_class=b1.b_id  and ex_question.q_knowledge=b2.b_id and ex_question.q_type=b3.b_id and ex_question.q_difficulty=b4.b_id and ex_question.q_class like ? and ex_question.q_knowledge like ? and ex_question.q_type like ? and ex_question.q_difficulty like ? ORDER BY Q_ID DESC";
  private final static String SELECT_QUESTIOIN_WithInt =
      "select ex_question.q_id as q_id,b1.b_value as q_class,b2.b_value as q_knowledge,ex_question.q_value as q_value,b3.b_value as q_type,b4.b_value as q_difficulty,ex_question.q_content as q_content,ex_question.q_answer as q_answer,ex_question.q_standard,ex_question.q_picture as q_picture from ex_question,ex_baseinfo b1, ex_baseinfo b2,ex_baseinfo b3,ex_baseinfo b4  where ex_question.q_class=b1.b_id  and ex_question.q_knowledge=b2.b_id and ex_question.q_type=b3.b_id and ex_question.q_difficulty=b4.b_id and ex_question.q_class like ? and ex_question.q_knowledge like ? and ex_question.q_type like ? and ex_question.q_difficulty like ? and ex_question.q_value=? ORDER BY Q_ID DESC";

  /**
   * get a connection from a DB pool
   * @return Connection
   */
  public QuestionDAO() {
    try {
      dbconn = new DBConn();
      conn = dbconn.getConnection();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * get an object for the row find by PK
   * @param question_id String
   * @throws SQLException
   * @return Examinee
   */

  public Question findByKey(long question_id) throws SQLException {
    Statement stmt = null;
    ResultSet rs = null;
    Question question = null;
    question = new Question();
    try {
      stmt = conn.createStatement();

      rs = stmt.executeQuery("SELECT * FROM ex_question WHERE q_id=" +
                             question_id);
      while (rs.next()) {
        question.setQ_id(rs.getLong("q_id"));
        question.setQ_class(rs.getString("q_class"));
        question.setQ_knowledge(rs.getString("q_knowledge"));
        question.setQ_value(rs.getFloat("q_value"));
        question.setQ_type(rs.getString("q_type"));
        question.setQ_difficulty(rs.getString("q_difficulty"));
        question.setQ_content(rs.getString("q_content"));
        question.setQ_answer(rs.getString("q_answer"));
        question.setQ_standard(rs.getString("q_standard"));
        question.setQ_picture(rs.getString("q_picture"));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new SQLException("SQLException on : quetionDAO.findByKey(args)");
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        rs.close();
        rs = null;
        stmt.close();
        stmt = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
     
    }
    return question;
  }

  public String getConditionStr() {
    return conditionStr;
  }

  public void setConditionStr(String conditionStr) {
    this.conditionStr = conditionStr;
  }

  public int getLength() {
    return (this.length);
  }

  public void setLength(int length) {
    this.length = length;
  }

  public String getPagestr(int ipage) {
    String strPage = "";
    if (getLength() > 0) {
      strPage += "共";
      strPage += String.valueOf(rowCount);
      strPage += "条记录，共";
      strPage += String.valueOf(pageCount);
      strPage += "页，当前是第";
      strPage += String.valueOf(ipage);
      strPage += "页，      ";
      int istart, iend;
      istart = ipage - 5;
      if (istart < 0) {
        istart = 0;
      }
      iend = istart + 10;
      if (iend > pageCount) {
        iend = pageCount;
      }
      istart = iend - 10;
      if (istart < 0) {
        istart = 0;
      }
      for (int i = istart; i < iend; i++) {
        strPage +=
            "<a href='listQuestionAction.do?page=";
        strPage += String.valueOf(i + 1);
        strPage += conditionStr;
        strPage += "'>";
        strPage += String.valueOf(i + 1);
        strPage += "</a>";
        strPage += "  ";
      }
    }
    this.pagestr = strPage;
    return strPage;
  }

  public String getPagestr_paper(int ipage) {
    String strPage = "";
    if (getLength() > 0) {
      strPage += "共";
      strPage += String.valueOf(rowCount);
      strPage += "条记录，共";
      strPage += String.valueOf(pageCount);
      strPage += "页，当前是第";
      strPage += String.valueOf(ipage);
      strPage += "页，      ";
      int istart, iend;
      istart = ipage - 5;
      if (istart < 0) {
        istart = 0;
      }
      iend = istart + 10;
      if (iend > pageCount) {
        iend = pageCount;
      }
      istart = iend - 10;
      if (istart < 0) {
        istart = 0;
      }
      for (int i = istart; i < iend; i++) {
        strPage +=
            "<a href='toListQuestionAction.do?page=";
        strPage += String.valueOf(i + 1);
        strPage += conditionStr;
        strPage += "'>";
        strPage += String.valueOf(i + 1);
        strPage += "</a>";
        strPage += "  ";
      }
    }

    this.pagestr = strPage;
    return strPage;
  }

  public Collection getSearch(QuestionActionForm questionActionForm, int ipage) throws
      SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Question question = null;
    Collection list = null;

    String Q_class = questionActionForm.getQuestion().getQ_class();
    String Q_knowledge = questionActionForm.getQuestion().getQ_knowledge();
    float Q_value = questionActionForm.getQuestion().getQ_value();
    String Q_type = questionActionForm.getQuestion().getQ_type();
    String Q_difficulty = questionActionForm.getQuestion().getQ_difficulty();

    try {
      if (Q_value == 0) {
        pstmt = conn.prepareStatement(SELECT_QUESTIOIN_NoInt,
                                      ResultSet.TYPE_SCROLL_SENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
      } else {
        pstmt = conn.prepareStatement(SELECT_QUESTIOIN_WithInt,
                                      ResultSet.TYPE_SCROLL_SENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
      }

      if (Q_class == null || Q_class == "") {
        pstmt.setString(1, "%");
      } else {
        if (! ("%".equals(Q_class))) {
          conditionStr += ("&question.q_class=" + Q_class);
        }

        pstmt.setString(1, Q_class);
      }

      if (Q_knowledge == null || Q_knowledge == "") {
        pstmt.setString(2, "%");
      } else {
        if (! ("%".equals(Q_knowledge))) {
          conditionStr += ("&question.q_knowledge=" + Q_knowledge);
        }

        pstmt.setString(2, Q_knowledge);
      }
      if (Q_type == null || Q_type == "") {
        pstmt.setString(3, "%");
      } else {
        if (! ("%".equals(Q_type))) {
          conditionStr += ("&question.q_type=" + Q_type);
        }

        pstmt.setString(3, Q_type);
      }
      if (Q_difficulty == null || Q_difficulty == "") {
        pstmt.setString(4, "%");
      } else {
        if (! ("%".equals(Q_difficulty))) {
          conditionStr += ("&question.q_difficulty=" + Q_difficulty);
        }

        pstmt.setString(4, Q_difficulty);
      }
      if (Q_value != 0) {
        pstmt.setFloat(5, Q_value);

        conditionStr += ("&question.q_value=" + Q_value);

      }

      rs = pstmt.executeQuery();
      int j = 1;
      while (rs.next()) {
        j++;
      }
      list = new ArrayList();
      rs.absolute( -1);
      this.rowCount = rs.getRow();
      int offset = 1;
      int pagesize = getLength();
      if (getLength() < 1) {
        pagesize = rowCount;
        pageCount = 1;
      } else {
        pageCount = rowCount / getLength() +
            ( (rowCount % getLength()) > 0 ? 1 : 0);
        offset = (ipage - 1) * getLength() + 1;
        if (offset < 1) {
          offset = 1;
        }

        if (offset > rowCount) {
          offset = rowCount;
        }
      }

      rs.absolute(offset);
      list = new ArrayList();
      for (int i = 0; i < pagesize && offset < rowCount + 1; i++, offset++) {
        question = new Question();
        question.setQ_id(rs.getLong("q_id"));
        question.setQ_class(rs.getString("q_class"));
        question.setQ_knowledge(rs.getString("q_knowledge"));
        question.setQ_value(rs.getFloat("q_value"));
        question.setQ_type(rs.getString("q_type"));
        question.setQ_difficulty(rs.getString("q_difficulty"));
        question.setQ_content(rs.getString("q_content"));
        question.setQ_answer(rs.getString("q_answer"));
        question.setQ_standard(rs.getString("q_standard"));
        question.setQ_picture(rs.getString("q_picture"));
        list.add(question);
        rs.next();
        continue;
      }
    }

    catch (SQLException ex) {
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

  public void addQuestion(Question question) throws SQLException {
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(ADD_QUESTIOIN);
      pstmt.setString(1, question.getQ_class());
      pstmt.setString(2, question.getQ_knowledge());
      pstmt.setFloat(3, question.getQ_value());
      pstmt.setString(4, question.getQ_type());
      pstmt.setString(5, question.getQ_difficulty());
      pstmt.setString(6, question.getQ_content());
      pstmt.setString(7, question.getQ_answer());
      pstmt.setString(8, question.getQ_standard());
      pstmt.setString(9, question.getQ_picture());
      pstmt.executeUpdate();
    } catch (SQLException ex) {
      ex.getStackTrace();
      throw new SQLException("SQLExction on : QuestionDAO.addQuestion()");
    } finally {
      try {
        conn.close();
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }
  }

  /**
   * remove a row from DB
   * @param question Examinee
   * @throws SQLException
   */
  public void removeQuestion(String[] questionIDList) throws SQLException {
    Statement stmt = null;
    String ids = "";
    for (int i = 0; i < questionIDList.length; i++) {
      ids += "'" + questionIDList[i] + "'";
      if (i != questionIDList.length - 1) {
        ids += ", ";
      }
    }
    try {
      stmt = conn.createStatement();
      stmt.executeUpdate("DELETE FROM ex_question WHERE q_id in (" + ids +
                         ")");

    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new SQLException("SQLExction on : QuestionDAO.removeQuestion()");
    } finally {
      try {
        stmt.close();
        stmt = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }

  }

  public void removeQuestion(Question question) throws SQLException {
    this.removeQuestion(question.getQ_id());
  }

  /**
   * remove a row from DB
   * @param examinee_id String
   * @throws SQLException
   */
  public void removeQuestion(long question_id) throws SQLException {
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
      String sql = "DELETE FROM EX_QUESTION WHERE Q_id=" + question_id;
      stmt.executeUpdate(sql);
    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new SQLException("SQLExction on : QuestionDAO.removeQuestion()");
    } finally {
      try {
        stmt.close();
        stmt = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }
  }

  /**
   * update a row
   * @param examinee Examinee
   * @throws SQLException
   */
  public void updateQuestion(Question question) throws SQLException {
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(UPDATE_QUESTION);
      pstmt.setString(1, question.getQ_class());
      pstmt.setString(2, question.getQ_knowledge());
      pstmt.setFloat(3, question.getQ_value());
      pstmt.setString(4, question.getQ_type());
      pstmt.setString(5, question.getQ_difficulty());
      pstmt.setString(6, question.getQ_content());
      pstmt.setString(7, question.getQ_answer());
      pstmt.setString(8, question.getQ_standard());
      pstmt.setString(9, question.getQ_picture());
      pstmt.setLong(10, question.getQ_id());
      pstmt.executeUpdate();
    } catch (SQLException ex) {
      ex.getStackTrace();
    } finally {
      try {
        pstmt.close();
        pstmt = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }
  }

  /**
   * get all record from DB
   * @throws SQLException
   * @return Collection
   */

  public Collection getAll() throws SQLException {
    Statement stmt = null;
    ResultSet rs = null;
    Question question = null;
    Collection list = null;

    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery("SELECT * FROM EX_QUESTION");
      list = new ArrayList();
      while (rs.next()) {
        question = new Question();
        question.setQ_id(rs.getLong("q_id"));
        question.setQ_class(rs.getString("q_class"));
        question.setQ_knowledge(rs.getString("q_knowledge"));
        question.setQ_value(rs.getFloat("q_value"));
        question.setQ_type(rs.getString("q_type"));
        question.setQ_difficulty(rs.getString("q_difficulty"));
        question.setQ_content(rs.getString("q_content"));
        question.setQ_answer(rs.getString("q_answer"));
        question.setQ_standard(rs.getString("q_standard"));
        question.setQ_picture(rs.getString("q_picture"));

        list.add(question);

      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        rs.close();
        rs = null;
        stmt.close();
        stmt = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }

    
    }
    return list;
  }
}
