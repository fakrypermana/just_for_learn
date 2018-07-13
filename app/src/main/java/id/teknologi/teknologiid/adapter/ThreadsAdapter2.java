package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.plumillonforge.android.chipview.Chip;
import com.plumillonforge.android.chipview.ChipView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.feature.Tag;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class ThreadsAdapter2 extends BaseRecyclerAdapter<Thread, ThreadsAdapter2.ThreadVH2> {

    public ThreadsAdapter2(Context context, List<Thread> threadList, RecyclerInterface recyclerCallback) {
        super(context, threadList, recyclerCallback);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_list_thread2;
    }

    @Override
    public ThreadsAdapter2.ThreadVH2 onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThreadsAdapter2.ThreadVH2(initView(viewType, parent), getRecyclerCallback());
    }

    class ThreadVH2 extends BaseViewHolder<Thread> {


        @BindView(R.id.tv_upvote)
        TextView tvUpvote;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_user_name)
        TextView tvUser_name;
        @BindView(R.id.tv_created_at)
        TextView tvCreated_at;
        @BindView(R.id.tv_user_work)
        TextView tvUser_work;
        @BindView(R.id.tv_comments)
        TextView tvComments;
        @BindView(R.id.tv_views)
        TextView tvViews;
        @BindView(R.id.iv_user_url_photo)
        ImageView ivUser_url_photo;
        @BindView(R.id.chip_tags_thread)
        ChipView chipView;
        ChipAdapter adapter;


        public ThreadVH2(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView, recyclerInterface);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(Thread thread) {


            Log.d("Thread",thread.getUser_url_photo());
            tvUpvote.setText(thread.getUpvote());
            tvTitle.setText(thread.getTitle());
            tvUser_name.setText(thread.getUsername());
            tvCreated_at.setText(thread.getCreated_at());
            tvUser_work.setText(thread.getUser_work());

            tvComments.setText(String.valueOf(thread.getComments() +" jawaban"));
            tvViews.setText(String.valueOf(thread.getViews()+" dilihat"));
            Glide.with(itemView)
                    .load(thread.getUser_url_photo())
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivUser_url_photo);

            //chip
            List<Chip> listChip = new ArrayList<>();
            if (thread.getTopics() != null)
            {

                List<String> listTags = new ArrayList<>(thread.getTopics());
                for (String tag : listTags
                        ) {
                    listChip.add(new Tag(tag));
                }

                chipView.setChipList(listChip);
            } else {
                chipView.setVisibility(View.GONE);
            }

        }
    }


}
