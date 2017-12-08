package edu.chapman.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

public class NoteDescriptionActivity extends MainActivity{
    public static final String EXTRA_NOTE_ID = "note_id";
    private static final String TAG = "notedescriptionactivity";

    @Override
    //pretty self explanatory tbh
    protected Fragment getFragment()
    {
        Log.d(TAG, "notedescriptionactivity getfragment");
        Bundle extras = getIntent().getExtras();

        NoteFragment frag = new NoteFragment();
        frag.setArguments(extras);

        return frag;
    }

}
