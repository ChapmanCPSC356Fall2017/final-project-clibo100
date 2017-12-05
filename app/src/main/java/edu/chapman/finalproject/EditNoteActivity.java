package edu.chapman.finalproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class EditNoteActivity extends MainActivity{
    public static final String EXTRA_EDITNOTE_ID = "editnote_id";

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
