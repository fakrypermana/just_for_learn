package id.teknologi.teknologiid.feature.profile;

import java.util.List;

import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.model.Profile;

public interface ProfileView extends BaseView {
    void onSuccessProfile(Profile profileList);
}
