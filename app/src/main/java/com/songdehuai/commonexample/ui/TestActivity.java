package com.songdehuai.commonexample.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.songdehuai.commonexample.R;
import com.songdehuai.commonlib.base.BaseActivity;
import com.songdehuai.commonlib.utils.filter.QuickClick;
import com.songdehuai.commonlib.utils.imagepicker.ImagePicker;
import com.songdehuai.commonlib.utils.imagepicker.ImagePickerCallBack;


import androidx.annotation.Nullable;

public class TestActivity extends BaseActivity {

    private Button send_btn;
    private EditText log_et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test, "测试");

        send_btn = findViewById(R.id.send_btn);
        log_et = findViewById(R.id.log_et);

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}
