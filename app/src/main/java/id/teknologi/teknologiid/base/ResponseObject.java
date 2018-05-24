package id.teknologi.teknologiid.base;

/**
 * Created by galihgasur on 10/9/17.
 */

public class ResponseObject<Model> extends BaseResponse{
    private Model data;

    public Model getData() {
        return data;
    }
}
