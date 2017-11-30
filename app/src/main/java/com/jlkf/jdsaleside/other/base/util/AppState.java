package com.jlkf.jdsaleside.other.base.util;


/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2016/5/30
 */
public class AppState {

    private boolean isLogin;
    private String userId;
    private String userName;
    private String userHead;
    private String userToken;
    private String sex;
    private String userNick;
    private String password;
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

    public String getPassword(){
        return ShareUtils.getInstance().getCache(AppSet.FLAG_PASSWORD);
    }

    public void setPassword(String password){
        ShareUtils.getInstance().setCache(AppSet.FLAG_PASSWORD, password);
    }

    public String getUserNick() {
        userNick = ShareUtils.getInstance().getCache(AppSet.FLAG_NICKNAME);
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
        ShareUtils.getInstance().setCache(AppSet.FLAG_NICKNAME, userNick);
    }

    public String getSex() {
        sex = ShareUtils.getInstance().getCache(AppSet.FLAG_SEX);
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
        ShareUtils.getInstance().setCache(AppSet.FLAG_SEX, sex);
    }

    public boolean isLogin() {
        isLogin = ShareUtils.getInstance().getFlag(AppSet.FLAG_ISLOGIN, false);
        return isLogin;
    }

    public void setLogin(boolean login) {
        this.isLogin = login;
        ShareUtils.getInstance().setFlag(AppSet.FLAG_ISLOGIN, login);
    }

    public String getUserId() {
        userId = ShareUtils.getInstance().getCache(AppSet.FLAG_USERID);
        return userId;
    }

    public String getUserToken() {
        userToken = ShareUtils.getInstance().getCache(AppSet.FLAG_USERTOKEN);
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
        ShareUtils.getInstance().setCache(AppSet.FLAG_USERTOKEN, userToken);
    }

    public void setUserId(String userId) {
        this.userId = userId;
        ShareUtils.getInstance().setCache(AppSet.FLAG_USERID, userId);
    }


    public String getUserName() {
        userName = ShareUtils.getInstance().getCache(AppSet.FLAG_USERNAME);
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        ShareUtils.getInstance().setCache(AppSet.FLAG_USERNAME, userName);
    }

    public String getUserHead() {
        userHead = ShareUtils.getInstance().getCache(AppSet.FLAG_USERHEAD);
        return userHead;
    }


    public void setUserHead(String userHead) {
        this.userHead = userHead;
        ShareUtils.getInstance().setCache(AppSet.FLAG_USERHEAD, userHead);
    }
}
