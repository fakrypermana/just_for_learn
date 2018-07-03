package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.model.QuestionCommentModel;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class QuestionCommentAdapter extends BaseRecyclerAdapter<QuestionCommentModel, QuestionCommentAdapter.QuestionCommentVH> {

    public QuestionCommentAdapter(Context context, List<QuestionCommentModel> questionCommentModels, RecyclerInterface recyclerCallback) {
        super(context, questionCommentModels,recyclerCallback);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_comment_answer_question;
    }

    @Override
    public QuestionCommentAdapter.QuestionCommentVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuestionCommentVH(initView(viewType, parent),getRecyclerCallback());
    }

    public class QuestionCommentVH extends BaseViewHolder<QuestionCommentModel> implements RecyclerInterface{

        @BindView(R.id.wvco_comment_question)
        WebView wvComment;
        @BindView(R.id.tvco_date)
        TextView tvDate;
        @BindView(R.id.tvco_user_name)
        TextView tvUsername;
        @BindView(R.id.ivco_user_profpict)
        ImageView ivCommentProfpict;

        public QuestionCommentVH(View itemView, RecyclerInterface recyclerCallback) {
            super(itemView,recyclerCallback);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(QuestionCommentModel questionCommentModel) {

            tvDate.setText(questionCommentModel.getCreated_at());
            tvUsername.setText(questionCommentModel.getUser_name());
            Glide.with(itemView)
                    .load(questionCommentModel.getUser_url_photo())
                    .into(ivCommentProfpict);
            String data = questionCommentModel.getComment();
            wvComment.loadData(data,"text/html", "UTF-8");
            wvComment.setWebViewClient(new WebViewClient());
            wvComment.setPadding(50,50,50,50);
            WebSettings webSettings = wvComment.getSettings();
            webSettings.setJavaScriptEnabled(true);


        }

        @Override
        public void onRecyclerItemClicked(int position) {

        }
    }
}
