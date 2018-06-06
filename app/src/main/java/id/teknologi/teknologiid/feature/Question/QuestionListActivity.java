package id.teknologi.teknologiid.feature.Question;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.QuestionListAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.QuestionListModel;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class QuestionListActivity extends BaseActivity implements QuestionView, RecyclerInterface{

    QuestionListPresenter questionListPresenter;
    QuestionListAdapter questionListAdapter;
    List<QuestionListModel> questionListModels = new ArrayList<>();

    @BindView(R.id.rv_QuestionList)
    RecyclerView rvQuestionList;



    @Override
    protected int contentView() {
        return R.layout.activity_main_question;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        questionListPresenter=new QuestionListPresenter(this);
        questionListPresenter.getQuestionList();
        questionListAdapter = new QuestionListAdapter(this, questionListModels,this);

    }

    @Override
    protected void setupView() {
        rvQuestionList.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvQuestionList.setAdapter(questionListAdapter);

    }

    @Override
    public void onSuccessQuestion(List<QuestionListModel> questionListModels) {

    }

    @Override
    public void onLoading(boolean isLoading) {

    }

    @Override
    public void onFailed(String message) {

    }

    @Override
    public void onRecyclerItemClicked(int position) {

    }
}
