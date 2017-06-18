package com.example.prateek.testingnotifications;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InnerActivity extends AppCompatActivity {
    TheDataBase dataBase;
    FloatingActionButton deletebutton,updatebutton,sharebutton;
    Long val;
    int notID;
    EditText t3,t4;
    NotificationMaker notificationMaker;
    NotificationManager mNotificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner);
        Log.d("TAG","Oncreate inner");
        dataBase=new TheDataBase(this);
        t3= (EditText) findViewById(R.id.editText3);
        t4= (EditText) findViewById(R.id.editText4);
         deletebutton= (FloatingActionButton) findViewById(R.id.floatingActionButton2);
        updatebutton= (FloatingActionButton) findViewById(R.id.floatingActionButton);
        sharebutton= (FloatingActionButton) findViewById(R.id.floatingActionButton4);
        deletebutton.setImageResource(R.drawable.ic_delete_black_24dp);
        updatebutton.setImageResource(R.drawable.ic_save_black_24dp);
        mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationMaker=new NotificationMaker(this);
        Intent intent = getIntent();
        val=(Long) intent.getLongExtra("key",0);
        notID=intent.getIntExtra("NotificationID",notID);
//        String value = dataBase.GetOneData(val);
//        String sub1=value.substring(0,value.indexOf("."));
//        String sub2=value.substring(value.indexOf(".")+1);
        final String sub1=intent.getStringExtra("msg1");
        final String sub2=intent.getStringExtra("msg2");

        t3.setText(sub1);
        t4.setText(sub2);

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNotificationManager.cancel(notID);
                t3.setText("");
                t4.setText("");
                moveTaskToBack(true);
                finish();
            }
        });
        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, sub1+" "+sub2);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }
        });
    }
    public void UpdateButton(View view)
    {
        Log.d("TAG","updatbutton inner");
        String s1,s2;
        s1=t3.getText().toString();
        s2=t4.getText().toString();
        int rowupdated=dataBase.UpdateData(val,s1,s2);
        if(rowupdated<1)
            Message_Toast.main(this,"No rows affected");
        else
            Message_Toast.main(this,"Entry Updated");


//        Intent intent = new Intent(this,MainActivity.class);
//        intent.putExtra("first", s1);
//        intent.putExtra("second",s2);
        mNotificationManager.cancel(notID);
//        startActivity(intent);
        notificationMaker.notimaker(s1,s2,true);
        t3=null;
        t4=null;
//        moveTaskToBack(true);

        System.exit(0);


    }

}
