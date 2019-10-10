package txkj.xian.com.newzjdemo.mvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import gpf.xian.com.commonlibrary.base.BaseMvpActivity;
import gpf.xian.com.commonlibrary.utils.Glide4Util;
import gpf.xian.com.commonlibrary.utils.ToastUtil;
import txkj.xian.com.newzjdemo.R;
import txkj.xian.com.newzjdemo.bean.BannerBean;
import txkj.xian.com.newzjdemo.mvp.presenter.MainPresenter;
import txkj.xian.com.newzjdemo.mvp.view.MainView;

public class MainActivity extends BaseMvpActivity<MainView, MainPresenter> implements MainView {

    @BindView(R.id.data)
    TextView data;
    @BindView(R.id.iv_default)
    ImageView iv_default;

    private String url = "http://img5.imgtn.bdimg.com/it/u=4096944214,1677787421&fm=26&gp=0.jpg";
    private String gifUrl = "https://upload-images.jianshu.io/upload_images/8669504-930fc652956b3f0e.gif";


    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initState(Bundle savedInstanceState) {
        if(savedInstanceState == null){
            ToastUtil.ToastShow("111111111111111");
        }else{
            ToastUtil.ToastShow("2222222222222222");
        }
    }

    @Override
    protected void initView() {

        Glide4Util.loadImage(this,url,iv_default,R.mipmap.ic_launcher,R.mipmap.ic_launcher);

    }

    // 获取presenter实例
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    // View接口中定义的方法
    @Override
    public void showProgressDialog() {
        showLoadingDialog("数据加载中..");
    }

    @Override
    public void hideProgressDialog() {
        hideLoadingDialog();
    }

    @Override
    public void onSuccess(List<BannerBean> list) {
        data.setText(list.get(1).getTitle());
    }

    @Override
    public void onError(Throwable exception) {
        // 数据获取失败
        Toast.makeText(this, "数据获取失败 ", Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.button,R.id.button2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.button:
                // 通过presenter调用网络请求的方法
                getPresenter().getBannerData();
                break;
            case R.id.button2:
                // 执行跳转
                jumpActivity(Main2Activity.class,null);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void doEvent(String a){

        ToastUtil.ToastShow("收到了通知");
        data.setText("响应了通知！！！！！！！");

    }

}
