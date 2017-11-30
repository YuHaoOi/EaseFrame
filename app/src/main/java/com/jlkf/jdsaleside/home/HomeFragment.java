package com.jlkf.jdsaleside.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.jdsaleside.R;
import com.jlkf.jdsaleside.other.base.BaseFragment;


/**
 * Created by DuoNuo on 2017/10/26.
 */

public class HomeFragment extends BaseFragment {

    @Override
    public View onCreateRootView(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }


    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    protected void initViews() {
    }

}
