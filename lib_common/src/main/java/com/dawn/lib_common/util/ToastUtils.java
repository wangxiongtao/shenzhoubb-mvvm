package com.dawn.lib_common.util;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.dawn.lib_common.base.BaseApplication;

/**
 * Created by lilfi on 2017/2/16.
 */

public class ToastUtils {

    private static Toast sToast;

    public static void showShortToast(Context context, String content) {
        showToast(context,content, Toast.LENGTH_SHORT);
    }
    public static void showShortToast2(String content) {
        Toast toast= Toast.makeText(BaseApplication.getApplication(),content, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }
    public static void showLongToast(Context context, String content) {
       showToast(context,content, Toast.LENGTH_LONG);
    }
    private static void showToast(Context context, String content, int duration) {
        Handler handler=new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (sToast == null) {
                    sToast = Toast.makeText(context.getApplicationContext(), content, duration);
                } else {
                    View view = sToast.getView();
                    sToast.cancel();
                    sToast = new Toast(context.getApplicationContext());
                    sToast.setView(view);
                    sToast.setDuration(duration);
                    sToast.setText(content);
                }
//                sToast.setGravity(Gravity.BOTTOM,0, (int) (context.getApplicationContext().getResources().getDimension(R.dimen.margin_10)*15));//y偏移150dp
                sToast.show();
            }
        });

    }

}
