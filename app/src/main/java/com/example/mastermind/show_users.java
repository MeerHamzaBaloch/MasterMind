package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class show_users extends AppCompatActivity {

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DBHelper db;

    ListView userlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_users);

        userlist = (ListView) findViewById(R.id.user_list);



        db = new DBHelper(this);
        listItem = new ArrayList<>();

        viewData();
    }

    private void viewData() {

        Cursor cursor = db.viewUsers();

        if(cursor.getCount() == 0){
            Toast.makeText(this,"No users", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(0));
                listItem.add(cursor.getString(1));
                listItem.add(cursor.getString(2));
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, listItem);
            userlist.setAdapter(adapter);

        }
    }
}