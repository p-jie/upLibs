package p_jie.library.http;

import android.content.Context;

import com.yolanda.nohttp.RequestMethod;

import p_jie.library.http.utils.JsonUtils;

/**
 * Created by jj on 2017/3/24.
 */

public class UploadRequest extends BaseRequest<UploadRequest> {
    public <T> UploadRequest(Context context, String url, T params, String root) {
        this.url = url;
        this.context = context;
        this.params = JsonUtils.string(params);
        this.root = root;
    }

    public <T> void execute(Class<T> classOfT, RequestJsonListener<T> l) {
        RequestManager.loadArray(context, RequestMethod.POST, params, url, classOfT, isLoading, loadingTitle, timeOut, retry, l);
    }

    public void executeList(RequestListener l) {
        RequestManager.loadUploadString(context, params, url, uploadFiles, isLoading, loadingTitle, root, l);
    }

    public void executeString(RequestStringListener l) {
        RequestManager.loadUploadString(context, params, url, uploadFiles, isLoading, loadingTitle, l);
    }
}
