package edu.chapman.finalproject;

import android.support.v4.app.Fragment;
import android.util.Log;

public class NotesListActivity extends MainActivity{
    private final String LOGTAG = "NotesListActivity";
    @Override
    //returns new activity
    protected Fragment getFragment()
    {
        Log.d(LOGTAG, "getFragment");
        return new NotesListFragment();
    }
}
