package p_jie.library.http;


import com.yanzhenjie.nohttp.rest.Response;

/**
 * Created by jj on 2017/4/1.
 */

public abstract class RequestStringListener extends BaseRequestListener {

    /**
     * 成功
     */
    public abstract void onSuccess(Response<String> response);


}
