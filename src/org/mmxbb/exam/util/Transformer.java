package org.mmxbb.exam.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Transformer {

  private Connection conn = null;
  DBConn dbcon = null;
  Statement stmt = null;
  ResultSet set = null;

  public Transformer() {
    try {
      dbcon = new DBConn();
      conn = dbcon.getConnection();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public String idToValue(String input) {
    String output = null;
    try {
      stmt = conn.createStatement();
      stmt.setMaxRows(1);
      String sql = "select B_VALUE from ex_BASEINFO where B_ID='" + input + "'";
      set = stmt.executeQuery(sql);
      if (set.next()) {
        output = set.getString("B_VALUE");
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    } finally {
      close();
    }
    if (output == null) {
      return null;
    } else {
      return output;
    }
  }

  public String valueToId(String input) {
    String output = null;
    try {
      stmt = conn.createStatement();
      stmt.setMaxRows(1);
      String sql = "select B_ID from ex_BASEINFO where B_VALUE='" + input + "'";
      set = stmt.executeQuery(sql);
      if (set.next()) {
        output = set.getString("B_ID");
      }
    } catch (SQLException sqle) {
      sqle.printStackTrace();
    } finally {
      close();
    }
    if (output == null) {
      return null;
    } else {
      return output;
    }
  }

  public void close() {
    close(set);
    close(stmt);
    close(conn);
  }

  public static void close(ResultSet rs) {
    try {
      rs.close();
      rs = null;
    } catch (Exception ignored) {}
  }

  public static void close(Statement stmt) {
    try {
      stmt.close();
      stmt = null;
    } catch (Exception ignored) {}
  }

  public static void close(Connection conn) {
    try {
      conn.close();
      conn = null;
    } catch (Exception ignored) {}
  }
}
