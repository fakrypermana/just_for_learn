package id.teknologi.teknologiid.feature.login_register;

import java.util.List;

import id.teknologi.teknologiid.base.BaseView;
import id.teknologi.teknologiid.model.Job;

public interface RegisterJobView extends BaseView{
    void onSuccessJob(List<Job> JobList);
}
