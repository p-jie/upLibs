package p_jie.library.http;

import android.widget.Toast;

import com.yanzhenjie.nohttp.rest.Response;

import p_jie.library.base.BaseApplication;

/**
 * Created by jj on 2017/3/24.
 */

public abstract class BaseRequestListener<T> extends OnUploadListener {

    /**
     * 错误
     */
    public void onFailed(int what, Response<T> response) {


        Toast.makeText(BaseApplication.getInstance(), "网络不给力···", Toast.LENGTH_SHORT).show();

    }
}