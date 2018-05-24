package id.teknologi.teknologiid.utils;

import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import id.teknologi.teknologiid.base.BaseException;
import id.teknologi.teknologiid.base.BaseResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
//import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by galihgasur on 10/9/17.
 */

public class RxUtils {
    public static <T> ObservableTransformer<T, T> applyScheduler() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<T, T> applyApiCall() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(throwable -> {
                    Logger.log(throwable);
                    if (throwable instanceof HttpException) {
                        try {
                            HttpException httpException = (HttpException) throwable;
                            String res = httpException.response().errorBody().string();
                            Log.d("connection error",res);
                            Gson gson = new Gson();
                            BaseResponse baseResponse = gson.fromJson(res, BaseResponse.class);
                            BaseException e = new BaseException(baseResponse, httpException.response().code());
                            return Observable.error(e);
                        } catch (IOException e) {
                            return Observable.empty();
                        }
                    } else
                        return Observable.empty();
                });
    }

    public static void checkMainThread() {
        boolean isMainThread = Looper.myLooper() == Looper.getMainLooper();
        Logger.log(Log.DEBUG, "RX___ Is main thread :" + isMainThread);
    }

    public static <T> ObservableTransformer<T, MapWithIndex.Indexed<T>> mapWithIndex() {
        return MapWithIndex.instance();
    }
}
