package id.teknologi.teknologiid.network;

import android.content.Context;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.GetTokenResult;

import id.teknologi.teknologiid.model.LoginModel;

public class TokenPreferences {
    public static final String PREFS_NAME = "token_prefs";
    public static final String PREFS_VAL = "token_value";

    public static void save(LoginModel data, OnCompleteListener<GetTokenResult> ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences((Context) ctx, PREFS_NAME, 0);
        complexPreferences.putObject(PREFS_VAL, data);
        complexPreferences.commit();
    }

    public static LoginModel load(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        return complexPreferences.getObject(PREFS_VAL, LoginModel.class);
    }

    public static String getJSON(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        return complexPreferences.getJSON(PREFS_VAL);
    }

    public static void clearAll(Context ctx){
        ComplexPreferences complexPreferences = ComplexPreferences.getComplexPreferences(ctx, PREFS_NAME, 0);
        complexPreferences.clearObject();
        complexPreferences.commit();
    }

}