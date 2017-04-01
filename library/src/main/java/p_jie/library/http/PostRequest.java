package p_jie.library.http;

import android.content.Context;


import com.yolanda.nohttp.RequestMethod;

import p_jie.library.http.utils.JsonUtils;

/**
 * Created by jj on 2017/3/24.
 */

public class PostRequest extends BaseRequest<PostRequest> {


    public <T> PostRequest(Context context, String url, T params, String root) {
        this.url = url;
        this.context = context;
        this.params = JsonUtils.string(params);
        this.root = root;
    }

    public <T> void execute(Class<T> classOfT, RequestJsonListener<T> l) {
        RequestManager.loadArray(context, RequestMethod.POST, params, url, classOfT, isLoading, loadingTitle, timeOut, retry, l);
    }

    public void executeList(RequestListener l) {
        RequestManager.loadList(context, RequestMethod.POST, params, url, isLoading, loadingTitle, timeOut, retry, root, l);
    }

    public void executeMap(RequestMapListener l) {
        RequestManager.loadMap(context, RequestMethod.POST, params, url, isLoading, loadingTitle, timeOut, retry, l);
    }
}
