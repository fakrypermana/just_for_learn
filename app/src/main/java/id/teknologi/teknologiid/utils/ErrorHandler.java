package id.teknologi.teknologiid.utils;

import android.util.Log;

import com.crashlytics.android.Crashlytics;

import id.teknologi.teknologiid.base.BaseException;
import id.teknologi.teknologiid.base.BaseResponse;
import retrofit2.HttpException;

/**
 * Created by galihgasur on 10/9/17.
 */

public class ErrorHandler {
    public static String handleError(Throwable throwable) {
        Logger.log(throwable);

        if (throwable == null) {
            Log.d("Connection","Error device network");
            return "Please check your network and try again.";
        }
        if (throwable instanceof BaseException) {

            BaseException rameException = (BaseException) throwable;
            BaseResponse baseResponse = rameException.getResponse();
            if (baseResponse != null) {
                String message = baseResponse.getMessage();
                if (!message.isEmpty()) {
                    Log.d("Connection","Error other: "+message);
                    return message;
                }
            }
        }

        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            if (httpException.code() == 403 || httpException.code() == 401) {
                forceLogout();
                Log.d("Connection","Error Permission");
                return "Please logout then re login";
            }
        }
        Log.d("Connection","Untracked error");
        Crashlytics.logException(throwable);
        return "Please check your network and try again.";
    }

    private static void forceLogout() {

    }
}
