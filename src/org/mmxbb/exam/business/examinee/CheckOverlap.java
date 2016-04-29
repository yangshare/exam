package org.mmxbb.exam.business.examinee;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mmxbb.exam.dao.ExamineeDAO;



public class CheckOverlap
    extends HttpServlet {
  private static final String CONTENT_TYPE = "text/html; charset=GBK";

 
  public void init() throws ServletException {
  }

  
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws
      ServletException, IOException {
    String var0 = request.getParameter("examinee_id");
    if (var0 == null) {
      return;
    }
    response.setContentType(CONTENT_TYPE);
    response.setHeader("Pragma", "no-cache");
    response.setHeader("Expires", "0");
    response.setHeader("Cache-Control", "no-store");

    PrintWriter out = response.getWriter();
    ExamineeDAO eDAO = null;
    eDAO = new ExamineeDAO();
    boolean overlap = eDAO.hasUser(var0);
    out.println("<font color=\"#FF0000\" size=\"+1\"><b>");
    if (overlap) {
      out.println("此准考证号已被注册过,请重新输入!");
    }
    out.println("</b></font>");
    out.close();
  }

  
  public void destroy() {
  }
}
