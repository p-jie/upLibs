package p_jie.library.http;

import android.content.Context;

/**
 * Created by jj on 2017/3/24.
 */

public class IRequest {
    /**
     * post请求
     */
    public static <T> PostRequest post(Context context, String url, T params, String root) {
        return new <T>PostRequest(context, url, params, root);
    }

    /**
     * get请求
     */
    public static GetRequest get(Context context, String url, String root) {
        return new GetRequest(context, url, root);
    }

    /**
     * 下载请求
     */
    public static DownloadRequest download(Context context, String url) {
        return new DownloadRequest(context, url);
    }

    /**
     * 上传请求
     */
    public static <T> UploadRequest upload(Context context, String url, T params, String root) {
        return new <T>UploadRequest(context, url, params, root);
    }
}
