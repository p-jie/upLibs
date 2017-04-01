package p_jie.library.http;

import com.yolanda.nohttp.BasicBinary;
import com.yolanda.nohttp.FileBinary;

/**
 * Created by jj on 2017/3/24.
 */

public abstract class OnUploadListener {

    /**
     * At the start of the upload is invoked.
     *
     * @param what what of {@link FileBinary#setUploadListener(int, OnUploadListener)}.
     * @see FileBinary#setUploadListener(int, OnUploadListener)
     */
    public void onStart(int what) {

    }

    /**
     * Called when the upload was cancelled.
     *
     * @param what what of {@link FileBinary#setUploadListener(int, OnUploadListener)}.
     * @see FileBinary#setUploadListener(int, OnUploadListener)
     */
    public void onCancel(int what) {

    }

    /**
     * Invoked when the upload progress changes.
     *
     * @param what     what of {@link FileBinary#setUploadListener(int, OnUploadListener)}.
     * @param progress progress
     * @see FileBinary#setUploadListener(int, OnUploadListener)
     */
    public void onProgress(int what, int progress) {

    }

    /**
     * Upload is complete is invoked.
     *
     * @param what what of {@link FileBinary#setUploadListener(int, OnUploadListener)}.
     * @see FileBinary#setUploadListener(int, OnUploadListener)
     */
    public void onFinish(int what) {

    }

    /**
     * Upload error is called.
     *
     * @param what      what of {@link FileBinary#setUploadListener(int, OnUploadListener)}. * @param exception upload is called when an error occurs.
     * @param exception error type.
     * @see BasicBinary#setUploadListener(int, OnUploadListener)
     */
    public void onError(int what, Exception exception) {

    }
}

