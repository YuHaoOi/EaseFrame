package com.jlkf.jdsaleside.other.activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.jlkf.jdsaleside.MainActivity;
import com.jlkf.jdsaleside.R;
import com.jlkf.jdsaleside.other.base.BaseActivity;
import com.jlkf.jdsaleside.other.base.util.AppState;
import com.jlkf.jdsaleside.other.utils.OiSimpleTransformer;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

public class StartActivity extends BaseActivity {

    @BindView(R.id.start_iv)
    ImageView startIv;

    private static final int ANIM_TIME = 2000;
    private static final float SCALE_END = 1.15F;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断是否是第一次开启应用
        boolean isFirstOpen = AppState.getInstance().isFirstIn();
        // 如果是第一次启动，则先进入功能引导页
        if (isFirstOpen) {
            WelcomeActivity.actionStart(this);
            finish();
            return;
        }
        setContentView(R.layout.activity_start);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        startMainActivity();
    }

    @Override
    protected void initDatas() {

    }

    private void startMainActivity() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .compose(new OiSimpleTransformer<Long>())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        startAnim();
                    }
                });
    }


    private void startAnim() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(startIv, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(startIv, "scaleY", 1f, SCALE_END);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(ANIM_TIME).play(animatorX).with(animatorY);
        set.start();
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                LoginOneActivity.actionStart(StartActivity.this);
                finish();
            }
        });
    }

    /**
     * 屏蔽物理返回按钮
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
