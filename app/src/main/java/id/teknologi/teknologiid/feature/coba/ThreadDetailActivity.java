package id.teknologi.teknologiid.feature.coba;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.CobaModel;
import id.teknologi.teknologiid.model.Comment;

public class ThreadDetailActivity extends BaseActivity implements CobaView{

    @BindView(R.id.wv_detileThread)
    WebView webView;
    private final static String ID = "ID";
    private final static String SLUG = "SLUG";
    private int id;
    private String slug;
    CobaPresenter presenter;
    @Override
    protected int contentView() {
        return R.layout.activity_detail_thread;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getIntExtra(ID, 0);
        slug = intent.getStringExtra(SLUG);
        presenter = new CobaPresenter(this);
        presenter.getThreadDetail(id, slug);
    }

    @Override
    protected void setupView() {
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onSuccessThreadDetail(CobaModel model) {
        String data = model.getPost();
        webView.loadData(data,"text/html", "UTF-8");
        for (Comment coba:model.getComments()){
            Log.d("INI KOMEN",coba.getCommenter_name());
        }
    }

    @Override
    public void onLoading(boolean isLoading) {

    }

    @Override
    public void onFailed(String message) {

    }

    public static void start(Activity activity, int id, String slug){
        Intent intent = new Intent(activity, ThreadDetailActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(SLUG, slug);
        activity.startActivity(intent);
    }
}
