package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class PekerjaanAdapter extends BaseRecyclerAdapter<Pekerjaan, PekerjaanAdapter.PekerjaanVH>
{
    public PekerjaanAdapter(Context context, List<id.teknologi.teknologiid.model.Pekerjaan> pekerjaans) {
        super(context, pekerjaans);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_list_pekerjaan;
    }

    @Override
    public PekerjaanVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PekerjaanVH(initView(viewType, parent), getRecyclerCallback());
    }

    public class PekerjaanVH extends BaseViewHolder<Pekerjaan>{

        @BindView(R.id.iv_cover_job)
        ImageView ivCoverJob;
        @BindView(R.id.tv_nama_job)
        TextView tvNamaJob;
        @BindView(R.id.tv_nama_perusahaan)
        TextView tvNamaPerusahaan;
        @BindView(R.id.tv_alamat_job)
        TextView tvAlamatJob;

        public PekerjaanVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView, recyclerInterface);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(Pekerjaan pekerjaan) {

        }
    }
}
