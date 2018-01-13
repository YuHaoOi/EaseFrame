package com.jlkf.jdsaleside;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.jlkf.jdsaleside.home.HomeFragment;
import com.jlkf.jdsaleside.mine.MineFragment;
import com.jlkf.jdsaleside.msg.MsgFragment;
import com.jlkf.jdsaleside.other.adapters.MyPagerAdapter;
import com.jlkf.jdsaleside.other.base.BaseActivity;
import com.jlkf.jdsaleside.other.base.util.OiConstants;
import com.jlkf.jdsaleside.other.base.util.FileMaker;
import com.jlkf.jdsaleside.other.utils.StatusBarUtil;
import com.jlkf.jdsaleside.other.utils.TabEntity;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.ArrayList;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {


    @BindView(R.id.vp_home)
    ViewPager mVpHome;
    @BindView(R.id.tablayout_home)
    CommonTabLayout mTablayoutHome;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitles = {"课程", "通讯录", "个人中心"};
    private int[] mIconSelectIds = {
            R.mipmap.home_tab1, R.mipmap.home_tab2,
            R.mipmap.home_tab3};
    private int[] mIconUnselectIds = {
            R.mipmap.home_tab11, R.mipmap.home_tab22,
            R.mipmap.home_tab33};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private int lastSel;
    private FileMaker fileMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        folderInit();
    }

    private void folderInit() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RxPermissions rxPermissions = new RxPermissions(this);
            rxPermissions.requestEach(
                    Manifest.permission.CAMERA,
                    Manifest.permission.INTERNET,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Permission>() {
                @Override
                public void accept(@NonNull Permission permission) throws Exception {
                    if (permission.granted) {
                        fileMaker = FileMaker.getInstance();
                        fileMaker.setMainPath(getString(R.string.app_name));
                        fileMaker.createFolder(OiConstants.FOLDER_DATA, "data");
                        fileMaker.createFolder(OiConstants.FOLDER_IMAGE, "image");
                        fileMaker.createFolder(OiConstants.FOLDER_CACHE, "cache");
                    } else if (permission.shouldShowRequestPermissionRationale) {
                    } else {
                    }
                }
            });
        } else {
            fileMaker = FileMaker.getInstance();
            fileMaker.setMainPath(getString(R.string.app_name));
            fileMaker.createFolder(OiConstants.FOLDER_DATA, "data");
            fileMaker.createFolder(OiConstants.FOLDER_IMAGE, "image");
            fileMaker.createFolder(OiConstants.FOLDER_CACHE, "cache");
        }
    }

    @Override
    protected void initViews() {
        StatusBarUtil.transparencyBar(this);
        initTabs();
    }

    @Override
    protected void initEvents() {
       stringFormat();
    }

    private void stringFormat() {

    }

    @Override
    protected void initDatas() {

    }

    private void initTabs() {
        mFragments.add(new HomeFragment());
        mFragments.add(new MsgFragment());
        mFragments.add(new MineFragment());

        mVpHome.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), mFragments, mTitles));
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mVpHome.setOffscreenPageLimit(2);
        mTablayoutHome.setTabData(mTabEntities);
        mTablayoutHome.setOnTabSelectListener(new OnTabSelectListener() {

            @Override
            public void onTabSelect(int position) {
                mVpHome.setCurrentItem(position, false);
            }

            @Override
            public void onTabReselect(int position) {
            }
        });

        mVpHome.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                lastSel = position;
                mTablayoutHome.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}