package p_jie.library.http;

import android.content.Context;

import com.yanzhenjie.nohttp.download.DownloadListener;


/**
 * Created by jj on 2017/3/24.
 */

public class DownloadRequested extends BaseRequest<DownloadRequested> {
    public DownloadRequested(Context context, String url) {
        this.url = url;
        this.context = context;
    }

    public void execute(DownloadListener l) {
        RequestManager.loadDownload(context, url, what, fileFolder, fileName, isRange, isDeleteOld, l);
    }
}

