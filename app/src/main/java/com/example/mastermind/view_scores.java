package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class view_scores extends AppCompatActivity {

    ArrayList<String> listItem;
    ArrayAdapter adapter;
    DBHelper db;
    Button btn_logout;

    ListView scores_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_scores);
        btn_logout = (Button) findViewById(R.id.scores_logout);

        scores_list = (ListView) findViewById(R.id.scores_list);



        db = new DBHelper(this);
        listItem = new ArrayList<>();

        viewScores();

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(view_scores.this);
                builder.setMessage("Are you sure you want to logout?");
                builder.setCancelable(true);
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {



                        Toast.makeText(view_scores.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(view_scores.this,LoginActivity.class);
                        startActivity(intent);




                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

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