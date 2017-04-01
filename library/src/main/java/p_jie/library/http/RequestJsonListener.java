package p_jie.library.http;


import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by jj on 2017/3/24.
 */

public abstract class RequestJsonListener<T> extends BaseRequestListener {
    /**
     * 成功
     */
    public abstract void onSuccess(Response<T> response);

}
