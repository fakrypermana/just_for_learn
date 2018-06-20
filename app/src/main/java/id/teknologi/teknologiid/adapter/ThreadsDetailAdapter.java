package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.List;

import id.teknologi.teknologiid.BaseApplication;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.model.DetileThread;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.utils.RecyclerInterface;


public class ThreadsDetailAdapter extends BaseApplication{

    public ThreadsAdapter(Context context, List<DetileThread> threadList, RecyclerInterface recyclerCallback) {
        super(context, threadList, recyclerCallback);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.list_comment;
    }


}

