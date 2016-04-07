package com.cmcc.tool.utils;

import java.text.DecimalFormat;

/**
 * 将long、int、double等string进行格式化
 * 
 * @author CMCC-ZHENGCHENG
 *
 */
public class FormatValueStringUtil {
  /**
   * Long 型数据格式化
   * 
   * @param value 数据
   * @return 格式化后数据
   */
  public static String fomatLongString(Long value) {
    DecimalFormat df = new DecimalFormat("#,###");
    return String.valueOf(df.format(value));
  }

  /**
   * String类型的Long字符串数据格式化
   * 
   * @param value 数据
   * @return 格式化后数据
   */
  public static String fomatLongString(String value) {
    DecimalFormat df = new DecimalFormat("#,###");
    return String.valueOf(df.format(Long.parseLong(value)));
  }

  /**
   * int 型数据格式化
   * 
   * @param value 数据
   * @return 格式化后数据
   */
  public static String fomatIntString(int value) {
    DecimalFormat df = new DecimalFormat("#,###");
    return String.valueOf(df.format(value));
  }

  /**
   * Double 型数据格式化
   * 
   * @param value 数据
   * @return 格式化后数据
   */
  public static String fomatDoubleString(Double value) {
    DecimalFormat df = new DecimalFormat("#,###.##");
    return String.valueOf(df.format(value));
  }
}
