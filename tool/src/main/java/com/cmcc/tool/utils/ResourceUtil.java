package com.cmcc.tool.utils;

import android.content.Context;

import java.lang.reflect.Field;


/**
 * 
 * @author CMCC-ZHENGCHENG
 * @category 资源解析反射类，为打jar包服务
 */
public class ResourceUtil {

  /**
   * 安卓自带资源ID获取
   */

  /**
   * 返回layout资源ID
   * 
   * @param paramContext 上下文
   * @param paramString 布局文件中定义的ID名
   * @return R.XX.ID
   */
  public static int getLayoutId(Context paramContext, String paramString) {
    return paramContext.getResources().getIdentifier(paramString, "layout",
        paramContext.getPackageName());
  }
  
  
/**
 * 返回字符串资源ID
 * @param paramContext
 * @param paramString
 * @return
 */
  public static int getStringId(Context paramContext, String paramString) {
    return paramContext.getResources().getIdentifier(paramString, "string",
        paramContext.getPackageName());
  }

  /**
   * 返回图片资源ID
   * @param paramContext
   * @param paramString
   * @return
   */
  public static int getDrawableId(Context paramContext, String paramString) {
    return paramContext.getResources().getIdentifier(paramString, "drawable",
        paramContext.getPackageName());
  }

  /**
   * 返回样式资源ID
   * @param paramContext
   * @param paramString
   * @return
   */
  public static int getStyleId(Context paramContext, String paramString) {
    return paramContext.getResources().getIdentifier(paramString, "style",
        paramContext.getPackageName());
  }
  
  /**
   * 返回组件资源ID
   * @param paramContext
   * @param paramString
   * @return
   */
  public static int getId(Context paramContext, String paramString) {
    return paramContext.getResources().getIdentifier(paramString, "id",
        paramContext.getPackageName());
  }

  /**
   * 返回颜色资源ID
   * @param paramContext
   * @param paramString
   * @return
   */
  public static int getColorId(Context paramContext, String paramString) {
    return paramContext.getResources().getIdentifier(paramString, "color",
        paramContext.getPackageName());
  }

  /**
   * 返回动画资源ID
   * @param paramContext
   * @param paramString
   * @return
   */
  public static int getAnimatorId(Context paramContext, String paramString) {
    return paramContext.getResources().getIdentifier(paramString, "animator",
        paramContext.getPackageName());
  }

  /**
   * 返回数组资源ID
   * @param paramContext
   * @param paramString
   * @return
   */
  public static int getArrayId(Context paramContext, String paramString) {
    return paramContext.getResources().getIdentifier(paramString, "array",
        paramContext.getPackageName());
  }

  /**
   * 安卓非自带资源ID获取
   */
  /**
   * Context.getResources().getIdentifier无法获得styleable相关数据，用此方法
   * 
   * @param paramContext
   * @param paramsString
   * @return
   */
  public static int getStyleable(Context paramContext, String paramsString) {
    return ((Integer) getResourceId(paramContext, paramsString, "styleable")).intValue();
  }

  /**
   * 获取styleable的ID号数组
   * 
   * @param paramContext
   * @param paramsString
   * @return
   */
  public static int[] getStyleableArray(Context paramContext, String paramsString) {
    return (int[]) getResourceId(paramContext, paramsString, "styleable");
  }

  private static Object getResourceId(Context paramContext, String paramsString, String type) {
    String className = paramContext.getPackageName() + ".R";
    try {
      Class<?> cls = Class.forName(className);
      for (Class<?> childClass : cls.getClasses()) {
        String simple = childClass.getSimpleName();
        if (simple.equals(type)) {
          for (Field field : childClass.getFields()) {
            String fieldName = field.getName();
            if (fieldName.equals(paramsString)) {
              return field.get(null);
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }


}
