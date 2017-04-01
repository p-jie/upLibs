package p_jie.library.http;

import android.content.Context;

import com.yanzhenjie.nohttp.RequestMethod;


/**
 * Created by jj on 2017/3/24.
 */

public class GetRequest extends BaseRequest<GetRequest> {


    public GetRequest(Context context, String url, String root) {
        this.url = url;
        this.context = context;
        this.root = root;
    }

    public <T> void execute(Class<T> classOfT, RequestJsonListener<T> l) {
        try {
            RequestManager.loadArray(context, RequestMethod.GET, null, url, classOfT, isLoading, loadingTitle, timeOut, retry, l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeList(RequestListener l) {
        try {
            RequestManager.loadList(context, RequestMethod.GET, null, url, isLoading, loadingTitle, timeOut, retry, root, l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void executeMap(RequestMapListener l) {
        try {
            RequestManager.loadMap(context, RequestMethod.GET, null, url, isLoading, loadingTitle, timeOut, retry, l);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
