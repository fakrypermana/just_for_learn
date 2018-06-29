package id.teknologi.teknologiid.feature.pekerjaan_detail;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.Pekerjaan;

public class DetailPekerjaanActivity extends BaseActivity implements DetailPekerjaanView {
    @BindView(R.id.tv_nama_job_detail)
    TextView tvNamaJobDetail;
    @BindView(R.id.tv_nama_perusahan_detail)
    TextView tvPerusahaanJobDetail;
    @BindView(R.id.tv_date_exp_job_detail)
    TextView tvDateJobDetail;
    /* @BindView(R.id.wv_job_detail)
     WebView wvJobDetail;*/
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
    Pekerjaan pekerjaan;

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
    }

    @Override
    protected void setupView() {
        /*wvJobDetail.setWebViewClient(new WebViewClient());
        wvJobDetail.setPadding(50,50,50,50);
        WebSettings webSettings = wvJobDetail.getSettings();
        webSettings.setJavaScriptEnabled(true);*/

        /*tvNamaJobDetail.setText(pekerjaan.getName());
        tvAlamatJobDetail.setText(pekerjaan.getLocation());
        tvDateJobDetail.setText(pekerjaan.getDate_exp());
        tvPerusahaanJobDetail.setText("Nyusul");*/
    }

    @Override
    public void onSuccessDetailPekerjaan(Pekerjaan pekerjaan) {
        //showProgressDialog();
       /* String desc = pekerjaan.getDesc_long();
        wvJobDetail.loadData(desc,"text/html", "UTF-8");*/
        tvNamaJobDetail.setText(pekerjaan.getName());
        Log.d("Deskripsi","data : "+pekerjaan.getName());
        tvPerusahaanJobDetail.setText("Nyusul Company");
        tvDateJobDetail.setText(pekerjaan.getDate_exp());
       //setTextView();
    }

    public void setTextView(){
        //expandableTextView.setText(pekerjaan.getDesc_long());
        //Log.d("Deskripsi","data : "+pekerjaan.getDesc_long());

    }

    @Override
    public void onLoading(boolean isLoading) {

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
