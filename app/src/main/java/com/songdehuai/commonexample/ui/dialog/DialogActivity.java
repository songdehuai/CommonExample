package com.songdehuai.commonexample.ui.dialog;

import android.app.Activity;
import android.os.Bundle;

import com.songdehuai.commonexample.R;

import androidx.annotation.Nullable;


public class DialogActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        findViewById(R.id.finish_btn).setOnClickListener(v -> {
            finish();
        });
    }

}
