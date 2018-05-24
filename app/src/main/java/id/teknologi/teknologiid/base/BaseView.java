package id.teknologi.teknologiid.base;

/**
 * Created by galihgasur on 10/1/17.
 */

public interface BaseView {
    void onLoading(boolean isLoading);
    void onFailed(String message);
}
