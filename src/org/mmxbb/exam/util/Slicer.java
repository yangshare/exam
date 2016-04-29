package org.mmxbb.exam.util;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Slicer {
  public Slicer() {
  }

  public ArrayList spilt(String input) {
    ArrayList output = null;
    output = new ArrayList();
    StringTokenizer tokenizer = new StringTokenizer(input, "##");

    while (tokenizer.hasMoreTokens()) {
      output.add(tokenizer.nextToken());
    }
    return output;
  }
}
