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
import android.view.Menu;
import android.view.MenuItem;
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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import butterknife.BindView;
import id.teknologi.teknologiid.Manifest;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.RelatedJobAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.Tag;
import id.teknologi.teknologiid.feature.pekerjaan.PekerjaanActivity;
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
    @BindView(R.id.tv_alamat_job_detail)
    TextView tvAlamatJobDetail;
    @BindView(R.id.tv_date_exp_job_detail)
    TextView tvDateJobDetail;
    @BindView(R.id.wv_job_desc_detail)
    WebView wvJobDetail;
    @BindView(R.id.tv_skill)
    TextView tvSkill;
    @BindView(R.id.tv_short_desc)
    TextView tvShortDesc;
    @BindView(R.id.tv_gaji_min)
    TextView tvGajiPekerjaanMin;
    @BindView(R.id.tv_gaji_max)
    TextView tvGajiPekerjaanMax;
    @BindView(R.id.iv_cover_job_detail)
    ImageView ivCoverJobDetail;
    @BindView(R.id.label_desc)
    TextView labelDesc;
    @BindView(R.id.label_skill)
    TextView labelSkill;
    @BindView(R.id.toolbar_detail_job)
    android.support.v7.widget.Toolbar toolbarDetail;
    @BindView(R.id.tv_lihat_semua_button)
    TextView tvLihatSemua;
    @BindView(R.id.btn_daftar_detail)
    Button btnDaftarDetail;

    ProgressDialog progressDialog;

    /**
     * for chip
     */
    @BindView(R.id.chip_tags_job_detail)
    ChipView chipView;

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

        if(progressDialog == null) {
            progressDialog = new ProgressDialog(DetailPekerjaanActivity.this);
        }

        showProgressDialog();

        setSupportActionBar(toolbarDetail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarDetail.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_black_24dp);

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

        tvLihatSemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPekerjaanActivity.this, PekerjaanActivity.class);
                startActivity(intent);
                finish();
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

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

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

        progressDialog.dismiss();
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
            chipView.setChipBackgroundRes(R.drawable.shape_chip_view_tag_job);
            chipView.setChipSpacing(12);
            chipView.setLineSpacing(6);
            chipView.setChipTextSize(8);
            chipView.setChipList(listChip);
            //chipView.setAdapter(adapterChip);
            //chipView.setChipBackgroundColor(R.color.colorAccent);
        } else{
            chipView.setVisibility(View.GONE);
        }


        String descJob = pekerjaan.getDesc_long();
        WebSettings webSettings = wvJobDetail.getSettings();
        webSettings.setDefaultFontSize(12);

        wvJobDetail.loadData(descJob, "text/html", "UTF-8");
        if (pekerjaan.getDescription() != null) {
            tvShortDesc.setText(pekerjaan.getDescription());
        } else{
            tvShortDesc.setVisibility(View.GONE);
            labelDesc.setVisibility(View.GONE);
        }

        if (pekerjaan.getSkills() != null){
            tvSkill.setText(pekerjaan.getSkills());
        } else{
            tvSkill.setVisibility(View.GONE);
            labelSkill.setVisibility(View.GONE);
        }
        tvGajiPekerjaanMin.setText(String.valueOf(pekerjaan.getSalary_min()));
        tvGajiPekerjaanMax.setText(String.valueOf(pekerjaan.getSalary_max()));

        Glide.with(this).load(pekerjaan.getPhoto())
                .into(ivCoverJobDetail);

        tvNamaJobDetail.setText(pekerjaan.getName());
        //Log.d("Deskripsi","data : "+pekerjaan.getName());
        tvAlamatJobDetail.setText(pekerjaan.getCompany().getAddress());
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
        Log.d("isi related","datanya"+pekerjaan);
        //Toast.makeText(this, "Clicked" + relatedList.get(position).getName(), Toast.LENGTH_SHORT).show();
        DetailPekerjaanActivity.start(this, pekerjaan.getId(), pekerjaan.getSlug());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_more_mert, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_more) {
            Toast.makeText(DetailPekerjaanActivity.this, "au dah fitur bagikan kapan dikasih taunya", Toast.LENGTH_LONG).show();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private void showProgressDialog(){
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(true);
        progressDialog.show();
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
