package id.teknologi.teknologiid.base;

/**
 * Created by galihgasur on 10/9/17.
 */

public class BaseException extends Exception {

    private final int mResponseCode;
    private final BaseResponse mBaseResponse;

    public BaseException(BaseResponse mBaseResponse) {
        this.mBaseResponse = mBaseResponse;
        this.mResponseCode = 200;
    }

    public BaseException(BaseResponse baseResponse, int responseCode) {
        this.mBaseResponse = baseResponse;
        this.mResponseCode = responseCode;
    }

    public BaseResponse getResponse() {
        return mBaseResponse;
    }

    public int getResponseCode() {
        return mResponseCode;
    }
}
