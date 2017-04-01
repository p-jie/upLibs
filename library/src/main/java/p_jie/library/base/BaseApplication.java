package p_jie.library.base;

import android.app.Application;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;


/**
 * Created by jj on 2017/3/13.
 */

public class BaseApplication extends Application {
    public BaseActivity activityting = null;
    private static Application instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        NoHttp.initialize(this);
        Logger.setDebug(true);
    }
    public static Application getInstance() {
        return instance;
    }
}
