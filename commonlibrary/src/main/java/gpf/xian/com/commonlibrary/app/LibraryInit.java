package gpf.xian.com.commonlibrary.app;

import android.app.Application;
import android.os.Build;

import com.coder.zzq.smartshow.core.SmartShow;
import com.coder.zzq.smartshow.toast.SmartToast;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;
import com.tencent.mmkv.MMKV;

import java.util.logging.Level;

import gpf.xian.com.commonlibrary.BuildConfig;
import gpf.xian.com.commonlibrary.MyEventBusIndex;
import gpf.xian.com.commonlibrary.common.RegisterActivityListener;
import gpf.xian.com.commonlibrary.utils.EventBusUtil;
import okhttp3.OkHttpClient;

/**
 * commonLibrary的初始化，将来与主app模块的初始化合并（采用反射）
 */

public class LibraryInit {

    public static Application app;

    public static Application getApp() {
        return app;
    }

    public void init(Application application) {

        this.app = application;

        // 全局监听管理Activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            application.registerActivityLifecycleCallbacks(new RegisterActivityListener());
        }

        // 配置EventBus
        EventBusUtil.installIndex(new MyEventBusIndex());

        // Toast初始化
        ToastInit(application);
        // 用来替代SharePreference的高性能key-value组件,初始化
        MMKVInit(application);
        // OkGo网络框架的初始化
        OkGoInit(application);
        initLogger();
    }

    private void initLogger() {
        Logger.addLogAdapter(new AndroidLogAdapter(){
            @Override
            public boolean isLoggable(int priority, String tag) {
                return BuildConfig.LOG_DEBUG;
            }
        });
    }

    private void OkGoInit(Application application) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new SPCookieStore(application)));
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        //log颜色级别，决定了log在控制台显示的颜色
        loggingInterceptor.setColorLevel(Level.INFO);
        builder.addInterceptor(loggingInterceptor);
//        builder.addInterceptor(new TokenInterceptor());

        OkGo.getInstance()
                .init(application)
                .setOkHttpClient(builder.build()) //设置OkHttpClient，不设置将使用默认的
                .setCacheMode(CacheMode.NO_CACHE)
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)
                .setRetryCount(3);
    }

    private void MMKVInit(Application application) {
        MMKV.initialize(application);
    }

    private void ToastInit(Application application) {
        SmartShow.init(application);
        SmartToast.setting()
                .dismissOnLeave(true); // 离开当前Activity，Toast自动消失
    }

}
