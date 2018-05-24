package id.teknologi.teknologiid.feature.home;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.ThreadsAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class HomeActivity extends BaseActivity implements HomeView, RecyclerInterface{

    HomePresenter presenter;
    ThreadsAdapter threadsAdapter;
    List<Thread> threadList = new ArrayList<>();

    @BindView(R.id.rv_threads)
    RecyclerView rvThreads;

    @Override
    protected int contentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        presenter = new HomePresenter(this);
        presenter.getThreads();
        threadsAdapter = new ThreadsAdapter(this, threadList, this);
    }

    @Override
    protected void setupView() {
        rvThreads.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvThreads.setAdapter(threadsAdapter);
    }

    @Override
    public void onLoading(boolean isLoading) {
        Log.d("THREADS","LOADING "+isLoading);
    }

    @Override
    public void onFailed(String message) {
        Log.d("THREADS","ERROR");
    }

    @Override
    public void onSuccessThreads(List<Thread> threadList) {
        Log.d("THREADS", new Gson().toJson(threadList));
        threadsAdapter.insertAndNotify(threadList);
    }

    @Override
    public void onRecyclerItemClicked(int position) {
        Toast.makeText(this, "Clicked "+threadList.get(position).getTitle(), Toast.LENGTH_SHORT).show();
    }
}
