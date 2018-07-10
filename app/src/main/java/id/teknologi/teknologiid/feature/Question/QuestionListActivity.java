package id.teknologi.teknologiid.feature.Question;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.adapter.QuestionListAdapter;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.thread.ThreadActivity;
import id.teknologi.teknologiid.feature.thread_new.ThreadNewActivity3;
import id.teknologi.teknologiid.model.QuestionListModel;
import id.teknologi.teknologiid.utils.AppUtils;
import id.teknologi.teknologiid.utils.RecyclerInterface;

public class QuestionListActivity extends  BaseActivity implements QuestionView, RecyclerInterface {

    QuestionListPresenter questionListPresenter;
    QuestionListAdapter questionListAdapter;
    List<QuestionListModel> questionListModels = new ArrayList<>();

    @BindView(R.id.rv_QuestionList)
    RecyclerView rvQuestionList;

    @BindView(R.id.action_search_view)
    SearchView searchView;
    @BindView(R.id.fab_question_create)
    FloatingActionButton fabCreateQuestion;



    @Override
    protected int contentView() {
        return R.layout.activity_main_question;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        questionListPresenter = new QuestionListPresenter(this);
        questionListPresenter.getQuestionList();
        questionListAdapter = new QuestionListAdapter(this, questionListModels, this);
        fabCreateQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuestionListActivity.this, QuestionCreateActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void setupView() {
        rvQuestionList.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvQuestionList.setAdapter(questionListAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                questionListAdapter.getFilter().filter(query);

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                questionListAdapter.getFilter().filter(newText);
                return true;
            }
        });

    }


    @Override
    public void onSuccessQuestion(List<QuestionListModel> questionListModels) {
        Log.d("QUESTION LIST", new Gson().toJson(questionListModels));
        questionListAdapter.insertAndNotify(questionListModels);


    }

    @Override
    public void onLoading(boolean isLoading) {
        Log.d("QUESTION LIST", "LOADING" + isLoading);

    }

    @Override
    public void onFailed(String message) {
        Log.d("QUESTION LIST", "ERROR");

    }

    @Override
    public void onRecyclerItemClicked(int position) {
        QuestionListModel question = questionListModels.get(position);
        Toast.makeText(this, "Clicked" + questionListModels.get(position).getTitle(), Toast.LENGTH_SHORT).show();
        QuestionDetailActivity.start(this, question.getId(), question.getSlug());

    }



}