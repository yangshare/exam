package org.mmxbb.exam.dao;

import java.sql.*;
import java.util.*;
import org.apache.struts.util.*;
import org.mmxbb.exam.business.baseinfo.BaseInfoActionForm;
import org.mmxbb.exam.util.DBConn;


public class baseInfoDAO {
  private Connection conn = null;
  public baseInfoDAO() {
    DBConn dbConn = new DBConn();
    conn = dbConn.getConnection();
  }

  public Collection findInUseForSelect() {
    PreparedStatement ps = null;
    ResultSet rs = null;
    ArrayList list = new ArrayList();
    String label = "";
    String sql = "select * from ex_baseinfo order by b_ID";
    try {
      if (conn.isClosed()) {
        throw new IllegalStateException("error.unexpected");
      }
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();

      while (rs.next()) {
        if (rs.getString("b_id").equals("001") ||
            rs.getString("b_id").equals("006") ||
            rs.getString("b_id").equals("007") ||
            rs.getString("b_id").equals("010")) {
          String value = rs.getString("b_id");
          if (rs.getString("b_id").equals("001")) {
            label = "试卷类型";
          }
          if (rs.getString("b_id").equals("006")) {
            label = "所属题库";
          }
          if (rs.getString("b_id").equals("007")) {
            label = "知识点";
          }
          if (rs.getString("b_id").equals("010")) {
            label = "业务类别";
          }

          list.add(new LabelValueBean(label, value));
        }
      }
      return list;

    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("error.unexpected");
    }
    finally {
      try {
        if (ps != null) {
          ps.close();
        }
        if (rs != null) {
          rs.close();

        }
      }
      catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("error.unexpected");
      }
    }
  }

  public void dbClose() {
    try {
      if (conn != null) {
        conn.close();
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  public Collection findInUseForSubSelect() {
    PreparedStatement ps = null;
    ResultSet rs = null;
    PreparedStatement psSub = null;
    ResultSet rsSub = null;
    int i = 0; //\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD
    int j = 0; //С\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD\uFFFD
    String classID = "";
    String subClassID = "";
    String subClassValue = "";

    ArrayList list = new ArrayList();
    BaseInfoActionForm baseInfoActionForm;

    String sql = "select * from ex_baseinfo order by b_ID";
    try {
      if (conn.isClosed()) {
        throw new IllegalStateException("error.unexpected");
      }
      ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();

      while (rs.next()) {

        if (rs.getString("b_id").equals("001") ||
            rs.getString("b_id").equals("006") ||
            rs.getString("b_id").equals("007") ||
            rs.getString("b_id").equals("010")) {
          i++;
          classID = rs.getString("b_ID");
          String sqlSub =
              "select * from ex_baseinfo where b_type=? and b_ID<>b_type order by b_id";
          psSub = conn.prepareStatement(sqlSub);
          psSub.setString(1, classID);
          rsSub = psSub.executeQuery();

          baseInfoActionForm = new BaseInfoActionForm();
          baseInfoActionForm.setSubI("" + i);
          baseInfoActionForm.setSubJ("" + j);
          baseInfoActionForm.setSubClassID("");
          baseInfoActionForm.setSubClassName("------------------");
          list.add(baseInfoActionForm);
          while (rsSub.next()) {
            subClassID = rsSub.getString("b_id");
            subClassValue = rsSub.getString("b_value");
            j++;
            //optionStr="articleSubClassGroup[" + i + "][" + j + "]= new Option('" +subClassName + "','" + subClassID + "')";
            baseInfoActionForm = new BaseInfoActionForm();
            baseInfoActionForm.setSubI("" + i);
            baseInfoActionForm.setSubJ("" + j);
            baseInfoActionForm.setSubClassID(subClassID);
            baseInfoActionForm.setSubClassName(subClassValue);
            list.add(baseInfoActionForm);
          }
        }
        j = 0;

      }
      return list;
    }
    catch (SQLException e) {
      e.printStackTrace();
      throw new RuntimeException("error.unexpected");
    }
    finally {
      try {
        if (ps != null) {
          ps.close();
        }
        if (rs != null) {
          rs.close();

        }
      }
      catch (SQLException e) {
        e.printStackTrace();
        throw new RuntimeException("error.unexpected");
      }
    }
  }

  public void del_ex_baseinfo(String b_id) {
    PreparedStatement pst = null;
    String sql = "";
    sql = "delete from ex_baseinfo where b_id = ?";
    try {
      pst = conn.prepareStatement(sql);
      pst.setString(1, b_id);
      pst.executeUpdate();
    }
    catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  public void manipulate_ex_baseinfo(String mani_type, String parClassID,
                                     String subClassID, String subClassValue) {
    PreparedStatement pst = null;
    String sql = "";
    String b_id = "";
    ResultSet rs = null;
    ResultSet rst = null;
    String b_id2 = "";
    if (mani_type.equals("edit")) {
      sql = "update ex_baseinfo set b_value=? where b_id=?";
      try {
        pst = conn.prepareStatement(sql);
        pst.setString(1, subClassValue);
        pst.setString(2, subClassID);
        pst.executeUpdate();
      }
      catch (SQLException ex) {
      }
    }
    else {
      sql =
          "select * from ex_baseinfo where b_type=? and b_type<>b_id order by b_id";
      try {
        pst = conn.prepareStatement(sql);
        pst.setString(1, parClassID);
        rs = pst.executeQuery();
        while (rs.next()) {
          b_id = rs.getString("b_id");
        }
        int b_id1 = Integer.parseInt(b_id);
        b_id1 = b_id1 + 1;
        b_id2 = Integer.toString(b_id1);
        //keep b_id.length=3
        if (b_id2.length() < 3) {
          while (b_id2.length() < 3) {
            b_id2 = "0" + b_id2;
          }
          //rs.close();
          rs = null;
          // pst.close();
          pst = null;
        }
        sql = "select * from ex_baseinfo where b_id=" + b_id2;
        Statement st = null;
        st = conn.createStatement();
        rst = st.executeQuery(sql);
        if (rst.next()) {

          if (rst.getString("b_type") == null) {
            sql = "update ex_baseinfo set b_type=? , b_value=? where b_id=?";
            pst = conn.prepareStatement(sql);
            pst.setString(1, parClassID);
            pst.setString(2, subClassValue);
            pst.setString(3, b_id2);
            pst.executeUpdate();
          }
        }
        else {
          rst = st.executeQuery("select * from ex_baseinfo order by b_id");
          while (rst.next()) {
            b_id = rst.getString("b_id");
          }
          b_id1 = Integer.parseInt(b_id);
          b_id1 = b_id1 + 1;
          b_id2 = Integer.toString(b_id1);
          sql = "insert into ex_baseinfo values(?,?,?)";
          pst = conn.prepareStatement(sql);
          pst.setString(1, b_id2);
          pst.setString(2, parClassID);
          pst.setString(3, subClassValue);
          pst.executeUpdate();
        }
      }
      catch (SQLException ex1) {
        ex1.printStackTrace();
      }
    }
  }

  public static void main(String args[]) {
    baseInfoDAO baseinfoDAO = new baseInfoDAO();
    ArrayList basein = null;
    baseinfoDAO.manipulate_ex_baseinfo("add", "010", "105",
                                       "Hello wohao  nihao");
    //baseinfoDAO.del_ex_baseinfo("102");
    /** basein = (ArrayList) baseinfoDAO.findInUseForSelect();
     Iterator it = basein.iterator();
     LabelValueBean lab;
     BaseInfoActionForm baseInfoActionForm = new BaseInfoActionForm();
     for (int i = 0; i < basein.size(); i++) {
       lab = (LabelValueBean) basein.get(i);
       System.out.println("value===========" + lab.getValue());
     }

         BaseInfoActionForm baseInfoActionForm = new BaseInfoActionForm();
         basein = (ArrayList) baseinfoDAO.findInUseForSubSelect();
         for (int i = 0; i < basein.size(); i++) {
      baseInfoActionForm = (BaseInfoActionForm) basein.get(i);
      System.out.println("ArrayClass[" + baseInfoActionForm.getSubI() + "][" +
                         baseInfoActionForm.getSubJ() + "]=" +
                         baseInfoActionForm.getSubClassName());
         } */
  }
}
