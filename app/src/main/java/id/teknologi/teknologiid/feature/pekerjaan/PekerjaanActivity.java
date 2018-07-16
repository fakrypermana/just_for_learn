package id.teknologi.teknologiid.feature.pekerjaan;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.PekerjaanAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.pekerjaan_detail.DetailPekerjaanActivity;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class PekerjaanActivity extends BaseActivity implements PekerjaanView,RecyclerInterface{
    PekerjaanPresenter presenter;
    PekerjaanAdapter adapter;
    List<Pekerjaan> pekerjaanList = new ArrayList<>();
    ProgressDialog progressDialog;
    private final static String PATH = "PATH";
    private final static int PAGE = 0;

    @BindView(R.id.rv_pekerjaan)
    RecyclerView rvPekerjaan;

    @Override
    protected int contentView() {
        return R.layout.activity_pekerjaan;
    }


    @Override
    protected void setupData(Bundle savedInstanceState) {
        presenter = new PekerjaanPresenter(this);
        presenter.getPekerjaan(PATH,PAGE);
        adapter = new PekerjaanAdapter(this, pekerjaanList, this);

    }

    @Override
    protected void setupView() {
        rvPekerjaan.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvPekerjaan.setAdapter(adapter);


    }

    @Override
    public void onSuccessPekerjaan(List<Pekerjaan> pekerjaanList) {
        //Log.d("Pekerjaan", new Gson().toJson(pekerjaanList));
        //showProgressDialog();
        adapter.insertAndNotify(pekerjaanList);
    }

    @Override
    public void onLoading(boolean isLoading) {
        //Log.d("Pekerjaan","LOADING "+isLoading);
        //showProgressDialog();
    }

    @Override
    public void onFailed(String message) {
        Log.d("Pekerjaan","ERROR");
        Toast.makeText(this, message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRecyclerItemClicked(int position) {
        Pekerjaan pekerjaan = pekerjaanList.get(position);
        Toast.makeText(this,"Clicked"+pekerjaanList.get(position).getName(), Toast.LENGTH_SHORT).show();
        DetailPekerjaanActivity.start(this,pekerjaan.getId(),pekerjaan.getSlug());
    }

    /* Show progress dialog. */
    /*private void showProgressDialog()
    {
        // Set progress dialog display message.
        progressDialog.setMessage("Please Wait");

        // The progress dialog can not be cancelled.
        progressDialog.setCancelable(true);

        // Show it.
        progressDialog.show();
    }

    *//* Hide progress dialog. *//*
    private void hideProgressDialog()
    {
        // Close it.
        progressDialog.hide();
    }*/


}
