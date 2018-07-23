package id.teknologi.teknologiid.feature.login_register.fragment_register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.plumillonforge.android.chipview.ChipView;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.feature.login_register.RegisterActivity;

public class DataRegistJobMinatFragment extends Fragment {
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_regist)
    Button btnRegist;
    Spinner spnListJob;
    ChipView chipTagMinat;

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

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RegisterActivity)getActivity()).setCurrentItem (1, true);
            }
        });

        return view;
    }

}
