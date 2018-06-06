package id.teknologi.teknologiid.feature.Question;

import java.util.List;

import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.model.QuestionListModel;


public interface QuestionView extends BaseView {
    void onSuccessQuestion(List<QuestionListModel> questionListModels);
}
