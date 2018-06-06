package id.teknologi.teknologiid.feature.Question;

import android.os.Bundle;

import java.util.List;

import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.model.QuestionListModel;

public class QuestionActivity extends BaseActivity implements QuestionView {
    @Override
    protected int contentView() {
        return 0;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

    }

    @Override
    protected void setupView() {

    }

    @Override
    public void onSuccessQuestion(List<QuestionListModel> questionListModels) {

    }
}
