package p_jie.uplibs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import p_jie.library.base.BaseActivity;
import p_jie.library.utils.ToastUtils;
import p_jie.library.view.imageselect.GlideImageLoader;
import p_jie.library.view.imageselect.ImagePicker;
import p_jie.library.view.imageselect.ui.ImageGridActivity;

public class MainActivity extends BaseActivity {
    private static final int REQUEST_CODE1 = 102;
    private RecyclerView mRecyclerView;
    private ImagePicker imagePicker;

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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePicker = ImagePicker.getInstance();
                imagePicker.setImageLoader(new GlideImageLoader());
                Intent intent = new Intent(MainActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, 100);

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


}
