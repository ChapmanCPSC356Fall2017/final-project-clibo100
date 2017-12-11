package edu.chapman.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import org.joda.time.DateTime;

public class TimeFragment extends DialogFragment
{
    public static final int REQUEST_CODE = 2000;
    private static final String ARG_TIME = "arg_time";
    public static final String EXTRA_TIME = "extra_time";

    public static TimeFragment GetInstance(DateTime date)
    {
        TimeFragment frag = new TimeFragment();

        Bundle b = new Bundle();
        b.putSerializable(ARG_TIME, date);

        frag.setArguments(b);

        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        DateTime date = (DateTime) getArguments().getSerializable(ARG_TIME);

        final TimePicker picker = new TimePicker(getActivity());
        if (date != null)
        {
            picker.setHour(date.getHourOfDay());
            picker.setMinute(date.getMinuteOfDay());
        }

        AlertDialog dialog;
        dialog = new AlertDialog.Builder(getActivity())
                .setTitle("Pick a date")
                .setView(picker)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        DateTime date = new DateTime(1, 1, 1, picker.getHour(), picker.getMinute());

                        Intent intent = new Intent();
                        intent.putExtra(EXTRA_TIME, date);

                        getTargetFragment().onActivityResult(REQUEST_CODE, Activity.RESULT_OK, intent);
                    }
                })
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        return dialog;
    }
}
