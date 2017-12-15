package edu.chapman.finalproject;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationPublisher extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "notification-id";
    public static String NOTIFICATION = "notification";

    public void onReceive(Context context, Intent intent) {
        Log.d("NotificationPublisher", "onReceive");
        String channel_id = "mychannel";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            Log.d("NotificationPublisher", "onReceive Oreo");
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel notificationChannel = new NotificationChannel(channel_id,
                    "My Notifications", NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            if (manager != null) {
                manager.createNotificationChannel(notificationChannel);
            }

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context, channel_id)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("Notifications Example")
                            .setContentText("This is a test notification");


            Intent notificationIntent = new Intent(context, NoteDescriptionActivity.class);

            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(contentIntent);
            builder.setAutoCancel(true);
            builder.setLights(Color.BLUE, 500, 500);
            long[] pattern = {500,500,500,500,500,500,500,500,500};
            builder.setVibrate(pattern);
            builder.setStyle(new NotificationCompat.InboxStyle());
            if (manager != null) {
                manager.notify(1, builder.build());
            }
        }
        else
        {
            Log.d("NotificationPublisher", "onReceive Not Oreo");

            NotificationCompat.Builder builder =
                    new NotificationCompat.Builder(context, channel_id)
                            .setSmallIcon(R.drawable.ic_launcher_foreground)
                            .setContentTitle("Notifications Example")
                            .setContentText("This is a test notification");


            Intent notificationIntent = new Intent(context, NoteDescriptionActivity.class);

            PendingIntent contentIntent = PendingIntent.getActivity(context, 0, notificationIntent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            builder.setContentIntent(contentIntent);
            builder.setAutoCancel(true);
            builder.setLights(Color.BLUE, 500, 500);
            long[] pattern = {500,500,500,500,500,500,500,500,500};
            builder.setVibrate(pattern);
            builder.setStyle(new NotificationCompat.InboxStyle());
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager != null) {
                manager.notify(1, builder.build());
            }
        }
    }
}
