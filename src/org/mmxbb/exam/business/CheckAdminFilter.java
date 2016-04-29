package org.mmxbb.exam.business;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class CheckAdminFilter
    extends HttpServlet
    implements javax.servlet.Filter {
  private FilterConfig filterConfig;
  final String LOGIN_PAGE = "../index.jsp";

  
  public void init(FilterConfig filterConfig) throws ServletException {
    this.filterConfig = filterConfig;
  }

  public void setFilterConfig(final FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }

  
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain filterChain) {
    HttpServletRequest hreq = (HttpServletRequest) request;
    HttpServletResponse hres = (HttpServletResponse) response;
    HttpSession session = hreq.getSession();
  
    try {
          filterChain.doFilter(request, response);
        } catch (ServletException ex) {
          ex.printStackTrace();
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        return;

  }

  
  public void destroy() {
    this.filterConfig = null;
  }
}
