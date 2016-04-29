package org.mmxbb.exam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import org.mmxbb.exam.bean.Examinee;
import org.mmxbb.exam.business.LogonForm;
import org.mmxbb.exam.business.examinee.ExamineeActionForm;
import org.mmxbb.exam.business.program.RandomDistributeExamPaperForm;
import org.mmxbb.exam.util.DBConn;


public class ExamineeDAO {
  DBConn dbconn = null;
  private Connection conn = null;

  private int rowCount;
  private int pageCount;
  private int length;
  private String pagestr;
  private String conditionStr = "";

  private static final String ADD_EXAMINEE =
      "INSERT INTO EX_EXAMINEE (examinee_id,password,name,sex,age,organization_id,post_index,state,operation,education_index,address,phone,email,remark) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  private static final String UPDATE_EXAMINEE =
      "UPDATE ex_examinee set NAME=?,SEX=?,AGE=?,ORGANIZATION_ID=?,POST_INDEX=?,STATE=?,OPERATION=?,EDUCATION_INDEX=?,ADDRESS=?,PHONE=?,EMAIL=?,REMARK=? WHERE EXAMINEE_ID=?";
  //************
  private static final String GET_SHARCH_RESULT =
      "select ex.examinee_id as examinee_id,ex.password as password,ex.name as name,ex.sex as sex,b1.b_value as sexName,ex.age as age,ex.organization_id as organization_id,o.org_name as organizationName,ex.post_index as post_index,p.post_name as postName,ex.state as state,b2.b_value as stateName,ex.operation as operation,b3.b_value as operationName,ex.education_index as education_index,e.education_name as educationName,ex.address as address,ex.phone as phone,ex.mobile as mobile,ex.email as email,ex.remark as remark from ex_examinee ex,b_organization o,b_post_type p,b_education_type e,ex_baseinfo b1,ex_baseinfo b2,ex_baseinfo b3 where b1.b_id = ex.sex and o.organization_id = ex.organization_id and p.post_index = ex.post_index and b2.b_id = ex.state and b3.b_id = ex.operation and e.education_index = ex.education_index AND ex.examinee_id LIKE ? AND ex.name LIKE ? AND ex.sex LIKE ? AND ex.organization_id LIKE ? AND ex.state LIKE ? AND ex.operation LIKE ?";
  private static final String GET_SHARCH_RESULT2 =
      "select ex.examinee_id as examinee_id,ex.password as password,ex.name as name,ex.sex as sex,b1.b_value as sexName,ex.age as age,ex.organization_id as organization_id,o.org_name as organizationName,ex.post_index as post_index,p.post_name as postName,ex.state as state,b2.b_value as stateName,ex.operation as operation,b3.b_value as operationName,ex.education_index as education_index,e.education_name as educationName,ex.address as address,ex.phone as phone,ex.mobile as mobile,ex.email as email,ex.remark as remark from ex_examinee ex,b_organization o,b_post_type p,b_education_type e,ex_baseinfo b1,ex_baseinfo b2,ex_baseinfo b3 where b1.b_id = ex.sex and o.organization_id = ex.organization_id and p.post_index = ex.post_index and b2.b_id = ex.state and b3.b_id = ex.operation and e.education_index = ex.education_index AND ex.examinee_id LIKE ? AND ex.name LIKE ? AND ex.sex LIKE ? AND ex.organization_id LIKE ? AND ex.state LIKE ? AND ex.operation LIKE ? AND ex.post_index = ?";
  private static final String GET_ALL_RESULT =
      "select ex.examinee_id as examinee_id,ex.password as password,ex.name as name,ex.sex as sex,b1.b_value as sexName,ex.age as age,ex.organization_id as organization_id,o.org_name as organizationName,ex.post_index as post_index,p.post_name as postName,ex.state as state,b2.b_value as stateName,ex.operation as operation,b3.b_value as operationName,ex.education_index as education_index,e.education_name as educationName,ex.address as address,ex.phone as phone,ex.mobile as mobile,ex.email as email,ex.remark as remark from ex_examinee ex,b_organization o,b_post_type p,b_education_type e,ex_baseinfo b1,ex_baseinfo b2,ex_baseinfo b3 where b1.b_id = ex.sex and o.organization_id = ex.organization_id and p.post_index = ex.post_index and b2.b_id = ex.state and b3.b_id = ex.operation and e.education_index = ex.education_index";
  private static final String FIND_BY_KEY =
      "SELECT * FROM EX_EXAMINEE WHERE examinee_id = ?";
  private static final String RE_INIT_PWD =
      "UPDATE EX_EXAMINEE SET password = examinee_id WHERE examinee_id = ?";
  private static final String UPDATE_PWD =
      "UPDATE EX_EXAMINEE SET password = ? WHERE examinee_id = ? AND password = ?";
  private static final String IS_USER =
      "SELECT password FROM EX_EXAMINEE WHERE examinee_id = ?";
  private static final String IS_ADMINUSER =
    "SELECT name,password FROM ex_adminuser where name=? and password=?";
  
