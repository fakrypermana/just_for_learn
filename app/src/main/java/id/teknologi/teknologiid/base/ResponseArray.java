package id.teknologi.teknologiid.base;

import java.util.List;

import id.teknologi.teknologiid.model.QuestionListModel;

/**
 * Created by galihgasur on 10/9/17.
 */

public class ResponseArray<Model> extends BaseResponse{
    private List<Model> data;

    public List<QuestionListModel> getData() {
        return data;
    }
}
