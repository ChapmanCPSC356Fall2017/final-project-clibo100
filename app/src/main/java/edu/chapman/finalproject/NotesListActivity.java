package edu.chapman.finalproject;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class NotesListActivity extends MainActivity{
    private final String LOGTAG = "NotesListActivity";
    @Override
    //returns new activity
    protected Fragment getFragment()
    {
        Log.d(LOGTAG, "getFragment");
        return new NotesListFragment();
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
