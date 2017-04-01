package p_jie.library.http;

import android.widget.Toast;


import p_jie.library.base.BaseApplication;

/**
 * Created by jj on 2017/3/24.
 */

public abstract class BaseRequestListener extends OnUploadListener {

    /**
     * 错误
     */
    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {


        Toast.makeText(BaseApplication.getInstance(), "网络不给力···", Toast.LENGTH_SHORT).show();

    }
}