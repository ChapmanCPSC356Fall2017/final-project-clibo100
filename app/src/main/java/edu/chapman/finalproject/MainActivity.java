package edu.chapman.finalproject;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public abstract class MainActivity extends AppCompatActivity {
    protected abstract Fragment getFragment();
    private static final String TAG = "mainactivity";

    @Override

    //fragment manager and such
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "mainactivity oncreate");
        super.onCreate(savedInstanceState);
        NoteCollection nc = NoteCollection.GetInstance(this);
        setContentView(R.layout.activity_main);
        showFragment(getFragment());
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

