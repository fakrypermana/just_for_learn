package id.teknologi.teknologiid.feature.pekerjaan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.PekerjaanAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class PekerjaanActivity extends BaseActivity implements PekerjaanView,RecyclerInterface{
    PekerjaanPresenter presenter;
    PekerjaanAdapter adapter;
    List<Pekerjaan> pekerjaanList = new ArrayList<>();

    @BindView(R.id.rv_pekerjaan)
    RecyclerView rvPekerjaan;

    @Override
    protected int contentView() {
        return R.layout.activity_pekerjaan;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        presenter = new PekerjaanPresenter(this);
        presenter.getPekerjaan();
        adapter = new PekerjaanAdapter(this, pekerjaanList, this);
    }

    @Override
    protected void setupView() {
        rvPekerjaan.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvPekerjaan.setAdapter(adapter);
    }

    @Override
    public void onSuccessPekerjaan(List<Pekerjaan> pekerjaanList) {
        Log.d("THREADS", new Gson().toJson(pekerjaanList));
        adapter.insertAndNotify(pekerjaanList);
    }

    @Override
    public void onLoading(boolean isLoading) {
        Log.d("Pekerjaan","LOADING "+isLoading);
    }

    @Override
    public void onFailed(String message) {
        Log.d("THREADS","ERROR");
    }

    @Override
    public void onRecyclerItemClicked(int position) {
        Pekerjaan pekerjaan = pekerjaanList.get(position);
        Toast.makeText(this,"Clicked"+pekerjaanList.get(position).getName(), Toast.LENGTH_SHORT).show();
    }
}
