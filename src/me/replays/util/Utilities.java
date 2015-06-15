package me.replays.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

  public static String md5(String string) throws UnsupportedEncodingException {
    return DigestUtils.md5Hex(string);
  }

  public static String upper(boolean bool) {
    char[] c = Boolean.toString(bool).toCharArray();
    c[0] = Character.toUpperCase(c[0]);
    return new String(c);
  }
}