package gpf.xian.com.commonlibrary.utils;

import android.os.Parcelable;

import com.tencent.mmkv.MMKV;

import java.util.Collections;
import java.util.Set;

/**
 * MMKV工具类
 */

public class SPUtil {

    private static SPUtil mInstance;
    private static MMKV mv;

    private SPUtil() {
        mv = MMKV.defaultMMKV();
    }

    /**
     * 初始化MMKV,只需要初始化一次，建议在Application中初始化
     *
     */
    public static SPUtil getInstance() {
        if (mInstance == null) {
            synchronized (SPUtil.class) {
                if (mInstance == null) {
                    mInstance = new SPUtil();
                }
            }
        }
        return mInstance;
    }

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static boolean encode(String key, Object object) {
        boolean result;
        if (object instanceof String) {
            result = mv.encode(key, (String) object);
        } else if (object instanceof Integer) {
            result = mv.encode(key, (Integer) object);
        } else if (object instanceof Boolean) {
            result = mv.encode(key, (Boolean) object);
        } else if (object instanceof Float) {
            result = mv.encode(key, (Float) object);
        } else if (object instanceof Long) {
            result = mv.encode(key, (Long) object);
        } else if (object instanceof Double) {
            result = mv.encode(key, (Double) object);
        } else if (object instanceof byte[] ) {
            result = mv.encode(key, (byte[]) object);
        } else {
            result = mv.encode(key, object.toString());
        }
        return result;
    }

    public static boolean encodeSet(String key,Set<String> sets) {
        return mv.encode(key, sets);
    }

    public static boolean encodeParcelable(String key,Parcelable obj) {
        return mv.encode(key, obj);
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     */
    public static Integer decodeInt(String key) {
        return mv.decodeInt(key, 0);
    }
    public static Double decodeDouble(String key) {
        return mv.decodeDouble(key, 0.00);
    }
    public static Long decodeLong(String key) {
        return mv.decodeLong(key, 0L);
    }
    public static Boolean decodeBoolean(String key) {
        return mv.decodeBool(key, false);
    }
    public static Float decodeFloat(String key) {
        return mv.decodeFloat(key, 0F);
    }
    public static byte[] decodeBytes(String key) {
        return mv.decodeBytes(key);
    }
    public static String decodeString(String key) {
        return mv.decodeString(key,"");
    }
    public static Set<String> decodeStringSet(String key) {
        return mv.decodeStringSet(key, Collections.<String>emptySet());
    }
    public static Parcelable decodeParcelable(String key) {
        return mv.decodeParcelable(key, null);
    }
    /**
     * 移除某个key对
     *
     * @param key
     */
    public static void removeKey(String key) {
        mv.removeValueForKey(key);
    }
    /**
     * 清除所有key
     */
    public static void clearAll() {
        mv.clearAll();
    }
}
