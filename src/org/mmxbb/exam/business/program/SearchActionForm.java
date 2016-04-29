package org.mmxbb.exam.business.program;

import javax.servlet.http.*;

import org.apache.struts.action.*;

public class SearchActionForm extends ActionForm {
    public SearchActionForm() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String e_type;
    private String e_name;
    private String e_state;
    private String e_begin;
    private String e_end;
    private String e_grade;
    private int page;
    private String pagestr;
    private String action;
    public String getE_type() {
        return e_type;
    }

    public String getE_name() {
        return e_name;
    }

    public String getE_state() {

        return e_state;
    }

    public String getE_begin() {
        return e_begin;
    }

    public String getE_end() {
        return e_end;
    }

    public String getE_grade() {
        return e_grade;
    }

    public int getPage() {
        return page;
    }

    public String getPagestr() {
        return pagestr;
    }

    public String getAction() {
        return action;
    }

    public void setE_type(String e_type) {
        this.e_type = e_type;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public void setE_state(String e_state) {

        this.e_state = e_state;
    }

    public void setE_begin(String e_begin) {
        this.e_begin = e_begin;
    }

    public void setE_end(String e_end) {
        this.e_end = e_end;
    }

    public void setE_grade(String e_grade) {
        this.e_grade = e_grade;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPagestr(String pagestr) {
        this.pagestr = pagestr;
    }

    public void setAction(String action) {
        this.action = action;
    }

    private void jbInit() throws Exception {
    }

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest request) {
        this.e_type = null;
        this.e_name = null;
        this.e_state = null;
        this.e_begin = null;
        this.e_end = null;
        this.e_grade = null;
        this.page = 0;
        this.pagestr = null;
        this.action = null;

    }


}
