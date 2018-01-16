package com.jlkf.jdsaleside.other.base.util;


/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2016/5/30
 */
public class AppState {

    private String userName;
    private boolean isFirstIn;
    private static AppState appState;

    private AppState(){}

    public static AppState getInstance() {
        if (appState == null) {
            synchronized (AppState.class) {
                if (appState == null) {
                    appState = new AppState();
                }
            }
        }
        return appState;
    }

    public String getUserName() {
        userName = ShareUtils.getInstance().getString(OiConstants.FLAG_USERNAME);
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        ShareUtils.getInstance().saveString(OiConstants.FLAG_USERNAME, userName);
    }

    public boolean isFirstIn(){
        isFirstIn = ShareUtils.getInstance().getBoolean(OiConstants.FLAG_IS_FIRST, true);
        return isFirstIn;
    }

    public void setFirstIn(){
        isFirstIn = false;
        ShareUtils.getInstance().saveBoolean(OiConstants.FLAG_IS_FIRST, false);
    }
}
