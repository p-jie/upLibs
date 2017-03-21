package p_jie.library.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import p_jie.library.base.BaseActivity;
import p_jie.library.base.BaseApplication;

/**
 * Created by jj on 2017/3/13.
 *
 */

/*
 网络监听广播接收者
 在使用的Application添加上 android:name="p_jie.library.base.BaseApplication"
 可以动态注册 也可以静态注册 现在只列出静态注册
  <receiver android:name="p_jie.library.utils.NetUtils">
            <intent-filter>
                <action android:name="android.net.conn.CONNECTIVITY_CHANGE"/>
            </intent-filter>
  </receiver>

  继承BaseActivity  回调callBreak 和 callConnect方法可以在断网和重新连接网络的时候做相应的操作 比如刷新
 */

public class NetUtils extends BroadcastReceiver {


    private final static int STATUS_WIFI = 2;
    private final static int STATUS_GPRS = 1;
    private final static int STATUS_ERROR = 0;
    private static int mStatus = 2;


    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.jj("网络状态改变,进入onReceive方法");
        BaseApplication app = (BaseApplication) context.getApplicationContext();
        BaseActivity activity = app.activityting;
        if (activity == null) {
            LogUtils.jj("-----------------------------------------------------------Activity==null...");
            return;
        }


        int tempStatus = -1;
        // 获得网络连接服务
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State state_wifi = null;
        NetworkInfo.State state_gprs = null;
        NetworkInfo wifiNetInfo = null;
        NetworkInfo mobNetInfo = null;
        try {
            wifiNetInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            state_wifi = wifiNetInfo.getState(); // 获取网络连接状态
        } catch (Exception e) {
            LogUtils.jj(e.toString());
            LogUtils.jj("测试机没有WIFI模块");
        }
        try {
            mobNetInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            state_gprs = mobNetInfo.getState(); // 获取网络连接状态
        } catch (Exception e) {
            LogUtils.jj(e.toString());
            LogUtils.jj("测试机没有GPRS模块");
        }
        if (null != state_wifi && NetworkInfo.State.CONNECTED == state_wifi) { // 判断是否正在使用WIFI网络
            tempStatus = STATUS_WIFI;
            LogUtils.jj("mStatus=" + mStatus + "改变后的网络为WIFI");
        } else if (null != state_gprs && NetworkInfo.State.CONNECTED == state_gprs) { // 判断是否正在使用GPRS网络
            tempStatus = STATUS_GPRS;
            LogUtils.jj("mStatus=" + mStatus + "改变后的网络为GPRS");
        } else {
            tempStatus = STATUS_ERROR;
            LogUtils.jj("mStatus=" + mStatus + "改变后的网络为ERROR");
        }
        if (mStatus != tempStatus) {
            LogUtils.jj("mStatus与改变后的网络不同，网络真的改变了");
            if (!mobNetInfo.isConnected() && !wifiNetInfo.isConnected()) {
                LogUtils.jj("网络已断开!");
                //回调当前activity的方法
                activity.callBreak();
                ToastUtils.showToast(context, "网络已断开!");
            } else {
                LogUtils.jj("网络已重新连接!");
                //回调当前activity的方法
                activity.callConnect();
                ToastUtils.showToast(context, "网络已重新连接!");
            }
        } else {
            LogUtils.jj("mStatus与改变后的网络相同，不处理");
        }
        mStatus = tempStatus;
    }

}
