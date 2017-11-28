package edu.chapman.finalproject;

import android.support.v4.app.Fragment;

public class NotesListActivity extends MainActivity{
    @Override
    //returns new activity
    protected Fragment getFragment()
    {
        return new NotesListFragment();
    }
}
