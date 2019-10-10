package gpf.xian.com.commonlibrary.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import gpf.xian.com.commonlibrary.base.BaseActivity;
import gpf.xian.com.commonlibrary.utils.NetWorkUtil;


/**
 * Created by AnOnYm on 2019/8/5.
 */

public class NetStateReceiver extends BroadcastReceiver {

    private INetEvent mINetEvent = BaseActivity.mINetEvent;

    @Override
    public void onReceive(Context context, Intent intent) {
        // 接收到网络变化广播,说明网络状态发生了变化
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //容错机制
            if(mINetEvent!=null) {
                // 接口回调传过去状态的类型
                mINetEvent.onNetChange(NetWorkUtil.getNetWorkState(context));
            }
        }
    }
}
