package id.teknologi.teknologiid.feature.thread;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.ThreadsAdapter;
import id.teknologi.teknologiid.adapter.ThreadsDetailAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.utils.AppUtils;

public class ThreadDetailActivity extends BaseActivity {

    ThreadDetailPresenter detailPresenter;
    ThreadsDetailAdapter detailAdapter;
    List<Thread> threadList = new ArrayList<>();


    private final static String ID = "ID";
    private final static String SLUG = "SLUG";
    //private final static String POST = "POST";

    @Override
    protected int contentView() {
        return R.layout.activity_detail_thread;
    }

    @BindView(R.id.wv_detileThread)
    WebView wvDetileThread;

    @Override
    protected void setupData(Bundle savedInstanceState) {

        //baru ditambahkan
        detailPresenter = new ThreadDetailPresenter(this);
        detailPresenter.getThreadDetilePresenter();
        detailAdapter = new ThreadsDetailAdapter(this,threadList,this);


        Intent intent = getIntent();
        Log.d("__TES",intent.getIntExtra(ID, 0)+"");
        Log.d("__TES",intent.getStringExtra(SLUG));
       // Log.d("__TES",intent.getStringExtra(POST));
    }

    @Override
    protected void setupView() {

        //baru ditambahkan
        wvDetileThread.setLayoutParams(this));
        //wvDetileThread.setDetileAdapter(detailAdapter);

        String url = "file:///android_asset/index.html";
        WebView web =(WebView)findViewById(R.id.wv_detileThread);
        web.setWebViewClient(new WebViewClient());
        //String data = "<p style=\"text-align: center;\"><iframe width=\" 480\" height=\"270\" src=\"https://www.youtube.com/embed/jF9VULLzwVk?feature=oembed\" frameborder=\"0\" allowfullscreen=\"allowfullscreen\"></iframe><img src=\"https://ph-files.imgix.net/d4e2ded5-b3d6-46ce-9d34-7464dae76567?auto=format&amp;auto=compress&amp;codec=mozjpeg&amp;cs=strip\" alt=\"\"><img src=\"https://ph-files.imgix.net/727db047-c452-4b2b-a44f-4eba4267a3e7?auto=format&amp;auto=compress&amp;codec=mozjpeg&amp;cs=strip\" alt=\"\"><img src=\"https://ph-files.imgix.net/f10a4ae9-6d62-46cd-a5c0-09547ba8ba7a?auto=format&amp;auto=compress&amp;codec=mozjpeg&amp;cs=strip\" alt=\"\"><img src=\"https://ph-files.imgix.net/79b4a3b6-3857-4353-85cd-7da5e74d4bbf?auto=format&amp;auto=compress&amp;codec=mozjpeg&amp;cs=strip\" alt=\"\"><img src=\"https://ph-files.imgix.net/28969b61-6983-4a5c-9717-39654aef9834?auto=format&amp;auto=compress&amp;codec=mozjpeg&amp;cs=strip\" alt=\"\"></p><p style=\"text-align: center;\">Prisma is a performant open-source GraphQL ORM-like layer doing the heavy lifting in your GraphQL server. It turns your database into a GraphQL API which can be consumed by your resolvers via GraphQL bindings.</p>";
        //web.loadData(data,"text/html", "UTF-8");
        web.loadUrl("https://www.google.com/");
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    public static void start(Activity activity, int id, String slug){
        Intent intent = new Intent(activity, ThreadDetailActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(SLUG, slug);
        activity.startActivity(intent);
    }
}
