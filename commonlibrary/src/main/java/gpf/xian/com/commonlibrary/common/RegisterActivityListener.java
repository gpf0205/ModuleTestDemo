package gpf.xian.com.commonlibrary.common;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import gpf.xian.com.commonlibrary.utils.ActivityManagerUtil;

/**
 * 全局的所有Activity生命周期监听管理类
 */

public class RegisterActivityListener implements Application.ActivityLifecycleCallbacks {

    private final ActivityManagerUtil activityManager;

    public RegisterActivityListener() {
        activityManager = ActivityManagerUtil.getInstance();
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        activityManager.addActivity(activity);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        activityManager.removeActivity(activity);
    }
}
