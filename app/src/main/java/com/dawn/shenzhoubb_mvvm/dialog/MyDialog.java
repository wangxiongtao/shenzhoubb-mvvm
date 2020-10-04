package com.dawn.shenzhoubb_mvvm.dialog;

import android.content.Context;

import com.dawn.lib_common.dialog.BaseDialog;
import com.dawn.shenzhoubb_mvvm.R;

import androidx.annotation.NonNull;

public class MyDialog extends BaseDialog {
    public MyDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_layout;
    }

    @Override
    public void initData() {


    }
}
