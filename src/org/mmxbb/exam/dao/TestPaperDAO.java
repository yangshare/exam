package org.mmxbb.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import org.mmxbb.exam.bean.TestPaper;
import org.mmxbb.exam.util.DBConn;
import org.mmxbb.exam.util.Transformer;


public class TestPaperDAO {
  DBConn dbconn = null;
  private Connection conn = null;

  String GET_BY_EID =
      "select test.t_id as t_id,test.examinee_id as examinee_id,e.name as name,test.e_id as e_id,test.t_state as t_state,b1.b_value as t_stateName,DATE_FORMAT(test.t_begin,'%Y-%m-%d %H:%i:%s') as t_begin,DATE_FORMAT(test.t_end,'%Y-%m-%d %H:%i:%s') as t_end,test.t_total as t_total,test.t_passvalue as t_passvalue, exam.e_state as e_state,b2.b_value as e_stateName from ex_testpaper test,ex_examinee e,ex_baseinfo b1,ex_baseinfo b2,ex_examinationpaper exam where test.examinee_id=e.examinee_id and b1.b_id=test.t_state and b2.b_id=exam.e_state and exam.e_id=test.e_id and test.e_id=?";
  String SORTING =
      "select test.examinee_id as examinee_id,exam.e_name as e_name,examinee.name as name,test.t_total as t_total from ex_examinationpaper exam,ex_testpaper test,ex_examinee examinee where test.e_id=exam.e_id and test.e_id=? and test.t_total>=0.0 and test.examinee_id=examinee.examinee_id order by test.t_total desc";
  private final static String FIND_ABSENCE =
      "SELECT test.examinee_id as examinee_id,e.name as name FROM ex_testpaper test,ex_examinationpaper exam,ex_examinee e WHERE test.e_id=exam.e_id AND test.examinee_id=e.examinee_id AND test.t_begin is null AND exam.e_end<= (now())AND exam.e_id=?";
  private final static String FIND_TESTPAPER =
      "SELECT t_id FROM EX_TESTPAPER WHERE e_id=? and examinee_id=?";

  private int rowCount;
  private int pageCount;
  private int length;
  private String pagestr;
  private String conditionStr = "";

  /**
   * get a connection from a DB pool
   * @return Connection
   */

  public TestPaperDAO() {
    try {
      dbconn = new DBConn();
      conn = dbconn.getConnection();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public int getLength() {
    return (this.length);
  }

  public void setLength(int length) {
    this.length = length;
  }

  public String getConditionStr() {
    return (this.conditionStr);
  }

  public void setConditionStr(String conditionStr) {
    this.conditionStr = conditionStr;
  }

  public String getPagestr(int ipage, String actionName) {
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
            "<a href=";
        strPage += actionName;
        strPage += ".do?page=";
        strPage += String.valueOf(i + 1);
        strPage += conditionStr;
        strPage += ">";
        strPage += String.valueOf(i + 1);
        strPage += "</a>";
        strPage += "  ";
      }
    }
    this.pagestr = strPage;
    return strPage;
  }

