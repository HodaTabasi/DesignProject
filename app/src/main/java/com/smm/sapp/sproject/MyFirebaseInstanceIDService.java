package com.smm.sapp.sproject;



import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.github.mikephil.charting.utils.Utils;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.smm.sapp.sproject.Activities.ContainerActivity;
import com.smm.sapp.sproject.Models.NotificationPayLoad;
import com.smm.sapp.sproject.Models.OfferModel;
import com.smm.sapp.sproject.Models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MyFirebaseInstanceIDService extends FirebaseMessagingService {
    String TAG = "MyFirebaseInstanceIDService";
    NotificationPayLoad payLoad = new NotificationPayLoad();
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.e(TAG, "From: " + remoteMessage.getNotification().getTitle());
        Log.e(TAG, "Message data payload: " + remoteMessage.getData());
        Log.e(TAG, "Message data payload: " + remoteMessage.getData());
        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            String s = remoteMessage.getNotification().getTitle();
            switch (s){
                case "رسالة جديدة":
                    payLoad.NotificationPayLoad(remoteMessage.getData());
                    Gson gson = new Gson();
                    payLoad.setUser(gson.fromJson(remoteMessage.getData().get("user"),User.class));
                    sendMessageNotification(payLoad);
                    break;
                case "تم قبول العرض الخاص بك , هنيئا لك":
                    OfferModel model = new OfferModel();
                    model.NotificationPayLoad(remoteMessage.getData());
                    sendAcceptOfferNotification(model);
                    break;
                case "تم قبول مشروعك":
                    sendAcceptProjectNotification(remoteMessage.getData().get("name"));
                    break;
                case "تم تقديم عرض على مشروع لك":
                    sendAddOfferNotification("تم تقديم عرض على مشروعك");
                    break;
                default:
                    sendNotification();
                        break;
            }

        }
    }
    private void sendNotification() {
        Intent intent = new Intent(this, ContainerActivity.class);
        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("notifiy",true);
        intent.putExtra("type",5);
        send(intent, 1, "اشعار جديد", "اشعار جديد");
    }
    private void sendMessageNotification(NotificationPayLoad payLoad) {
        Intent intent = new Intent(this, ContainerActivity.class);
       // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("messagePayload",payLoad);
        intent.putExtra("notifiy",true);
        intent.putExtra("type",1);
        send(intent, 1, "رسالة جديدة", payLoad.getMessage());
    }

    private void sendAcceptOfferNotification(OfferModel payLoad) {
        Intent intent = new Intent(this, ContainerActivity.class);
        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("messagePayload",payLoad);
        intent.putExtra("notifiy",true);
        intent.putExtra("type",2);
        send(intent, 1, "تم قبول العرض الخاص بك",payLoad.getDescr());
    }

    private void sendAcceptProjectNotification(String name) {
        Intent intent = new Intent(this, ContainerActivity.class);
        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("notifiy",true);
        intent.putExtra("type",3);
        send(intent, 1, "تم قبول مشروعك",name);
    }

    private void sendAddOfferNotification(String name) {
        Intent intent = new Intent(this, ContainerActivity.class);
        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("notifiy",true);
        intent.putExtra("type",4);
        send(intent, 1, "تم تقديم عرض على مشروعك",name);
    }

    private void send(Intent intent, int action, String title, String message) {
        ConstantInterFace.NOTIFICATION_NUMBER ++;
        int notificationNumber = 0;
        int id = 1;
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationManager notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = Objects.requireNonNull(notifyManager).getNotificationChannel(String.valueOf(id));
            if (mChannel == null) {
                mChannel = new NotificationChannel(String.valueOf(id), title, importance);
                mChannel.setDescription(message);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifyManager.createNotificationChannel(mChannel);
            }
        }
        builder = new NotificationCompat.Builder(this, String.valueOf(id));
        builder.setContentTitle(title)  // required
                .setSmallIcon(R.drawable.img1)
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setTicker(title + " : " + message)
                .setContentText(message)
                .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        builder.setNumber(notificationNumber)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL);
        if (notifyManager != null) {
            notifyManager.notify(id, builder.build());
        }
    }

    @Override
    public void onMessageSent(String s) {
        super.onMessageSent(s);
    }

//        // Check if message contains a notification payload.
//        if (remoteMessage.getNotification() != null) {
//            ConstantInterFace.NOTIFICATION_NUMBER ++;
//            Log.e(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
//            Log.e(TAG, "Message Notification title: " + remoteMessage.getNotification().getTitle());
//
//            /*
//             * Displaying a notification locally
//             */
//            MyNotificationManager.getInstance(this).displayNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
//        }
}
