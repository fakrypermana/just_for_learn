package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.model.QuestionCommentModel;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class QuestionCommentAdapter extends BaseRecyclerAdapter<QuestionCommentModel, QuestionCommentAdapter.QuestionCommentVH> {

    public QuestionCommentAdapter(Context context, List<QuestionCommentModel> questionCommentModels, RecyclerInterface recyclerInterface) {
        super(context, questionCommentModels,recyclerInterface);
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_comment_answer_question;
    }

    @Override
    public QuestionCommentAdapter.QuestionCommentVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuestionCommentVH(initView(viewType, parent),getRecyclerCallback());
    }

    public class QuestionCommentVH extends BaseViewHolder<QuestionCommentModel> {

        @BindView(R.id.tvco_comment)
        TextView tvComment;
//        @BindView(R.id.tvco)

        public QuestionCommentVH(View itemView, RecyclerInterface recyclerCallback) {
            super(itemView,recyclerCallback);
        }

        @Override
        public void onBind(QuestionCommentModel questionCommentModel) {

        }
    }
}