  private static final String HAS_USER =
      "SELECT EXAMINEE_ID FROM EX_EXAMINEE WHERE EXAMINEE_ID = ?";
  private static final String GET_SHARCH_RESULT_WITHOUT_PAGE =
      "select ex.examinee_id as examinee_id,ex.password as password,ex.name as name,ex.sex as sex,b1.b_value as sexName,ex.age as age,ex.organization_id as organization_id,o.org_name as organizationName,ex.post_index as post_index,p.post_name as postName,ex.state as state,b2.b_value as stateName,ex.operation as operation,b3.b_value as operationName,ex.education_index as education_index,e.education_name as educationName,ex.address as address,ex.phone as phone,ex.mobile as mobile,ex.email as email,ex.remark as remark from ex_examinee ex,b_organization o,b_post_type p,b_education_type e,ex_baseinfo b1,ex_baseinfo b2,ex_baseinfo b3 where b1.b_id = ex.sex and o.organization_id = ex.organization_id and p.post_index = ex.post_index and b2.b_id = ex.state and b3.b_id = ex.operation and e.education_index = ex.education_index AND ex.examinee_id LIKE ? AND ex.name LIKE ? AND ex.sex LIKE ? AND ex.organization_id LIKE ? AND ex.state LIKE ? AND ex.operation LIKE ? AND  EX.STATE='063'";
  private static final String GET_SHARCH_RESULT2_WITHOUT_PAGE =
      "select ex.examinee_id as examinee_id,ex.password as password,ex.name as name,ex.sex as sex,b1.b_value as sexName,ex.age as age,ex.organization_id as organization_id,o.org_name as organizationName,ex.post_index as post_index,p.post_name as postName,ex.state as state,b2.b_value as stateName,ex.operation as operation,b3.b_value as operationName,ex.education_index as education_index,e.education_name as educationName,ex.address as address,ex.phone as phone,ex.mobile as mobile,ex.email as email,ex.remark as remark from ex_examinee ex,b_organization o,b_post_type p,b_education_type e,ex_baseinfo b1,ex_baseinfo b2,ex_baseinfo b3 where b1.b_id = ex.sex and o.organization_id = ex.organization_id and p.post_index = ex.post_index and b2.b_id = ex.state and b3.b_id = ex.operation and e.education_index = ex.education_index AND ex.examinee_id LIKE ? AND ex.name LIKE ? AND ex.sex LIKE ? AND ex.organization_id LIKE ? AND ex.state LIKE ? AND ex.operation LIKE ? AND ex.post_index = ? AND  EX.STATE='063'";

