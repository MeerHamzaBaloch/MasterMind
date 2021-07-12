package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class view_scores extends AppCompatActivity {

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DBHelper db;

    ListView scores_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);

        scores_list = (ListView) findViewById(R.id.scores_list);



        db = new DBHelper(this);
        listItem = new ArrayList<>();

        viewScores();
    }

    private void viewScores() {
        Cursor cursor = db.viewScores();

        if(cursor.getCount() == 0){
            Toast.makeText(this,"No score", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(0));
                listItem.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, listItem);
            scores_list.setAdapter(adapter);

        }
    }
}