package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.feature.pekerjaan.PekerjaanPresenter;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class RelatedJobAdapter extends BaseRecyclerAdapter<Pekerjaan, RelatedJobAdapter.RelatedVH> {
    public RelatedJobAdapter(Context context, List<Pekerjaan> pekerjaanList, RecyclerInterface recyclerCallBack) {
        super(context, pekerjaanList, recyclerCallBack);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_list_related_job;
    }

    @Override
    public RelatedJobAdapter.RelatedVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RelatedVH(initView(viewType, parent), getRecyclerCallback());
    }

    public class RelatedVH extends BaseViewHolder<Pekerjaan> {

        @BindView(R.id.tv_nama_related_job)
        TextView tvNamaRelated;
        @BindView(R.id.iv_cover_related_job)
        ImageView ivCoverRelated;
        @BindView(R.id.tv_nama_perusahaan_related_job)
        TextView tvPerusahaanRelated;
        @BindView(R.id.btn_daftar_related_job)
        Button btnDaftarRelated;
        @BindView(R.id.btn_simpan_related_job)
        Button btnSimpanRelated;

        public RelatedVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(Pekerjaan pekerjaan) {
            Glide.with(itemView).load(pekerjaan.getPhoto())
                    .into(ivCoverRelated);
            tvNamaRelated.setText(pekerjaan.getName());
            tvPerusahaanRelated.setText("nyusul");
        }
    }
}
