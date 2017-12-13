package edu.chapman.finalproject;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import java.util.List;

public abstract class MainActivity extends AppCompatActivity {
    protected abstract Fragment getFragment();
    private static final String TAG = "mainactivity";

    @Override
    //fragment manager and such
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(TAG, "mainactivity oncreate");
        super.onCreate(savedInstanceState);
        NoteCollection.GetInstance(this);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        showFragment(getFragment());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bar_menu, menu);
        return true;
    }

    private void showFragment(Fragment frag)
    {
        Log.d(TAG, "mainactivity showfragment");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_fragment_container, frag)
                .commit();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa onStart()");
        super.onStart();
        List<NoteModel> notes = NoteCollection.GetInstance(this).getListElements();
        for (NoteModel note : notes) {
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
    }

    private void scheduleNotification(Notification notification, long delay) {
        Log.d(TAG, "schduleNotification");
        Intent notificationIntent = new Intent(this, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
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
}

