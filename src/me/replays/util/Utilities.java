package me.replays.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.codec.digest.DigestUtils;

public class Utilities {
  public static String getExtension(File file) {
    String fileName = file.getName();
    int i = fileName.lastIndexOf('.');
    return i > 0 ? fileName.substring(i + 1) : "";
  }

  public static String md5(File file) throws IOException {
    FileInputStream fis = new FileInputStream(file);
    String md5 = DigestUtils.md5Hex(fis);
    fis.close();
    return md5;
  }
}