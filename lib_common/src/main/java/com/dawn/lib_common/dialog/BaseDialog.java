package com.dawn.lib_common.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.dawn.lib_common.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;


/**
 * Created by Administrator on 2018/4/3 0003.
 */

public abstract class BaseDialog extends AlertDialog {
    protected Context activity;



    public BaseDialog(@NonNull Context context) {
        super(context, R.style.half_trans_Dialog);
        this.activity =context;
    }

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.activity =context;
    }

    protected BaseDialog(@NonNull Context context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.activity =context;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initData();

    }
    public abstract int getLayoutId();

    public abstract void initData();

    public Activity getActivity() {
        return (Activity)activity;
    }

    @Override
    public void show() {
        try {
            if(activity instanceof Activity){
                Activity activity= (Activity) this.activity;
                if(activity.isDestroyed()||activity.isFinishing()||isShowing()){
                    return;
                }
                super.show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void dismiss() {
        try {
            if(activity instanceof Activity){
                Activity activity= (Activity) this.activity;
                if(activity.isFinishing()||!isShowing()){
                    return;
                }
                super.dismiss();
            }
        }catch (Exception e){
            e.printStackTrace();

        }


    }
}
