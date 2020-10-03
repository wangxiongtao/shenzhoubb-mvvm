package com.dawn.lib_common.util;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.githang.statusbar.StatusBarCompat;

/**
 * Created by Administrator on 2018/2/26 0026.
 * https://github.com/msdx/status-bar-compat
 */

public class StatusBarUtil {

    /**
     *  compile 'com.githang:status-bar-compat:0.7'
     * 设置状态栏颜色
     * setContentView方法之后调用
     * @param activity
     * @param colorResId 颜色可以是资源 也可以是Color.parseColor()的转换结果
     */
    public static void setStatusBarColor(Activity activity, int colorResId) {

        StatusBarCompat.setStatusBarColor(activity, colorResId);
    }

    /**
     *根据状态栏的颜色改变状态栏文字的颜色 主要是只有黑白两种
     * @param activity
     * @param isLightStatusBar 状态栏的背景颜色是否是轻色调的也就是趋于白色的。如果是true 则状态栏文字就是黑色的反之文字就是白色的
     */
    public static void setLightStatusBar(Activity activity, boolean isLightStatusBar) {
        if(activity==null){
            return;
        }
        StatusBarCompat.setLightStatusBar(activity.getWindow(), isLightStatusBar);
    }
    public static void setStatusBarColor(Dialog dialog, int colorResId) {

        StatusBarCompat.setStatusBarColor(dialog.getWindow(), colorResId,true);
    }

    /**
     * 布局的内容延伸到状态栏并且状态栏透明
     * @param activity
     */
    public static void setContentToStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//
        } else {
            Window win = activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
    }
    /**
     * 布局的内容延伸到状态栏并且状态栏透明
     * 与setStatusBarColor方法不能共存
     */
    public static void setContentToStatusBar(Dialog dialog) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = dialog.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//
        } else {
            Window win = dialog.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            winParams.flags |= bits;
            win.setAttributes(winParams);
        }
    }

}
