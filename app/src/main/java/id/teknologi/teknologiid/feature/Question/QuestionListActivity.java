package id.teknologi.teknologiid.feature.Question;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

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
    List<QuestionListModel> questionList = new ArrayList<>();

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
        questionListAdapter = new QuestionListAdapter(this, questionList,this);

    }

    @Override
    protected void setupView() {
        rvQuestionList.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvQuestionList.setAdapter(questionListAdapter);

    }

    @Override
    public void onSuccessQuestion(List<QuestionListModel> questionList) {
        Log.d("QUESTION LIST", new Gson().toJson(questionList));
        questionListAdapter.insertAndNotify(questionList);

    }

    @Override
    public void onLoading(boolean isLoading) {
        Log.d("QUESTION LIST","LOADING"+isLoading);

    }

    @Override
    public void onFailed(String message) {
        Log.d("QUESTION LIST","ERROR");

    }

    @Override
    public void onRecyclerItemClicked(int position) {
        QuestionListModel question= questionList.get(position);
        Toast.makeText(this,"Clicked"+questionList.get(position).getTitle(),Toast.LENGTH_SHORT).show();
//        QuestionDetailActivity.start

    }
}
