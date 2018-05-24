package id.teknologi.teknologiid.feature.thread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.ThreadsAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.utils.AppUtils;

public class ThreadDetailActivity extends BaseActivity {
    private final static String ID = "ID";
    private final static String SLUG = "SLUG";

    @Override
    protected int contentView() {
        return R.layout.activity_detail_thread;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Log.d("__TES",intent.getIntExtra(ID, 0)+"");
        Log.d("__TES",intent.getStringExtra(SLUG));
    }

    @Override
    protected void setupView() {

        String url = "file:///android_asset/index.html";
        WebView web =(WebView)findViewById(R.id.wv_detileThread);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("http://dev.teknologi.id/forum/question/5/bagaimana-cara-memusatkan-%3Cdiv%3E-secara-horizontal-di-%3Cdiv%3E-lain%3F");
    }

    public static void start(Activity activity, int id, String slug){
        Intent intent = new Intent(activity, ThreadDetailActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(SLUG, slug);
        activity.startActivity(intent);
    }
}
