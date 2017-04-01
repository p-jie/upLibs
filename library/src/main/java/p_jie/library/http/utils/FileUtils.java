package p_jie.library.http.utils;

import android.os.Environment;

/**
 * Created by jj on 2017/3/23.
 */

public class FileUtils {

    /**
     * 判断是否有SD卡
     */
    public static boolean isExistSDCard() {
        return Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * 返回手机内存路径优先内存卡
     *
     * @return
     */
    public static String getCachePath() {
        if (isExistSDCard()) {
            return Environment.getExternalStorageDirectory() + "/p-jie/";
        } else {
            return "/data" + "/p-jie/";
        }
    }
}