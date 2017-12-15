package edu.chapman.finalproject;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.DateTime;

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

    //this does a whole lot hold on to your butts
    public void onClickSave(View view) {
        Log.d(TAG, "onClickSave()");
        titleEditText = findViewById(R.id.et_title);
        bodyEditText = findViewById(R.id.et_description);
        dateCheck = findViewById(R.id.date_check);
        dateTimeTextView = findViewById(R.id.actual_datetime_tv);

        NoteModel note = frag.getNote();

        //remember the old title, set the new title and body to the note
        String old_title = note.getTitle();
        note.setTitle(titleEditText.getEditableText().toString());
        note.setBody(bodyEditText.getEditableText().toString());

        //sets date if there is one
        if (!dateTimeTextView.getText().toString().isEmpty())
        {
            note.setDate(DateTime.parse(dateTimeTextView.getText().toString()));
        }

        //if title isnt empty, try to save it
        if (note.getTitle() != null && !Objects.equals(note.getTitle(), "") && !Objects.equals(note.getTitle(), " "))
        {
            //if they changed the title it deletes the file from the filesystem that had the old title before saving the new one
            if (!Objects.equals(note.getTitle(), old_title)) {
                NoteCollection.GetInstance(null).remove(old_title);
                Log.d(TAG, "onClickSave() removed file with old title");
            }
            NoteCollection.GetInstance(null).add(note);
            Log.d(TAG, "onClickSave() added new note");
            frag.setNote(note);

            //make a toast to inform the user
            Toast.makeText(getApplicationContext(), "Note Saved", Toast.LENGTH_SHORT).show();
        }

        //tell the user if it failed too
        else
        {
            Toast.makeText(getApplicationContext(), "Note Failed to Save: Invalid Title", Toast.LENGTH_SHORT).show();
        }

        //schedule the notification for this note if it wants one
        if (note.getDate() != null) {
            long currentTime = SystemClock.elapsedRealtime();
            long reminderTime = note.getDate().getMillis();
            long delay = reminderTime - currentTime;
            Log.d(TAG, "onStart delay = " + delay);
            if (delay > 0)
            {
                scheduleNotification(getNotification(note.getTitle()), delay);
            }
        }
    }

    //schedules a notification
    private void scheduleNotification(Notification notification, long delay) {
        Log.d(TAG, "schduleNotification");

        //makes new intent
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        notificationIntent.setClass(this, NotificationPublisher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        if (alarmManager != null) {
            alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
            Log.d(TAG, "schduleNotification alarmmanager.set");
        }
    }

    private Notification getNotification(String content) {
        Log.d(TAG, "getNotification()");
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Noter Reminder!");
        builder.setContentText(content);
        builder.setSmallIcon(R.drawable.icon);
        return builder.build();
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
