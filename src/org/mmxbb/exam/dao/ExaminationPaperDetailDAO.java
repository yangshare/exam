package org.mmxbb.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.mmxbb.exam.util.DBConn;


public class ExaminationPaperDetailDAO {

  public ExaminationPaperDetailDAO() {
  }

  public void addExaminationPaperDetail(long e_id, String[] q_idList) {
    DBConn dbconn = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    String addBykeySql =
        "insert into ex_examinationpaper_detail (e_id,q_id) values(?,?)";
    dbconn = new DBConn();
    conn = dbconn.getConnection();
    try {
      for (int i = 0; i < q_idList.length; i++) {
        int q_idL = Integer.parseInt(q_idList[i]);
        if (q_idL == 0)
          continue;
        long q_id = (long) q_idL;
        pstmt = conn.prepareStatement(addBykeySql);
        pstmt.setLong(1, e_id);
        pstmt.setLong(2, q_id);
        pstmt.executeUpdate();
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        pstmt.close();
        pstmt = null;
        dbconn.close();
        dbconn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }
  }

  public void removeExaminationPaperDetail_byE_id(long e_id){
    DBConn dbconn = null;
    Connection conn = null;
    PreparedStatement pstmt = null;
    String removeExaminationPaperDetail_byE_id =
        "delete from EX_EXAMINATIONPAPER_DETAIL WHERE E_ID=?";
    dbconn = new DBConn();
    conn = dbconn.getConnection();
    try {
      pstmt = conn.prepareStatement(removeExaminationPaperDetail_byE_id);
      pstmt.setLong(1,e_id);
      pstmt.executeUpdate();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }finally{
      try {
        pstmt.close();
        pstmt = null;
        dbconn.close();
        dbconn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }

    }
  }
}
