package p_jie.library.http;

import java.util.Map;

/**
 * Created by jj on 2017/3/24.
 */

public abstract class RequestMapListener extends BaseRequestListener {


    /**
     * 成功
     */
    public abstract void onSuccess(Map<String, Object> response);
}
