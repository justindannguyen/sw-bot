package com.justin.swbot.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileUtil {
  public static String readTextFile(String path) {
    StringBuilder sb = new StringBuilder();
    BufferedReader br = null;
    FileReader fr = null;

    try {
      fr = new FileReader(path);
      br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (br != null) br.close();
        if (fr != null) fr.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    return sb.toString();
  }

  public static boolean writeTextFile(String path, String content) {
    PrintWriter writer = null;
    try {
      writer = new PrintWriter(path);
      writer.print(content);
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    } finally {
      if (writer != null) {
        writer.close();
      }
    }
  }
}
