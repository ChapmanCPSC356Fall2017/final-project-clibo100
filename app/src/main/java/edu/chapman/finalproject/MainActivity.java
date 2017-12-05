package edu.chapman.finalproject;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public abstract class MainActivity extends AppCompatActivity {
    protected abstract Fragment getFragment();
    FloatingActionButton FAB;

    @Override

    //fragment manager and such
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FAB = findViewById(R.id.myFAB);
        showFragment(getFragment());
    }

    private void showFragment(Fragment frag)
    {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_fragment_container, frag)
                .commit();
    }

    public void onFABClick(View view) {

    }
}

