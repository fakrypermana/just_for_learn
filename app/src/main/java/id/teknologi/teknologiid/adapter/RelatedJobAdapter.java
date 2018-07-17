package id.teknologi.teknologiid.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

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
        @BindView(R.id.tv_alamat_related)
        TextView tvAlamatRelated;
        @BindView(R.id.tv_date_exp_related)
        TextView tvExpRelated;


        //List<RelatedPekerjaan> relatedPekerjaanList = new ArrayList<>();

        public RelatedVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @Override
        public void onBind(RelatedPekerjaan related) {
            tvNamaRelated.setText(related.getName());
            //Log.d("wadidaw","isinya"+new Gson().toJson(related.getName()));
            Glide.with(itemView).load(related.getPhoto())
                    .into(ivCoverRelated);

            tvAlamatRelated.setText(related.getLocation());
            tvExpRelated.setText(related.getDate_exp());

            //chip
            /*List<Chip> listChip = new ArrayList<>();
            if (related.getTags() != null)
            {

                List<String> listTags = new ArrayList<>(related.getTags());
                for (String tag : listTags
                        ) {
                    listChip.add(new Tag(tag));
                }

                //chipView.setAdapter(adapter);
                //chipView.setChipBackgroundColor(R.color.colorAccent);
                chipView.setChipList(listChip);
            } else {
                chipView.setVisibility(View.GONE);
            }*/

        }

        /*public void toDetail(int position, Context context){

            RelatedPekerjaan pekerjaan = relatedPekerjaanList.get(position);
            //Toast.makeText(this,"Clicked"+.get(position).getName(), Toast.LENGTH_SHORT).get.show();
            DetailPekerjaanActivity.move( context ,pekerjaan.getId(),pekerjaan.getSlug());
        }*/

    }
}
