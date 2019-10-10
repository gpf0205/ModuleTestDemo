package gpf.xian.com.commonlibrary.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import gpf.xian.com.commonlibrary.utils.EventBusUtil;

/**
 * Created by 25367 on 2018/12/24.
 */

public abstract class BaseFragment extends Fragment {

    private View mRootView;
    protected Activity mActivity;
    private Unbinder bind;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(),container,false);
        bind = ButterKnife.bind(this, mRootView);
        // 注册eventbus
        EventBusUtil.register(this);
        initPresenter();
        initView();
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        initData();
        setListener();
        super.onActivityCreated(savedInstanceState);
    }

    // 实例化presenter
    protected void initPresenter(){}
    protected void initView(){}
    protected void initData(){}
    protected void setListener(){}
    protected abstract int getContentViewId();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind(); // 解绑butterknife
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 取消注册
        EventBusUtil.unregister(this);
    }

    /**
     * 默认绑定一个事件，防止源码里面去找方法的时候找不到报错。
     * @param fragment
     */
    @Subscribe
    public void onEvent(BaseFragment fragment){

    }

}
