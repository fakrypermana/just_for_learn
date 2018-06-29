package id.teknologi.teknologiid.feature.pekerjaan_detail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.RelatedJobAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.pekerjaan.PekerjaanPresenter;
import id.teknologi.teknologiid.feature.pekerjaan.PekerjaanView;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class DetailPekerjaanActivity extends BaseActivity implements DetailPekerjaanView,RecyclerInterface {
    @BindView(R.id.tv_nama_job_detail)
    TextView tvNamaJobDetail;
    @BindView(R.id.tv_nama_perusahan_detail)
    TextView tvPerusahaanJobDetail;
    @BindView(R.id.tv_date_exp_job_detail)
    TextView tvDateJobDetail;
    @BindView(R.id.wv_job_detail)
    WebView wvJobDetail;
    /*@BindView(R.id.expandable_text_view)
    ExpandableTextView expandableTextView;
    @BindView(R.id.tv_descexpandable_detail)
    TextView tvDescExpand;*/


    ProgressDialog progressDialog;

    private int id;
    private String name;
    private String slug;
    private final static String ID = "ID";
    private final static String SLUG = "SLUG";
    DetailPekerjaanPresenter presenter;
    //Pekerjaan pekerjaan;

    //related requirement
    RelatedJobAdapter adapter;
    List<Pekerjaan> relatedList = new ArrayList<>();
    @BindView(R.id.rv_related_job)
    RecyclerView rvRelated;
    //PekerjaanPresenter pekerjaanPresenter;

    @Override
    protected int contentView() {
        return R.layout.activity_detail_pekerjaan;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        id = intent.getIntExtra(ID, 0);
        slug = intent.getStringExtra(SLUG);
        presenter = new DetailPekerjaanPresenter(this);
        presenter.getDetailPekerjaan(id, slug);

        /*pekerjaanPresenter = new PekerjaanPresenter(this);
        pekerjaanPresenter.getPekerjaan();*/

        adapter = new RelatedJobAdapter(this, relatedList, this);
    }

    @Override
    protected void setupView() {
        wvJobDetail.setWebViewClient(new WebViewClient());
        wvJobDetail.setPadding(50,50,50,50);
        WebSettings webSettings = wvJobDetail.getSettings();
        webSettings.setJavaScriptEnabled(true);

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvRelated.setLayoutManager(layoutManager);
        rvRelated.setAdapter(adapter);
    }

    @Override
    public void onSuccessDetailPekerjaan(Pekerjaan pekerjaan) {

       setTextView(pekerjaan);

    }

    public void setTextView(Pekerjaan pekerjaan){

        String desc = pekerjaan.getDesc_long();
        wvJobDetail.loadData(desc,"text/html", "UTF-8");

        tvNamaJobDetail.setText(pekerjaan.getName());
        Log.d("Deskripsi","data : "+pekerjaan.getName());
        tvPerusahaanJobDetail.setText("Nyusul Company");
        tvDateJobDetail.setText(pekerjaan.getDate_exp());
    }

    @Override
    public void onLoading(boolean isLoading) {
        //showProgressDialog();
    }

    @Override
    public void onFailed(String message) {

    }

    public static void start(Activity activity, int id, String slug) {
        Intent intent = new Intent(activity, DetailPekerjaanActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(SLUG, slug);
        activity.startActivity(intent);
    }

    @Override
    public void onRecyclerItemClicked(int position) {
        Pekerjaan pekerjaan = relatedList.get(position);
        Toast.makeText(this,"Clicked"+relatedList.get(position).getName(), Toast.LENGTH_SHORT).show();
        DetailPekerjaanActivity.start(this,pekerjaan.getId(),pekerjaan.getSlug());
    }



    /* Show progress dialog. */
    /*private void showProgressDialog() {
        // Set progress dialog display message.
        progressDialog.setMessage("Please Wait");

        // The progress dialog can not be cancelled.
        progressDialog.setCancelable(true);

        // Show it.
        progressDialog.show();
    }

    *//* Hide progress dialog. *//*
    private void hideProgressDialog() {
        // Close it.
        progressDialog.hide();
    }*/
}
