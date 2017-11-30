package com.jlkf.jdsaleside.other.utils;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.TextView;

import com.jlkf.jdsaleside.R;

import razerdp.basepopup.BasePopupWindow;

/**
 * Created by DuoNuo on 2017/9/22.
 */

public class SingleInfoPop extends BasePopupWindow {

    private final TextView contentTv;

    public SingleInfoPop(Activity context) {
        super(context);
        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        contentTv = (TextView) findViewById(R.id.content);
    }

    public void setContent(String content) {
        contentTv.setText(content);
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.pop_single_info);
    }

    @Override
    public View initAnimaView() {
        return findViewById(R.id.popup_anima);
    }
}
