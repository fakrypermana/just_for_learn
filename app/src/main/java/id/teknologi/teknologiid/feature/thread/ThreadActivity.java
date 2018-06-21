package id.teknologi.teknologiid.feature.thread;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
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
import id.teknologi.teknologiid.feature.thread_new.ThreadNewActivity;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class ThreadActivity extends BaseActivity implements ThreadView, RecyclerInterface{

    ThreadPresenter presenter;
    ThreadsAdapter threadsAdapter;
    List<Thread> threadList = new ArrayList<>();
    int scrollDist = 0;
    boolean isVisible = true;

//    @BindView(R.id.rv_threads)
//    RecyclerView rvThreads;

//    @BindView(R.id.floating_action_button)
//    FloatingActionButton floatingActionButton;



    @Override
    protected int contentView() {
        return R.layout.activity_thread;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        presenter = new ThreadPresenter(this);
        presenter.getThreads();
        threadsAdapter = new ThreadsAdapter(this, threadList, this);
    }

    @Override
    protected void setupView() {
//        rvThreads.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
//        rvThreads.setAdapter(threadsAdapter);
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

    static final float MINIMUM = 25;
//    public abstract void show();
//    public abstract void hide();

//    protected void setFloatingActionButton(){
//        rvThreads.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                if (isVisible && scrollDist > MINIMUM) {
////                    hide();
//                    scrollDist = 0;
//                    isVisible = false;
//                }
//                else if (!isVisible && scrollDist < -MINIMUM) {
////                    show();
//                    scrollDist = 0;
//                    isVisible = true;
//                }
//
//                if ((isVisible && dy > 0) || (!isVisible && dy < 0)) {
//                    scrollDist += dy;
//                }
//            }
//        });
//    }
}
