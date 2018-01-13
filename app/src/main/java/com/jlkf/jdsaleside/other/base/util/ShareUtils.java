package com.jlkf.jdsaleside.other.base.util;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * description
 *
 * @author created by wuwang on 2016/5/25
 */
public class ShareUtils {

    private static ShareUtils instance;
    private SharedPreferences sp;
    private final String defaultFlag="hello123456ForceSeeYou";

    private ShareUtils(){
    }

    public static ShareUtils getInstance(){
        if(instance==null){
            synchronized (ShareUtils.class){
                if(instance==null){
                    instance=new ShareUtils();
                }
            }
        }
        return instance;
    }

    public void init(Application application){
        sp=application.getSharedPreferences(application.getPackageName(), Context.MODE_PRIVATE);
    }

    public void setBoolean(String flag, boolean bool){
        sp.edit().putBoolean(flag,bool).apply();
    }

    public boolean getBoolean(String flag, boolean defaultFlag){
        return sp.getBoolean(flag,defaultFlag);
    }


    public void saveString(String key, String value){
        sp.edit().putString(key,value).apply();
    }

    public void saveLong(String key, long value){
        sp.edit().putLong(key,value).apply();
    }

    public void saveInt(String key, int value){
        sp.edit().putInt(key,value).apply();
    }

    public String getString(String key){
        return sp.getString(key,null);
    }

    public long getLong(String key, long value){
        return sp.getLong(key,value);
    }

    public int getInt(String key, int value){
        return sp.getInt(key,value);
    }
}
