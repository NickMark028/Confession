package com.example.confession.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.confession.R;
import com.example.confession.views.activities.HomePageActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebasePushNotificationService extends FirebaseMessagingService {

    public MyFirebasePushNotificationService() {
        super();
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.d("FirebasePush ", "From: " + remoteMessage.getFrom());
        getFirebaseMessage(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    public void getFirebaseMessage(String title, String body) {
        PendingIntent pendingIntent = PendingIntent.getActivity(
                getApplicationContext(),
                0,
                new Intent(getApplicationContext(), HomePageActivity.class),
                0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "FirebaseChannel");
        builder.setSmallIcon(R.drawable.avatar);
        builder.setContentTitle(title);
        builder.setContentText(body);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);


        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(1, builder.build());

        Log.d(title, body);
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);


    }
}
