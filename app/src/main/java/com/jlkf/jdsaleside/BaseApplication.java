package com.jlkf.jdsaleside;

import android.app.Application;
import android.util.Log;

import com.jlkf.jdsaleside.other.base.FakeCrashLibrary;
import com.jlkf.jdsaleside.other.base.util.ShareUtils;

import timber.log.Timber;

/**
 * Created by DuoNuo on 2017/10/26.
 */

public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        ShareUtils.getInstance().init(this);
        initTimber();

    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }
    }


    /**
     * A tree which logs important information for crash reporting.
     */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }
            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}
