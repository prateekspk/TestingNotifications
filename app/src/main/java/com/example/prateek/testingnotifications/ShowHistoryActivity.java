package com.example.prateek.testingnotifications;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ShowHistoryActivity extends AppCompatActivity {
    TheDataBase dataBase;
    ListView l1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBase=new TheDataBase(this);

        setContentView(R.layout.activity_show_history);
        l1= (ListView) findViewById(R.id.listview);

        String data= dataBase.Getalldata();
//        String a=values.toString();
//        String[] b=values.split("\n");
//        String d1="Sunday Monday Tueasday Wednerday Thursday Friday Saturday";
        String[] dummy={"Sunday","Monday","Tueasday","Wednerday","Thursday","Friday","Saturday"};
//        String[] p1=d1.split(" ");

        Message_Toast.main(this,data);

//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dummy);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dummy);
        l1.setAdapter(adapter);
    }
}
