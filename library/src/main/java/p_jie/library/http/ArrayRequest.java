package p_jie.library.http;

import android.content.Context;

import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.RestRequest;
import com.yolanda.nohttp.rest.StringRequest;

import p_jie.library.http.utils.JsonUtils;

/**
 * Created by jj on 2017/3/24.
 */

public class ArrayRequest<T> extends RestRequest<T> {
    private Class<T> classOfT;
    private Context context;

    public ArrayRequest(String url) {
        super(url);
    }

    public ArrayRequest(Context context, String url, RequestMethod requestMethod, Class<T> classOfT) {
        super(url, requestMethod);
        this.classOfT = classOfT;
        this.context = context;
    }

//    @Override
//    public T parseResponse(String url, Headers responseHeaders, byte[] responseBody) {
//        String result = StringRequest.parseResponseString(url, responseHeaders, responseBody);
//        return JsonUtils.object(result, classOfT);
//    }

    @Override
    public T parseResponse(Headers responseHeaders, byte[] responseBody) throws Throwable {
        String result = StringRequest.parseResponseString(responseHeaders, responseBody);
        return JsonUtils.object(result, classOfT);
    }

    @Override
    public String getAccept() {
        return "application/json";
    }
}
