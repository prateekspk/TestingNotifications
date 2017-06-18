package com.example.prateek.testingnotifications;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by Prateek on 6/8/2017.
 */

public class NotificationMaker {
    Context mcontext;
    public NotificationMaker(Context mcontext)
    {this.mcontext=mcontext;}

    TheDataBase theDataBase;
    long id= 0;
    int notid;
    NotificationManager mNotificationManager;
    public void notimaker(String s1,String s2, Boolean cb)
    {
        theDataBase=new TheDataBase(mcontext);
       id = theDataBase.InsertData(s1,s2);

        Message_Toast.main(mcontext,Long.toString(id));
        Intent myintent2=new Intent(mcontext,MainActivity.class);



        Intent myintent=new Intent(mcontext,InnerActivity.class);
        myintent.putExtra("key",id);
        myintent.putExtra("msg1",s1);
        myintent.putExtra("msg2",s2);
        myintent.setAction(Long.toString(System.currentTimeMillis()));

        notid = (int)System.currentTimeMillis();
        myintent.putExtra("NotificationID",notid);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, s1+" "+s2);
        sendIntent.setType("text/plain");

        PendingIntent pendingIntent = PendingIntent.getActivity(mcontext, notid, myintent, PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent pendingIntent2=PendingIntent.getActivity(mcontext,notid,myintent2,PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent sharePI=PendingIntent.getActivity(mcontext,notid,sendIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        long[] v = {100,350};
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(mcontext);
        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.ic_spk);
        mBuilder.setContentTitle(s1);
        mBuilder.setContentText(s2);
        mBuilder.setVibrate(v);
        mBuilder.setAutoCancel(false);
        mBuilder.addAction(R.drawable.ic_add,"Add new",pendingIntent2);
        mBuilder.addAction(R.drawable.ic_edit,"Edit",pendingIntent);
        mBuilder.addAction(R.drawable.ic_edit,"Share",sharePI);

        if(cb)
            mBuilder.setOngoing(true);


        mNotificationManager =
                (NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(notid, mBuilder.build());
    }
}
