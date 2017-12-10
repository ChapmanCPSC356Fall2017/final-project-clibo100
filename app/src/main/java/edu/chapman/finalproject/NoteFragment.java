package edu.chapman.finalproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.format.DateTimeFormat;

import java.util.Objects;

public class NoteFragment extends Fragment {
    private NoteModel note;
    private static final String TAG = "NoteFragment";
    CheckBox datecheck;

    @Override
    //gets all the info from the notedescriptionactivity
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "OnCreate()");
        super.onCreate(savedInstanceState);

        String listElementId = getArguments().getString(NoteDescriptionActivity.EXTRA_NOTE_ID);
        this.note = NoteCollection.GetInstance(getContext()).getListElement(listElementId);
    }

    @Nullable
    @Override
    //inflates view, sets text of stuff, etc
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        if (this.note == null)
        {
            this.note = new NoteModel();
        }
        Log.d(TAG, "onCreateView()");
        View v = inflater.inflate(R.layout.fragment_notedescription, container, false);

        final EditText titleEditText = v.findViewById(R.id.et_title);
        if (this.note.getTitle() != null)
        {
            titleEditText.setText(this.note.getTitle());
        }

        final EditText bodyEditText = v.findViewById(R.id.et_description);
        if (this.note.getBody() != null)
        {
            bodyEditText.setText(this.note.getBody());
        }

        datecheck = v.findViewById(R.id.date_check);

        this.datecheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                Log.d(TAG, "datechecked setOnCheckedListener");
                if (isChecked)
                {
                    DateTimeFragment frag = DateTimeFragment.GetInstance(note.getDate());
                    frag.setTargetFragment(NoteFragment.this, DateTimeFragment.REQUEST_CODE);

                    frag.show(getFragmentManager(), null);
                }
                else
                {
                    note.setDate(null);
                }
            }
        });

        return v;
    }

    public NoteModel getNote()
    {
        Log.d(TAG, "getNote()");
        return this.note;
    }

    public void setNote(NoteModel note)
    {
        Log.d(TAG, "setNote()");
        this.note = note;
    }
}
