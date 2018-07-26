package id.teknologi.teknologiid.feature.login_register;

import android.app.ActivityGroup;
import android.app.LocalActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import id.teknologi.teknologiid.R;
import id.teknologi.teknologiid.base.BaseActivity;
import id.teknologi.teknologiid.feature.profile.ProfileActivity;
import id.teknologi.teknologiid.network.ApiService;
import id.teknologi.teknologiid.network.DataManager;
import id.teknologi.teknologiid.network.TokenPreferences;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrevLoginRegistActivity extends BaseActivity{

    @BindView(R.id.sign_in_google)
    SignInButton signInGoogle;
    @BindView(R.id.sign_in_facebook)
    LoginButton loginFacebookButton;

    /**
     * hibah dari login
     */

    @BindView(R.id.input_email_login)
    EditText email;
    @BindView(R.id.input_password_login)
    EditText password;
    @BindView(R.id.link_regist)
    TextView linkRegis;
    @BindView(R.id.btn_login)
    Button loginButton;

    Context mContext;
    private ApiService apiService;
    private ProgressDialog progressDialog;

    TokenPreferences preferences;

    private static final int RC_SIGN_IN = 1;

    private GoogleApiClient mGoogleSignInClient;
    private CallbackManager mCallbackManager;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private static final String TAG = "Google Sign In";
    private static final String TAG_FACE = "FACEBOOK LOG";

    @Override
    protected int contentView() {
        return R.layout.activity_prev_registlogin;
    }

    @Override
    protected void setupData(Bundle savedInstanceState) {

        Hawk.init(PrevLoginRegistActivity.this).build();

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                        Toast.makeText(PrevLoginRegistActivity.this, "Error...", Toast.LENGTH_SHORT).show();

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (firebaseAuth.getCurrentUser() != null){

                    Intent intent =  new Intent(PrevLoginRegistActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    //finish();
                }

            }
        };

        signInGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signIn();

            }
        });

        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        loginFacebookButton.setReadPermissions("email", "public_profile");
        loginFacebookButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG_FACE, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
                Log.d(TAG_FACE, "token facebook" + loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG_FACE, "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG_FACE, "facebook:onError", error);
                // ...
            }
        });

        /**
         * hibah setup data dari login
         */

        mContext = this;
        apiService = DataManager.getApiService();
        linkRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrevLoginRegistActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        if(progressDialog == null) {
            progressDialog = new ProgressDialog(PrevLoginRegistActivity.this);
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
                        Log.d("azz", new Gson().toJson(response));
                            progressDialog.hide();
                            if(response.isSuccessful()){
                                try{
                                    String returnBodyText = response.body().string();
                                    JSONObject jsonRESULTS = new JSONObject(returnBodyText);
                                    Log.d("isinyabanyak",new Gson().toJson(returnBodyText));
                                    if (jsonRESULTS.getString("status").equals("success")){
                                        /*Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                        Intent intent =  new Intent(PrevLoginRegistActivity.this, ProfileActivity.class);
                                        startActivity(intent);*/
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

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void updateUI() {
        Toast.makeText(PrevLoginRegistActivity.this,"You are logged in", Toast.LENGTH_SHORT).show();
    }

    private void signIn() {
        Auth.GoogleSignInApi.signOut(mGoogleSignInClient);
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleSignInClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d("accountoy","isinya"+account.toJson());

                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
                // ...
            }
        }

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //handler google
    String idTokenString = "";
    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
        idTokenString = account.getIdToken();

        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");

                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("azz uid", user.getUid());

                            user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                @Override
                                public void onComplete(@NonNull Task<GetTokenResult> task) {
                                    Hawk.put("token",task.getResult().getToken());
                                    Log.d("azura", task.getResult().getToken());
                                    String name = user.getDisplayName();
                                    String email = user.getEmail();
                                    String uid = user.getUid();
                                    String access_token = task.getResult().getToken();

                                    apiService.postTokenFire(name,email,uid,access_token).enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            Log.d("pls", new Gson().toJson(response));
                                            if (response.isSuccessful()){
                                                try {
                                                    String returnBodyText = response.body().string();
                                                    JSONObject jsonRESULTS = new JSONObject(returnBodyText);
                                                    Log.d("uhuy",new Gson().toJson(returnBodyText));
                                                    if (jsonRESULTS.getString("status").equals("success")){
                                                        String token = jsonRESULTS.getJSONObject("data").getString("token");
                                                        Log.d("mamah",token);
                                                        Hawk.deleteAll();
                                                        Hawk.put("token_google",token);
                                                        String token_legend = Hawk.get("token_google");
                                                        Log.d("tokon","token"+token_legend);
                                                        /*Toast.makeText(mContext, "BERHASIL LOGIN", Toast.LENGTH_SHORT).show();
                                                        Intent intent =  new Intent(PrevLoginRegistActivity.this, ProfileActivity.class);
                                                        startActivity(intent);*/
                                                    } else {
                                                        String error_message = jsonRESULTS.getString("message");
                                                        Log.d("message",error_message);
                                                        Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                                    }
                                                }catch (JSONException e) {
                                                    e.printStackTrace();
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                                        }
                                    });
                                    /*String saveToken = task.getResult().getToken();
                                    Log.d("tokene","token"+saveToken);
                                    TokenPreferences.save(new LoginModel(saveToken),this);*/
                                }
                            });
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(PrevLoginRegistActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

    //handler facebook
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("azz", user.getUid());
                            Log.d("azz", user.getProviderId());
                            user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
                                @Override
                                public void onComplete(@NonNull Task<GetTokenResult> task) {
                                    Log.d("azz", task.getResult().getToken());
                                }
                            });

                            //updateUI();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(PrevLoginRegistActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI();
                        }

                        // ...
                    }
                });
    }

    private void showProgressDialog(){
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(true);
        progressDialog.show();
    }

}
