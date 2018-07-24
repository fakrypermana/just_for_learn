package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import id.teknologi.teknologiid.model.ThreadCommentOfCommentModel;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class ThreadsCommentOfCommentAdapter extends BaseRecyclerAdapter<ThreadCommentOfCommentModel, ThreadsCommentOfCommentAdapter.COCVH> {

    public ThreadsCommentOfCommentAdapter(Context context, List<ThreadCommentOfCommentModel> threadCommentOfCommentModels, RecyclerInterface recyclerCallback) {
        super(context, threadCommentOfCommentModels,recyclerCallback);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_comment_of_comment_thread;
    }

    @Override
    public ThreadsCommentOfCommentAdapter.COCVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new COCVH(initView(viewType, parent),getRecyclerCallback());
    }

    public class COCVH extends BaseViewHolder<ThreadCommentOfCommentModel> implements RecyclerInterface{

        @BindView(R.id.iv_user)
        ImageView ivUser;
        @BindView(R.id.tv_user)
        TextView tvUser;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.wv_coc)
        WebView wvCoc;

        public COCVH(View itemView, RecyclerInterface recyclerCallback) {
            super(itemView,recyclerCallback);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(ThreadCommentOfCommentModel threadCommentOfCommentModel){

            Glide.with(itemView)
                    .load(threadCommentOfCommentModel.getComment_of_comment_url_photo())
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivUser);
            tvUser.setText(threadCommentOfCommentModel.getComment_of_comment_name());

            String data = threadCommentOfCommentModel.getComment_of_comment_comment();
            wvCoc.loadData(data,"text/html", "UTF-8");
            wvCoc.setWebViewClient(new WebViewClient());
            wvCoc.setPadding(50,50,50,50);
            WebSettings webSettings = wvCoc.getSettings();
            webSettings.setJavaScriptEnabled(true);
        }

        @Override
        public void onRecyclerItemClicked(int position) {

        }
    }
}
