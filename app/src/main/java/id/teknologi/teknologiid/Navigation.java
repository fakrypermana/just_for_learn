package id.teknologi.teknologiid;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.concurrent.ThreadFactory;

import id.teknologi.teknologiid.dummy_activity.Notifikasi;
import id.teknologi.teknologiid.dummy_activity.Pekerjaan;
import id.teknologi.teknologiid.dummy_activity.Pertanyaan;
import id.teknologi.teknologiid.feature.thread.ThreadActivity;
import id.teknologi.teknologiid.model.Thread;

public class Navigation extends TabActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);

        final TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost); // initiate TabHost
        TabHost.TabSpec spec; // Reusable TabSpec for each tab
        Intent intent; // Reusable Intent for each tab

        spec = tabHost.newTabSpec("pertanyaan"); // Create a new TabSpec using tab host
        spec.setIndicator("",getResources().getDrawable(R.drawable.pertanyaan)); // set the “HOME” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, Pertanyaan.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        // Do the same for the other tabs

        spec = tabHost.newTabSpec("thread"); // Create a new TabSpec using tab host
        spec.setIndicator("",getResources().getDrawable(R.drawable.thread)); // set the “CONTACT” as an indicator

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, ThreadActivity.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("berita"); // Create a new TabSpec using tab host
        spec.setIndicator("",getResources().getDrawable(R.drawable.berita)); // set the “ABOUT” as an indicator
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent(this, Thread.class);
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
        spec = tabHost.newTabSpec("pekerjaan");
        spec.setIndicator("", getResources().getDrawable(R.drawable.pekerjaan));

        intent = new Intent(this, Pekerjaan.class);
        spec.setContent(intent);
        tabHost.addTab(spec);


        spec = tabHost.newTabSpec("pemberitahuan");
        spec.setIndicator("", getResources().getDrawable(R.drawable.pemberitahuan));

        intent = new Intent(this, Notifikasi.class);
        spec.setContent(intent);
        tabHost.addTab(spec);

    }
}
