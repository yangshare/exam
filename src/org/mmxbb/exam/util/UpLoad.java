package org.mmxbb.exam.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts.upload.FormFile;

public class UpLoad {
  private String relativePath = "/upload/images/";

  private String realPath = null;

  public UpLoad() {
  }

  public UpLoad(String workPath) {
    realPath = workPath + relativePath;
    File d = new File(workPath + "/upload"); 
    if (!d.exists()) { 
      System.out.println("dir does not exits! now make it");
      d.mkdir(); 
    }
    d = new File(workPath + "/upload" + "/images");
    if (!d.exists()) { 
      System.out.println("dir does not exits! now make it");
      d.mkdir(); 
    }
  }

  public String getRelativePath() {
    return relativePath;
  }

  public void setRelativePath(String relativePath) {
    this.relativePath = relativePath;
  }

  public String UpLoad(FormFile file) {
    try {
      File f = new File(realPath + file.getFileName());
      String fileName = file.getFileName();
      String pre = "";
      String middle = "";
      int i = 0;
      while (f.exists()) {
        i++;
        fileName = f.getName();
        if (i == 1) {
          pre = fileName.substring(0, fileName.lastIndexOf("."));
          fileName = fileName.replaceFirst(pre, pre + "_" + i);
        } else {
          pre = fileName.substring(0, fileName.lastIndexOf("_"));
          middle = fileName.substring(fileName.lastIndexOf("_"),
                                      fileName.lastIndexOf("."));
          fileName = fileName.replaceFirst(middle, "_" + i);
        }
        f = new File(realPath + "/" + fileName);
      }
      UpLoad(file, f.getName());
      return f.getName();
    } catch (Exception ex) {
      return "";
    }
  }

  public void UpLoad(FormFile file, String fileName) {
    try {
      InputStream stream = file.getInputStream();
      OutputStream bos = new FileOutputStream(realPath + fileName);
      int bytesRead = 0;
      byte[] buffer = new byte[8192];
      while ( (bytesRead = stream.read(buffer, 0, 8192)) != -1) {
        bos.write(buffer, 0, bytesRead);
      }
      bos.close();
      stream.close();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
 public void uploadFile(FormFile formFile, String filePath) {
    try {
      InputStream inputStream = formFile.getInputStream(); 
      OutputStream outputStream = new FileOutputStream(filePath); 
      int bytesRead = 0;
      byte[] buffer = new byte[8192];
      while ( (bytesRead = inputStream.read(buffer, 0, 8192)) != -1) {
        outputStream.write(buffer, 0, bytesRead); 
      }
      outputStream.close();
      inputStream.close();
    } catch (IOException ioe) {
      ioe.getMessage();
    }
  }
}
