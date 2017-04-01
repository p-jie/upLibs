package p_jie.library.http;

import java.util.List;
import java.util.Map;

/**
 * Created by jj on 2017/3/24.
 */

public abstract class RequestListener extends BaseRequestListener {

//    /**
//     * 成功
//     */
//    public abstract void onSuccess(Response<String> response);

    /**
     * 成功
     */
    public abstract void onSuccess(List<Map<String,Object>> response);
}
