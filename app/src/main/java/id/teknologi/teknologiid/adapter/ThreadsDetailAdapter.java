package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.model.Thread;
import id.teknologi.teknologiid.utils.RecyclerInterface;


public class ThreadsDetailAdapter extends BaseRecyclerAdapter<Thread, ThreadsDetailAdapter.ThreadDetailVH>{

    public ThreadsDetailAdapter(Context context, List<Thread> threadDetileList, RecyclerInterface recyclerCallback){
        super(context,threadDetileList,recyclerCallback);
    }

    @Override
    protected int getResLayout(int type){
        return R.layout.list_comment;
    }

    @Override
    public ThreadDetailVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThreadDetailVH(initView(viewType, parent), getRecyclerCallback());
    }

    class ThreadDetailVH extends BaseViewHolder<Thread> {
        @BindView(R.id.tv_user_name)
        TextView tv_username;
        @BindView(R.id.tv_user_work)
        TextView tvUserwork;
        @BindView(R.id.iv_user_url_photo)
        TextView ivUser_url_photo;

        public ThreadDetailVH(View itemView, RecyclerInterface recyclerInterface){
            super(itemView,recyclerInterface);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(Thread thread){
//            Glide.with(itemView)
//                    .load(thread.getUser_url_photo())
//                    .apply(RequestOptions.circleCropTransform())
//                    .into(ivUser_url_photo);
            tv_username.setText(thread.getUsername());
            tvUserwork.setText(thread.getUser_work());
        }
    }
}

