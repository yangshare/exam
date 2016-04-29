package org.mmxbb.exam.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.mmxbb.exam.bean.TestPaper;
import org.mmxbb.exam.util.DBConn;


public class ExamineeTestDetailsDAO {
  DBConn dbconn = null;
  private Connection conn = null;

  private int rowCount;
  private int pageCount;
  private int length;
  private String pagestr;
  private String conditionStr = "";

  private static final String GET_SHARCH_RESULT =
      "SELECT T.T_STATE AS T_STATE,B.B_VALUE AS T_STATENAME,E.E_NAME AS E_NAME,T.T_BEGIN AS T_BEGIN,T.T_END AS T_END,T.T_PASSVALUE AS T_PASSVALUE,E.E_TOTAL AS E_TOTAL,E.E_STATE AS E_STATE,B1.B_VALUE AS E_STATENAME,EXAMINEE.NAME AS EXAMINEENAME,T.T_TOTAL AS T_TOTAL FROM EX_TESTPAPER T,EX_BASEINFO B,EX_BASEINFO B1,EX_EXAMINATIONPAPER E,EX_EXAMINEE EXAMINEE WHERE B.B_ID = T.T_STATE AND B1.B_ID = E.E_STATE AND E.E_ID = T.E_ID AND EXAMINEE.EXAMINEE_ID = T.EXAMINEE_ID AND T.EXAMINEE_ID = ?";

  public ExamineeTestDetailsDAO() {
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
            "<a href='examineeTestDetails.do?page=";
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

  public Collection getSearch(String examinee_id, int ipage) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    TestPaper paper = null;
    Collection list = null;
    list = new ArrayList();


    if (examinee_id == null || "".equals(examinee_id)) {
      return null;
    } else {
      conditionStr += ("&examinee_id=" + examinee_id);
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
        paper = new TestPaper();
        paper.setT_state(rs.getString("T_STATENAME"));
        paper.setE_name(rs.getString("E_NAME"));
        paper.setT_begin(rs.getString("T_BEGIN"));
        paper.setT_end(rs.getString("T_END"));
        paper.setT_passvalue(rs.getFloat("T_PASSVALUE"));
        paper.setT_total(rs.getFloat("T_TOTAL"));
        paper.setE_total(rs.getFloat("E_TOTAL"));
        paper.setE_stateName(rs.getString("E_STATENAME"));
        paper.setName(rs.getString("EXAMINEENAME"));
        paper.setE_state(rs.getString("E_STATE"));

        rs.next();
        list.add(paper);
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
      }
    }
    return list;
  }
}
