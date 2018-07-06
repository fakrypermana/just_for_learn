package id.teknologi.teknologiid.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.plumillonforge.android.chipview.ChipView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.feature.pekerjaan.PekerjaanPresenter;
import id.teknologi.teknologiid.feature.pekerjaan_detail.DetailPekerjaanActivity;
import id.teknologi.teknologiid.feature.profile.ProfileActivity;
import id.teknologi.teknologiid.model.Pekerjaan;
import id.teknologi.teknologiid.model.RelatedPekerjaan;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class RelatedJobAdapter extends BaseRecyclerAdapter<RelatedPekerjaan, RelatedJobAdapter.RelatedVH> {
    public RelatedJobAdapter(Context context, List<RelatedPekerjaan> relatedList, RecyclerInterface recyclerCallBack) {
        super(context, relatedList, recyclerCallBack);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_list_related_job;
    }

    @Override
    public RelatedJobAdapter.RelatedVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RelatedVH(initView(viewType, parent), getRecyclerCallback());
    }

    public class RelatedVH extends BaseViewHolder<RelatedPekerjaan> {

        @BindView(R.id.tv_nama_related_job)
        TextView tvNamaRelated;
        @BindView(R.id.iv_cover_related_job)
        ImageView ivCoverRelated;
        @BindView(R.id.tv_salary_min)
        TextView tvSalaryMin;
        @BindView(R.id.tv_salary_max)
        TextView tvSalaryMax;
        @BindView(R.id.btn_lihat_detail_related)
        Button btnDetailRelated;
        @BindView(R.id.btn_simpan_related_job)
        Button btnSimpanRelated;

        //List<RelatedPekerjaan> relatedPekerjaanList = new ArrayList<>();

        public RelatedVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(RelatedPekerjaan related) {
            tvNamaRelated.setText(related.getName());
            tvSalaryMax.setText(String.valueOf(related.getSalary_max()));
            tvSalaryMin.setText(String.valueOf(related.getSalary_min()));
            //Log.d("wadidaw","isinya"+new Gson().toJson(related.getName()));

            btnSimpanRelated.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ProfileActivity.class);
                    v.getContext().startActivity(intent);
                    ((Activity)context).finish();
                }
            });

        }

        /*public void toDetail(int position, Context context){

            RelatedPekerjaan pekerjaan = relatedPekerjaanList.get(position);
            //Toast.makeText(this,"Clicked"+.get(position).getName(), Toast.LENGTH_SHORT).get.show();
            DetailPekerjaanActivity.move( context ,pekerjaan.getId(),pekerjaan.getSlug());
        }*/

    }
}
