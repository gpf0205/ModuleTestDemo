package txkj.xian.com.newzjdemo.mvp.presenter;

import java.util.List;

import gpf.xian.com.commonlibrary.base.BasePresenter;
import gpf.xian.com.commonlibrary.bean.ResponseData;
import gpf.xian.com.commonlibrary.net.BaseObserver;
import gpf.xian.com.commonlibrary.net.BaseRx;
import io.reactivex.disposables.Disposable;
import txkj.xian.com.newzjdemo.api.ApiService;
import txkj.xian.com.newzjdemo.bean.BannerBean;
import txkj.xian.com.newzjdemo.mvp.view.MainView;

/**
 * Created by AnOnYm on 2019/9/10.
 */

public class MainPresenter extends BasePresenter<MainView> {

    // 一般来说Presenter中应该持有M层和V层的引用
    // M层引用 --- 通过getMvpView()获取引用

    // 获取顶部播图数据
    public void getBannerData() {
        ApiService.getBannerData()
                .compose(BaseRx.<ResponseData<List<BannerBean>>>io4main()) // IO线程中发起请求，在主线程中获取结果
                .subscribe(new BaseObserver<ResponseData<List<BannerBean>>>() {

                    @Override
                    public void _onSubscribe(Disposable disposable) {
                        //请求加入管理,统一管理订阅,防止内存泄露
                        addDisposable(disposable);
                        // 显示进度提示
                        getMvpView().showProgressDialog();
                    }

                    @Override
                    public void _onSuccess(ResponseData<List<BannerBean>> listResponseData) {
                        getMvpView().onSuccess(listResponseData.getData());
                    }

                    @Override
                    public void _onError(Throwable exception) {
                        getMvpView().onError(exception);
                    }

                    @Override
                    public void _onComplete() {
                        // 隐藏进度
                        getMvpView().hideProgressDialog();
                    }
                });
    }
}
