package gpf.xian.com.commonlibrary.utils;

import com.coder.zzq.smartshow.toast.SmartToast;

/**
 * Toast工具类
 */

public class ToastUtil {

    // 默认底部显示
    public static void ToastShow(CharSequence msg){
        SmartToast.show(msg);
    }
    public static void ToastShow(int msg){
        SmartToast.show(msg);
    }
    public static void ToastShowLong(CharSequence msg){
        SmartToast.show(msg);
    }
    public static void ToastShowLong(int msg){
        SmartToast.show(msg);
    }

    // 中间显示
    public static void showInCenter(CharSequence msg){
        SmartToast.showInCenter(msg);
    }
    public static void showInCenter(int msg){
        SmartToast.showInCenter(msg);
    }
    public static void showInCenterLong(CharSequence msg){
        SmartToast.showLongInCenter(msg);
    }
    public static void showInCenterLong(int msg){
        SmartToast.showLongInCenter(msg);
    }

    // 顶部显示
    public static void showAtTop(CharSequence msg){
        SmartToast.showAtTop(msg);
    }
    public static void showAtTop(int msg){
        SmartToast.showAtTop(msg);
    }
    public static void showAtTopLong(CharSequence msg){
        SmartToast.showLongAtTop(msg);
    }
    public static void showAtTopLong(int msg){
        SmartToast.showLongAtTop(msg);
    }

    //如，在左上角，x，y偏移量为10dp的位置显示
//    SmartToast.showAtLocation(msg,Gravity.LEFT | Gravity.TOP,10,10);

    // 自定义显示位置
    public static void showAtLocation(CharSequence msg, int gravity, float xOffsetDp, float yOffsetDp){
        SmartToast.showAtLocation(msg,gravity,xOffsetDp,yOffsetDp);
    }
    public static void showAtLocation(int msg, int gravity, float xOffsetDp, float yOffsetDp){
        SmartToast.showAtLocation(msg,gravity,xOffsetDp,yOffsetDp);
    }
    public static void showLongAtLocation(CharSequence msg, int gravity, float xOffsetDp, float yOffsetDp){
        SmartToast.showLongAtLocation(msg,gravity,xOffsetDp,yOffsetDp);
    }
    public static void showLongAtLocation(int msg, int gravity, float xOffsetDp, float yOffsetDp){
        SmartToast.showLongAtLocation(msg,gravity,xOffsetDp,yOffsetDp);
    }

}
