package id.teknologi.teknologiid.feature.Question;

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

public class QuestionDetailActivity extends BaseActivity implements QuestionDetailView {

    QuestionDetailPresenter detailPresenter;
    QuestionDetailAdapter detailAdapter;
    List<QuestionDetailModel> detailModels = new ArrayList<>();

    @Override
    protected int contentView() {
        return R.layout.item_detail_question;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        detailPresenter=new QuestionDetailPresenter(this);
        detailPresenter.getQuestionDetail();
        detailAdapter= new QuestionDetailAdapter(this, detailModels,this);

    }

    @Override
    protected void setupView() {

    }

    public static void start(QuestionListActivity questionListActivity, int id, String slug) {
    }

    @Override
    public void onSuccessQuestionDetail(List<QuestionListModel> questionDetailModels) {
        Log.d("QUESTION LIST", new Gson().toJson(detailModels));
        detailAdapter.insertAndNotify(detailModels);

    }

    @Override
    public void onLoading(boolean isLoading) {

    }

    @Override
    public void onFailed(String message) {

    }
}
