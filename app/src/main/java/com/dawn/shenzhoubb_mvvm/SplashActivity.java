package com.dawn.shenzhoubb_mvvm;

import android.content.Intent;
import android.os.Bundle;

import com.dawn.lib_common.base.RxLifeObserver;
import com.dawn.lib_common.util.StatusBarUtil;

import java.util.concurrent.TimeUnit;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setLightStatusBar(this,true);
        Observable.timer(1, TimeUnit.SECONDS).subscribe(new RxLifeObserver<Long>(this) {
            @Override
            public void onNext(Long aLong) {
                Intent intent=new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}