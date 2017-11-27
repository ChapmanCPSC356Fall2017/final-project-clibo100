package edu.chapman.finalproject;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class MainActivity extends AppCompatActivity {
    protected abstract Fragment getFragment();

    @Override

    //fragment manager and such
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showFragment(getFragment());
    }

    private void showFragment(Fragment frag)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_fragment_container, frag)
                .commit();
    }
}

