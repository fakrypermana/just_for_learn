package id.teknologi.teknologiid.feature.pekerjaan_detail;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.nbsp.materialfilepicker.ui.FilePickerActivity;
import com.plumillonforge.android.chipview.Chip;
import com.plumillonforge.android.chipview.ChipView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import id.teknologi.teknologiid.Manifest;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.ChipAdapter;
import id.teknologi.teknologiid.adapter.RelatedJobAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.Tag;
import id.teknologi.teknologiid.feature.pekerjaan.PekerjaanPresenter;
import id.teknologi.teknologiid.feature.pekerjaan.PekerjaanView;
import id.teknologi.teknologiid.feature.profile.ProfileActivity;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.model.Profile;
import id.teknologi.teknologiid.model.RelatedPekerjaan;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class DetailPekerjaanActivity extends BaseActivity implements DetailPekerjaanView, RecyclerInterface {
    @BindView(R.id.tv_nama_job_detail)
    TextView tvNamaJobDetail;
    @BindView(R.id.tv_nama_perusahan_detail)
    TextView tvPerusahaanJobDetail;
    @BindView(R.id.tv_date_exp_job_detail)
    TextView tvDateJobDetail;
    @BindView(R.id.wv_job_desc_detail)
    WebView wvJobDetail;
    @BindView(R.id.wv_skill)
    WebView wvSkill;
    @BindView(R.id.wv_short_desc)
    WebView wvShortDesc;
    @BindView(R.id.tv_gaji_min)
    TextView tvGajiPekerjaanMin;
    @BindView(R.id.tv_gaji_max)
    TextView tvGajiPekerjaanMax;
    @BindView(R.id.iv_cover_job_detail)
    ImageView ivCoverJobDetail;



    @BindView(R.id.btn_daftar_detail)
    Button btnDaftarDetail;

    ProgressDialog progressDialog;

    /**
     * for chip
     */
    @BindView(R.id.chip_tags_detail)
    ChipView chipView;
    ChipAdapter adapterChip;


    private int id;
    private String name;
    private String slug;
    private final static String ID = "ID";
    private final static String SLUG = "SLUG";
    DetailPekerjaanPresenter presenter;

    /**
     * for related job (recyclerview)
     */
    RelatedJobAdapter adapter;
    List<RelatedPekerjaan> relatedList = new ArrayList<>();
    Pekerjaan pekerjaan;
    @BindView(R.id.rv_related_job)
    RecyclerView rvRelated;
    //PekerjaanPresenter pekerjaanPresenter;

    @Override
    protected int contentView() {
        return R.layout.activity_detail_pekerjaan;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
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

        btnDaftarDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Initiate Custom Dialog
                 */
                final Dialog dialog = new Dialog(DetailPekerjaanActivity.this);
                dialog.setContentView(R.layout.activity_form_daftar_job);
                dialog.setTitle("Daftarkan diri anda");
                dialog.setCanceledOnTouchOutside(true);
                dialog.setCancelable(true);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;

                /**
                 * Mengeset komponen dari custom dialog
                 */
                Button btnAddFileCV = dialog.findViewById(R.id.btn_upload_cv);
                Button btnClose = dialog.findViewById(R.id.btn_close_dialog);
                EditText inputNamaDaftar = dialog.findViewById(R.id.input_nama_daftar_job);

                EditText inputEmailDaftar = dialog.findViewById(R.id.input_email_daftar_job);

                EditText inputTeleponDaftar = dialog.findViewById(R.id.input_telepon_daftar_job);

                TextView tvFilePath = dialog.findViewById(R.id.tv_filepath);


                /**
                 * Jika tombol diklik
                 */
                btnClose.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                /**
                 * permission access file
                 */
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
                }

                btnAddFileCV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new MaterialFilePicker()
                                .withActivity(DetailPekerjaanActivity.this)
                                .withRequestCode(1000)
                                .withHiddenFiles(true) // Show hidden files and folders
                                .start();
                    }
                });


                dialog.show();
                dialog.getWindow().setAttributes(lp);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK) {
            String filePath = data.getStringExtra(FilePickerActivity.RESULT_FILE_PATH);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1001 : {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }
    }

    @SuppressLint({"ClickableViewAccessibility", "NewApi"})
    @Override
    protected void setupView() {
        // disable scroll on touch
        wvJobDetail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });

        /*LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);*/

        rvRelated.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvRelated.setAdapter(adapter);

    }

    @Override
    public void onSuccessDetailPekerjaan(Pekerjaan pekerjaan) {
        Log.d("pekerjaan Detail", new Gson().toJson(pekerjaan.getRelatedJob()));
        this.pekerjaan=pekerjaan;
        setTextView(pekerjaan);
        adapter.insertAndNotify(pekerjaan.getRelatedJob());
        Log.d("related","isinya"+pekerjaan.getRelatedJob());
    }



    @SuppressLint("ResourceAsColor")
    public void setTextView(Pekerjaan pekerjaan) {

        //chip
        if (pekerjaan.getTags() != null){
            List<Chip> listChip = new ArrayList<>();
            List<String> listTags = new ArrayList<>(pekerjaan.getTags());
            for (String tag : listTags
                    ) {
                listChip.add(new Tag(tag));
            }

            //chipView.setAdapter(adapterChip);
            //chipView.setChipBackgroundColor(R.color.colorAccent);
            chipView.setChipList(listChip);
        } else{
            chipView.setVisibility(View.GONE);
        }


        String descJob = pekerjaan.getDesc_long();
        String descSingkat = pekerjaan.getDescription();
        String skill = pekerjaan.getSkills();

        wvJobDetail.loadData(descJob, "text/html", "UTF-8");
        wvShortDesc.loadData(descSingkat, "text/html", "UTF-8");
        wvSkill.loadData(skill, "text/html", "UTF-8");
        tvGajiPekerjaanMin.setText(String.valueOf(pekerjaan.getSalary_min()));
        tvGajiPekerjaanMax.setText(String.valueOf(pekerjaan.getSalary_max()));

        Glide.with(this).load(pekerjaan.getPhoto())
                .into(ivCoverJobDetail);

        tvNamaJobDetail.setText(pekerjaan.getName());
        //Log.d("Deskripsi","data : "+pekerjaan.getName());
        if (pekerjaan.getCompany() != null) {
            tvPerusahaanJobDetail.setText(pekerjaan.getCompany().getCompany_name());
        } else {
            tvPerusahaanJobDetail.setText("-");
        }
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
        RelatedPekerjaan pekerjaan = relatedList.get(position);
        Toast.makeText(this, "Clicked" + relatedList.get(position).getName(), Toast.LENGTH_SHORT).show();
        //DetailPekerjaanActivity.start(this, pekerjaan.getId(), pekerjaan.getSlug());
        Intent intent = new Intent(DetailPekerjaanActivity.this, ProfileActivity.class);
        startActivity(intent);
        finish();
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
