package org.mmxbb.exam.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Filter {
  public Filter() {
  }

  public String[] Split(String src) {
    String[] res = new String[5];
    StringTokenizer tokenizer = new StringTokenizer(src, "##");
    int i = 0;
    while (tokenizer.hasMoreTokens()) {
      res[i++] = tokenizer.nextToken();
    }
    return res;
  }

  public ArrayList parseString(String strSource) {
    String strSpace = "##";
    ArrayList list = new ArrayList();

    StringTokenizer strTokenizer = new StringTokenizer(strSource, "##");
    while (strTokenizer.hasMoreTokens()) {
      list.add(strTokenizer.nextToken());
    }

    for (int i = 0; i < list.size(); i++) {
      String str = (String) list.get(i);
      if (" ".equals(str)) {
        list.remove(" ");
      }
    }
    return list;
  }

  public String[] SplitE_idList(String e_idList) {
    String[] stringMultibox = null;
    String[] strsE_type = new String[4];
    StringTokenizer tokenizer = new StringTokenizer(e_idList, "@@");
    int i = 0;
    while (tokenizer.hasMoreTokens()) {
      strsE_type[i] = tokenizer.nextToken();
      i++;
    }
    int k = 0;
    for (int j = 0; j < strsE_type.length; j++) {
      StringTokenizer st = new StringTokenizer(strsE_type[j], "##");
      while (st.hasMoreTokens()) {
        st.nextToken();
        k++;
      }
    }
    stringMultibox = new String[k];
    k = 0;
    for (int j = 0; j < strsE_type.length; j++) {
      StringTokenizer st = new StringTokenizer(strsE_type[j], "##");
      while (st.hasMoreTokens()) {
        stringMultibox[k] = st.nextToken();
        k++;
      }
    }
    return stringMultibox;
  }

  public String connectBefore(String[] resource) {
    int i = 0;
    int len = resource.length;
    StringBuffer result = new StringBuffer();
    while (i < len) {
      result.append("##");
      result.append(resource[i].toString());
      i++;
    }
    return result.toString();
  }


  public String connect(String[] resource) {
    int i = 0;
    int len = resource.length;
    StringBuffer result = new StringBuffer();
    while (i < len) {
      result.append(resource[i].toString());
      if (i < len - 1) {
        result.append("##");
      }
      i++;
    }
    return result.toString();
  }

  public String Connect(String[] resource) {
    StringBuffer result = new StringBuffer("0");
    int i = 0;
    int len = resource.length;
    if (len > 0) {
      result = new StringBuffer();
    } while (i < len) {
      result.append("##");
      if(resource[i] != null){
        result.append(resource[i].toString());
      }
      else{
        result.append("0");
      }
      i++;
    }

    return result.toString();
  }


}
