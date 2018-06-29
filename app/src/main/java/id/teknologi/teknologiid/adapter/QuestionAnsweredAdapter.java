package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
import id.teknologi.teknologiid.model.QuestionAnsweredModel;
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




    public class QuestionAnsweredVH extends BaseViewHolder<QuestionAnsweredModel> {

        @BindView(R.id.tvaq_answer_question)
        TextView tvAnswer;
        @BindView(R.id.tvaq_date)
        TextView tvDate;
        @BindView(R.id.tvaq_user_name)
        TextView tvUsername;
        @BindView(R.id.ivaq_user_profpict)
        ImageView ivAnswerProfpict;


        public QuestionAnsweredVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView, recyclerInterface);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(QuestionAnsweredModel questionAnsweredModel) {
            tvAnswer.setText(questionAnsweredModel.getAnswer());
            tvDate.setText(questionAnsweredModel.getCreated_at());
            tvUsername.setText(questionAnsweredModel.getUser_name());
            Glide.with(itemView).
                    load(questionAnsweredModel.getUser_url_photo()).
                    into(ivAnswerProfpict);

        }
    }
}
