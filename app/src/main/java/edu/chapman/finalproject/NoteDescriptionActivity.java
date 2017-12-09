package edu.chapman.finalproject;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

public class NoteDescriptionActivity extends MainActivity{
    public static final String EXTRA_NOTE_ID = "note_id";
    private static final String TAG = "NoteDescriptionActivity";
    EditText titleEditText, bodyEditText;
    NoteFragment frag;

    @Override
    //pretty self explanatory tbh
    protected Fragment getFragment()
    {
        Log.d(TAG, "getFragment()");
        Bundle extras = getIntent().getExtras();

        NoteFragment frag = new NoteFragment();
        frag.setArguments(extras);
        this.frag = frag;

        return frag;
    }

    public void onClickSave(View view) {
        Log.d(TAG, "onClickSave()");
        titleEditText = findViewById(R.id.et_title);
        bodyEditText = findViewById(R.id.et_description);

        NoteModel note = frag.getNote();

        String old_title = note.getTitle();
        note.setTitle(titleEditText.getEditableText().toString());
        note.setBody(bodyEditText.getEditableText().toString());

        if (!Objects.equals(note.getTitle(), old_title))
        {
            NoteCollection.GetInstance(null).remove(old_title);
            Log.d(TAG, "onClickSave() removed file with old title");
        }

        NoteCollection.GetInstance(null).add(note);
        Log.d(TAG, "onClickSave() added new note");
        frag.setNote(note);

        Toast.makeText(getApplicationContext(), "Note Saved", Toast.LENGTH_SHORT).show();
    }
}
