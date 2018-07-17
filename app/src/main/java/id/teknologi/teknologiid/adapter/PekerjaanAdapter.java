package id.teknologi.teknologiid.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nbsp.materialfilepicker.MaterialFilePicker;
import com.plumillonforge.android.chipview.Chip;
import com.plumillonforge.android.chipview.ChipView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.feature.pekerjaan_detail.DetailPekerjaanActivity;
import id.teknologi.teknologiid.feature.pekerjaan_detail.FormDaftarJobActivity;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class PekerjaanAdapter extends BaseRecyclerAdapter<Pekerjaan, PekerjaanAdapter.PekerjaanVH> {
    public PekerjaanAdapter(Context context, List<Pekerjaan> pekerjaanList, RecyclerInterface recyclerCallBack) {
        super(context, pekerjaanList, recyclerCallBack);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_list_pekerjaan;
    }

    @Override
    public PekerjaanVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PekerjaanVH(initView(viewType, parent), getRecyclerCallback());
    }

    static class PekerjaanVH extends BaseViewHolder<Pekerjaan> {

        @BindView(R.id.iv_cover_job)
        ImageView ivCoverJob;
        @BindView(R.id.tv_nama_job)
        TextView tvNamaJob;
        @BindView(R.id.tv_nama_perusahaan)
        TextView tvNamaPerusahaan;
        @BindView(R.id.tv_alamat_job)
        TextView tvAlamatJob;
        @BindView(R.id.tv_date_exp_job)
        TextView tvDateExp;
        @BindView(R.id.chip_tags_job)
        ChipView chipView;
        @BindView(R.id.btn_simpan_item)
        Button btnSimpan;
        @BindView(R.id.btn_daftar_item)
        Button btnDaftar;
        @BindView(R.id.btn_more_item_job)
        Button btnMore;

        public PekerjaanVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView, recyclerInterface);
            ButterKnife.bind(this, itemView);


            /**
             * btn clicked
             */
            btnDaftar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * Initiate Custom Dialog
                     */
                    final Dialog dialog = new Dialog(v.getContext());
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
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(v.getContext(),android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED){
                        ((Activity) v.getContext()).requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1001);
                    }

                    btnAddFileCV.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            new MaterialFilePicker()
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

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBind(Pekerjaan pekerjaan) {


            Glide.with(itemView).load(pekerjaan.getPhoto())
                    .into(ivCoverJob);
            tvNamaJob.setText(pekerjaan.getName());
            if (pekerjaan.getCompany() != null) {
                tvNamaPerusahaan.setText(pekerjaan.getCompany().getCompany_name());
                tvAlamatJob.setText(pekerjaan.getCompany().getAddress());
            } else {
                tvNamaPerusahaan.setVisibility(View.INVISIBLE);
                tvAlamatJob.setVisibility(View.GONE);
                //System.out.println("Tidak ada data perusahaan");
            }
            //Log.d("perusahaan","isinya"+pekerjaan.getCompany().);

            tvDateExp.setText(pekerjaan.getDate_exp());

            //chip
            //chip
            if (pekerjaan.getTags() != null){
                List<Chip> listChip = new ArrayList<>();
                List<String> listTags = new ArrayList<>(pekerjaan.getTags());
                for (String tag : listTags
                        ) {
                    listChip.add(new id.teknologi.teknologiid.feature.Tag(tag));
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

        }
    }
}
