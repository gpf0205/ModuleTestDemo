package txkj.xian.com.newzjdemo.app;

import android.app.Application;

import gpf.xian.com.commonlibrary.app.LibraryInit;

/**
 * Created by AnOnYm on 2019/10/8.
 */

public class MyApplication extends Application {

    // 基础库的初始化类的全路径名（包名+类名）
    private static final String COMMON_INIT_PATH = "gpf.xian.com.commonlibrary.app.LibraryInit";

    public static MyApplication instance;

    public static MyApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // commonlibrary模块的APP初始化
        commonLibraryInit(this);

        instance = this;
    }

    private void commonLibraryInit(Application application) {
        try {
            Class<?> clazz = Class.forName(COMMON_INIT_PATH);
            Object obj = clazz.newInstance();
            if (obj instanceof LibraryInit){
                ((LibraryInit) obj).init(application);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
