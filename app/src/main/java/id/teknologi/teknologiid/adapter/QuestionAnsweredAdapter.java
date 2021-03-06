package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.feature.Question.QuestionDetailPresenter;
import id.teknologi.teknologiid.model.QuestionAnsweredModel;
import id.teknologi.teknologiid.model.QuestionCommentModel;
import id.teknologi.teknologiid.model.QuestionDetailModel;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class QuestionAnsweredAdapter extends BaseRecyclerAdapter<QuestionAnsweredModel,QuestionAnsweredAdapter.QuestionAnsweredVH> {
    public QuestionAnsweredAdapter(Context context, List<QuestionAnsweredModel> questionAnsweredModels, RecyclerInterface recyclerCallback) {
        super(context, questionAnsweredModels, recyclerCallback);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_answer_question;
    }

    @Override
    public QuestionAnsweredVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuestionAnsweredVH(initView(viewType, parent),getRecyclerCallback());
    }

    public class QuestionAnsweredVH extends BaseViewHolder<QuestionAnsweredModel> implements RecyclerInterface {

        @BindView(R.id.wvaq_answer_question)
        WebView wvAnswer;
        @BindView(R.id.tvaq_date)
        TextView tvDate;
        @BindView(R.id.tvaq_user_name)
        TextView tvUsername;
        @BindView(R.id.ivaq_user_profpict)
        ImageView ivAnswerProfpict;
        @BindView(R.id.rv_comment_question)
        RecyclerView rvComment;
        QuestionCommentAdapter commentAdapter;
        List<QuestionCommentModel> commentModels = new ArrayList<>();



        public QuestionAnsweredVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView, recyclerInterface);
            ButterKnife.bind(this,itemView);

        }

        @Override
        public void onBind(QuestionAnsweredModel questionAnsweredModel) {
            Log.d("answer masuk","answer masuk"+questionAnsweredModel.getAnswer());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.smile);
            requestOptions.error(R.drawable.smile);

            Glide.with(itemView)
                    .setDefaultRequestOptions(requestOptions)
                    .load(questionAnsweredModel.getUser_url_photo())
                    .into(ivAnswerProfpict);
            String data = questionAnsweredModel.getAnswer();
            wvAnswer.loadData(data,"text/html", "UTF-8");
            wvAnswer.setWebViewClient(new WebViewClient());
            wvAnswer.setPadding(50,50,50,50);
            WebSettings webSettings = wvAnswer.getSettings();
            webSettings.setJavaScriptEnabled(true);
            tvUsername.setText(questionAnsweredModel.getUser_name());
            Log.d("username answer", "onBind: "+questionAnsweredModel.getUser_name());
            tvDate.setText(questionAnsweredModel.getCreated_at());

            commentAdapter= new QuestionCommentAdapter(context, commentModels,this);
            rvComment.setLayoutManager(AppUtils.defaultLinearLayoutManager(context));
            rvComment.setAdapter(commentAdapter);
            commentAdapter.insertAndNotify(questionAnsweredModel.getComments());

            }

        @Override
        public void onRecyclerItemClicked(int position) {

        }
    }


}
