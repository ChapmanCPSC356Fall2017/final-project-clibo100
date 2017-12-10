package edu.chapman.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class NoteFragment extends Fragment {
    private NoteModel note;
    private static final String TAG = "NoteFragment";
    CheckBox datecheck;
    TextView dateTimeTextView;

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

        this.dateTimeTextView = v.findViewById(R.id.datetime_tv);

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
        if (this.note.getDate() != null)
        {
            datecheck.setChecked(true);
            dateTimeTextView.setText(this.note.getDate().toString(DateTimeFormat.longDate()));
        }


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
                    dateTimeTextView.setText("");
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

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == DateTimeFragment.REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            // Get date extra from data
            DateTime date = (DateTime) data.getSerializableExtra(DateTimeFragment.EXTRA_DATE);
            this.note.setDate(date);

            this.dateTimeTextView.setText(date.toString(DateTimeFormat.longDate()));
        }
    }
}
