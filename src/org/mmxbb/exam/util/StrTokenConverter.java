package org.mmxbb.exam.util;



import java.util.ArrayList;
import java.util.StringTokenizer;

public class StrTokenConverter {
  public static String convert(String[] strs) {
    String str = "##";
    int l = strs.length;
    if (l == 0) {
      return null;
    }
    for (int i = 0; i < l - 1; i++) {
      str += strs[i] + "##";
    }
    str += strs[l - 1];
    return str;
  }

  public static String[] convert(String str) {
    String[] strs;
    ArrayList list = new ArrayList();
    StringTokenizer strTokenizer = new StringTokenizer(str, "##");
    while (strTokenizer.hasMoreTokens()) {
      list.add(strTokenizer.nextToken());
    }
    int length = list.size();
    strs = new String[length];
    for (int i = 0; i < length; i++) {
      strs[i] = (String) list.get(i);
    }
    return strs;
  }
}
