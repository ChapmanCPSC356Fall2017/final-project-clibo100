package edu.chapman.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class NoteDescriptionActivity extends MainActivity{
    public static final String EXTRA_NOTE_ID = "note_id";
    private static final String TAG = "NoteDescriptionActivity";
    EditText titleEditText, bodyEditText;
    CheckBox dateCheck;
    TextView dateTimeTextView;
    NoteDescriptionFragment frag;

    @Override
    //pretty self explanatory tbh
    protected Fragment getFragment()
    {
        Log.d(TAG, "getFragment()");
        Bundle extras = getIntent().getExtras();

        NoteDescriptionFragment frag = new NoteDescriptionFragment();
        frag.setArguments(extras);
        this.frag = frag;

        return frag;
    }

    public void onClickSave(View view) {
        Log.d(TAG, "onClickSave()");
        titleEditText = findViewById(R.id.et_title);
        bodyEditText = findViewById(R.id.et_description);
        dateCheck = findViewById(R.id.date_check);
        dateTimeTextView = findViewById(R.id.datetime_tv);

        NoteModel note = frag.getNote();

        String old_title = note.getTitle();
        note.setTitle(titleEditText.getEditableText().toString());
        note.setBody(bodyEditText.getEditableText().toString());
        if (note.getTitle() != null && !Objects.equals(note.getTitle(), "") && !Objects.equals(note.getTitle(), " "))
        {
            if (!Objects.equals(note.getTitle(), old_title)) {
                NoteCollection.GetInstance(null).remove(old_title);
                Log.d(TAG, "onClickSave() removed file with old title");
            }

            NoteCollection.GetInstance(null).add(note);
            Log.d(TAG, "onClickSave() added new note");
            frag.setNote(note);

            Toast.makeText(getApplicationContext(), "Note Saved", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Note Failed to Save: Invalid Title", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action:
                NoteModel note = new NoteModel();
                Intent listElementIntent = new Intent(getApplicationContext(), NoteDescriptionActivity.class);
                listElementIntent.putExtra(NoteDescriptionActivity.EXTRA_NOTE_ID, note.getId());

                getApplicationContext().startActivity(listElementIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

}
