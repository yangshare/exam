package org.mmxbb.exam.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.mmxbb.exam.bean.ExaminationPaper;
import org.mmxbb.exam.business.program.RandomDistributeExamPaperForm;
import org.mmxbb.exam.business.program.SearchActionForm;
import org.mmxbb.exam.util.DBConn;
import org.mmxbb.exam.util.GetQuestionCount;


public class ExaminationPaperDAO {
  private final static String GETALL_EXAMINATIONPAPER =
      "select * from EX_EXAMINATIONPAPER";
  private final static String GET_SHARCH_RESULT =
      "select e.e_id as e_id,e.e_name as e_name,e.e_type as e_type,b.b_value as e_typename,date_format(e.e_begin,'%Y-%m-%d') as e_begin,date_format(e.e_end,'%Y-%m-%d') as e_end,e.e_total as e_total,e.e_passvalue as e_passvalue,e.e_timer as e_timer,t.t_state as t_state from ex_examinationpaper e,ex_baseinfo b,ex_testpaper t where b.b_id=e.e_type and e.e_id=t.e_id and t.examinee_id=? and (now())<e.e_end and (now())>e.e_begin and t_state='096' ORDER BY E_ID DESC";
  private final static String UPDATE_PAPER =
      "UPDATE EX_EXAMINATIONPAPER SET E_NAME=?,E_TYPE=?,E_TIMER=?,E_IDLIST=?,E_BEGIN=date_format(?,'%Y-%m-%d'),E_END=date_format(?,'%Y-%m-%d'),E_EXAMINEELIST=?,E_PASSVALUE=?,E_TOTAL=?,E_STATE=? WHERE E_ID=?";
  private final static String UPDATE_PAPER2 =
      "UPDATE EX_EXAMINATIONPAPER SET E_NAME=?,E_TYPE=?,E_TIMER=?,E_IDLIST=?,E_BEGIN=(now()),E_END=(DATE_ADD(now(),INTERVAL 10 DAY )),E_EXAMINEELIST=?,E_PASSVALUE=?,E_TOTAL=?,E_STATE=? WHERE E_ID=?";
  private final static String UPDATE_PAPER_BASEINFO =
      "UPDATE EX_EXAMINATIONPAPER SET E_NAME=?,E_TYPE=?,E_TIMER=?,E_BEGIN=date_format(?,'%Y-%m-%d'),E_END=date_format(?,'%Y-%m-%d'),,E_PASSVALUE=?,E_TOTAL=?,WHERE E_ID=?";
  private final static String ADDPAPER =
      "INSERT INTO EX_EXAMINATIONPAPER (E_ID,E_IDLIST,E_BEGIN,E_END) VALUES ('','@@0@@0@@0@@0',(now()),(DATE_ADD(now(),INTERVAL 1 DAY )))";
  private final static String REMOVEEXAMINATIONPAPERBYKEY =
      "delete from EX_EXAMINATIONPAPER WHERE E_ID=?";
  private final static String UPDATE_E_STATE =
      "update ex_examinationpaper set e_state=? where e_id=?";
  private final static String GETBYKEY =
      "SELECT exam.e_id as e_id,exam.e_name as e_name,exam.e_type as e_type,exam.e_timer as e_timer,exam.e_idlist as e_idlist,date_format(exam.e_begin,'%Y-%m-%d') as e_begin,date_format(exam.e_end,'%Y-%m-%d') as e_end,exam.e_examineeList as e_examineeList,exam.e_passvalue as e_passvalue,exam.e_total as e_total,b2.b_value as e_state FROM EX_EXAMINATIONPAPER exam,EX_BASEINFO b2  WHERE exam.e_state=b2.b_id and exam.e_id=?";
  private final static String FINDBYE_STATE =
      "SELECT E_ID,E_NAME,E_TYPE,E_TIMER,E_IDLIST,date_format(E_BEGIN,'%Y-%m-%d') as E_BEGIN,date_format(E_END,'%Y-%m-%d') as E_END,E_EXAMINEELIST,E_PASSVALUE,E_TOTAL,E_STATE FROM EX_EXAMINATIONPAPER where e_state IS NULL";
  private final static String GET_BY_KEY_WHEN_ESTATE_NULL =
      "select e_id,e_name,e_type,e_timer,date_format(e_begin,'%Y-%m-%d') as e_begin,date_format(e_end,'%Y-%m-%d') as e_end,e_idlist,e_examineelist,e_passvalue,e_total,e_state from ex_examinationpaper where e_id=?";
  private final static String GET_IDLIST =
      "SELECT E_IDLIST FROM EX_EXAMINATIONPAPER WHERE E_ID = ?";
  private final static String CLEAR_IDLIST =
      "UPDATE EX_EXAMINATIONPAPER SET E_IDLIST = ? WHERE E_ID = ?";
  private final static String REMOVEEXAMINEE_LIST =
      "UPDATE EX_EXAMINATIONPAPER SET E_EXAMINEELIST='' WHERE E_ID=?";
  private final static String UPDATE_E_STATE_UNVALIDATE_EXAMPAPER =
      "UPDATE EX_EXAMINATIONPAPER SET e_state='040' where e_end<(now())";

