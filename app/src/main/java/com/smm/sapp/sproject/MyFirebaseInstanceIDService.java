package com.smm.sapp.sproject;



import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.smm.sapp.sproject.Models.NotificationPayLoad;

import org.json.JSONException;
import org.json.JSONObject;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    String TAG = "MyFirebaseInstanceIDService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.e(TAG, "From: " + remoteMessage.getNotification());
        Log.e(TAG, "Message data payload: " + remoteMessage.getData());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.e(TAG, "Message data payload: " + remoteMessage.getData());
            Gson gson = new Gson();
//            NotificationPayLoad notificationPayLoad = gson.fromJson(remoteMessage.getData().toString(),NotificationPayLoad.class);
//            try {
//                JSONObject object = new JSONObject(remoteMessage.getData().toString());
//                Log.e("ffdr",object.getString("seen") + " ");
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.

            } else {
                // Handle message within 10 seconds

            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            ConstantInterFace.NOTIFICATION_NUMBER ++;
            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
            Log.e(TAG, "Message Notification title: " + remoteMessage.getNotification().getTitle());

            /*
             * Displaying a notification locally
             */
            MyNotificationManager.getInstance(this).displayNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

}
