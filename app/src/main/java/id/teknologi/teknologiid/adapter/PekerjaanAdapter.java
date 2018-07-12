package id.teknologi.teknologiid.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.robertlevonyan.views.chip.Chip;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
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
        Chip chipView;

        public PekerjaanVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView, recyclerInterface);
            ButterKnife.bind(this, itemView);
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
            if (pekerjaan.getTags() != null)
            {

                List<String> listTags = new ArrayList<>(pekerjaan.getTags());
                for (String tag : listTags
                        ) {
                    chipView.setChipText(tag);
                }

                //chipView.setAdapter(adapter);
                //chipView.setChipBackgroundColor(R.color.colorAccent);

            } else {
                chipView.setVisibility(View.GONE);
            }

        }
    }
}
