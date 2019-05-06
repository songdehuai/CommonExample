package com.songdehuai.commonexample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.songdehuai.commonexample.R;
import com.songdehuai.commonlib.base.BaseActivity;

import org.jetbrains.annotations.Nullable;


public class SplashActivity extends BaseActivity {

    ImageView splashIv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splashIv = findViewById(R.id.splash_iv);
        splashIv.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000);

    }

}
