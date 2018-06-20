package id.teknologi.teknologiid.feature.Question;

import java.util.List;

import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.model.QuestionDetailModel;
import id.teknologi.teknologiid.model.QuestionListModel;

public interface QuestionDetailView extends BaseView {
        void onSuccessQuestionDetail(List<QuestionDetailModel> questionDetailModels);
}

