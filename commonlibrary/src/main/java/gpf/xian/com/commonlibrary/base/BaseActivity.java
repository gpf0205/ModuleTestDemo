package gpf.xian.com.commonlibrary.base;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import gpf.xian.com.commonlibrary.common.INetEvent;
import gpf.xian.com.commonlibrary.common.NetStateReceiver;
import gpf.xian.com.commonlibrary.dialog.LoadingDialog;
import gpf.xian.com.commonlibrary.utils.EventBusUtil;
import gpf.xian.com.commonlibrary.utils.NetWorkUtil;


public abstract class BaseActivity extends AppCompatActivity implements INetEvent {

    public static INetEvent mINetEvent;
    // 网络状态变化的广播接收器
    private NetStateReceiver mNetStateReceiver;
    // 当前网路连接状态，网络状态发生变化，该值会动态更新
    // -1：没有网络    0：移动网络     1：wifi网络
    protected int netWorkState;


    // 默认显示状态栏，一般我们都会设置application的Theme为noActionBar，所以此处基类里去掉了针对标题栏显示的开关设置
    private boolean isShowState = true;

    protected LoadingDialog dialog; // 子类共享的进度提示dialog

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setShowStateAndOther();
        if (!isShowState) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        //设置BaseActivity的布局
        setContentView(setLayout());

        //初始化网络状态的监听
        mINetEvent = this;
        // 初始化时检查网络连接
        this.netWorkState = NetWorkUtil.getNetWorkState(BaseActivity.this);
        // 动态注册网络状态监听广播
        mNetStateReceiver = new NetStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetStateReceiver, filter);

        //绑定ButterKnife
        ButterKnife.bind(this);

        initPresenter();

        // 注册,
        EventBusUtil.register(this);
        // 设置所有activity的状态栏颜色，推荐2个常用的第三方库：StatusBarUtil
        // https://github.com/laobie/StatusBarUtil
        // https://github.com/gyf-dev/ImmersionBar
//        StatusBarUtil.setStatusBarColor(this, getResources().getColor(R.color.colorAccent));
        // 初始化页面
        initView();
        // 子类中需要获取Activity异常销毁时的值的时候使用
        initState(savedInstanceState);
        // 设置数据
        initData();
        // 设置监听
        setListener();
    }


    // 子类中去设置要不要显示状态栏和其他功能的开关，子类调用该方法然后重写一个或者多个设置开关的方法
    protected void setShowStateAndOther() {

    }

    protected void setListener() {

    }

    protected void initData() {

    }

    // 当页面因为异常销毁需要保存数据时调用该方法
    protected void initState(Bundle savedInstanceState) {

    }

    protected abstract int setLayout();

    protected void initView() {
    }

    // 实例化presenter
    protected void initPresenter() {
    }

    /**
     * 设置是否显示状态栏
     *
     * @param ishow
     */
    protected void setState(boolean ishow) {
        isShowState = ishow;
    }

    /**
     * 页面跳转，依赖bundle传递参数，不需要传参时，参数2设置为null即可
     *
     * @param clz
     * @param bundle
     */
    protected void jumpActivity(Class<?> clz, @Nullable Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 获得屏幕的宽度
     */
    protected int getScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenWidth = dm.widthPixels;
        return screenWidth;
    }

    /**
     * 获得屏幕的高度
     */
    protected int getScreenHeigh() {
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenHeigh = dm.heightPixels;
        return screenHeigh;
    }

    /**
     * 带请求码的页面跳转，不需要传参时，参数3设置为null即可
     *
     * @param targetClass
     * @param requestCode
     * @param bundle
     */
    protected void startActivityForResult(Class<?> targetClass, int requestCode, @Nullable Bundle bundle) {
        Intent intent = new Intent(this, targetClass);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册
        unregisterReceiver(mNetStateReceiver);
        // 避免内存泄漏，置空
        if (mINetEvent != null) {
            mINetEvent = null;
        }
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        // 取消注册
        EventBusUtil.unregister(this);
    }

    /**
     * 显示进度弹框提示
     */
    protected void showLoadingDialog(String info) {
        if (dialog == null) {
            dialog = new LoadingDialog(this, info);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
        } else {
            dialog.setTitle(info);
        }
        dialog.show();
    }

    /**
     * 隐藏进度弹框提示
     */
    protected void hideLoadingDialog() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    /**
     * 全局检测网络广播的回调 处理网络变化,子类Activity选择性实现
     *
     * @param netWorkState 网络状态    -1:没网络  0:移动网络  1:WiFi网络
     */
    public void onNetChanged(int netWorkState) {
    }

    @Override
    public void onNetChange(int netWorkState) {
        this.netWorkState = netWorkState;
        onNetChanged(netWorkState);
    }

    /**
     * 重写getResources()方法，让APP的字体不受系统设置字体大小影响
     */
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    /**
     * EventBus默认绑定一个事件，防止源码里面去找方法的时候找不到报错。
     *
     * @param
     */
    @Subscribe
    public void onEvent(BaseActivity activity) { }

}
