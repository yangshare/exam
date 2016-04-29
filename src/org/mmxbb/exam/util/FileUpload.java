package org.mmxbb.exam.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts.upload.FormFile;

public class FileUpload {

  public void uploadFile(FormFile formFile, String filePath) {
    try {
      InputStream inputStream = formFile.getInputStream(); //param1 for inputStream
      OutputStream outputStream = new FileOutputStream(filePath); //param2 for outputStream
      int bytesRead = 0;
      byte[] buffer = new byte[8192];
      while ( (bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
        outputStream.write(buffer, 0, bytesRead);
      }
      outputStream.close();
      inputStream.close();
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }
  }
}