  /**
   * get an object for the row find by PK
   * @param examinee_id String
   * @throws SQLException
   * @return testpaper
   */
  public TestPaper findByKey(long t_id) {
    String FIND_BY_KEY = "SELECT t_id,examinee_id,e_id,DATE_FORMAT(t_begin,'%Y-%m-%d %H:%i:%s') as T_BEGIN,DATE_FORMAT(t_end,'%Y-%m-%d %H:%i:%s') as T_END,t_passvalue,e_autovalue,e_manualvalue,t_total,t_state FROM EX_TESTPAPER WHERE t_id=" +
        t_id;
    Statement stmt = null;
    ResultSet rs = null;
    TestPaper testpaper = null;
    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(FIND_BY_KEY);
      testpaper = new TestPaper();
      while (rs.next()) {
        testpaper.setT_id(rs.getLong("T_ID"));
        testpaper.setExaminee_id(rs.getString("EXAMINEE_ID"));
        testpaper.setE_id(rs.getLong("E_ID"));
        testpaper.setT_begin(rs.getString("T_BEGIN"));
        testpaper.setT_end(rs.getString("T_END"));
        testpaper.setT_passvalue(rs.getFloat("T_PASSVALUE"));
        testpaper.setE_autovalue(rs.getFloat("E_AUTOVALUE"));
        testpaper.setE_manualvalue(rs.getFloat("E_MANUALVALUE"));
        testpaper.setT_total(rs.getFloat("T_TOTAL"));
        testpaper.setT_state(rs.getString("T_STATE"));
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
 
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
    return testpaper;
  }

  public String findT_id(String e_id, String examinee_id) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String t_id = "";
    try {
      pstmt = conn.prepareStatement(FIND_TESTPAPER);
      pstmt.setString(1, e_id);
      pstmt.setString(2, examinee_id);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        t_id = rs.getString("T_ID");
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
   
    } catch (Exception e) {
      e.printStackTrace();
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
    return t_id;
  }

  public void gradeTestPaper(TestPaper t) throws SQLException {
    PreparedStatement pstmt = null;
    String GRADE_TESTPAPER =
        "UPDATE EX_TESTPAPER set T_end=now(), E_autovalue=?, T_total=?,T_state=? where T_ID=?";

    try {
      pstmt = conn.prepareStatement(GRADE_TESTPAPER);
      pstmt.setFloat(1, t.getE_autovalue());
      pstmt.setFloat(2, t.getT_total());
      pstmt.setString(3, t.getT_state());
      pstmt.setLong(4, t.getT_id());
      pstmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
      ex.getStackTrace();
      throw new SQLException("SQLExction on : TestPaperDAO.gradeTestPaper()");

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

  public void setBeginTime(long t_id) {
    PreparedStatement pstmt = null;
    String UPDATE_TESTPAPER_1 =
        "UPDATE EX_TESTPAPER set T_begin=now() where T_ID=?";
    try {
      pstmt = conn.prepareStatement(UPDATE_TESTPAPER_1);
      pstmt.setLong(1, t_id);
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

  public Collection getNotGrade(long e_id, int ipage) throws SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    TestPaper testpaper = null;
    Collection list = null;
    Transformer transformer = new Transformer();
    String t_state1 = transformer.valueToId("已考完未评分");
    transformer = new Transformer();
    String t_state2 = transformer.valueToId("已评分");
    list = new ArrayList();
    String GET_SEARCH_RESULT =
        "select test.examinee_id as examinee_id,test.t_id as t_id,e.name as name,exam.e_name as e_name,DATE_FORMAT(test.t_begin,'%Y-%m-%d %H:%i:%s') as t_begin,DATE_FORMAT(test.t_end,'%Y-%m-%d %H:%i:%s') as t_end,b1.b_value as t_stateName,test.t_state as t_state,test.e_autovalue     as e_autovalue, test.e_manualvalue as e_manualvalue     from ex_examinationpaper exam,ex_testpaper test,ex_examinee e,     ex_baseinfo b1      where e.examinee_id=test.examinee_id and test.e_id=exam.e_id     and b1.b_id=test.t_state and test.e_id=? and (test.t_state='" +
        t_state1 + "' or test.t_state='" + t_state2 + "')";

    try {
      pstmt = conn.prepareStatement(GET_SEARCH_RESULT,
                                    ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE);
      pstmt.setLong(1, e_id);
      rs = pstmt.executeQuery();
      int j = 0;
      while (rs.next()) {
        j++;
      }
      if (false == rs.last()) {
        rowCount = 0;
        pageCount = 0;
        ipage = 0;
        return list;
      }

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

      for (int i = 0; i < pagesize && offset < rowCount + 1; i++, offset++) {
        testpaper = new TestPaper();
        testpaper.setExaminee_id(rs.getString("EXAMINEE_ID"));
        testpaper.setName(rs.getString("NAME"));
        testpaper.setE_name(rs.getString("E_NAME"));
        testpaper.setT_begin(rs.getString("T_BEGIN"));
        testpaper.setT_end(rs.getString("T_END"));
        testpaper.setT_state(rs.getString("T_STATE"));
        testpaper.setE_autovalue(rs.getFloat("E_AUTOVALUE"));
        testpaper.setE_manualvalue(rs.getFloat("E_MANUALVALUE"));
        testpaper.setT_id(rs.getLong("T_ID"));
        rs.next();
        list.add(testpaper);
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

  /**
   * add a row into DB
   * @param testpaper TestPaper
   * @throws SQLException
   */
  public void addTestPaper(TestPaper testpaper) throws SQLException {
    PreparedStatement pstmt = null;
    String ADD_TESTPAPER =
        "INSERT INTO EX_TESTPAPER VALUES ('',?,?,DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s'),DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s'),?,?,?,?,?)";

    try {
      pstmt = conn.prepareStatement(ADD_TESTPAPER);
      pstmt.setString(1, testpaper.getExaminee_id());
      pstmt.setLong(2, testpaper.getE_id());
      pstmt.setString(3, testpaper.getT_begin());
      pstmt.setString(4, testpaper.getT_end());
      pstmt.setDouble(5, testpaper.getT_passvalue());
      pstmt.setDouble(6, testpaper.getE_autovalue());
      pstmt.setDouble(7, testpaper.getE_manualvalue());
      pstmt.setDouble(8, testpaper.getT_total());
      pstmt.setString(9, testpaper.getT_state());
      pstmt.executeUpdate();

    } catch (SQLException ex) {
      ex.printStackTrace();
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

  public void addMany(ArrayList examineeIdList, long e_id, float e_passvalue) throws
      SQLException {
    PreparedStatement pstmt = null;
    Transformer transformer = new Transformer();
    String t_state = transformer.valueToId("待考");

    String ADD_MANY = "insert into EX_TESTPAPER (t_id,e_id,examinee_id,t_state,t_passvalue,t_total) values ('',?,?,?,?,?)";

    try {
      for (int i = 0; i < examineeIdList.size(); i++) {
        pstmt = conn.prepareStatement(ADD_MANY);
        pstmt.setLong(1, e_id);
        pstmt.setString(2, (String) examineeIdList.get(i));
        pstmt.setString(3, t_state);
        pstmt.setFloat(4, e_passvalue);
        pstmt.setFloat(5, -1);
        pstmt.executeUpdate();
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        pstmt.close();
        pstmt = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
      }
    }

  }

  /**
   * remove a row from DB
   * @param testpaper TestPaper
   * @throws SQLException
   */
  public void removeTestPaper(TestPaper testpaper) throws SQLException {
    this.removeTestPaper(testpaper.getT_id());
  }

  /**
   * remove a row from DB
   * @param t_id long
   * @throws SQLException
   */
  public void removeTestPaper(long t_id) throws SQLException {
    Statement stmt = null;
    String REMOVE_TESTPAPER = "DELETE FROM EX_TESTPAPER WHERE T_ID=" + t_id;
    try {
      stmt = conn.createStatement();
      stmt.execute(REMOVE_TESTPAPER);
    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new SQLException(
          "SQLExction on : TestPaperDAO.removeTestPaper()");
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

  public void removeTestPaper_byE_id(long e_id){
    Statement stmt = null;
    String REMOVE_TESTPAPER_BYE_ID = "DELETE FROM EX_TESTPAPER WHERE E_ID=" + e_id;
    try {
      stmt = conn.createStatement();
      stmt.execute(REMOVE_TESTPAPER_BYE_ID);
    } catch (SQLException ex) {
      ex.printStackTrace();
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
   * @param testpaper TestPaper
   * @throws SQLException
   */
  public void updateTestPaper(TestPaper testpaper) throws SQLException {
    PreparedStatement pstmt = null;
    String UPDATE_TESTPAPER =
        "UPDATE EX_TESTPAPER set EXAMINEE_ID=?,E_ID=?,T_BEGIN=DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s'),T_END=DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s'),T_PASSVALUE=?,E_AUTOVALUE=?,E_MANUALVALUE=?,T_TOTAL=?,T_STATE=? where T_ID=?";
    try {
      pstmt = conn.prepareStatement(UPDATE_TESTPAPER);
      pstmt.setString(1, testpaper.getExaminee_id());
      pstmt.setLong(2, testpaper.getE_id());
      pstmt.setString(3, testpaper.getT_begin());
      pstmt.setString(4, testpaper.getT_end());
      pstmt.setDouble(5, testpaper.getT_passvalue());
      pstmt.setDouble(6, testpaper.getE_autovalue());
      pstmt.setDouble(7, testpaper.getE_manualvalue());
      pstmt.setDouble(8, testpaper.getT_total());
      pstmt.setString(9, testpaper.getT_state());
      pstmt.setLong(10, testpaper.getT_id());
      pstmt.executeUpdate();
    } catch (SQLException ex) {
      ex.getStackTrace();
      throw new SQLException(
          "SQLExction on : TestpPaperDAO.updateTestPaper()");
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

  public void updateTestPaper(float manualValue, float totalValue, String state,
                              long t_id) throws SQLException {
    PreparedStatement pstmt = null;
    String UPDATE_TESTPAPER =
        "UPDATE EX_TESTPAPER set E_MANUALVALUE=?,T_TOTAL=?,T_STATE=? where T_ID=?";
    try {
      pstmt = conn.prepareStatement(UPDATE_TESTPAPER);
      pstmt.setFloat(1, manualValue);
      pstmt.setFloat(2, totalValue);
      pstmt.setString(3, state);
      pstmt.setLong(4, t_id);
      pstmt.executeUpdate();
    } catch (SQLException ex) {
      ex.getStackTrace();
      throw new SQLException("SQLExction on : TestpPaperDAO.updateTestPaper()");
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

  public void updateT_state(long t_id,String t_state,String t_begin,String t_end){
    PreparedStatement pstmt = null;
    String UPDATET_STATAE = "UPDATE EX_TESTPAPER SET T_STATE=?,T_BEGIN=DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s'),T_END=DATE_FORMAT(?,'%Y-%m-%d %H:%i:%s') WHERE T_ID=?";
    try {
      pstmt = conn.prepareStatement(UPDATET_STATAE);
      pstmt.setString(1, t_state);
      pstmt.setString(2, t_begin);
      pstmt.setString(3, t_end);
      pstmt.setLong(4, t_id);
      pstmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }finally{
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

  public Vector getT_idList(long e_id) throws SQLException {
    Statement stmt = null;
    ResultSet rs = null;
    Vector list = null;
    String GET_T_IDLIST = "select t_id from ex_testpaper where e_id=" +
        e_id;
    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(GET_T_IDLIST);
      list = new Vector();
      while (rs.next()) {
        list.add(new Long(rs.getLong("T_ID")));
      }
    } catch (SQLException ex) {
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

  public Collection getByE_id(long e_id, int ipage) throws SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    TestPaper testpaper = null;
    Collection list = null;
    list = new ArrayList();

    try {
      pstmt = conn.prepareStatement(GET_BY_EID,
                                    ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE);
      pstmt.setLong(1, e_id);
      rs = pstmt.executeQuery();
      if (false == rs.last()) {
        rowCount = 0;
        pageCount = 0;
        ipage = 0;
        return list;
      }

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

      for (int i = 0; i < pagesize && offset < rowCount + 1; i++, offset++) {
        testpaper = new TestPaper();
        testpaper.setT_id(rs.getLong("T_ID"));
        testpaper.setExaminee_id(rs.getString("EXAMINEE_ID"));
        testpaper.setName(rs.getString("NAME"));
        testpaper.setE_id(rs.getLong("E_ID"));
        testpaper.setT_begin(rs.getString("T_BEGIN"));
        testpaper.setT_end(rs.getString("T_END"));
        testpaper.setT_passvalue(rs.getFloat("T_PASSVALUE"));
        testpaper.setT_total(rs.getFloat("T_TOTAL"));
        testpaper.setT_state(rs.getString("T_STATE"));
        testpaper.setT_stateName(rs.getString("T_STATENAME"));
        testpaper.setE_stateName(rs.getString("E_STATENAME"));
        testpaper.setE_id(rs.getLong("E_ID"));
        testpaper.setE_state(rs.getString("E_STATE"));
        rs.next();
        list.add(testpaper);
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

  public Collection getSearch(long e_id, int ipage) throws SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    TestPaper testpaper = null;
    Collection list = null;
    list = new ArrayList();
    String GET_SEARCH_RESULT =
        "select test.examinee_id as examinee_id,e.name as name,exam.e_name as e_name,test.t_begin as t_begin,test.t_end as t_end,b1.b_value as t_state,test.e_autovalue as e_autovalue, test.e_manualvalue as e_manualvalue from ex_examinationpaper exam,ex_testpaper test,ex_examinee e,ex_baseinfo b1 where e.examinee_id=test.examinee_id and test.e_id=exam.e_id and b1.b_id=test.t_state and test.e_id＝?";

    try {
      pstmt = conn.prepareStatement(GET_SEARCH_RESULT,
                                    ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE);
      pstmt.setLong(1, e_id);
      rs = pstmt.executeQuery();

      if (false == rs.last()) {
        rowCount = 0;
        pageCount = 0;
        ipage = 0;
        return list;
      }

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

      for (int i = 0; i < pagesize && offset < rowCount + 1; i++, offset++) {
        testpaper = new TestPaper();
        testpaper.setExaminee_id(rs.getString("EXAMINEE_ID"));
        testpaper.setName(rs.getString("NAME"));
        testpaper.setE_name(rs.getString("E_NAME"));
        testpaper.setT_begin(rs.getString("T_BEGIN"));
        testpaper.setT_end(rs.getString("T_END"));
        testpaper.setT_state(rs.getString("T_STATE"));
        testpaper.setE_autovalue(rs.getFloat("E_AUTOVALUE"));
        testpaper.setE_manualvalue(rs.getFloat("E_MANUALVALUE"));
        rs.next();
        list.add(testpaper);
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

  public Collection sorting(long e_id, int ipage) throws
      SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    TestPaper testpaper = null;
    Collection list = null;
    list = new ArrayList();

    try {
      pstmt = conn.prepareStatement(SORTING,
                                    ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE);
      pstmt.setLong(1, e_id);
      rs = pstmt.executeQuery();

      if (false == rs.last()) {
        rowCount = 0;
        pageCount = 0;
        ipage = 0;
        return list;
      }

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

      for (int i = 0; i < pagesize && offset < rowCount + 1; i++, offset++) {
        testpaper = new TestPaper();
        testpaper.setExaminee_id(rs.getString("EXAMINEE_ID"));
        testpaper.setE_name(rs.getString("E_NAME"));
        testpaper.setT_total(rs.getFloat("T_TOTAL"));
        testpaper.setName(rs.getString("NAME"));
        rs.next();
        list.add(testpaper);
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

  public Collection statistic(long e_id, float e_total, float e_passvalue) throws
      SQLException {
    Statement stmt = null;
    TestPaper testpaper = null;
    ResultSet rs = null;
    Collection list = null;
    list = new ArrayList();

    //get the full score examinees
    testpaper = new TestPaper();
        String findFullScore =
            "select distinct exam.e_name as e_name,round((100*(select count(*) from ex_testpaper test where test.t_total=" +
            e_total + " and test.e_id=" +
            e_id +
            ")/(select count(*) from ex_testpaper test where test.e_id=" +
            e_id + ")),1)||'%' as e_percent,(select count(*) from ex_testpaper test where test.t_total=" +
            e_total + " and test.e_id=" +
            e_id +
            ") as subSum from ex_testpaper test,ex_examinationpaper exam where test.e_id=exam.e_id and test.e_id=" +
            e_id;

        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery(findFullScore);
        while (rs.next()) {
          testpaper.setScore_area("满分");
          testpaper.setE_name(rs.getString("E_NAME"));
          testpaper.setE_percent(rs.getString("E_PERCENT"));
          testpaper.setSubSum(rs.getInt("SUBSUM"));
          list.add(testpaper);
        }

    //get the rest examinees
    try {
      for (float i = e_total; i >= 0; i = i - 10) {
        testpaper = new TestPaper();
        String score_area;
        float j = 0;
        if (i > e_passvalue) {
          j = i - 10;
          if(j < 0){
            j = 0;
          }
        } else {
          j = 0;
        }
        score_area = j + "-" + i;

        String STA =
            "select distinct exam.e_name as e_name,round((100*(select count(*) from ex_testpaper test where test.t_total<" +
            i + " and test.t_total>=" + j + " and test.e_id=" +
            e_id +
            ")/(select count(*) from ex_testpaper test where test.e_id=" +
            e_id + ")),1)||'%' as e_percent,(select count(*) from ex_testpaper test where test.t_total<" +
            i + " and test.t_total>=" + j + " and test.e_id=" +
            e_id +
            ") as subSum from ex_testpaper test,ex_examinationpaper exam where test.e_id=exam.e_id and test.e_id=" +
            e_id;

        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE);
        rs = stmt.executeQuery(STA);
        while (rs.next()) {
          testpaper.setScore_area(score_area);
          testpaper.setE_name(rs.getString("E_NAME"));
          testpaper.setE_percent(rs.getString("E_PERCENT"));
          testpaper.setSubSum(rs.getInt("SUBSUM"));
          list.add(testpaper);
        }
        if (i <= e_passvalue) {
          break;
        }
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

  public Collection findAbsence(long e_id) throws
      SQLException {
    PreparedStatement pstmt = null;
    TestPaper testpaper = null;
    ResultSet rs = null;
    Collection list = null;
    list = new ArrayList();

    try {
      pstmt = conn.prepareStatement(FIND_ABSENCE);
      pstmt.setLong(1, e_id);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        testpaper = new TestPaper();
        testpaper.setExaminee_id(rs.getString("EXAMINEE_ID"));
        testpaper.setName(rs.getString("NAME"));
        list.add(testpaper);
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
}
