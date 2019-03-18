package com.songdehuai.commonexample.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.songdehuai.commonexample.R;
import com.songdehuai.commonexample.ui.account.entity.LoginEntity;
import com.songdehuai.commonexample.ui.account.entity.LoginParams;
import com.songdehuai.commonlib.base.BaseActivity;
import com.songdehuai.commonlib.base.BaseParams;
import com.songdehuai.commonlib.base.BaseSync;
import com.songdehuai.commonlib.base.ParamsBuilder;
import com.songdehuai.commonlib.net.Result;
import com.songdehuai.commonlib.net.ResultCallBack;
import com.songdehuai.commonlib.utils.FreeSync;
import com.songdehuai.commonlib.utils.imagepicker.ImagePicker;
import com.songdehuai.commonlib.utils.imagepicker.ImagePickerCallBack;


import java.util.HashMap;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TestActivity extends BaseActivity<String> implements BaseSync<String> {

    private Button send_btn;
    private EditText log_et;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        send_btn = findViewById(R.id.send_btn);
        log_et = findViewById(R.id.log_et);


        ImagePicker.getInstance().startImagePicker(this, new ImagePickerCallBack() {
            @Override
            public void onSuccess(Uri uri, String filePath) {

            }
        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void showData(String s) {
        super.showData(s);

    }
}
