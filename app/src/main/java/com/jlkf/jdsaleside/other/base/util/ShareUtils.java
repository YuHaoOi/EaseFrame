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

    public void setFlag(String flag, boolean bool){
        sp.edit().putBoolean(flag,bool).apply();
    }

    public boolean getFlag(String flag, boolean defaultFlag){
        return sp.getBoolean(flag,defaultFlag);
    }


    public void setCache(String key, String value){
        sp.edit().putString(key,value).apply();
    }

    public void setCache(String key, long value){
        sp.edit().putLong(key,value).apply();
    }

    public void setCache(String key, int value){
        sp.edit().putInt(key,value).apply();
    }

    public String getCache(String key){
        return sp.getString(key,null);
    }
    public long getCacheLong(String key){
        return sp.getLong(key, System.currentTimeMillis());
    }
    public long getCache(String key, long value){
        return sp.getLong(key,value);
    }

    public int getCache(String key, int value){
        return sp.getInt(key,value);
    }
}
