package txkj.xian.com.newzjdemo.mvp.view;

import java.util.List;

import gpf.xian.com.commonlibrary.base.BaseView;
import txkj.xian.com.newzjdemo.bean.BannerBean;


/**
 * Created by AnOnYm on 2019/9/10.
 */

public interface MainView extends BaseView {

    void onSuccess(List<BannerBean> list);

}
