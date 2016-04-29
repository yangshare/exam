package org.mmxbb.exam.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.struts.util.LabelValueBean;

public class Selector {
  private Connection conn = null;
  DBConn dbconn = null;

  private static final String SELECT_OPTIONS =
      "select b_id as id,b_value as name from ex_baseinfo where b_type = (select b_id from ex_baseinfo where b_value = ?) and b_id <> b_type";

  public Selector() {
    try {
      dbconn = new DBConn();
      conn = dbconn.getConnection();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public ArrayList getOptions(String lable) {
    ArrayList options = null;
    PreparedStatement pstmt = null;
    Statement stmt = null;
    ResultSet rs = null;
    options = new ArrayList();
    options.add(new LabelValueBean("-----«Î—°‘Ò----", "%"));

    if (lable.equals("organization_id")) {
      try {
        stmt = conn.createStatement();
        rs = stmt.executeQuery(
            "SELECT organization_id AS id, org_name AS name FROM b_organization");
      } catch (SQLException ex2) {
        ex2.printStackTrace();
      }
    } else if (lable.equals("post_index")) {
      try {
        stmt = conn.createStatement();
        rs = stmt.executeQuery(
            "SELECT post_index AS id, post_name AS name FROM B_POST_TYPE");
      } catch (SQLException ex2) {
        ex2.printStackTrace();
      }
    } else if (lable.equals("education_index")) {
      try {
        stmt = conn.createStatement();
        rs = stmt.executeQuery(
            "SELECT education_index AS id, education_name AS name FROM b_education_type");
      } catch (SQLException ex2) {
        ex2.printStackTrace();
      }
    } else {
      try {
        pstmt = conn.prepareStatement(SELECT_OPTIONS);
        pstmt.setString(1, lable);
        rs = pstmt.executeQuery();
      } catch (SQLException ex) {
      }
    }

    try {
      while (rs.next()) {
        options.add(new LabelValueBean(rs.getString(2), rs.getString(1)));
      }
    } catch (SQLException ex1) {
      ex1.printStackTrace();
    }

    return options;
  }

  public void close() {
    try {
      conn.close();
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
