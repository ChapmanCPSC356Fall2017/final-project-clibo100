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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Calendar;

public class NoteDescriptionFragment extends Fragment {
    private NoteModel note;
    private static final String TAG = "NoteDescriptionFragment";
    View v;
    CheckBox datecheck;
    TextView dateTimeTextView;
    Button setDateButton, setTimeButton;
    DateTime resultdate = null, resulttime = null, date;

    @SuppressWarnings("ConstantConditions")
    @Override
    //gets all the info from the notedescriptionactivity
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        Log.d(TAG, "OnCreate()");
        super.onCreate(savedInstanceState);

        String listElementId = getArguments().getString(NoteDescriptionActivity.EXTRA_NOTE_ID);
        this.note = NoteCollection.GetInstance(getContext()).getListElement(listElementId);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
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
        this.v = inflater.inflate(R.layout.fragment_notedescription, container, false);

        this.dateTimeTextView = v.findViewById(R.id.datetime_tv);
        this.setDateButton = v.findViewById(R.id.button_setdate);
        this.setTimeButton = v.findViewById(R.id.button_settime);
        this.resulttime = null;
        this.resultdate = null;

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
            setDateButton.setVisibility(View.VISIBLE);
            setTimeButton.setVisibility(View.VISIBLE);
            setDateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DateFragment frag = DateFragment.GetInstance(note.getDate());
                    frag.setTargetFragment(NoteDescriptionFragment.this, DateFragment.REQUEST_CODE);
                    frag.show(getFragmentManager(), null);
                }
            });
            setTimeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TimeFragment frag = TimeFragment.GetInstance(note.getDate());
                    frag.setTargetFragment(NoteDescriptionFragment.this, TimeFragment.REQUEST_CODE);
                    frag.show(getFragmentManager(), null);
                }
            });
            datecheck.setChecked(true);
            dateTimeTextView.setText(this.note.getDate().toString(DateTimeFormat.longDateTime()));
        }


        this.datecheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                Log.d(TAG, "datechecked setOnCheckedListener");
                if (isChecked)
                {
                    setDateButton.setVisibility(View.VISIBLE);
                    setTimeButton.setVisibility(View.VISIBLE);
                    setDateButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            DateFragment frag = DateFragment.GetInstance(note.getDate());
                            frag.setTargetFragment(NoteDescriptionFragment.this, DateFragment.REQUEST_CODE);
                            frag.show(getFragmentManager(), null);
                        }
                    });
                    setTimeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            TimeFragment frag = TimeFragment.GetInstance(note.getDate());
                            frag.setTargetFragment(NoteDescriptionFragment.this, TimeFragment.REQUEST_CODE);
                            frag.show(getFragmentManager(), null);
                        }
                    });
                }
                else
                {
                    setTimeButton.setVisibility(View.GONE);
                    setDateButton.setVisibility(View.GONE);
                    note.setDate(null);
                    dateTimeTextView.setText(R.string.remindernotset);
                    resultdate = null;
                    resulttime = null;
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


        if (requestCode == DateFragment.REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            this.resultdate = (DateTime) data.getSerializableExtra(DateFragment.EXTRA_DATE);
            this.resultdate = this.resultdate.withHourOfDay(10);
        }

        else if (requestCode == TimeFragment.REQUEST_CODE && resultCode == Activity.RESULT_OK)
        {
            this.resulttime = (DateTime) data.getSerializableExtra(TimeFragment.EXTRA_TIME);

            if (Calendar.getInstance().get(Calendar.MONTH) != 12)
            {
                this.resulttime = this.resulttime.withYear(Calendar.getInstance().get(Calendar.YEAR))
                        .withMonthOfYear(Calendar.getInstance().get(Calendar.MONTH)+1)
                        .withDayOfMonth(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            }
            else
            {
                this.resulttime = this.resulttime.withYear(Calendar.getInstance().get(Calendar.YEAR))
                        .withMonthOfYear(1)
                        .withDayOfMonth(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
            }
        }

        if (resultdate == null)
        {
            this.date = resulttime;
        }
        else if (resulttime == null)
        {
            this.date = resultdate;
        }
        else
        {
            this.date = resultdate;
            this.date = this.date.withHourOfDay(resulttime.getHourOfDay()).withMinuteOfHour(resulttime.getMinuteOfHour());
            Log.d(TAG, "aaaaaaaaaaaaaaaa this is where date is"+date.toString(DateTimeFormat.longDateTime()));
        }

        this.note.setDate(date);


        this.dateTimeTextView.setText(date.toString(DateTimeFormat.longDateTime()));
    }
}
