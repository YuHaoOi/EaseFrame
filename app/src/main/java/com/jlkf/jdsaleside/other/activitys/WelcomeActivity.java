package com.jlkf.jdsaleside.other.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.jlkf.jdsaleside.R;
import com.jlkf.jdsaleside.other.adapters.GuideViewPagerAdapter;
import com.jlkf.jdsaleside.other.base.BaseActivity;
import com.jlkf.jdsaleside.other.base.util.AppState;
import com.jlkf.jdsaleside.other.widget.DotView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.guide_vp)
    ViewPager guideVp;
    @BindView(R.id.banner)
    DotView dotView;
    private Button startBtn;


    private List<View> views;
    // 引导页图片资源
    private static final int[] pics = { R.layout.guide_view1,
            R.layout.guide_view2, R.layout.guide_view3};
    private GuideViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, WelcomeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {

        views = new ArrayList<>();

        //初始化引导页视图列表
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i], null);

            if (i == pics.length - 1) {
                startBtn = view.findViewById(R.id.btn_enter);
                startBtn.setTag("enter");
                startBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AppState.getInstance().setFirstIn();
                        LoginOneActivity.actionStart(WelcomeActivity.this);
                        finish();
                    }
                });
            }

            views.add(view);
        }

        adapter = new GuideViewPagerAdapter(views);
        guideVp.setAdapter(adapter);
        guideVp.addOnPageChangeListener(new PageChangeListener());

        dotView.setDotNum(pics.length);
    }


    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int position) {

        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
            dotView.setChecked(position);
        }
    }
}
