package edu.chapman.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

public class NoteDescriptionActivity extends MainActivity{
    public static final String EXTRA_NOTE_ID = "note_id";

    @Override
    //pretty self explanatory tbh
    protected Fragment getFragment()
    {
        Bundle extras = getIntent().getExtras();

        NoteFragment frag = new NoteFragment();
        frag.setArguments(extras);

        return frag;
    }

}
