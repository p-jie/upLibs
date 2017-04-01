package p_jie.library.http;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.yanzhenjie.nohttp.*;
import com.yanzhenjie.nohttp.OnUploadListener;
import com.yanzhenjie.nohttp.download.*;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;

import java.util.List;

import p_jie.library.http.model.UploadFile;
import p_jie.library.http.utils.JsonUtils;
import p_jie.library.http.view.LoadingDialog;

/**
 * Created by jj on 2017/3/24.
 */

public class RequestManager {

    private static RequestQueue mRequestQueue;
    private static DownloadQueue mDownloadQueue;

    /**
     * 数据请求的Queue
     *
     * @return
     */
    static RequestQueue getInstance() {
        if (mRequestQueue == null) {
            synchronized (RequestManager.class) {
                mRequestQueue = NoHttp.newRequestQueue(5);
            }
        }

        return mRequestQueue;
    }

    /**
     * 文件下载的Queue
     *
     * @return
     */
    static DownloadQueue getInstance1() {
        if (mDownloadQueue == null) {
            synchronized (RequestManager.class) {
                mDownloadQueue = NoHttp.newDownloadQueue(5);
            }
        }
        return mDownloadQueue;
    }

    private RequestManager() {

    }

    /**
     * 统一添加头部
     *
     * @param request
     * @param <T>
     */
    private static <T> void getHeader(Request<T> request) {
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json; charset=UTF-8");
        request.addHeader("PLATFORM", "android");
        request.addHeader("OS_NAME", "android");
        request.addHeader("OS_VERSION", "5.0.1");
        request.addHeader("APP_VERSION", "2.6.2");
        request.addHeader("MAC_ID", "CC3A61066EDC");
        request.addHeader("IMEI", "A0000044559EAA");
        request.addHeader("COOKIE", "PHPSESSID=uqgapn23iapj13v2bvuvsvalj7");
        request.addHeader("MEMBER_TOKEN", "d4016952efb53698d526b97e7518fb0e");
    }

