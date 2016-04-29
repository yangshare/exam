package org.mmxbb.exam.util;

//The main function of this class is to get the remain string from a original string reducing a certain substring
public class StringUtil {

  public StringUtil() {}

  public String remainGetter(String original, String sub) {
    if (original.indexOf(sub) == 0) { //If the substring is at the beginning of the original string
      return original.substring(sub.length(), original.length());
    } else if (original.indexOf(sub) + sub.length() == original.length()) { //If the substring is at the mid of the original string
      return original.substring(0, original.indexOf(sub));
    } else { //If the substring is at the bottom of the original string
      return original.substring(0, original.indexOf(sub)) +
          original.substring(original.indexOf(sub) + sub.length(),
                             original.length());
    }
  }
}
