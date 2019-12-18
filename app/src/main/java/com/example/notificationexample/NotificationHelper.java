package com.example.notificationexample;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationHelper extends ContextWrapper {
    public static final String channel1ID="channel1ID";
    public static final String channel1NAME="channel 1";
    public NotificationManager mNotoficationManager;


    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            createChannel();
        }
        Log.d("1","NotificationHelper");
    }


    @TargetApi(Build.VERSION_CODES.O)
    public void createChannel(){
        NotificationChannel nc=new NotificationChannel(channel1ID,channel1NAME, NotificationManager.IMPORTANCE_DEFAULT);

        nc.enableLights(true);
        nc.enableVibration(true);
        nc.setLightColor(R.color.colorPrimary);
        nc.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(nc);

        Log.d("2","CreateChannel");
    }

    public NotificationManager getManager (){
            if(mNotoficationManager==null){
                mNotoficationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Log.d("3","Getmanager");
            }

            return mNotoficationManager;
    }

    public NotificationCompat.Builder getChannelNotification(String title,String message){
        Log.d("4","GetChannelNotification");

        //SET NOTIFICATION
        Intent i=new Intent(this,Main2Activity.class);
        Intent i2=new Intent(this,MainActivity.class);

        PendingIntent pendingIntent=PendingIntent.getActivity(this,1,i,PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent2=PendingIntent.getActivity(this,1,i2,PendingIntent.FLAG_UPDATE_CURRENT);

        //WITH IMAGE
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.flower);
        //END SET NOTIFICATION

        return new NotificationCompat.Builder(getApplicationContext(),channel1ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_looks_one_black_24dp)
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setSummaryText(message))

                //SET NOTIFICATION ACTION
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_mood_black_24dp,"HELLO",pendingIntent2);
    }
}
