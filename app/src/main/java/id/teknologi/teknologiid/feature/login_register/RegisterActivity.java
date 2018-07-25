package id.teknologi.teknologiid.feature.login_register;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.UserManager;
import android.provider.ContactsContract;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.badoualy.stepperindicator.StepperIndicator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.CustomViewPager;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.login_register.fragment_register.DataRegistFragment;
import id.teknologi.teknologiid.feature.login_register.fragment_register.DataRegistJobMinatFragment;
import id.teknologi.teknologiid.feature.profile.ViewPagerAdapter;
import id.teknologi.teknologiid.model.Job;
import id.teknologi.teknologiid.network.ApiService;
import id.teknologi.teknologiid.network.DataManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.pager_registration)
    CustomViewPager pagerRegist;
    @BindView(R.id.step_indicator)
    StepperIndicator stepIndic;


    @Override
    protected int contentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

        setupViewPager(pagerRegist);
        pagerRegist.disableScroll(true);
        //stepIndicator

        stepIndic.setViewPager(pagerRegist, true);
        stepIndic.setStepCount(2);
        stepIndic.setCurrentStep(0);

        stepIndic.addOnStepClickListener(new StepperIndicator.OnStepClickListener() {
            @Override
            public void onStepClicked(int step) {
                pagerRegist.setCurrentItem(step, true);
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new DataRegistFragment(), "step 1");
        adapter.addFragment(new DataRegistJobMinatFragment(), "step 2");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void setupView() {

    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        pagerRegist.setCurrentItem(item, smoothScroll);
    }

}
