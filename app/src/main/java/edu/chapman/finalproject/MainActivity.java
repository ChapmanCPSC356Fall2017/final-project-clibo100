package edu.chapman.finalproject;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

public abstract class MainActivity extends AppCompatActivity {
    protected abstract Fragment getFragment();
    private static final String TAG = "mainactivity";

    @Override
    //fragment manager and such
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "mainactivity oncreate");
        super.onCreate(savedInstanceState);
        NoteCollection.GetInstance(this);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        showFragment(getFragment());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return true;
    }

    private void showFragment(Fragment frag)
    {
        Log.d(TAG, "mainactivity showfragment");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_fragment_container, frag)
                .commit();
    }
}

