package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.model.QuestionListModel;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class QuestionListAdapter extends BaseRecyclerAdapter<QuestionListModel, QuestionListAdapter.QuestionListVH>  {
    public QuestionListAdapter(Context context, List<QuestionListModel> questionList, RecyclerInterface recyclerCallback) {
        super(context, questionList, recyclerCallback);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_list_question ;
    }

    @Override
    public QuestionListVH onCreateViewHolder(ViewGroup parent, int viewType) {

        return new QuestionListVH(initView(viewType, parent),getRecyclerCallback());
    }

    public class QuestionListVH extends BaseViewHolder<QuestionListModel> {

        @BindView(R.id.iv_user_profile_pict)
        ImageView iv_userProfilePict;

//        @BindView(R.id.btn_addQuestion)
//        Button btnAddQuestion;
//        @BindView(R.id.ib_answered)
//        ImageButton imageButtonAnsered;
//        @BindView(R.id.ib_favorite)
//        ImageButton imageButtonFavorite;
        @BindView(R.id.tv_date)
        TextView tvDate;
        @BindView(R.id.tv_question)
        TextView tvQuestion;
        @BindView(R.id.tv_tag)
        TextView tvTag;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
//        @BindView(R.id.spin_filter)
//        Spinner spinFilter;
        @BindView(R.id.tv_voted)
        TextView tvVoted;



        public QuestionListVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView, recyclerInterface);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(QuestionListModel questionList) {
            Glide.with(itemView)
                    .load(questionList.getUser_url_photo())
                    .into(iv_userProfilePict);
            Log.d("QuestionList", questionList.getUser_url_photo());
            tvDate.setText(questionList.getCreated_at());
            tvQuestion.setText(questionList.getTitle());
            tvTag.setText(questionList.getTags().toArray().toString());
            tvUserName.setText(questionList.getUser_name());
            tvVoted.setText(""+questionList.getUpvote());





        }
    }
}
