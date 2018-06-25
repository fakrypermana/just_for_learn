package id.teknologi.teknologiid.feature.thread_detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.ThreadsDetailAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.CobaModel;
import id.teknologi.teknologiid.model.Comment;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class  ThreadDetailActivity extends BaseActivity implements ThreadDetailView, RecyclerInterface {


    @BindView(R.id.wv_detileThread)
    WebView webView;

    @BindView(R.id.rv_detileThreads)
    RecyclerView rvDetileThreads;

    private final static String ID = "ID";
    private final static String SLUG = "SLUG";
    private int id;
    private String slug;
    ThreadDetailPresenter presenter;
    ThreadsDetailAdapter threadsDetailAdapter;
    List<Thread> threadDetailList = new ArrayList<>();

    @Override
    protected int contentView() {
        return R.layout.activity_detail_thread;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getIntExtra(ID, 0);
        slug = intent.getStringExtra(SLUG);
        presenter = new ThreadDetailPresenter(this);
        presenter.getThreadDetail(id, slug);

        //rv
        threadsDetailAdapter = new ThreadsDetailAdapter(this, threadDetailList, this);
    }

    @Override
    protected void setupView() {
        webView.setWebViewClient(new WebViewClient());
        webView.setPadding(50,50,50,50);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //rv
        rvDetileThreads.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvDetileThreads.setAdapter(threadsDetailAdapter);
    }

    @Override
    public void onSuccessThreadDetail(CobaModel model) {
        String data = model.getPost();
        webView.loadData(data,"text/html", "UTF-8");
        for (Comment coba:model.getComments()){
            Log.d("INI KOMEN",coba.getCommenter_name());
        }

        //rv
        Log.d("DETAIL THREADS", new Gson().toJson(threadDetailList));
        threadsDetailAdapter.insertAndNotify(threadDetailList);
    }

    @Override
    public void onLoading(boolean isLoading) {

        //rv
        Log.d("DETAIL THREADS", "LOADING" +isLoading);

    }

    @Override
    public void onFailed(String message) {

        //rv
        Log.d("DETAIL THREADS", "ERROR");
    }

    public static void start(Activity activity, int id, String slug){
        Intent intent = new Intent(activity, ThreadDetailActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(SLUG, slug);
        activity.startActivity(intent);
    }

    @Override
    public void onRecyclerItemClicked(int position) {
        Thread thread = threadDetailList.get(position);
        Toast.makeText(this, "Clicked " + threadDetailList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        ThreadDetailActivity.start(this, thread.getId(), thread.getSlug());
    }
}
