package p_jie.uplibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanzhenjie.nohttp.rest.Response;

import java.util.ArrayList;

import p_jie.library.base.BaseActivity;
import p_jie.library.http.IRequest;
import p_jie.library.http.RequestJsonListener;
import p_jie.library.utils.LogUtils;
import p_jie.library.utils.ToastUtils;
import p_jie.library.view.imageselect.ImagePicker;
import p_jie.library.view.imageselect.bean.ImageItem;

public class MainActivity extends BaseActivity {
    private static final int REQUEST_CODE1 = 102;
    private RecyclerView mRecyclerView;
    private ImagePicker imagePicker;
    ImageView picture_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onCreateCustomToolBar(Toolbar toolbar) {
        super.onCreateCustomToolBar(toolbar);
        toolbar.showOverflowMenu();
        getLayoutInflater().inflate(R.layout.toobar_login_button, toolbar);
        TextView title_tv = (TextView) toolbar.findViewById(R.id.title_tv);
        TextView btn = (TextView) toolbar.findViewById(R.id.id_txt_btn);
        picture_iv = (ImageView) findViewById(R.id.picture_iv);

/*
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUrl = "http://www.weather.com.cn/data/sk/101110101.html";
                IRequest.get(MainActivity.this, mUrl, "weatherinfo").executeList(new RequestListener() {


                    @Override
                    public void onSuccess(List<Map<String, Object>> response) {

                    }

                    @Override
                    public void onFailed(int what, String url, Object tag, Exception exception, int responseCode, long networkMillis) {
                        super.onFailed(what, url, tag, exception, responseCode, networkMillis);
                    }
                });

            }
        });
        */
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUrl = "http://www.weather.com.cn/data/sk/101110101.html";
                IRequest.get(MainActivity.this, mUrl, "weatherinfo").execute(Bean.class, new RequestJsonListener<Bean>() {
                    @Override
                    public void onSuccess(Response<Bean> response) {
                        LogUtils.jj(response.get().getWeatherinfo().getCity());
                    }
                });

            }
        });
    }


    @Override
    public void callBreak() {
        super.callBreak();
        ToastUtils.showToast(this, "Break do something");
    }


    @Override
    public void callConnect() {
        super.callConnect();
        ToastUtils.showToast(this, "Connect do something");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE1 && resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            int size = 120;
            if (data != null) {
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                imagePicker.getImageLoader().displayImage(MainActivity.this, images.get(0).path, picture_iv, size, size);
            }
        }
    }
}
