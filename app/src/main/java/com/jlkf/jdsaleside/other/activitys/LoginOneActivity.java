package com.jlkf.jdsaleside.other.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jlkf.jdsaleside.MainActivity;
import com.jlkf.jdsaleside.R;
import com.jlkf.jdsaleside.other.base.BaseActivity;
import com.jlkf.jdsaleside.other.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginOneActivity extends BaseActivity implements TextWatcher {

    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.password_et)
    EditText passwordEt;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.tomain_tv)
    TextView toMainTv;

    @OnClick({R.id.login_btn, R.id.tomain_tv})
    public void viewClick(View view) {
        switch (view.getId()){
            case R.id.login_btn:
                MainActivity.actionStart(this);
                break;
            case R.id.tomain_tv:
                MainActivity.actionStart(this);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_one);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginOneActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setOiStatusBarColor(this);
        StatusBarUtil.StatusBarDarkMode(this);
    }

    @Override
    protected void initEvents() {
        nameEt.addTextChangedListener(this);
        passwordEt.addTextChangedListener(this);
    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (nameEt.length() > 0 && passwordEt.length() > 0){
            loginBtn.setEnabled(true);
        } else {
            loginBtn.setEnabled(false);
        }
    }
}
