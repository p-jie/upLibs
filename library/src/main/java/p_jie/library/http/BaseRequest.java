package p_jie.library.http;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import p_jie.library.http.model.UploadFile;
import p_jie.library.http.utils.FileUtils;

/**
 * Created by jj on 2017/3/23.
 */

public abstract class BaseRequest<T extends BaseRequest> {
    /**
     * 地址
     */
    protected String url;

    /**
     * 解析头
     */
    protected String root;

    /**
     * 上下文
     */
    protected Context context;
    /**
     * 是否显示loading
     */
    protected boolean isLoading = false;
    /**
     * loading提示文字
     */
    protected String loadingTitle;
    /**
     * post请求参数
     * 目前这里写的body
     * 如果传的是map或者对象其他
     * 把这个参数改下就行，哪里需要了这个参数，同时也改掉
     */
    protected String params;
    /**
     * 超时时间
     */
    protected int timeOut = 30000;
    /**
     * 重试次数
     */
    protected int retry = 0;
    /**
     * 请求标示
     */
    protected int what = 0;
    /**
     * 上传文件对象
     */
    protected List<UploadFile> uploadFiles = new ArrayList<>();
    /**
     * 保存的文件夹
     **/
    protected String fileFolder = FileUtils.getCachePath();
    /**
     * 文件名 不传 默认是时间戳
     **/
    protected String fileName = String.valueOf(System.currentTimeMillis());
    /**
     * 是否断点续传下载 默认是
     **/
    protected boolean isRange = true;
    /**
     * 如果发现文件已经存在是否删除后重新下载
     **/
    protected boolean isDeleteOld = false;

    public T loading(boolean loading) {
        this.isLoading = loading;
        return (T) this;
    }

    public T loadingTitle(String title) {
        this.loadingTitle = title;
        return (T) this;
    }

    public T timeOut(int timeOut) {
        this.timeOut = timeOut;
        return (T) this;
    }

    public T retry(int retry) {
        this.retry = retry;
        return (T) this;
    }

    public T what(int what) {
        this.what = what;
        return (T) this;
    }

    public T fileFolder(String fileFolder) {
        this.fileFolder = fileFolder;
        return (T) this;
    }

    public T fileName(String fileName) {
        this.fileName = fileName;
        return (T) this;
    }

    public T isRange(boolean isRange) {
        this.isRange = isRange;
        return (T) this;
    }

    public T isDeleteOld(boolean isDeleteOld) {
        this.isDeleteOld = isRange;
        return (T) this;
    }

    public T uploadFiles(List<UploadFile> uploadFiles) {
        this.uploadFiles = uploadFiles;
        return (T) this;
    }
}
