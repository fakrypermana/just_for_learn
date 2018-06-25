

package id.teknologi.teknologiid.feature.thread_new;


import android.os.Bundle;
import android.webkit.WebView;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;

public class ThreadNewActivity extends BaseActivity {

    @BindView(R.id.wv_newThread)
    WebView webView;

    @Override
    protected int contentView(){
        return R.layout.activity_thread_new;
    }

    @Override
    protected void setupData(Bundle savedInstanceState){

    }

    @Override
    protected void setupView(){

    }
}
