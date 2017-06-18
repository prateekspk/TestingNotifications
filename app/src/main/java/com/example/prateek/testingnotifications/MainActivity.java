package com.example.prateek.testingnotifications;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TheDataBase theDataBase;
    TextView tv1,tv2;
    String s1,s2,ss1,ss2;
    CheckBox checkBox,checkBox2;
    long id= 0;
    boolean cb;

    private int req_code = 0;
    NotificationMaker notificationMaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        theDataBase=new TheDataBase(this);
        tv1= (TextView) findViewById(R.id.editText);
        tv2= (TextView) findViewById(R.id.editText2);
        checkBox= (CheckBox) findViewById(R.id.checkBox);
        checkBox.setChecked(true);
        checkBox2= (CheckBox) findViewById(R.id.checkBox2);
        checkBox2.setChecked(true);

         notificationMaker=new NotificationMaker(this);


    }

    public void sendNotification(View view) {
        s1=tv1.getText().toString();
        s2=tv2.getText().toString();

        cb=checkBox2.isChecked();

        hideSoftKeyboard();
        notificationMaker.notimaker(s1,s2,cb);

        tv1.setText("");
        tv2.setText("");
        if(checkBox.isChecked()) {

           System.exit(0);

        }
    }


    public void senme(View v)
    {
        Intent intent = new Intent(this, ShowHistoryActivity.class);
        startActivity(intent);
    }
    public void hideSoftKeyboard() {
        if(getCurrentFocus()!=null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

}



