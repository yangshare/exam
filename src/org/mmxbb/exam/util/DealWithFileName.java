package org.mmxbb.exam.util;

import java.io.*;

public class DealWithFileName {
  public static String dealFileName(String filename) throws IOException {

    //filename = new String(filename.getBytes(), "UTF-8");

    String newFileName = "";
    int i = 0;
    i = filename.indexOf("\\");
    if (i > 0) {
      newFileName = filename.substring(i + 1, filename.length());
    } else {
      newFileName = filename;
    }
    i = newFileName.indexOf('.');
    String extName = newFileName.substring(i + 1, newFileName.length());
    newFileName = newFileName.substring(0, i);
    while ( (i = newFileName.indexOf("?")) > -1) {
      newFileName = newFileName.substring(0, i - 1) +
          newFileName.substring(i + 1, newFileName.length());

    }
    return newFileName + "." + extName;
  }

}
