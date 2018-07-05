package id.teknologi.teknologiid.feature.Question;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.SearchView;
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

public class QuestionListActivity extends BaseActivity implements QuestionView, RecyclerInterface {

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
        questionListPresenter = new QuestionListPresenter(this);
        questionListPresenter.getQuestionList();
        questionListAdapter = new QuestionListAdapter(this, questionListModels, this);

    }

    @Override
    protected void setupView() {
        rvQuestionList.setLayoutManager(AppUtils.defaultLinearLayoutManager(this));
        rvQuestionList.setAdapter(questionListAdapter);

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

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getLayoutInflater().inflate(R.layout.activity_main_question, (ViewGroup) menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {

                        String userInput = s.toLowerCase();
                        List<QuestionListModel> newList = new ArrayList<>();

                        for (QuestionListModel qlModel : questionListModels) {

                            if (qlModel.getTitle().toLowerCase().contains(userInput)) {

                                newList.add(qlModel);

                            }

                        }
                        questionListAdapter.updateList(newList);
                        return true;
                    }
                });


        return true;
    }



}