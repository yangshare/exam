package org.mmxbb.exam.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.mmxbb.exam.business.program.ChooseExamineeActionForm;
import org.mmxbb.exam.util.DBConn;


public class ExamineeChooserDAO {

  DBConn dbconn = null;
  private Connection conn = null;

  private int rowCount;
  private int pageCount;
  private int length;
  private String pagestr;
  private String conditionStr = "";

  private static final String GET_SHARCH_RESULT =
      "SELECT EXAMINEE_ID,NAME FROM EX_EXAMINEE WHERE ORGANIZATION_ID LIKE ? AND STATE='063'";
  private static final String GET_SHARCH_RESULT2 =
      "SELECT EXAMINEE_ID,NAME FROM EX_EXAMINEE WHERE ORGANIZATION_ID LIKE ? AND POST_INDEX = ? AND STATE='063'";
  private static final String UPDATE_LIST =
      "UPDATE EX_EXAMINATIONPAPER SET E_EXAMINEELIST = ? WHERE E_ID = ?";
  private static final String ADD_TO_LIST =
      "UPDATE EX_EXAMINATIONPAPER SET E_EXAMINEELIST = ( SELECT concat(E_EXAMINEELIST,?) FROM EX_EXAMINATIONPAPER WHERE E_ID = ? ) WHERE E_ID = ?";
  private static final String GET_LIST =
      "SELECT E_EXAMINEELIST FROM EX_EXAMINATIONPAPER WHERE E_ID = ?";

  /**
   * get a connection from a DB pool
   * @return Connection
   */
  public ExamineeChooserDAO() {
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
            "<a href='chooseExaminee.do?page=";
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

  public void saveToList(String examineeList, long e_id) {
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(ADD_TO_LIST);
      pstmt.setString(1, examineeList);
      pstmt.setLong(2, e_id);
      pstmt.setLong(3, e_id);
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

  public void updateList(String examineeList, long e_id) {
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(UPDATE_LIST);
      pstmt.setString(1, examineeList);
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

  public String getList(long e_id) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    String list = null;
    try {
      pstmt = conn.prepareStatement(GET_LIST);
      pstmt.setLong(1, e_id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        list = (rs.getString(1));
        if ((null == list)||("".equals(list)))
          list = "";
      }
    } catch (SQLException ex) {
      ex.getStackTrace();
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

  public Collection getList(ChooseExamineeActionForm form, int ipage) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    ChooseExamineeActionForm ceForm = null;
    Collection list = null;
    list = new ArrayList();

    String organization_id = form.getOrganization_id();
    String post_index = form.getPost_index();

    if (organization_id == null || "".equals(organization_id)) {
      organization_id = "%";
    } else if (! ("%".equals(organization_id))) {
      conditionStr += ("&organization_id=" + organization_id);
    }

    if (post_index == null || "".equals(post_index)) {
      post_index = "%";
    } else if (! ("%".equals(post_index))) {
      conditionStr += ("&post_index=" + post_index);
    }


    try {
      if ("%".equals(post_index)) {
        pstmt = conn.prepareStatement(GET_SHARCH_RESULT,
                                      ResultSet.TYPE_SCROLL_SENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);

        pstmt.setString(1, organization_id);

      } else {
        pstmt = conn.prepareStatement(GET_SHARCH_RESULT2,
                                      ResultSet.TYPE_SCROLL_SENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
        pstmt.setString(1, organization_id);
        pstmt.setString(2, post_index);
      }
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
        ceForm = new ChooseExamineeActionForm();
        ceForm.setExaminee_id(rs.getString(1));
        ceForm.setName(rs.getString(2));
        rs.next();
        list.add(ceForm);
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

  public void destroy() {
    try {
      this.conn.close();
    } catch (SQLException ex) {
    }
    this.conn = null;
  }
}
