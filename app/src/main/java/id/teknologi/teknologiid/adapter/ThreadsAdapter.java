package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class ThreadsAdapter extends BaseRecyclerAdapter<Thread, ThreadsAdapter.ThreadVH>{

    public ThreadsAdapter(Context context, List<Thread> threadList, RecyclerInterface recyclerCallback) {
        super(context, threadList, recyclerCallback);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_list_thread;
    }

    @Override
    public ThreadVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThreadVH(initView(viewType, parent), getRecyclerCallback());
    }

    class ThreadVH extends BaseViewHolder<Thread> {

        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ThreadVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView, recyclerInterface);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBind(Thread thread) {
            Glide.with(itemView)
                    .load(thread.getUrl_cover())
                    .into(ivCover);
            Log.d("Thread",thread.getUrl_cover());
            tvTitle.setText(thread.getTitle());
        }
    }
}
