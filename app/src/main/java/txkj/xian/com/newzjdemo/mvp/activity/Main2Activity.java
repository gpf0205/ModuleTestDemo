package txkj.xian.com.newzjdemo.mvp.activity;

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import gpf.xian.com.commonlibrary.base.BaseActivity;
import gpf.xian.com.commonlibrary.utils.EventBusUtil;
import txkj.xian.com.newzjdemo.R;

public class Main2Activity extends BaseActivity {

    @BindView(R.id.sends)
    Button btn;

    @Override
    protected int setLayout() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.sends,R.id.back})
    public void send(View view){
        switch (view.getId()){
            case R.id.sends:
                EventBusUtil.post("22");
                break;
            case R.id.back:
                finish();
                break;
        }
    }


}
