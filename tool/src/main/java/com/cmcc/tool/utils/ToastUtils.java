package com.cmcc.tool.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * ToastUtils
 *
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-12-9
 */
public class ToastUtils {
    private static Toast toast = null; //Toast的对象！

    public static void showToast(Context mContext, String message) {
        if (toast == null) {
            toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
        }
        else {
            toast.setText(message);
        }
        toast.show();
        Log.e("Toast",message);
    }
}
