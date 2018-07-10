package id.teknologi.teknologiid.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.plumillonforge.android.chipview.Chip;
import com.plumillonforge.android.chipview.ChipView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindFont;
import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseRecyclerAdapter;
import id.teknologi.teknologiid.base.BaseViewHolder;
import id.teknologi.teknologiid.feature.Tag;
import id.teknologi.teknologiid.model.QuestionListModel;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class QuestionListAdapter extends BaseRecyclerAdapter<QuestionListModel, QuestionListAdapter.QuestionListVH> implements Filterable {

    private final Context context;
    private List<QuestionListModel> defaulList;
    private List<QuestionListModel>filteredList;
    public MyFilter myFilter;

    public QuestionListAdapter(Context context, List<QuestionListModel> questionList, RecyclerInterface recyclerCallback) {
        super(context, questionList, recyclerCallback);
        this.context=context;
        this.defaulList= questionList;
        this.filteredList = new ArrayList<QuestionListModel>();
    }

    @Override
    public Filter getFilter(){

        if(myFilter == null){
            filteredList.clear();
            filteredList.addAll(this.defaulList);
            myFilter=new QuestionListAdapter.MyFilter(this,filteredList);
        }
        return myFilter;
    }

    @Override
    protected int getResLayout(int type) {
        return R.layout.item_list_question ;
    }

    @Override
    public QuestionListVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_list_question,parent,false);
//        return new Holderview(layout);

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
//        @BindView(R.id.tv_tag)
//        TextView tvTag;
        @BindView(R.id.tv_user_name)
        TextView tvUserName;
//        @BindView(R.id.spin_filter)
//        Spinner spinFilter;
        @BindView(R.id.tv_voted)
        TextView tvVoted;
        @BindView(R.id.iv_isAnswered)
        ImageView ivIsAnswered;
        @BindView(R.id.chv_tag)
        ChipView chvTag;

        ChipAdapter chipAdapter;


        public QuestionListVH(View itemView, RecyclerInterface recyclerInterface) {
            super(itemView, recyclerInterface);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onBind(QuestionListModel questionList) {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.smile);
            requestOptions.error(R.drawable.smile);

            Glide.with(itemView)
                    .setDefaultRequestOptions(requestOptions)
                    .load(questionList.getUser_url_photo())
                    .into(iv_userProfilePict);
//            Glide.with(itemView)
//                    .load(questionList.getUser_url_photo())
//                    .into(iv_userProfilePict);
            Log.d("QuestionList", questionList.getUser_url_photo());
            tvDate.setText(questionList.getCreated_at());
            tvQuestion.setText(questionList.getTitle());
//            tvTag.setText(questionList.getTags().toArray().toString());
            tvUserName.setText(questionList.getUser_name());
            tvVoted.setText(""+questionList.getUpvote());
//            for (List<String> i = questionList.getTags(); i.hasNext();) {
//                String item = i.next();
//                System.out.println(item);
//            }
            Boolean isAnswered=questionList.getIs_Answered();
            if(isAnswered==false){
                ivIsAnswered.setVisibility(View.INVISIBLE);
            }

            List<Chip> listChip = new ArrayList<>();
            if (questionList.getTags() != null)
            {

                List<String> listTags = new ArrayList<>(questionList.getTags());
                for (String tag : listTags
                        ) {
                    listChip.add(new Tag(tag));
                }

                //chipView.setAdapter(adapter);
                //chipView.setChipBackgroundColor(R.color.colorAccent);
                chvTag.setChipList(listChip);
            } else {
                chvTag.setVisibility(View.GONE);
            }


            }


        }

    private static class MyFilter extends Filter {

        private final QuestionListAdapter adapter;
        private final List<QuestionListModel> originalList;
        private final List<QuestionListModel> filteredList;


        private MyFilter(QuestionListAdapter adapter, List<QuestionListModel> originalList) {

            this.adapter = adapter;
            this.originalList = originalList;
            this.filteredList = new ArrayList<QuestionListModel>();

        }

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            filteredList.clear();
            final FilterResults results = new FilterResults();
            if (charSequence.length() == 0){
                filteredList.addAll(originalList);
            }else {
                final String filterPattern = charSequence.toString().toLowerCase().trim();
                for ( QuestionListModel qlModel : originalList){
                    if (qlModel.getUser_name().toLowerCase().contains(filterPattern)||
                            qlModel.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(qlModel);
                    }
                }
            }

            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            adapter.defaulList.clear();
            adapter.defaulList.addAll((ArrayList<QuestionListModel>)filterResults.values);
            adapter.notifyDataSetChanged();

        }
    }


}
