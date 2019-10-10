package txkj.xian.com.newzjdemo.api;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okrx2.adapter.ObservableBody;

import java.util.List;

import gpf.xian.com.commonlibrary.bean.ResponseData;
import gpf.xian.com.commonlibrary.net.JsonConvert;
import io.reactivex.Observable;
import txkj.xian.com.newzjdemo.bean.BannerBean;

/**
 * Created by AnOnYm on 2019/10/8.
 */

public class ApiService {

    /**
     * 首页Banner
     *
     * @GET("/banner/json")
     */
    public static Observable<ResponseData<List<BannerBean>>> getBannerData() {
        return OkGo.<ResponseData<List<BannerBean>>>get(Api.HOME_BANNER)
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                .converter(new JsonConvert<ResponseData<List<BannerBean>>>() {
                })
                .adapt(new ObservableBody<ResponseData<List<BannerBean>>>());
    }

}
