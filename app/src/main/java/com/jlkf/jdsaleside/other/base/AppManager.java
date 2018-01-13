package com.jlkf.jdsaleside.other.base;


import android.app.Activity;

import com.jlkf.jdsaleside.MainActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Activity管理类，可用于一键关闭应用
 * 不过每个Activity应继承BaseActivity
 *
 * @author jack.wuxiazhi
 */
public class AppManager {
    public static List<Activity> activitys = new ArrayList<Activity>();

    public static void activityCreated(Activity activity) {
        activitys.add(activity);
    }

    public static void activityDestroyed(Activity activity) {
        activitys.remove(activity);
    }

    public static void quitApp() {
        if (activitys.size() != 0) {
            Iterator<Activity> aIterator = activitys.iterator();
            while (aIterator.hasNext()) {
                Activity activity = aIterator.next();
                if (activity != null && !activity.isFinishing()) {
                    activity.finish();
                }
            }
        }
        activitys.clear();
    }

    public static void quit() {
        if (activitys.size() != 0) {
            Iterator<Activity> aIterator = activitys.iterator();
            while (aIterator.hasNext()) {
                Activity activity = aIterator.next();
                if (activity != null && !activity.isFinishing()) {
                    if (activity instanceof MainActivity) {
                    } else {
                        activity.finish();
                    }

                }
            }
        }
    }
}
