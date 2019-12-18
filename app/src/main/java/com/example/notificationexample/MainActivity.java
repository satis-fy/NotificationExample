package com.example.notificationexample;

import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText mTitle,mMessage;
    Button mButton;
    private NotificationHelper mNH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle=findViewById(R.id.title1);
        mMessage=findViewById(R.id.message11);
        mButton=findViewById(R.id.button1);

        mNH=new NotificationHelper(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setOnchannel(mTitle.getText().toString(),mMessage.getText().toString());
            }
        });
    }

    public void setOnchannel (String title,String message){
        NotificationCompat.Builder nb=mNH.getChannelNotification(title,message);
        mNH.getManager().notify(1,nb.build());
    }
}
