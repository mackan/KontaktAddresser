package se.kontaktAddresser;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.util.Log;

public class Main extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab

        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, SearchActivity.class);  

        // Initialize a TabSpec for each tab and add it to the TabHost
        String news = "Uppdatera";
        spec = tabHost.newTabSpec(news).setIndicator(news, res.getDrawable(R.drawable.icon_magnify_glass))
                      .setContent(intent);
        tabHost.addTab(spec);
        
        String jobs = "Visa";
        intent = new Intent(this.getApplicationContext(), MapsActivity.class);
        spec = tabHost.newTabSpec(jobs).setIndicator(jobs, res.getDrawable(R.drawable.icon_home))
                      .setContent(intent);
        tabHost.addTab(spec);

        tabHost.setCurrentTab(0);  
    }
    

}