package id.teknologi.teknologiid.feature.login_register;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.network.ApiService;
import id.teknologi.teknologiid.network.DataManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.input_email_login)
    EditText email;
    @BindView(R.id.input_password_login)
    EditText password;
    @BindView(R.id.link_login)
    TextView linkRegis;
    @BindView(R.id.btn_login)
    Button loginButton;

    Context mContext;
    private ApiService apiService;
    private ProgressDialog progressDialog;

    @Override
    protected int contentView() {
        return R.layout.activity_login;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {
        mContext = this;
        apiService = DataManager.getApiService();
        linkRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(LoginActivity.this);
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProgressDialog();
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                if(Email.equals("") || Password.equals("")){
                    email.setError("Email Tidak Boleh Kosong");
                    password.setError("Password Tidak Boleh Kosong");
                    return;
                }
                apiService.loginUser(Email,Password).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        progressDialog.hide();
                        if(response.isSuccessful()){
                            try{
                                String returnBodyText = response.body().string();
                                JSONObject jsonRESULTS = new JSONObject(returnBodyText);
                                if (jsonRESULTS.getString("status").equals("success")){
                                    Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                } else {
                                    String error_message = jsonRESULTS.getString("message");
                                    Log.d("message",error_message);
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Log.d("error",response.toString());
                            Toast.makeText(mContext,"Connection Error",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        progressDialog.hide();
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
    }

    @Override
    protected void setupView() {

    }

    private void showProgressDialog(){
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }
}
