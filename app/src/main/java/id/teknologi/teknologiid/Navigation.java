package id.teknologi.teknologiid;

import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;


import id.teknologi.teknologiid.feature.Question.QuestionListActivity;
import id.teknologi.teknologiid.feature.login_register.PrevLoginRegistActivity;

import id.teknologi.teknologiid.feature.login_register.RegisterActivity;
import id.teknologi.teknologiid.feature.pekerjaan.PekerjaanActivity;
import id.teknologi.teknologiid.feature.profile.ProfileActivity;
import id.teknologi.teknologiid.feature.thread.ThreadActivity;
import id.teknologi.teknologiid.feature.thread_new.ThreadNewActivity;
import id.teknologi.teknologiid.feature.thread_new.ThreadNewActivity2;
import id.teknologi.teknologiid.model.Thread;

public class Navigation extends TabActivity{

    ProgressDialog progress;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        spec = tabHost.newTabSpec("pertanyaan"); // Create a new TabSpec using tab host
        spec.setIndicator("",getResources().getDrawable(R.drawable.ic_question_answer_black_24dp)); // set the “HOME” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, QuestionListActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs

        spec = tabHost.newTabSpec("thread"); // Create a new TabSpec using tab host
        spec.setIndicator("",getResources().getDrawable(R.drawable.ic_explore_black_24dp)); // set the “CONTACT” as an indicator

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, ThreadActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("pekerjaan"); // Create a new TabSpec using tab host
        spec.setIndicator("",getResources().getDrawable(R.drawable.ic_work_black_24dp)); // set the “ABOUT” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, PekerjaanActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
        //set tab which one you want to open first time 0 or 1 or 2
        tabHost.setCurrentTab(1);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                // display the name of the tab whenever a tab is changed
                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();
            }
        });
        spec = tabHost.newTabSpec("pemberitahuan");
        spec.setIndicator("", getResources().getDrawable(R.drawable.pemberitahuan));
//        intent = new Intent(this, ThreadNewActivity3.class);

        intent = new Intent(this, PrevLoginRegistActivity.class);
        spec.setIndicator("", getResources().getDrawable(R.drawable.ic_notifications_black_24dp));
        intent = new Intent(this, ThreadNewActivity2.class);
        spec.setContent(intent);
        tabHost.addTab(spec);


        spec = tabHost.newTabSpec("profil");  //Create a new TabSpec using tab host
        spec.setIndicator("", getResources().getDrawable(R.drawable.ic_person_black_24dp)); // set the “HOME” as an indicator
        //intent = new Intent(this, PrevLoginRegistActivity.class);
        intent = new Intent(this, PrevLoginRegistActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
    }
}
