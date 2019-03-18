package com.songdehuai.commonlib.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.songdehuai.commonlib.R;

/**
 * 标题控件
 */
public class TitleView extends RelativeLayout {

    private TextView finishTv;
    private ImageView finishIv;
    private TextView titleTv;
    private LinearLayout finishLi;
    private TextView rightTv;
    private ImageView rightIv;
    private RelativeLayout rightRl;


    public TitleView(Context context) {
        this(context, null);
        initViews();
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initViews();
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }


    private void initViews() {
        inflate(getContext(), R.layout.view_title, this);
        finishTv = findViewById(R.id.title_finish_tv);
        finishIv = findViewById(R.id.title_finish_iv);
        finishLi = findViewById(R.id.title_finish_li);
        titleTv = findViewById(R.id.title_tv);
        rightTv = findViewById(R.id.title_right_tv);
        rightRl = findViewById(R.id.title_right_rl);
        rightIv = findViewById(R.id.title_right_iv);

        rightRl.setVisibility(View.GONE);
    }
}
