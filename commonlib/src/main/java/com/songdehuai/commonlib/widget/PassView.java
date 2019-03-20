package com.songdehuai.commonlib.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.ImageView;


import com.songdehuai.commonlib.R;

import androidx.annotation.Nullable;

/**
 * 密码输入眼睛
 */
@SuppressLint("AppCompatCustomView")
public class PassView extends ImageView {

    private boolean isShow = false;
    private EditText editText;
    private boolean isDark = false;

    public PassView(Context context) {
        super(context);
        initViews();
    }

    public PassView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public PassView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public boolean isDark() {
        return isDark;
    }

    public void setDark(boolean dark) {
        isDark = dark;
        if (isDark) {
            setImageResource(R.drawable.ic_pass_close9);
        } else {
            setImageResource(R.drawable.ic_pass_close);
        }
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    private void initViews() {
        if (isDark) {
            setImageResource(R.drawable.ic_pass_close9);
        } else {
            setImageResource(R.drawable.ic_pass_close);
        }
        setOnClickListener(v -> {
            if (editText != null) {
                if (isShow) {
                    if (isDark) {
                        setImageResource(R.drawable.ic_pass_close9);
                    } else {
                        setImageResource(R.drawable.ic_pass_close);
                    }
                    isShow = false;
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {
                    if (isDark) {
                        setImageResource(R.drawable.ic_pass_open9);
                    } else {
                        setImageResource(R.drawable.ic_pass_open);
                    }

                    isShow = true;
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });
    }

}
