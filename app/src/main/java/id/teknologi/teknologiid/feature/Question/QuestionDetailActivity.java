package id.teknologi.teknologiid.feature.Question;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.QuestionDetailModel;

public class QuestionDetailActivity extends BaseActivity implements QuestionDetailView {

    QuestionDetailPresenter detailPresenter;

    //QuestionDetailModel detailModels;
    private final static String ID = "ID";
    private final static String SLUG = "SLUG";
    private int id;
    private String slug;
    QuestionDetailModel questionDetailModel;
    @BindView(R.id.wv_question)
    WebView wvQuestion;
    @BindView(R.id.tvdetail_voted)
    TextView tvVoted;
    @BindView(R.id.tvdetail_username)
    TextView tvUsername;
    @BindView(R.id.tvdetail_date)
    TextView tvDate;
    @BindView(R.id.tvdetail_tittle)
    TextView tvTittle;
    @BindView(R.id.ivdetail_user_profpict)
    ImageView ivUserProfilePict;
    

    @Override
    protected int contentView() {
        return R.layout.item_detail_question;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        detailPresenter=new QuestionDetailPresenter(this);
        Intent intent = getIntent();
        id = intent.getIntExtra(ID, 0);
        slug = intent.getStringExtra(SLUG);
        detailPresenter.getQuestionDetail(id, slug);
//        detailAdapter= new QuestionDetailAdapter(this, detailModels,this);

    }

    @Override
    protected void setupView() {




    }

    public static void start(QuestionListActivity questionListActivity, int id, String slug) {
        Intent intent = new Intent(questionListActivity, QuestionDetailActivity.class);
        intent.putExtra(ID, id);
        intent.putExtra(SLUG, slug);
        questionListActivity.startActivity(intent);
    }

    @Override
    public void onLoading(boolean isLoading) {

    }

    @Override
    public void onFailed(String message) {

    }

//    @Override
//    public void onSuccessQuestionDetail(List<QuestionDetailModel> questionDetailModels) {
//
//    }

    @Override
    public void onSuccessQuestionDetail(QuestionDetailModel questionDetailModels) {
        Log.d("QUESTION Detail", new Gson().toJson(questionDetailModels));
        this.questionDetailModel=questionDetailModels;
        setDetailView();
        String data = questionDetailModels.getQuestion();
        wvQuestion.loadData(data,"text/html", "UTF-8");

//        Glide.with()
//                .load(questionDetailModels.getUser_url_photo())
//                .into(ivUserProfilePict);
    }
    public void setDetailView(){
        tvTittle.setText(questionDetailModel.getTitle());
        tvDate.setText(questionDetailModel.getCreated_at());

        tvUsername.setText(questionDetailModel.getUser_name());
//        tvTag.setText(questionDetailModel.getTags());
        tvVoted.setText(""+questionDetailModel.getUpvote());
        wvQuestion.setWebViewClient(new WebViewClient());
        wvQuestion.setPadding(50,50,50,50);
        WebSettings webSettings = wvQuestion.getSettings();
        webSettings.setJavaScriptEnabled(true);

        

    }
}
