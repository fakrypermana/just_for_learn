package id.teknologi.teknologiid.feature.login_register.fragment_register;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.plumillonforge.android.chipview.ChipView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.ResponseArray;
import id.teknologi.teknologiid.feature.login_register.RegisterActivity;
import id.teknologi.teknologiid.feature.login_register.RegisterJobPresenter;
import id.teknologi.teknologiid.feature.login_register.RegisterJobView;
import id.teknologi.teknologiid.model.Job;
import id.teknologi.teknologiid.network.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataRegistJobMinatFragment extends Fragment implements RegisterJobView{
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    Spinner spnListJob;
    ChipView chipTagMinat;
    RegisterJobPresenter presenter;
    Job job;
    //List<Job> jobList = new ArrayList<>();
    ApiService apiService;
    Context mContext;

    public DataRegistJobMinatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_data_regist_job_minat, container, false);

        btnBack = view.findViewById(R.id.btn_back);
        spnListJob = view.findViewById(R.id.spn_list_pekerjaan_regist);
        chipTagMinat = view.findViewById(R.id.chip_tags_filter_regist);
        btnRegist = view.findViewById(R.id.btn_regist);

        presenter = new RegisterJobPresenter(this);
        presenter.getJob();

        //List<Job> jobList = new ArrayList<>();

        //Log.d("joboy",new Gson().toJson(presenter));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegisterActivity)getActivity()).setCurrentItem (0, true);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onSuccessJob(List<Job> jobList) {
        //int i = 0;
        //String[] jobItems = new String[jobList.size()];


        //List<Job> jobList = new ArrayList<>();
        List<Job> jobUhuy = new ArrayList<>(jobList);
        Log.d("jobitem","uhuy"+jobUhuy.toString());
        List<String> jobListItem = new ArrayList<String>();

        for (int j = 0; j < jobUhuy.size(); j++) {
            jobListItem.add(jobUhuy.get(j).getName());
        }

        ArrayAdapter<String> spinnerArrayAdapterBudget = new ArrayAdapter<>(getActivity().getBaseContext(),
                android.R.layout.simple_spinner_item,jobListItem);
        spinnerArrayAdapterBudget.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnListJob.setAdapter(spinnerArrayAdapterBudget);

        spnListJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity().getApplicationContext(), "diClick" + selectedName, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        //Log.d("aku harus apa","lieur"+jobListItem);
        /*for (Job job:jobList) {
            jobItems[i].add = job.getName();
            i++;
        }*/
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, jobListItem);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnListJob.setAdapter(adapter);*/
        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, jobItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnListJob.setAdapter(adapter);*/
        Log.d("pasti ayaan",new Gson().toJson(jobList));
    }

    @Override
    public void onLoading(boolean isLoading) {

    }

    public void setView(){
    }

    @Override
    public void onFailed(String message) {

    }


}