    /**
     * 反回T
     *
     * @param context
     * @param method
     * @param params       参数
     * @param url          地址
     * @param tClass
     * @param isLoading    是否显示进度
     * @param loadingTitle 进度提示
     * @param timeOut      超时时间
     * @param retry        重试次数
     * @param l
     */
    public static <T> void loadArray(Context context, RequestMethod method, String params, String url, Class<T> tClass, final boolean isLoading, String loadingTitle, int timeOut, int retry, final RequestJsonListener<T> l) {
        final Request<T> request = new ArrayRequest(context, url, method, tClass);
        request.setConnectTimeout(timeOut);
        if (RequestMethod.POST == method) {
            request.setDefineRequestBodyForJson(params);
        }
        final LoadingDialog dialog = new LoadingDialog(context);
        if (!TextUtils.isEmpty(loadingTitle)) {
            dialog.setLoadText(loadingTitle);
        }
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                request.cancel();
            }
        });
        request.setConnectTimeout(timeOut);
        request.setRetryCount(retry);
        request.setTag(context);
        request.setCancelSign(context);
        request.setCacheMode(CacheMode.NONE_CACHE_REQUEST_NETWORK);
        /**
         * 如果有头部请求则调用getHeader
         * 把自己需要定义的参数都传过去即可
         */
        //getHeader(request);
        getInstance().add(0, request, new OnResponseListener<T>() {
            @Override
            public void onStart(int what) {
                if (dialog != null && !dialog.isShowing() && isLoading)
                    dialog.show();
            }

            @Override
            public void onSucceed(int what, Response<T> response) {
                if (l != null) {

                    try {
                        l.onSuccess(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<T> response) {
                if (l != null) {
                    try {
                        l.onFailed(what, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFinish(int what) {
                if (dialog != null && dialog.isShowing() && isLoading)
                    dialog.dismiss();
            }
        });
    }

    /**
     * 反回List
     *
     * @param context
     * @param method
     * @param params       参数
     * @param url          地址
     * @param isLoading    是否显示进度
     * @param loadingTitle 进度提示
     * @param timeOut      超时时间
     * @param root         解析的List字段
     * @param retry        重试次数
     * @param l
     */
    public static void loadList(Context context, RequestMethod method, String params, String url, final boolean isLoading, String loadingTitle, int timeOut, int retry, final String root, final RequestListener l) {
        final Request<String> request = NoHttp.createStringRequest(url, method);
        request.setConnectTimeout(timeOut);
        if (RequestMethod.POST == method) {
            request.setDefineRequestBodyForJson(params);
        }
        request.setCacheMode(CacheMode.NONE_CACHE_REQUEST_NETWORK);
        /**
         * 如果是https就打开
         */
//        SSLContext sslContext = SSLContextUtil.getSSLContext();
//        if (sslContext != null) {
//            request.setSSLSocketFactory(sslContext.getSocketFactory());
//        }
        /**
         * 如果有头部请求则调用getHeader
         * 把自己需要定义的参数都传过去即可
         */
        //getHeader(request);
        final LoadingDialog dialog = new LoadingDialog(context);
        if (!TextUtils.isEmpty(loadingTitle)) {
            dialog.setLoadText(loadingTitle);
        }
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                request.cancel();
            }
        });
        request.setConnectTimeout(timeOut);
        request.setRetryCount(retry);
        request.setTag(context);
        request.setCancelSign(context);
        getInstance().add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                if (dialog != null && !dialog.isShowing() && isLoading)
                    dialog.show();
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (l != null) {

                    try {
                        l.onSuccess(JsonUtils.listJson(response.get(), root));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                if (l != null) {
                    try {
                        l.onFailed(what, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFinish(int what) {
                if (dialog != null && dialog.isShowing() && isLoading)
                    dialog.dismiss();
            }
        });
    }

    /**
     * 反回Map
     *
     * @param context
     * @param method
     * @param params       参数
     * @param url          地址
     * @param isLoading    是否显示进度
     * @param loadingTitle 进度提示
     * @param timeOut      超时时间
     * @param retry        重试次数
     * @param l
     */

    public static void loadMap(final Context context, RequestMethod method, String params, String url, final boolean isLoading, String loadingTitle, int timeOut, int retry, final RequestMapListener l) {
        final Request<String> request = NoHttp.createStringRequest(url, method);
        request.setConnectTimeout(timeOut);
        if (RequestMethod.POST == method) {
            request.setDefineRequestBodyForJson(params);
        }
        request.setCacheMode(CacheMode.NONE_CACHE_REQUEST_NETWORK);
        /**
         * 如果是https就打开
         */
//        SSLContext sslContext = SSLContextUtil.getSSLContext();
//        if (sslContext != null) {
//            request.setSSLSocketFactory(sslContext.getSocketFactory());
//        }
        /**
         * 如果有头部请求则调用getHeader
         * 把自己需要定义的参数都传过去即可
         */
        //getHeader(request);
        final LoadingDialog dialog = new LoadingDialog(context);
        if (!TextUtils.isEmpty(loadingTitle)) {
            dialog.setLoadText(loadingTitle);
        }
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                request.cancel();
            }
        });
        request.setConnectTimeout(timeOut);
        request.setRetryCount(retry);
        request.setTag(context);
        request.setCancelSign(context);
        getInstance().add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                if (dialog != null && !dialog.isShowing() && isLoading)
                    dialog.show();
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (l != null) {

                    try {
                        l.onSuccess(JsonUtils.MapJson(response.get()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                if (l != null) {
                    try {
                        l.onFailed(what, response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFinish(int what) {
                if (dialog != null && dialog.isShowing() && isLoading)
                    dialog.dismiss();
            }
        });
    }

    /**
     * 下载文件
     *
     * @param context     地址
     * @param url         请求标记
     * @param what        文件路径
     * @param fileFolder  文件名字
     * @param filename    是否续传
     * @param isRange     存在是否删除
     * @param isDeleteOld 回调
     * @param l
     */
    public static void loadDownload(Context context, String url, int what, String fileFolder, String filename, boolean isRange, boolean isDeleteOld, final DownloadListener l) {

        final DownloadRequest request = NoHttp.createDownloadRequest(url, fileFolder, filename, isRange, isDeleteOld);
        request.setTag(context);
        getInstance1().add(what, request, new DownloadListener() {

            @Override
            public void onDownloadError(int what, Exception exception) {
                if (l != null) {
                    l.onDownloadError(what, exception);
                }
            }

            @Override
            public void onStart(int what, boolean isResume, long rangeSize, Headers responseHeaders, long allCount) {

                if (l != null) {
                    l.onStart(what, isResume, rangeSize, responseHeaders, allCount);
                }

            }

            @Override
            public void onProgress(int what, int progress, long fileCount, long speed) {
                if (l != null) {
                    l.onProgress(what, progress, fileCount, speed);
                }
            }

            @Override
            public void onFinish(int what, String filePath) {
                if (l != null) {
                    l.onFinish(what, filePath);
                }
            }

            @Override
            public void onCancel(int what) {
                if (l != null) {
                    l.onCancel(what);
                }
            }
        });
    }

    /**
     * 反回List
     *
     * @param context
     * @param params       参数
     * @param url          地址
     * @param files        上传对象
     * @param isLoading    是否显示显示进度
     * @param loadingTitle 进度文字提示
     * @param l
     */
    public static void loadUploadString(Context context, String params, String url, List<UploadFile> files, final boolean isLoading, String loadingTitle, final String root, final RequestListener l) {
        final Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);


        if (!TextUtils.isEmpty(params)) {
            request.setDefineRequestBodyForJson(params);
        }
        /**
         * 给上传文件做个监听，可以不需要
         */
        for (UploadFile file : files) {

            /**
             * 需要注意
             * FileBinary这里支持多种上传方式
             * 如：FileBinary
             *    BitmapBinary
             *    InputStreamBinary
             *    这里只处理一种 因为一个项目应该不会出现2种上传类型的方式
             *    如果自己的项目用的BitmapBinary 只需要把 UploadFile里面的类型修改就行了
             *
             * BasicBinary binary3 = new InputStreamBinary(new FileInputStream(file3), file3.getName());
             * BasicBinary binary2 = new BitmapBinary(file2, "userHead.jpg");// 或者：BasicBinary binary2 = new BitmapBinary(file2, null);
             *
             */
            FileBinary fileBinary = new FileBinary(file.getFile());
            request.add(file.getKey(), file.getFile());
            fileBinary.setUploadListener(file.getWhat(), new OnUploadListener() {
                @Override
                public void onStart(int what) {
                    if (l != null) {
                        l.onStart(what);
                    }
                }

                @Override
                public void onCancel(int what) {
                    if (l != null) {
                        l.onCancel(what);
                    }
                }

                @Override
                public void onProgress(int what, int progress) {
                    if (l != null) {
                        l.onProgress(what, progress);
                    }
                }

                @Override
                public void onFinish(int what) {
                    if (l != null) {
                        l.onFinish(what);
                    }
                }

                @Override
                public void onError(int what, Exception exception) {
                    if (l != null) {
                        l.onError(what, exception);
                    }
                }
            });
            /**
             * 这个key可以不传
             * 目前没发现有什么不一样的
             */
            request.add("", fileBinary);
        }
        /**
         * 如果有头部请求则调用getHeader
         * 把自己需要定义的参数都传过去即可
         */
        //getHeader(request);
        request.setTag(context);
        final LoadingDialog dialog = new LoadingDialog(context);
        if (!TextUtils.isEmpty(loadingTitle)) {
            dialog.setLoadText(loadingTitle);
        }
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                request.cancel();
            }
        });
        getInstance().add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                if (dialog != null && !dialog.isShowing() && isLoading)
                    dialog.show();
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (l != null) {
                    l.onSuccess(JsonUtils.listJson(response.get(), root));
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                if (l != null) {
                    l.onFailed(what, response);
                }
            }

            @Override
            public void onFinish(int what) {
                if (dialog != null && dialog.isShowing() && isLoading)
                    dialog.dismiss();
            }
        });
    }


    /**
     * 反回List
     *
     * @param context
     * @param params       参数
     * @param url          地址
     * @param files        上传对象
     * @param isLoading    是否显示显示进度
     * @param loadingTitle 进度文字提示
     * @param l
     */
    public static void loadUploadString(Context context, String params, String url, List<UploadFile> files, final boolean isLoading, String loadingTitle, final RequestStringListener l) {
        final Request<String> request = NoHttp.createStringRequest(url, RequestMethod.POST);

        if (!TextUtils.isEmpty(params)) {
            request.setDefineRequestBodyForJson(params);
        }
        /**
         * 给上传文件做个监听，可以不需要
         */
        for (UploadFile file : files) {

            /**
             * 需要注意
             * FileBinary这里支持多种上传方式
             * 如：FileBinary
             *    BitmapBinary
             *    InputStreamBinary
             *    这里只处理一种 因为一个项目应该不会出现2种上传类型的方式
             *    如果自己的项目用的BitmapBinary 只需要把 UploadFile里面的类型修改就行了
             *
             * BasicBinary binary3 = new InputStreamBinary(new FileInputStream(file3), file3.getName());
             * BasicBinary binary2 = new BitmapBinary(file2, "userHead.jpg");// 或者：BasicBinary binary2 = new BitmapBinary(file2, null);
             *
             */
            FileBinary fileBinary = new FileBinary(file.getFile());
            request.add(file.getKey(), file.getFile());
            fileBinary.setUploadListener(file.getWhat(), new OnUploadListener() {
                @Override
                public void onStart(int what) {
                    if (l != null) {
                        l.onStart(what);
                    }
                }

                @Override
                public void onCancel(int what) {
                    if (l != null) {
                        l.onCancel(what);
                    }
                }

                @Override
                public void onProgress(int what, int progress) {
                    if (l != null) {
                        l.onProgress(what, progress);
                    }
                }

                @Override
                public void onFinish(int what) {
                    if (l != null) {
                        l.onFinish(what);
                    }
                }

                @Override
                public void onError(int what, Exception exception) {
                    if (l != null) {
                        l.onError(what, exception);
                    }
                }
            });
            /**
             * 这个key可以不传
             * 目前没发现有什么不一样的
             */
            request.add("", fileBinary);
        }
        /**
         * 如果有头部请求则调用getHeader
         * 把自己需要定义的参数都传过去即可
         */
        //getHeader(request);
        request.setTag(context);
        final LoadingDialog dialog = new LoadingDialog(context);
        if (!TextUtils.isEmpty(loadingTitle)) {
            dialog.setLoadText(loadingTitle);
        }
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                request.cancel();
            }
        });
        getInstance().add(0, request, new OnResponseListener<String>() {
            @Override
            public void onStart(int what) {
                if (dialog != null && !dialog.isShowing() && isLoading)
                    dialog.show();
            }

            @Override
            public void onSucceed(int what, Response<String> response) {
                if (l != null) {
                    l.onSuccess(response);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                if (l != null) {
                    l.onFailed(what, response);
                }
            }

            @Override
            public void onFinish(int what) {
                if (dialog != null && dialog.isShowing() && isLoading)
                    dialog.dismiss();
            }
        });
    }


    /**
     * 反回对象
     *
     * @param context
     * @param params       参数
     * @param url          地址
     * @param files        上传对象
     * @param tClass
     * @param isLoading    是否显示显示进度
     * @param loadingTitle 进度文字提示
     * @param l
     */
    public static <T> void loadUploaArray(Context context, String params, String url, List<UploadFile> files, Class<T> tClass, final boolean isLoading, String loadingTitle, final RequestJsonListener<T> l) {
        final Request<T> request = new ArrayRequest(context, url, RequestMethod.POST, tClass);

        if (!TextUtils.isEmpty(params)) {
            request.setDefineRequestBodyForJson(params);
        }
        /**
         * 给上传文件做个监听，可以不需要
         */
        for (UploadFile file : files) {

            /**
             * 需要注意
             * FileBinary这里支持多种上传方式
             * 如：FileBinary
             *    BitmapBinary
             *    InputStreamBinary
             *    这里只处理一种 因为一个项目应该不会出现2种上传类型的方式
             *    如果自己的项目用的BitmapBinary 只需要把 UploadFile里面的类型修改就行了
             *
             * BasicBinary binary3 = new InputStreamBinary(new FileInputStream(file3), file3.getName());
             * BasicBinary binary2 = new BitmapBinary(file2, "userHead.jpg");// 或者：BasicBinary binary2 = new BitmapBinary(file2, null);
             *
             */

            FileBinary fileBinary = new FileBinary(file.getFile());
            request.add(file.getKey(), file.getFile());
            fileBinary.setUploadListener(file.getWhat(), new OnUploadListener() {
                @Override
                public void onStart(int what) {
                    if (l != null) {
                        l.onStart(what);
                    }
                }

                @Override
                public void onCancel(int what) {
                    if (l != null) {
                        l.onCancel(what);
                    }
                }

                @Override
                public void onProgress(int what, int progress) {
                    if (l != null) {
                        l.onProgress(what, progress);
                    }
                }

                @Override
                public void onFinish(int what) {
                    if (l != null) {
                        l.onFinish(what);
                    }
                }

                @Override
                public void onError(int what, Exception exception) {
                    if (l != null) {
                        l.onError(what, exception);
                    }
                }
            });
            /**
             * 这个key可以不传
             * 目前没发现有什么不一样的
             */
            request.add("", fileBinary);
        }
        /**
         * 如果有头部请求则调用getHeader
         * 把自己需要定义的参数都传过去即可
         */
        //  getHeader(request);
        request.setTag(context);
        final LoadingDialog dialog = new LoadingDialog(context);
        if (!TextUtils.isEmpty(loadingTitle)) {
            dialog.setLoadText(loadingTitle);
        }
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                request.cancel();
            }
        });

        getInstance().add(0, request, new OnResponseListener<T>() {
            @Override
            public void onStart(int what) {
                if (dialog != null && !dialog.isShowing() && isLoading)
                    dialog.show();
            }

            @Override
            public void onSucceed(int what, Response<T> response) {
                if (l != null) {

                    l.onSuccess(response);
                }
            }

            @Override
            public void onFailed(int what, Response<T> response) {
                if (l != null) {
                    l.onFailed(what, response);
                }
            }

            @Override
            public void onFinish(int what) {
                if (dialog != null && dialog.isShowing() && isLoading)
                    dialog.dismiss();
            }
        });
    }
}

