package org.mmxbb.exam.business.program;
import org.apache.struts.action.*;

public class DistributeActionForm extends ActionForm {
    public DistributeActionForm() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private int xh;
    private long t_id;
    private String e_name;
    private String e_begin;
    private String e_end;
    private String e_state;
    private String e_grade;
    private String e_examineeList;
    private long e_id;
    public int getXh() {
        return xh;
    }

    public long getT_id() {
        return t_id;
    }

    public String getE_name() {
        return e_name;
    }

    public String getE_begin() {
        return e_begin;
    }

    public String getE_end() {
        return e_end;
    }

    public String getE_state() {
        return e_state;
    }

    public String getE_grade() {
        return e_grade;
    }

    public String getE_examineeList() {

        return e_examineeList;
    }

    public long getE_id() {
        return e_id;
    }

    public void setXh(int xh) {
        this.xh = xh;
    }

    public void setT_id(long t_id) {
        this.t_id = t_id;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public void setE_begin(String e_begin) {
        this.e_begin = e_begin;
    }

    public void setE_end(String e_end) {
        this.e_end = e_end;
    }

    public void setE_state(String e_state) {
        this.e_state = e_state;
    }

    public void setE_grade(String e_grade) {
        this.e_grade = e_grade;
    }

    public void setE_examineeList(String e_examineeList) {

        this.e_examineeList = e_examineeList;
    }

    public void setE_id(long e_id) {
        this.e_id = e_id;
    }

    private void jbInit() throws Exception {
    }
}
