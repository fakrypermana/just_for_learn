package id.teknologi.teknologiid.feature.login_register.fragment_register;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import id.teknologi.teknologiid.R;


public class DataRegistFragment extends Fragment {
    @BindView(R.id.btn_lanjut)
    Button btnLanjut;

    public DataRegistFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_regist, container, false);

    }
}
