package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.feature.Question.QuestionDetailActivity;
import id.teknologi.teknologiid.model.QuestionDetailModel;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class QuestionDetailAdapter extends BaseRecyclerAdapter<QuestionDetailModel,QuestionDetailAdapter.QuestionDetailVH> {
    public QuestionDetailAdapter(Context context, List<QuestionDetailModel> questionDetailModels, QuestionDetailActivity questionDetailActivity) {
        super(context, questionDetailModels);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_detail_question;
    }

    @Override
    public QuestionDetailVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuestionDetailVH(initView(viewType, parent),getRecyclerCallback());
    }

    public class QuestionDetailVH extends BaseViewHolder<QuestionDetailModel> {
//        @BindView(R.id.iv_user_profpict)
//        ImageView iv_userProfpict;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_tag)
        TextView tvTag;
        @BindView(R.id.tv_voted)
        TextView tvVoted;
        @BindView(R.id.tv_question)
        TextView tvQuestion;

        public QuestionDetailVH(View itemView, RecyclerInterface recyclerCallback) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(QuestionDetailModel questionDetailModel) {
        tvUserName.setText(questionDetailModel.getId_user());
        tvDate.setText(questionDetailModel.getCreated_at());
        tvTag.setText(questionDetailModel.getTags().toArray().toString());
        tvVoted.setText(""+questionDetailModel.getUpvote());
        tvQuestion.setText(questionDetailModel.getQuestion());
        }
    }
}