  private Connection conn;
  private DBConn dbconn;
  private int length = 3;
  private int rowCount;
  private int pageCount;
  private String pagestr;
  private String conditionStr = "";

  //Connect to db!!
  public ExaminationPaperDAO() {
    dbconn = new DBConn();
    conn = dbconn.getConnection();
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getLength() {
    return length;
  }

  public String getConditionStr() {
    return (this.conditionStr);
  }

  public void setConditionStr(String conditionStr) {
    this.conditionStr = conditionStr;
  }

  //Add ExaminationPaper!
  public void addExamintionPaper(ExaminationPaper examinationPaper) {
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(ADDPAPER);
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

  public Collection getCanGrade(SearchActionForm searchForm, int ipage) throws
      SQLException {

    ArrayList src = (ArrayList)this.getSearch(searchForm, ipage);

    Collection res = new ArrayList();
    ExaminationPaper ePaper = new ExaminationPaper();
    for (int i = 0; i < src.size(); i++) {
      ePaper = (ExaminationPaper) src.get(i);

      if (ePaper.getE_state().equals("进行中") || ePaper.getE_state().equals("已结束")) {
        res.add(src.get(i));
      }
    }

    this.rowCount = res.size();
    int pagesize = getLength();
    if (getLength() < 1) {
      pagesize = rowCount;
      this.pageCount = 1;
    } else {
      this.pageCount = rowCount / getLength() +
          ( (rowCount % getLength()) > 0 ? 1 : 0);
    }
    return res;
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
            "<a href='searchAction.do?page=";
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

  public String getPagestr_(int ipage) {
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
            "<a href='examinationPaperListAction.do?page=";
        strPage += String.valueOf(i + 1);
        strPage += "'>";
        strPage += String.valueOf(i + 1);
        strPage += "</a>";
        strPage += "  ";
      }
    }
    this.pagestr = strPage;
    return strPage;
  }

  //Remove Examinationpaper!!
  public void removeExaminationPaper(ExaminationPaper examinationPaper) {
    removeExaminationPaper(examinationPaper.getE_id());
  }

  //Remove by key!!
  public void removeExaminationPaper(long e_id) {
    PreparedStatement pstmt = null;

    try {
      pstmt = conn.prepareStatement(REMOVEEXAMINATIONPAPERBYKEY);
      pstmt.setLong(1, e_id);
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

//Update E_state!!
  public void updateE_state(long e_id, String e_state) {
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(UPDATE_E_STATE);
      pstmt.setString(1, e_state);
      pstmt.setLong(2, e_id);
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

  public void removeExaminee_list(long e_id){
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(REMOVEEXAMINEE_LIST);
      pstmt.setLong(1, e_id);
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

  //Update ExaminationPaper!!
  public void updateExaminationPaper(ExaminationPaper examinationPaper) {
    PreparedStatement pstmt = null;
    try {
      if (null == examinationPaper.getE_begin() ||
          "".equals(examinationPaper.getE_begin())) {
        pstmt = conn.prepareStatement(UPDATE_PAPER2);
        pstmt.setString(1, examinationPaper.getE_name());
        pstmt.setString(2, examinationPaper.getE_type());
        pstmt.setInt(3, examinationPaper.getE_timer());
        pstmt.setString(4, examinationPaper.getE_idlist());
        pstmt.setString(5, examinationPaper.getE_examineeList());
        pstmt.setFloat(6, examinationPaper.getE_passvalue());
        pstmt.setFloat(7, examinationPaper.getE_total());
        pstmt.setString(8, examinationPaper.getE_state());
        pstmt.setLong(9, examinationPaper.getE_id());
        pstmt.executeUpdate();
      } else {
        pstmt = conn.prepareStatement(UPDATE_PAPER);
        pstmt.setString(1, examinationPaper.getE_name());
        pstmt.setString(2, examinationPaper.getE_type());
        pstmt.setInt(3, examinationPaper.getE_timer());
        pstmt.setString(4, examinationPaper.getE_idlist());
        pstmt.setString(5, examinationPaper.getE_begin());
        pstmt.setString(6, examinationPaper.getE_end());
        pstmt.setString(7, examinationPaper.getE_examineeList());
        pstmt.setFloat(8, examinationPaper.getE_passvalue());
        pstmt.setFloat(9, examinationPaper.getE_total());
        pstmt.setString(10, examinationPaper.getE_state());
        pstmt.setLong(11, examinationPaper.getE_id());
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
        ex1.printStackTrace();
      }
    }
  }

  public void updateE_stateUnvalidateExamPaper(){
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(UPDATE_E_STATE_UNVALIDATE_EXAMPAPER);
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

  public void updatePaperBaseinfo(ExaminationPaper examinationPaper) {
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(UPDATE_PAPER_BASEINFO);
      pstmt.setString(1, examinationPaper.getE_name());
      pstmt.setString(2, examinationPaper.getE_type());
      pstmt.setInt(3, examinationPaper.getE_timer());
      pstmt.setString(4, examinationPaper.getE_begin());
      pstmt.setString(5, examinationPaper.getE_end());
      pstmt.setFloat(6, examinationPaper.getE_passvalue());
      pstmt.setFloat(7, examinationPaper.getE_total());
      pstmt.setLong(8, examinationPaper.getE_id());
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

  /**
   * clearIdList
   *
   * @param e_id String
   * @param action String
   */
  public void clearIdList(String e_id, String action) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    GetQuestionCount gqc = null;
    String[] questionTypes = null;
    try {
      pstmt = conn.prepareStatement(GET_IDLIST);
      pstmt.setString(1, e_id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        String idList = rs.getString(1);
        gqc = new GetQuestionCount();
        questionTypes = gqc.GetTypeIdListWithoutDecorate(idList);
        if ("single".equals(action)) {
          questionTypes[0] = "0";
        } else if ("multi".equals(action)) {
          questionTypes[1] = "0";
        } else if ("fitin".equals(action)) {
          questionTypes[2] = "0";
        } else if ("answer".equals(action)) {
          questionTypes[3] = "0";
        }
        idList = "@@" + questionTypes[0] + "@@" + questionTypes[1] + "@@" +
            questionTypes[2] + "@@" +
            questionTypes[3];
        pstmt.close();
        pstmt = null;

        pstmt = conn.prepareStatement(CLEAR_IDLIST);
        pstmt.setString(1, idList);
        pstmt.setString(2, e_id);
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
        ex1.printStackTrace();
      }
    }
  }

  //Get by String!!
  public ExaminationPaper findBykey(long e_id) {

    PreparedStatement pstmt = null;
    ExaminationPaper examinationPaper = null;
    ResultSet rs = null;
    try {
      pstmt = conn.prepareStatement(GETBYKEY);
      pstmt.setLong(1, e_id);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        examinationPaper = new ExaminationPaper();
        examinationPaper.setE_name(rs.getString("E_NAME"));
        examinationPaper.setE_id(rs.getLong("E_ID"));
        examinationPaper.setE_type(rs.getString("E_TYPE"));
        examinationPaper.setE_timer(rs.getInt("E_TIMER"));
        examinationPaper.setE_idlist(rs.getString("E_IDLIST"));
        examinationPaper.setE_begin(rs.getString("E_BEGIN"));
        examinationPaper.setE_end(rs.getString("E_END"));
        examinationPaper.setE_examineeList(rs.getString(
            "E_EXAMINEELIST"));
        examinationPaper.setE_passvalue(rs.getFloat("E_PASSVALUE"));
        examinationPaper.setE_total(rs.getFloat("E_TOTAL"));
        examinationPaper.setE_state(rs.getString("E_STATE"));
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
    return examinationPaper;
  }

  //getByKeyWhenE_stateNull!!
  public ExaminationPaper getByKeyWhenE_stateNull(long e_id) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ExaminationPaper examinationPaper = new ExaminationPaper();

    try {
      pstmt = conn.prepareStatement(GET_BY_KEY_WHEN_ESTATE_NULL);
      pstmt.setLong(1, e_id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        examinationPaper.setE_id(rs.getLong("e_id"));
        examinationPaper.setE_name(rs.getString("e_name"));
        examinationPaper.setE_type(rs.getString("e_type"));
        examinationPaper.setE_timer(rs.getInt("e_timer"));
        examinationPaper.setE_begin(rs.getString("e_begin"));
        examinationPaper.setE_end(rs.getString("e_end"));
        examinationPaper.setE_idlist(rs.getString("e_idlist"));
        examinationPaper.setE_examineeList(rs.getString("e_examineelist"));
        examinationPaper.setE_passvalue(rs.getFloat("e_passvalue"));
        examinationPaper.setE_total(rs.getFloat("e_total"));
        examinationPaper.setE_state(rs.getString("e_state"));

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
    return examinationPaper;
  }

  //GetAll ExaminationPaper!!
  public Collection getAll() {
    PreparedStatement pstmt = null;
    ExaminationPaper examinationPaper = new ExaminationPaper();
    ResultSet rs = null;
    Collection list = null;
    try {
      list = new ArrayList();
      pstmt = conn.prepareStatement(GETALL_EXAMINATIONPAPER);
      rs = pstmt.executeQuery();

      while (rs.next()) {
        examinationPaper.setE_type(rs.getString("E_TYPE"));
        examinationPaper.setE_name(rs.getString("E_NAME"));
        examinationPaper.setE_passvalue(rs.getFloat("E_PASSVALUE"));
        examinationPaper.setE_id(rs.getLong("E_ID"));
        examinationPaper.setE_timer(rs.getInt("E_TIMER"));
        examinationPaper.setE_idlist(rs.getString("E_IDLIST"));
        examinationPaper.setE_begin(rs.getString("E_BEGIN"));
        examinationPaper.setE_end(rs.getString("E_END"));
        examinationPaper.setE_examineeList(rs.getString(
            "E_EXAMINEELIST"));
        examinationPaper.setE_total(rs.getInt("E_TOTAL"));
        examinationPaper.setE_state(rs.getString("E_STATE"));
        list.add(examinationPaper);
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

  public ExaminationPaper findByE_state() {
    ExaminationPaper examinationPaper = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
      pstmt = conn.prepareStatement(FINDBYE_STATE);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        examinationPaper = new ExaminationPaper();
        examinationPaper.setE_id(rs.getLong("E_ID"));
        examinationPaper.setE_name(rs.getString("E_NAME"));
        examinationPaper.setE_type(rs.getString("E_TYPE"));
        examinationPaper.setE_timer(rs.getInt("E_TIMER"));
        examinationPaper.setE_idlist(rs.getString("E_IDLIST"));
        examinationPaper.setE_begin(rs.getString("E_BEGIN"));
        examinationPaper.setE_end(rs.getString("E_END"));
        examinationPaper.setE_examineeList(rs.getString(
            "E_EXAMINEELIST"));
        examinationPaper.setE_passvalue(rs.getFloat("E_PASSVALUE"));
        examinationPaper.setE_total(rs.getInt("E_TOTAL"));
        examinationPaper.setE_state(rs.getString("E_STATE"));
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
    return examinationPaper;
  }

  //get search!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  public Collection getSearch(SearchActionForm searchForm, int ipage) throws
      SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ExaminationPaper testpaper = null;
    Collection list = null;
    list = new ArrayList();

    String e_type = searchForm.getE_type();
    String e_name = searchForm.getE_name();
    String e_state = searchForm.getE_state();
    String e_begin = searchForm.getE_begin();
    String e_end = searchForm.getE_end();

    String GET_SEARCH_RESULT_NOTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATENAME,E.E_STATE AS E_STATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_TYPE LIKE ? AND e_name LIKE ? AND e_state LIKE ? order by e.e_begin desc";
    String GET_SEARCH_RESULT_NOENDTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATENAME,E.E_STATE AS E_STATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_BEGIN>=date_format('" +
        e_begin +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND e_state LIKE ? order by e.e_begin desc";
    String GET_SEARCH_RESULT_NOBEGINTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATENAME,E.E_STATE AS E_STATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_END<=date_format('" +
        e_end +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND e_state LIKE ? order by e.e_begin desc";
    String GET_SEARCH_RESULT_BOTHTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATENAME,E.E_STATE AS E_STATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_BEGIN>=date_format('" +
        e_begin + "','%Y-%m-%d') and e_end<=date_format('" + e_end +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND e_state LIKE ? order by e.e_begin desc";

    if ("".equals(e_type) ||
        (e_type == null)) {
      e_type = "%";
    } else {
      conditionStr += "&e_type=" + e_type;
    }

    if ( ("".equals(e_name)) ||
        (e_name == null)) {
      e_name = "%";
    } else {
      conditionStr += "&e_name=" + e_name;
    }

    if ( ("".equals(e_state)) ||
        (e_state == null)) {
      e_state = "%";
    } else {
      conditionStr += "&e_state=" + e_state;
    }

    try {
      if ( ("".equals(e_begin)) || (e_begin == null))

      {
        if ( ("".equals(e_end)) || (e_end == null)) {
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        } else {
          conditionStr += "&e_end=" + e_end;
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOBEGINTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        }
      } else {
        conditionStr += "&e_begin=" + e_begin;
        if ( ("".equals(e_end)) || (e_end == null)) {
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOENDTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        } else {
          conditionStr += "&e_end=" + e_end;
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_BOTHTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        }
      }
      pstmt.setString(1, e_type);
      pstmt.setString(2, e_name);
      pstmt.setString(3, e_state);
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

      rs.absolute( -1);
      this.rowCount = rs.getRow();
      int offset = 1;
      int pagesize = getLength();
      if (getLength() < 1) {
        pagesize = rowCount;
        this.pageCount = 1;
      } else {
        this.pageCount = rowCount / getLength() +
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
        testpaper = new ExaminationPaper();
        testpaper.setE_id(rs.getLong("E_ID"));
        testpaper.setE_type(rs.getString("E_TYPE"));
        testpaper.setE_name(rs.getString("E_NAME"));
        testpaper.setE_passvalue(rs.getFloat("E_PASSVALUE"));
        testpaper.setE_begin(rs.getString("E_BEGIN"));
        testpaper.setE_end(rs.getString("E_END"));
        testpaper.setE_state(rs.getString("E_STATE"));
        testpaper.setE_total(rs.getFloat("E_TOTAL"));
        testpaper.setE_stateName(rs.getString("E_STATENAME"));
        testpaper.setE_examineeList(rs.getString("E_EXAMINEELIST") == null ? "" :
                                    rs.getString("E_EXAMINEELIST"));

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

  public Collection getSearchCanGrade(SearchActionForm searchForm, int ipage) throws
      SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ExaminationPaper testpaper = null;
    Collection list = null;
    list = new ArrayList();

    String e_type = searchForm.getE_type();
    String e_name = searchForm.getE_name();
    String e_state = searchForm.getE_state();
    String e_begin = searchForm.getE_begin();
    String e_end = searchForm.getE_end();

    String GET_SEARCH_RESULT_NOTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_TYPE LIKE ? AND e_name LIKE ? AND (e_state='040' or e_state='041') order by e.e_begin desc";
    String GET_SEARCH_RESULT_NOENDTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_BEGIN>=date_format('" +
        e_begin +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND (e_state='040' or e_state='041') order by e.e_begin desc";
    String GET_SEARCH_RESULT_NOBEGINTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_END<=date_format('" +
        e_end +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND (e_state='040' or e_state='041') order by e.e_begin desc";
    String GET_SEARCH_RESULT_BOTHTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE E.E_STATE E_STATEID FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_BEGIN>=date_format('" +
        e_begin + "','%Y-%m-%d') and e_end<=date_format('" + e_end +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND (e_state='040' or e_state='041') order by e.e_begin desc";

    if ("".equals(e_type) ||
        (e_type == null)) {
      e_type = "%";
    } else {
      conditionStr += "&e_type=" + e_type;
    }

    if ( ("".equals(e_name)) ||
        (e_name == null)) {
      e_name = "%";
    } else {
      conditionStr += "&e_name=" + e_name;
    }

    if ( ("".equals(e_state)) ||
        (e_state == null)) {
      e_state = "%";
    } else {
      conditionStr += "&e_state=" + e_state;
    }

    try {
      if ( ("".equals(e_begin)) || (e_begin == null))

      {
        if ( ("".equals(e_end)) || (e_end == null)) {
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        } else {
          conditionStr += "&e_end=" + e_end;
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOBEGINTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        }
      } else {
        conditionStr += "&e_begin=" + e_begin;
        if ( ("".equals(e_end)) || (e_end == null)) {
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOENDTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        } else {
          conditionStr += "&e_end=" + e_end;
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_BOTHTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        }
      }
      pstmt.setString(1, e_type);
      pstmt.setString(2, e_name);
      rs = pstmt.executeQuery();

      if (false == rs.last()) {
        rowCount = 0;
        pageCount = 0;
        ipage = 0;
        return list;
      }

      rs.absolute( -1);
      this.rowCount = rs.getRow();
      int offset = 1;

      int pagesize = getLength();
      if (getLength() < 1) {
        pagesize = rowCount;
        this.pageCount = 1;
      } else {
        this.pageCount = rowCount / getLength() +
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

        testpaper = new ExaminationPaper();

        testpaper.setE_id(rs.getLong("E_ID"));
        testpaper.setE_type(rs.getString("E_TYPE"));
        testpaper.setE_name(rs.getString("E_NAME"));
        testpaper.setE_passvalue(rs.getFloat("E_PASSVALUE"));
        testpaper.setE_begin(rs.getString("E_BEGIN"));
        testpaper.setE_end(rs.getString("E_END"));
        testpaper.setE_state(rs.getString("E_STATE"));
        testpaper.setE_total(rs.getFloat("E_TOTAL"));
        testpaper.setE_examineeList(rs.getString("E_EXAMINEELIST") == null ? "" :
                                    rs.getString("E_EXAMINEELIST"));

        list.add(testpaper);
        rs.next();
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


  public Collection getSearch(String examinee_id, int ipage) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ExaminationPaper examinationPaper = null;
    Collection list = null;
    list = new ArrayList();

    if (examinee_id == null || "".equals(examinee_id)) {
      return null;
    }
    try {
      pstmt = conn.prepareStatement(GET_SHARCH_RESULT,
                                    ResultSet.TYPE_SCROLL_SENSITIVE,
                                    ResultSet.CONCUR_UPDATABLE);
      pstmt.setString(1, examinee_id);
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
        examinationPaper = new ExaminationPaper();
        examinationPaper.setE_id(rs.getLong("e_id"));
        examinationPaper.setE_type(rs.getString("e_type"));
        examinationPaper.setE_typeName(rs.getString("e_typename"));
        examinationPaper.setE_name(rs.getString("e_name"));
        examinationPaper.setT_begin(rs.getString("e_begin"));
        examinationPaper.setT_end(rs.getString("e_end"));
        examinationPaper.setE_passvalue(rs.getFloat("e_passvalue"));
        examinationPaper.setE_total(rs.getFloat("e_total"));
        examinationPaper.setE_timer(rs.getInt("e_timer"));

        rs.next();
        list.add(examinationPaper);
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

  public Collection getValidateExaminationPaper(SearchActionForm searchForm,int ipage)throws
      SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ExaminationPaper testpaper = null;
    Collection list = null;
    list = new ArrayList();

    String e_type = searchForm.getE_type();
    String e_name = searchForm.getE_name();
    String e_state = searchForm.getE_state();
    String e_begin = searchForm.getE_begin();
    String e_end = searchForm.getE_end();

    String GET_SEARCH_RESULT_NOTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE,E.E_STATE AS ESTATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE E.E_END>(now()) AND B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_TYPE LIKE ? AND e_name LIKE ? AND e_state LIKE ? order by e.e_begin desc";
    String GET_SEARCH_RESULT_NOENDTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE,E.E_STATE AS ESTATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE E.E_END>(now()) AND B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_BEGIN>=date_format('" +
        e_begin +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND e_state LIKE ? order by e.e_begin desc";
    String GET_SEARCH_RESULT_NOBEGINTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE,E.E_STATE AS ESTATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE E.E_END>(now()) AND B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_END<=date_format('" +
        e_end +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND e_state LIKE ? order by e.e_begin desc";
    String GET_SEARCH_RESULT_BOTHTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE,E.E_STATE AS ESTATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE E.E_END>(now()) AND B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_BEGIN>=date_format('" +
        e_begin + "','%Y-%m-%d') and e_end<=date_format('" + e_end +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND e_state LIKE ? order by e.e_begin desc";

    if ("".equals(e_type) ||
        (e_type == null)) {
      e_type = "%";
    } else {
      conditionStr += "&e_type=" + e_type;
    }

    if ( ("".equals(e_name)) ||
        (e_name == null)) {
      e_name = "%";
    } else {
      conditionStr += "&e_name=" + e_name;
    }

    if ( ("".equals(e_state)) ||
        (e_state == null)) {
      e_state = "%";
    } else {
      conditionStr += "&e_state=" + e_state;
    }

    try {
      if ( ("".equals(e_begin)) || (e_begin == null))

      {
        if ( ("".equals(e_end)) || (e_end == null)) {
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        } else {
          conditionStr += "&e_end=" + e_end;
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOBEGINTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        }
      } else {
        conditionStr += "&e_begin=" + e_begin;
        if ( ("".equals(e_end)) || (e_end == null)) {
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOENDTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        } else {
          conditionStr += "&e_end=" + e_end;
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_BOTHTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        }
      }
      pstmt.setString(1, e_type);
      pstmt.setString(2, e_name);
      pstmt.setString(3, e_state);
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

      rs.absolute( -1);
      this.rowCount = rs.getRow();
      int offset = 1;
      int pagesize = getLength();
      if (getLength() < 1) {
        pagesize = rowCount;
        this.pageCount = 1;
      } else {
        this.pageCount = rowCount / getLength() +
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
        testpaper = new ExaminationPaper();
        testpaper.setE_id(rs.getLong("E_ID"));
        testpaper.setE_type(rs.getString("E_TYPE"));
        testpaper.setE_name(rs.getString("E_NAME"));
        testpaper.setE_passvalue(rs.getFloat("E_PASSVALUE"));
        testpaper.setE_begin(rs.getString("E_BEGIN"));
        testpaper.setE_end(rs.getString("E_END"));
        testpaper.setE_state(rs.getString("ESTATE"));
        testpaper.setE_stateName(rs.getString("E_STATE"));
        testpaper.setE_total(rs.getFloat("E_TOTAL"));
        testpaper.setE_examineeList(rs.getString("E_EXAMINEELIST") == null ? "" :
                                    rs.getString("E_EXAMINEELIST"));

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

  public Collection getValidateExaminationPaper(RandomDistributeExamPaperForm randomDistributeExamPaperForm)throws
      SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ExaminationPaper testpaper = null;
    Collection list = null;
    list = new ArrayList();

    String e_type = randomDistributeExamPaperForm.getE_type();
    String e_name = randomDistributeExamPaperForm.getE_name();
    String e_state = randomDistributeExamPaperForm.getE_state();
    String e_begin = randomDistributeExamPaperForm.getE_begin();
    String e_end = randomDistributeExamPaperForm.getE_end();

    String GET_SEARCH_RESULT_NOTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE E.E_END>(now()) AND B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_TYPE LIKE ? AND e_name LIKE ? AND e_state='038'";
    String GET_SEARCH_RESULT_NOENDTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE E.E_END>(now) AND B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_BEGIN>=date_format('" +
        e_begin +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND e_state='038'";
    String GET_SEARCH_RESULT_NOBEGINTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE E.E_END>(now()) AND B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_END<=date_format('" +
        e_end +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND e_state='038'";
    String GET_SEARCH_RESULT_BOTHTIME =
        "SELECT E.E_ID E_ID,E.E_NAME E_NAME,B1.B_VALUE E_TYPE,E.E_TIMER E_TIMER,E.E_IDLIST E_IDLIST,date_format(E.E_BEGIN,'%Y-%m-%d') E_BEGIN,date_format(E.E_END,'%Y-%m-%d') E_END,E.E_EXAMINEELIST E_EXAMINEELIST,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,B2.B_VALUE E_STATE FROM EX_EXAMINATIONPAPER E,EX_BASEINFO B1,EX_BASEINFO B2 WHERE E.E_END>(now()) AND B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND E_BEGIN>=date_format('" +
        e_begin + "','%Y-%m-%d') and e_end<=date_format('" + e_end +
        "','%Y-%m-%d') and e_type LIKE ? AND e_name LIKE ? AND e_state='038'";

    if ("".equals(e_type) ||
        (e_type == null)) {
      e_type = "%";
    } else {
      conditionStr += "&e_type=" + e_type;
    }

    if ( ("".equals(e_name)) ||
        (e_name == null)) {
      e_name = "%";
    } else {
      conditionStr += "&e_name=" + e_name;
    }

    if ( ("".equals(e_state)) ||
        (e_state == null)) {
      e_state = "%";
    } else {
      conditionStr += "&e_state=" + e_state;
    }

    try {
      if ( ("".equals(e_begin)) || (e_begin == null))

      {
        if ( ("".equals(e_end)) || (e_end == null)) {
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        } else {
          conditionStr += "&e_end=" + e_end;
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOBEGINTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        }
      } else {
        conditionStr += "&e_begin=" + e_begin;
        if ( ("".equals(e_end)) || (e_end == null)) {
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_NOENDTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        } else {
          conditionStr += "&e_end=" + e_end;
          pstmt = conn.prepareStatement(GET_SEARCH_RESULT_BOTHTIME,
                                        ResultSet.
                                        TYPE_SCROLL_SENSITIVE,
                                        ResultSet.CONCUR_UPDATABLE);
        }
      }
      pstmt.setString(1, e_type);
      pstmt.setString(2, e_name);
      rs = pstmt.executeQuery();

      /*int j = 0;
      while (rs.next()) {
        j++;
      }

      if (false == rs.last()) {
        rowCount = 0;
        pageCount = 0;
        ipage = 0;
        return list;
      }

      rs.absolute( -1);
      this.rowCount = rs.getRow();
      int offset = 1;
      int pagesize = getLength();
      if (getLength() < 1) {
        pagesize = rowCount;
        this.pageCount = 1;
      } else {
        this.pageCount = rowCount / getLength() +
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
        testpaper = new ExaminationPaper();
        testpaper.setE_id(rs.getLong("E_ID"));
        testpaper.setE_type(rs.getString("E_TYPE"));
        testpaper.setE_name(rs.getString("E_NAME"));
        testpaper.setE_passvalue(rs.getFloat("E_PASSVALUE"));
        testpaper.setE_begin(rs.getString("E_BEGIN"));
        testpaper.setE_end(rs.getString("E_END"));
        testpaper.setE_state(rs.getString("E_STATE"));
        testpaper.setE_total(rs.getFloat("E_TOTAL"));
        testpaper.setE_examineeList(rs.getString("E_EXAMINEELIST") == null ? "" :
                                    rs.getString("E_EXAMINEELIST"));

        rs.next();
        list.add(testpaper);
      }*/
  while(rs.next()){
    testpaper = new ExaminationPaper();
        testpaper.setE_id(rs.getLong("E_ID"));
        testpaper.setE_type(rs.getString("E_TYPE"));
        testpaper.setE_name(rs.getString("E_NAME"));
        testpaper.setE_passvalue(rs.getFloat("E_PASSVALUE"));
        testpaper.setE_begin(rs.getString("E_BEGIN"));
        testpaper.setE_end(rs.getString("E_END"));
        testpaper.setE_state(rs.getString("E_STATE"));
        testpaper.setE_total(rs.getFloat("E_TOTAL"));
        testpaper.setE_examineeList(rs.getString("E_EXAMINEELIST") == null ? "" :
                                    rs.getString("E_EXAMINEELIST"));

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
