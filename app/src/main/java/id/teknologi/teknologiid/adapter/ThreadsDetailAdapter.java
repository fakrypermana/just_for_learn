package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        return R.layout.item_comment_thread;
    }

    @Override
    public ThreadDetailVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThreadDetailVH(initView(viewType, parent), getRecyclerCallback());
    }

    class ThreadDetailVH extends BaseViewHolder<Thread> {


        public ThreadDetailVH(View itemView, RecyclerInterface recyclerInterface){
            super(itemView,recyclerInterface);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(Thread thread){

        }
    }
}

