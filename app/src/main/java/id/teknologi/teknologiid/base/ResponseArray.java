package id.teknologi.teknologiid.base;

import java.util.List;

/**
 * Created by galihgasur on 10/9/17.
 */

public class ResponseArray<Model> extends BaseResponse{
    private List<Model> data;

    public List<Model> getData() {
        return data;
    }
}
