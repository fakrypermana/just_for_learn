package id.teknologi.teknologiid.feature.Question;

import java.util.List;

import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.model.QuestionAnsweredModel;

public interface QuestionAnsweredView extends BaseView {
    void onSuccessAnswer(List<QuestionAnsweredModel> questionAnsweredModels);
}
