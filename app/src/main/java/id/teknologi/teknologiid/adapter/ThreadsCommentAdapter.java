package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.model.ThreadCommentModel;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class ThreadsCommentAdapter extends BaseRecyclerAdapter<ThreadCommentModel, ThreadsCommentAdapter.ThreadCommentVH > {

    public ThreadsCommentAdapter(Context context, List<ThreadCommentModel> threadCommentModelList, RecyclerInterface recyclerCallback){
        super(context, threadCommentModelList, recyclerCallback);
    }

    @Override
    protected int getResLayout(int type){
        return R.layout.item_comment_thread;
    }

    @Override
    public ThreadCommentVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ThreadCommentVH(initView(viewType, parent),getRecyclerCallback());
    }

    class ThreadCommentVH extends BaseViewHolder<ThreadCommentModel>{

        @BindView(R.id.iv_downvoteCT)
        ImageView ivUpvoteCT;
        @BindView(R.id.iv_user_url_photoCT)
        ImageView ivUserUrlPhotoCT;
        @BindView(R.id.tv_usernameCT)
        TextView tvUsernameCT;
        @BindView(R.id.tv_jobCT)
        TextView tvJobCT;
        @BindView(R.id.tv_createdAtCT)
        TextView tvCreatedAtCT;
        @BindView(R.id.wv_commentThread)
        TextView wvCommentThread;

        public ThreadCommentVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView, recyclerInterface);
            ButterKnife.bind(this,itemView);

        }

        @Override
        public void onBind(ThreadCommentModel threadCommentModel) {
            Log.d("comment masuk","comment masuk"+threadCommentModel.getComment());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.smile);
            requestOptions.error(R.drawable.smile);

            Glide.with(itemView)
                    .setDefaultRequestOptions(requestOptions)
                    .load(threadCommentModel.getCommenter_url_photo())
                    .into(ivUserUrlPhotoCT);
            tvUsernameCT.setText(threadCommentModel.getCommenter_name().toString());

            }


    }
}
