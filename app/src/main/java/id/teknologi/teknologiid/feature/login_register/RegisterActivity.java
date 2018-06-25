package id.teknologi.teknologiid.feature.login_register;

import android.app.ProgressDialog;
import android.os.UserManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.network.DataManager;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.input_username_regist)
    EditText edtusername = null;
    @BindView(R.id.input_nama_regist)
    EditText edtname = null;
    @BindView(R.id.input_email_regist)
    EditText edtemail = null;
    @BindView(R.id.input_password_regist)
    EditText edtpass = null;
    @BindView(R.id.input_conpassword_regist)
    EditText edtconfpass = null;
    @BindView(R.id.btn_register)
    Button btnRegist = null;

    private ProgressDialog progressDialog;

    @Override
    protected int contentView() {
        return R.layout.activity_register;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

        initControls();

        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showProgressDialog();

                String username = edtusername.getText().toString();
                String name = edtname.getText().toString();
                String email = edtemail.getText().toString();
                String password = edtpass.getText().toString();
                String confpass = edtconfpass.getText().toString();

                // Use default converter factory, so parse response body text to okhttp3.ResponseBody object.
                Call<ResponseBody> call = DataManager.getUserManagerService(null).registUser(username,name,email,
                        password);

                // Create a Callback object, because we do not set JSON converter, so the return object is ResponseBody be default.
                retrofit2.Callback<ResponseBody> callback = new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        hideProgressDialog();

                        StringBuffer messageBuffer   = new StringBuffer();
                        int statusCode = response.code();
                        if(statusCode == 200)
                        {
                            try{
                                //get return String
                                String returnBodyText = response.body().string();

                                // Because return text is a json format string, so we should parse it manually.
                                Gson gson = new Gson();

                                TypeToken<List<RegisterResponse>> typeToken = new TypeToken<List<RegisterResponse>>(){};

                                // Get the response data list from JSON string.
                                List<RegisterResponse> registerResponseList = gson.fromJson(returnBodyText, typeToken.getType());

                                if(registerResponseList!=null && registerResponseList.size() > 0) {

                                    RegisterResponse registerResponse = registerResponseList.get(0);

                                    if (registerResponse.isSuccess()) {
                                        messageBuffer.append(registerResponse.getMessage());
                                    } else {
                                        messageBuffer.append("User register failed.");
                                    }
                                }

                            }catch (IOException ex)
                            {
                                Log.e("TAG", ex.getMessage());
                            }
                        }
                        else
                        {
                            // If server return error.
                            messageBuffer.append("Server return error code is ");
                            messageBuffer.append(statusCode);
                            messageBuffer.append("\r\n\r\n");
                            messageBuffer.append("Error message is ");
                            messageBuffer.append(response.message());
                        }

                        // Show a Toast message.
                        Toast.makeText(getApplicationContext(), messageBuffer.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        hideProgressDialog();

                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    }
                };

                // Use callback object to process web server return data in asynchronous mode.
                call.enqueue(callback);

            }
        });

    }

    private void initControls() {
        if(edtusername==null)
        {
            edtusername = findViewById(R.id.input_username_regist);
        }
        if (edtname==null)
        {
            edtname = findViewById(R.id.input_nama_regist);
        }
        if (edtemail==null)
        {
            edtemail = findViewById(R.id.input_email_regist);
        }
        if (edtpass == null)
        {
            edtpass = findViewById(R.id.input_password_regist);
        }
        if (btnRegist == null)
        {
            btnRegist = findViewById(R.id.btn_register);
        }
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(RegisterActivity.this);
        }
    }

    /* Show progress dialog. */
    private void showProgressDialog()
    {
        // Set progress dialog display message.
        progressDialog.setMessage("Please Wait");

        // The progress dialog can not be cancelled.
        progressDialog.setCancelable(true);

        // Show it.
        progressDialog.show();
    }

    /* Hide progress dialog. */
    private void hideProgressDialog()
    {
        // Close it.
        progressDialog.hide();
    }

    @Override
    protected void setupView() {

    }

}