  /**
   * get a connection from a DB pool
   * @return Connection
   */
  public ExamineeDAO() {
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
            "<a href='showExaminee.do?page=";
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

  /**
   * get an object for the row find by PK
   * @param examinee_id String
   * @throws SQLException
   * @return Examinee
   */
  public Examinee findByKey(String examinee_id) throws SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Examinee examinee = null;
    try {
      pstmt = conn.prepareStatement(FIND_BY_KEY);
      pstmt.setString(1, examinee_id);
      rs = pstmt.executeQuery();
      examinee = new Examinee();
      if (rs.next()) {
        examinee.setExaminee_id(rs.getString(1));
        examinee.setPassword(rs.getString("PASSWORD"));
        examinee.setName(rs.getString("NAME"));
        examinee.setSex(rs.getString("SEX"));
        examinee.setAge(rs.getString("AGE"));
        examinee.setOrganization_id(rs.getString("ORGANIZATION_ID"));
        examinee.setPost_index(rs.getString("POST_INDEX"));
        examinee.setState(rs.getString("STATE"));
        examinee.setOperation(rs.getString("OPERATION"));
        examinee.setEducation_index(rs.getString("EDUCATION_INDEX"));
        examinee.setAddress(rs.getString("ADDRESS"));
        examinee.setPhone(rs.getString("PHONE"));
        examinee.setMobile(rs.getString("MOBILE"));
        examinee.setEmail(rs.getString("EMAIL"));
        examinee.setRemark(rs.getString("REMARK"));
      }

    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new SQLException("SQLException on : ExamineeDAO.findByKey(args)");
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
    return examinee;
  }

  /**
   * add a row into DB
   * @param examinee Examinee
   * @throws SQLException
   */
  public void addExaminee(Examinee examinee) throws SQLException {
    PreparedStatement pstmt = null;

    try {
      pstmt = conn.prepareStatement(ADD_EXAMINEE);
      pstmt.setString(1, examinee.getExaminee_id());
      pstmt.setString(2, examinee.getExaminee_id());
      pstmt.setString(3, examinee.getName());
      pstmt.setString(4, examinee.getSex());
      pstmt.setString(5, examinee.getAge());
      pstmt.setString(6, examinee.getOrganization_id());
      pstmt.setString(7, examinee.getPost_index());
      pstmt.setString(8, examinee.getState());
      pstmt.setString(9, examinee.getOperation());
      pstmt.setString(10, examinee.getEducation_index());
      pstmt.setString(11, examinee.getAddress());
      pstmt.setString(12, examinee.getPhone());
      pstmt.setString(13, examinee.getEmail());
      pstmt.setString(14, examinee.getRemark());
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
   * remove a set of examinee from DB
   * @param examinee Examinee
   * @throws SQLException
   */
  public void removeExaminee(String[] examineeList) throws SQLException {
    Statement stmt = null;
    String ids = "";
    for (int i = 0; i < examineeList.length; i++) {
      ids += "'" + examineeList[i] + "'";
      if (i != examineeList.length - 1) {
        ids += ", ";
      }
    }

    try {
      stmt = conn.createStatement();
      stmt.executeUpdate("DELETE FROM ex_examinee WHERE examinee_id in (" + ids +
                         ")");
    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new SQLException("SQLExction on : ExamineeDAO.removeExaminee()");
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
   * remove a row from DB
   * @param examinee Examinee
   * @throws SQLException
   */
  public void removeExaminee(Examinee examinee) throws SQLException {
    this.removeExaminee(examinee.getExaminee_id());
  }

  /**
   * remove a row from DB
   * @param examinee_id String
   * @throws SQLException
   */
  public void removeExaminee(String examinee_id) throws SQLException {
    Statement stmt = null;
    try {
      stmt = conn.createStatement();
      stmt.execute("DELETE FROM ex_examinee WHERE examinee_id='" +
                   examinee_id + "'");
    } catch (SQLException ex) {
      ex.printStackTrace();
      throw new SQLException("SQLExction on : ExamineeDAO.removeExaminee()");
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
   * @param examinee Examinee
   * @throws SQLException
   */
  public void updateExaminee(ExamineeActionForm examinee) throws SQLException {
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(UPDATE_EXAMINEE);
      pstmt.setString(1, examinee.getName());
      pstmt.setString(2, examinee.getSex());
      pstmt.setString(3, examinee.getAge());
      pstmt.setString(4, examinee.getOrganization_id());
      pstmt.setString(5, examinee.getPost_index());
      pstmt.setString(6, examinee.getState());
      pstmt.setString(7, examinee.getOperation());
      pstmt.setString(8, examinee.getEducation_index());
      pstmt.setString(9, examinee.getAddress());
      pstmt.setString(10, examinee.getPhone());
      pstmt.setString(11, examinee.getEmail());
      pstmt.setString(12, examinee.getRemark());
      pstmt.setString(13, examinee.getExaminee_id());
      pstmt.executeUpdate();

    } catch (SQLException ex) {
      ex.getStackTrace();
      throw new SQLException("SQLExction on : ExamineeDAO.updateExaminee()");
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
   * get all record from DB
   * @throws SQLException
   * @return Collection
   */
  public Collection getAll() throws SQLException {
    Statement stmt = null;
    ResultSet rs = null;
    Examinee examinee = null;
    Collection list = null;

    list = new ArrayList();

    try {
      stmt = conn.createStatement();
      rs = stmt.executeQuery(GET_ALL_RESULT);
      while (rs.next()) {
        examinee = new Examinee();
        examinee.setExaminee_id(rs.getString(1));
        examinee.setPassword(rs.getString("PASSWORD"));
        examinee.setName(rs.getString("NAME"));
        examinee.setSex(rs.getString("SEX"));
        examinee.setSexName(rs.getString("SEXNAME"));
        examinee.setAge(rs.getString("AGE"));
        examinee.setOrganization_id(rs.getString("ORGANIZATION_ID"));
        examinee.setOrganizationName(rs.getString("ORGANIZATIONNAME"));
        examinee.setPost_index(rs.getString("POST_INDEX"));
        examinee.setPostName(rs.getString("POSTNAME"));
        examinee.setState(rs.getString("STATE"));
        examinee.setStateName(rs.getString("STATENAME"));
        examinee.setOperation(rs.getString("OPERATION"));
        examinee.setOperationName(rs.getString("OPERATIONNAME"));
        examinee.setEducation_index(rs.getString("EDUCATION_INDEX"));
        examinee.setEducationName(rs.getString("EDUCATIONNAME"));
        examinee.setAddress(rs.getString("ADDRESS"));
        examinee.setPhone(rs.getString("PHONE"));
        examinee.setMobile(rs.getString("MOBILE"));
        examinee.setEmail(rs.getString("EMAIL"));
        examinee.setRemark(rs.getString("REMARK"));

        list.add(examinee);
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

  public Collection getSearch(ExamineeActionForm examinnForm, int ipage) throws
      SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Examinee examinee = null;
    Collection list = null;
    list = new ArrayList();

    String examinee_id = examinnForm.getExaminee_id();
    String name = examinnForm.getName();
    String sex = examinnForm.getSex();
    String organization_id = examinnForm.getOrganization_id();
    String post_index = examinnForm.getPost_index();
    String state = examinnForm.getState();
    String operation = examinnForm.getOperation();

    if (examinee_id == null || "".equals(examinee_id)) {
      examinee_id = "%";
    } else if (! ("%".equals(examinee_id))) {
      conditionStr += ("&examinee_id=" + examinee_id);
    }

    if (name == null || "".equals(name)) {
      name = "%";
    } else if (! ("%".equals(name))) {
      conditionStr += ("&name=" + name);
    }

    if (sex == null || "".equals(sex)) {
      sex = "%";
    } else if (! ("%".equals(sex))) {
      conditionStr += ("&sex=" + sex);
    }

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

    if (state == null || "".equals(state)) {
      state = "%";
    } else if (! ("%".equals(state))) {
      conditionStr += ("&state=" + state);
    }

    if (operation == null || "".equals(operation)) {
      operation = "%";
    } else if (! ("%".equals(operation))) {
      conditionStr += ("&operation=" + operation);
    }


    try {
      if ("%".equals(post_index)) {
        pstmt = conn.prepareStatement(GET_SHARCH_RESULT,
                                      ResultSet.TYPE_SCROLL_SENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
        pstmt.setString(1, examinee_id);
        pstmt.setString(2, name);
        pstmt.setString(3, sex);
        pstmt.setString(4, organization_id);
        pstmt.setString(5, state);
        pstmt.setString(6, operation);
      } else {
        pstmt = conn.prepareStatement(GET_SHARCH_RESULT2,
                                      ResultSet.TYPE_SCROLL_SENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
        pstmt.setString(1, examinee_id);
        pstmt.setString(2, name);
        pstmt.setString(3, sex);
        pstmt.setString(4, organization_id);
        pstmt.setString(5, state);
        pstmt.setString(6, operation);
        pstmt.setString(7, post_index);
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
        examinee = new Examinee();
        examinee.setExaminee_id(rs.getString(1));
        examinee.setPassword(rs.getString("PASSWORD"));
        examinee.setName(rs.getString("NAME"));
        examinee.setSex(rs.getString("SEX"));
        examinee.setSexName(rs.getString("SEXNAME"));
        examinee.setAge(rs.getString("AGE"));
        examinee.setOrganization_id(rs.getString("ORGANIZATION_ID"));
        examinee.setOrganizationName(rs.getString("ORGANIZATIONNAME"));
        examinee.setPost_index(rs.getString("POST_INDEX"));
        examinee.setPostName(rs.getString("POSTNAME"));
        examinee.setState(rs.getString("STATE"));
        examinee.setStateName(rs.getString("STATENAME"));
        examinee.setOperation(rs.getString("OPERATION"));
        examinee.setOperationName(rs.getString("OPERATIONNAME"));
        examinee.setEducation_index(rs.getString("EDUCATION_INDEX"));
        examinee.setEducationName(rs.getString("EDUCATIONNAME"));
        examinee.setAddress(rs.getString("ADDRESS"));
        examinee.setPhone(rs.getString("PHONE"));
        examinee.setMobile(rs.getString("MOBILE"));
        examinee.setEmail(rs.getString("EMAIL"));
        examinee.setRemark(rs.getString("REMARK"));

        rs.next();
        list.add(examinee);
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

  public Collection getSearchWithoutPage(RandomDistributeExamPaperForm randomDistributeExamPaperForm) throws
      SQLException {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Examinee examinee = null;
    Collection list = null;
    list = new ArrayList();

    String examinee_id = randomDistributeExamPaperForm.getExaminee_id();
    String name = randomDistributeExamPaperForm.getName();
    String sex = randomDistributeExamPaperForm.getSex();
    String organization_id = randomDistributeExamPaperForm.getOrganization_id();
    String post_index = randomDistributeExamPaperForm.getPost_index();
    String state = randomDistributeExamPaperForm.getState();
    String operation = randomDistributeExamPaperForm.getOperation();

    if (examinee_id == null || "".equals(examinee_id)) {
      examinee_id = "%";
    } else if (! ("%".equals(examinee_id))) {
      conditionStr += ("&examinee_id=" + examinee_id);
    }

    if (name == null || "".equals(name)) {
      name = "%";
    } else if (! ("%".equals(name))) {
      conditionStr += ("&name=" + name);
    }

    if (sex == null || "".equals(sex)) {
      sex = "%";
    } else if (! ("%".equals(sex))) {
      conditionStr += ("&sex=" + sex);
    }

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

    if (state == null || "".equals(state)) {
      state = "%";
    } else if (! ("%".equals(state))) {
      conditionStr += ("&state=" + state);
    }

    if (operation == null || "".equals(operation)) {
      operation = "%";
    } else if (! ("%".equals(operation))) {
      conditionStr += ("&operation=" + operation);
    }


    try {
      if ("%".equals(post_index)) {
        pstmt = conn.prepareStatement(GET_SHARCH_RESULT_WITHOUT_PAGE,
                                      ResultSet.TYPE_SCROLL_SENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
        pstmt.setString(1, examinee_id);
        pstmt.setString(2, name);
        pstmt.setString(3, sex);
        pstmt.setString(4, organization_id);
        pstmt.setString(5, state);
        pstmt.setString(6, operation);
      } else {
        pstmt = conn.prepareStatement(GET_SHARCH_RESULT2_WITHOUT_PAGE,
                                      ResultSet.TYPE_SCROLL_SENSITIVE,
                                      ResultSet.CONCUR_UPDATABLE);
        pstmt.setString(1, examinee_id);
        pstmt.setString(2, name);
        pstmt.setString(3, sex);
        pstmt.setString(4, organization_id);
        pstmt.setString(5, state);
        pstmt.setString(6, operation);
        pstmt.setString(7, post_index);
      }
      rs = pstmt.executeQuery();

      while(rs.next()){
        examinee = new Examinee();
        examinee.setExaminee_id(rs.getString(1));
        examinee.setPassword(rs.getString("PASSWORD"));
        examinee.setName(rs.getString("NAME"));
        examinee.setSex(rs.getString("SEX"));
        examinee.setSexName(rs.getString("SEXNAME"));
        examinee.setAge(rs.getString("AGE"));
        examinee.setOrganization_id(rs.getString("ORGANIZATION_ID"));
        examinee.setOrganizationName(rs.getString("ORGANIZATIONNAME"));
        examinee.setPost_index(rs.getString("POST_INDEX"));
        examinee.setPostName(rs.getString("POSTNAME"));
        examinee.setState(rs.getString("STATE"));
        examinee.setStateName(rs.getString("STATENAME"));
        examinee.setOperation(rs.getString("OPERATION"));
        examinee.setOperationName(rs.getString("OPERATIONNAME"));
        examinee.setEducation_index(rs.getString("EDUCATION_INDEX"));
        examinee.setEducationName(rs.getString("EDUCATIONNAME"));
        examinee.setAddress(rs.getString("ADDRESS"));
        examinee.setPhone(rs.getString("PHONE"));
        examinee.setMobile(rs.getString("MOBILE"));
        examinee.setEmail(rs.getString("EMAIL"));
        examinee.setRemark(rs.getString("REMARK"));

        list.add(examinee);

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





  public boolean isUser(LogonForm logonForm) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    boolean isUser = false;

    try {
      pstmt = conn.prepareStatement(IS_USER);
      pstmt.setString(1, logonForm.getUserName());
      rs = pstmt.executeQuery();
      if (rs.next()) {
        if (rs.getString(1).equals(logonForm.getPassword())) {
          isUser = true;
        }
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
    return isUser;
  }

  public boolean isAdminUser(LogonForm logonForm) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    boolean isAdminUser = false;

    try {
      pstmt = conn.prepareStatement(IS_ADMINUSER);
      pstmt.setString(1,logonForm.getUserName());
      pstmt.setString(2,logonForm.getPassword());
      rs = pstmt.executeQuery();
      while(rs.next()){
      	isAdminUser=true;
      	return isAdminUser;
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
    return isAdminUser;
  }
  
  
  
  /**
   * hasUser
   *
   * @param examinee_id String
   * @return boolean
   */
  public boolean hasUser(String examinee_id) {
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    boolean overlap = false;
    try {
      pstmt = conn.prepareStatement(HAS_USER);
      pstmt.setString(1, examinee_id);
      rs = pstmt.executeQuery();
      if (rs.next()) {
        overlap = true;
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
    return overlap;
  }

  /**
   * re-initialize password as examinee_id
   * @param examinee_id String
   * @throws SQLException
   */
  public void reInitPwd(String examinee_id) throws SQLException {
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(RE_INIT_PWD);
      pstmt.setString(1, examinee_id);
      pstmt.executeUpdate();
    } catch (SQLException ex) {
      ex.getStackTrace();
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
   * updatePwd password as examinee_set
   * @param examinee_id String
   * @throws SQLException
   */
  public void updatePwd(String examinee_id, String oldPwd, String newPwd) throws
      SQLException {
    PreparedStatement pstmt = null;
    try {
      pstmt = conn.prepareStatement(UPDATE_PWD);
      pstmt.setString(1, newPwd);
      pstmt.setString(2, examinee_id);
      pstmt.setString(3, oldPwd);
      pstmt.executeUpdate();
    } catch (SQLException ex) {
      ex.getStackTrace();
      throw new SQLException("SQLExction on : ExamineeDAO.updatePwd()");
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
