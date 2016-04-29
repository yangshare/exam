package org.mmxbb.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.mmxbb.exam.bean.TestPaper;
import org.mmxbb.exam.util.DBConn;


public class ExamineeInquiryDAO {
  DBConn dbconn = null;
  private Connection conn = null;

  private int rowCount;
  private int pageCount;
  private int length;

  private static final String GET_SHARCH_RESULT =
      "SELECT E.E_ID E_ID,E.E_NAME E_NAME,E.E_TYPE E_TYPE,B1.B_VALUE E_TYPENAME,E.E_TIMER E_TIMER,E.E_BEGIN E_BEGIN,E.E_END E_END,E.E_PASSVALUE E_PASSVALUE,E.E_TOTAL E_TOTAL,E.E_STATE E_STATE,B2.B_VALUE E_STATENAME,T.T_TOTAL T_TOTAL,T.T_STATE T_STATE,B3.B_VALUE T_STATENAME FROM EX_EXAMINATIONPAPER E,EX_TESTPAPER T,EX_BASEINFO B1,EX_BASEINFO B2,EX_BASEINFO B3 WHERE T.E_ID=E.E_ID AND B1.B_ID=E.E_TYPE AND B2.B_ID=E.E_STATE AND B3.B_ID=T.T_STATE AND (now())>E.E_END AND T.EXAMINEE_ID=?";

  public ExamineeInquiryDAO() {
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
            "<a href='examineeInquiryAction.do?page=";
        strPage += String.valueOf(i + 1);
        strPage += "'>";
        strPage += String.valueOf(i + 1);
        strPage += "</a>";
        strPage += "  ";
      }
    }
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
        paper.setE_id(rs.getLong("E_ID"));
        paper.setE_name(rs.getString("e_name"));
        paper.setE_typeName(rs.getString("e_typename"));
        paper.setE_timer(rs.getInt("e_timer"));
        paper.setT_total(rs.getFloat("t_total"));
        paper.setT_stateName(rs.getString("t_statename"));
        paper.setT_passvalue(rs.getFloat("E_PASSVALUE"));
        paper.setE_stateName(rs.getString("e_statename"));
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
