package id.teknologi.teknologiid.feature.Question;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.QuestionDetailAdapter;
import id.teknologi.teknologiid.adapter.QuestionListAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.QuestionDetailModel;
import id.teknologi.teknologiid.model.QuestionListModel;

import static android.os.Build.ID;

public class QuestionDetailActivity extends BaseActivity implements QuestionDetailView {

    QuestionDetailPresenter detailPresenter;
    QuestionDetailAdapter detailAdapter;
    List<QuestionDetailModel> detailModels = new ArrayList<>();
    private final static String ID = "ID";
    private final static String SLUG = "SLUG";
    private int id;
    private String slug;

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
        detailAdapter= new QuestionDetailAdapter(this, detailModels,this);

    }

    @Override
    protected void setupView() {

    }

    public static void start(QuestionListActivity questionListActivity, int id, String slug) {
    }

    @Override
    public void onLoading(boolean isLoading) {

    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    public void onSuccessQuestionDetail(List<QuestionDetailModel> questionDetailModels) {

    }
}
