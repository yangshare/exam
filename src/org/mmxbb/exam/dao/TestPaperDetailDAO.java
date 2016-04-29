package org.mmxbb.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

import org.mmxbb.exam.bean.TestPaperDetail;
import org.mmxbb.exam.util.DBConn;
import org.mmxbb.exam.util.Transformer;


public class TestPaperDetailDAO {
  DBConn dbConn = null;
  private Connection conn = null;
  public TestPaperDetailDAO() {
    try {
      dbConn = new DBConn();
      conn = dbConn.getConnection();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

public void updateTestPaperDetail(long t_id,long q_id,String t_answer,float t_value){
  PreparedStatement pstmt = null;
     try {
       String sql =
           "UPDATE ex_testpaperdetail set t_answer=?,t_value=? WHERE t_id=? and q_id=?";
       pstmt = conn.prepareStatement(sql);
       pstmt.setString(1,t_answer);
       pstmt.setFloat(2,t_value);
       pstmt.setLong(3,t_id);
       pstmt.setLong(4,q_id);

       pstmt.executeUpdate();

     } catch (SQLException ex) {
       ex.getStackTrace();
     }finally {
     	  try {
            conn.close();
          } catch (SQLException ex1) {
            ex1.printStackTrace();
          }
     
     }
     
  }
  public void close(){
    try {
        conn.close();
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }

  }
  public TestPaperDetail findByKey(long T_ID, long Q_ID) {
    PreparedStatement pst = null;
    ResultSet rs = null;
    TestPaperDetail testPaperDetail = new TestPaperDetail();
    String sql = "select * from ex_testpaperdetail where t_id = ? and q_id = ?";
    try {
      pst = conn.prepareStatement(sql);
      pst.setLong(1, T_ID);
      pst.setLong(2, Q_ID);
      rs = pst.executeQuery();
      while (rs.next()) {
        testPaperDetail.setQ_ID(rs.getLong("q_id"));
        testPaperDetail.setT_answer(rs.getString("t_answer"));
        testPaperDetail.setT_ID(rs.getLong("t_id"));
        testPaperDetail.setT_value(rs.getFloat("t_value"));
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        rs.close();
        rs = null;
        pst.close();
        pst = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }

    return testPaperDetail;
  }

  public void updateTestPaperDetail(TestPaperDetail testPaperDetail) throws
      SQLException {
    PreparedStatement pstmt = null;
    try {
      String sql =
          "UPDATE ex_testpaperdetail set t_answer=?,t_value=? WHERE t_id=? and q_id=?";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1, testPaperDetail.getT_answer());
      pstmt.setFloat(2, testPaperDetail.getT_value());
      pstmt.setLong(3, testPaperDetail.getT_ID());
      pstmt.setLong(4, testPaperDetail.getQ_ID());

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

  public float countTotalValue(long t_id) {
    float total = 0;
    ArrayList list = (ArrayList)this.findByTID(t_id);
    TestPaperDetail testPaperDetail = null;
    float t_value = 0;
    for (int i = 0; i < list.size(); i++) {
      testPaperDetail = (TestPaperDetail) list.get(i);
      t_value = testPaperDetail.getT_value();
   /*
      if (t_value < 0) {
        total = -1;
        break;
      }*/
      total += t_value;
    }
    return total;
  }

  public void add_TestPaperDetail(Vector t_idList, String[] q_idList) throws
      SQLException {
    PreparedStatement pst = null;
    String addSql =
        "insert into EX_TestPaperDetail (t_id,q_id,t_value) values (?,?,?)";
    try {
      for (int j = 0; j < q_idList.length; j++) {
        for (int i = 0; i < t_idList.size(); i++) {

          Long t_idL = (Long) t_idList.get(i);
          long t_id = t_idL.longValue();
          int q_idL = Integer.parseInt(q_idList[j]);
          if (q_idL == 0)
            continue;
          long q_id = (long) q_idL;

          pst = conn.prepareStatement(addSql);
          pst.setLong(1, t_id);
          pst.setLong(2, q_id);
          pst.setFloat(3, -1);
          pst.executeUpdate();
        }
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        pst.close();
        pst = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
      }
    }
  }

  public Collection findByTID(long T_ID) {
    PreparedStatement pst = null;
    ResultSet rs = null;
    Collection list = null;
    list = new ArrayList();
    TestPaperDetail TestPaperDetail = new TestPaperDetail();
    String sql = "select * from ex_testpaperdetail where t_id = ? ";
    try {
      pst = conn.prepareStatement(sql);
      pst.setLong(1, T_ID);
      rs = pst.executeQuery();
      while (rs.next()) {
        TestPaperDetail = new TestPaperDetail();
        TestPaperDetail.setQ_ID(rs.getLong("q_id"));
        TestPaperDetail.setT_answer(rs.getString("t_answer"));
        TestPaperDetail.setT_ID(rs.getLong("t_id"));
        TestPaperDetail.setT_value(rs.getFloat("t_value"));

        list.add(TestPaperDetail);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        rs.close();
        rs = null;
        pst.close();
        pst = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }

    return list;

  }

  public Collection findNotGraded (long T_ID) throws SQLException {
    PreparedStatement pst = null;
    ResultSet rs = null;
    Collection list = null;
    Transformer transformer = new Transformer();
    String q_type1 = transformer.valueToId("Ìî¿ÕÌâ");
    transformer= new Transformer();
    String q_type2 = transformer.valueToId("¼ò´ðÌâ");

    list = new ArrayList();
    TestPaperDetail TestPaperDetail = new TestPaperDetail();

   String sql = "select ex_testpaperdetail.t_id as t_id,ex_testpaperdetail.q_id as q_id,ex_testpaperdetail.t_answer as t_answer,ex_testpaperdetail.t_value  as t_value from ex_testpaperdetail,ex_question where ex_testpaperdetail.t_id = ? and ex_testpaperdetail.q_id=ex_question.q_id and (ex_question.q_type='"+q_type1+"' or ex_question.q_type='"+q_type2+"')";
    try {
      pst = conn.prepareStatement(sql);
      pst.setLong(1, T_ID);
      rs = pst.executeQuery();
      while (rs.next()) {
        TestPaperDetail = new TestPaperDetail();
        TestPaperDetail.setQ_ID(rs.getLong("q_id"));
        TestPaperDetail.setT_answer(rs.getString("t_answer"));
        TestPaperDetail.setT_ID(rs.getLong("t_id"));
        TestPaperDetail.setT_value(rs.getFloat("t_value"));

        list.add(TestPaperDetail);
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    } finally {
      try {
        rs.close();
        rs = null;
        pst.close();
        pst = null;
        conn.close();
        conn = null;
      } catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }

    return list;

  }

  public void removeByT_id(long t_id){
    PreparedStatement pstmt = null;
    String removeByT_id = "delete from EX_TESTPAPERDETAIL WHERE T_ID=?";
    try {
      pstmt = conn.prepareStatement(removeByT_id);
      pstmt.setLong(1, t_id);
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
}
