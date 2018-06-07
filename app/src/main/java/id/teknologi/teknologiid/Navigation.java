package id.teknologi.teknologiid;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import id.teknologi.teknologiid.feature.Question.QuestionListActivity;
import id.teknologi.teknologiid.feature.thread.ThreadActivity;

public class Navigation extends TabActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent;

        //Pertanyaan
        spec = tabHost.newTabSpec("Pertanyaan"); // Create a new TabSpec using tab host
        spec.setIndicator("", getResources().getDrawable(R.drawable.pertanyaan)); // set the “HOME” as an indicator
        intent = new Intent(this, QuestionListActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
        tabHost.addTab(spec);

        //Thread
        spec = tabHost.newTabSpec("Thread");  //Create a new TabSpec using tab host
        spec.setIndicator("", getResources().getDrawable(R.drawable.pertanyaan)); // set the “HOME” as an indicator
        intent = new Intent(this, ThreadActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
        tabHost.addTab(spec);

        //Pekerjaan
        spec = tabHost.newTabSpec("Pekerjaan");  //Create a new TabSpec using tab host
        spec.setIndicator("", getResources().getDrawable(R.drawable.pertanyaan)); // set the “HOME” as an indicator
        intent = new Intent(this, ThreadActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
        tabHost.addTab(spec);

        //Profil
        spec = tabHost.newTabSpec("profil");  //Create a new TabSpec using tab host
        spec.setIndicator("", getResources().getDrawable(R.drawable.pertanyaan)); // set the “HOME” as an indicator
        intent = new Intent(this, ThreadActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
        tabHost.addTab(spec);

        //Notif
        spec = tabHost.newTabSpec("Notif");  //Create a new TabSpec using tab host
        spec.setIndicator("", getResources().getDrawable(R.drawable.pertanyaan)); // set the “HOME” as an indicator
        intent = new Intent(this, ThreadActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(1);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
                                            @Override
                                            public void onTabChanged(String tabId) {
                                                 //display the name of the tab whenever a tab is changed
                                                Toast.makeText(getApplicationContext(), tabId, Toast.LENGTH_SHORT).show();

                                            }
                                        }
        );

    }
}
