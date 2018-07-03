package id.teknologi.teknologiid.feature.thread;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.ThreadsAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.thread_detail.ThreadDetailActivity;
import id.teknologi.teknologiid.feature.thread_new.ThreadNewActivity3;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class ThreadActivity extends BaseActivity implements ThreadView, RecyclerInterface{

    ThreadPresenter presenter;
    ThreadsAdapter threadsAdapter;
    List<Thread> threadList = new ArrayList<>();


    @BindView(R.id.rv_threads)
    RecyclerView rvThreads;

    @BindView(R.id.fab_threadNew)
    FloatingActionButton fab_threadnew;

    @Override
    protected int contentView() {
        return R.layout.activity_thread;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

        presenter = new ThreadPresenter(this);
        presenter.getThreads();
        threadsAdapter = new ThreadsAdapter(this, threadList, this);

        fab_threadnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreadActivity.this, ThreadNewActivity3.class);
                startActivity(intent);
            }
        });



    }



    @Override
    protected void setupView() {
        rvThreads.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvThreads.setAdapter(threadsAdapter);


    }

    @Override
    public void onLoading(boolean isLoading) {
        Log.d("THREADS", "LOADING " + isLoading);
    }

    @Override
    public void onFailed(String message) {
        Log.d("THREADS", "ERROR");
    }

    @Override
    public void onSuccessThreads(List<Thread> threadList) {
        Log.d("THREADS", new Gson().toJson(threadList));
        threadsAdapter.insertAndNotify(threadList);
    }

    @Override
    public void onRecyclerItemClicked(int position) {
        Thread thread = threadList.get(position);
        Toast.makeText(this, "Clicked " + threadList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        ThreadDetailActivity.start(this, thread.getId(), thread.getSlug());
    }



}
