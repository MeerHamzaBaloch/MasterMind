package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class ViewWordsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_words);
        btn_logout = (Button) findViewById(R.id.words_logout);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DBHelper databaseHelperClass = new DBHelper(this);
        List<WordsModelClass> wordsModelClasses = databaseHelperClass.getWordsList();

        if (wordsModelClasses.size() > 0){
            WordsAdapterClass wordsAdapterClass = new WordsAdapterClass(wordsModelClasses, ViewWordsActivity.this);
            recyclerView.setAdapter(wordsAdapterClass);
        }else {
            Toast.makeText(this, "There is no words in the database", Toast.LENGTH_SHORT).show();
        }


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ViewWordsActivity.this);
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



                        Toast.makeText(ViewWordsActivity.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(ViewWordsActivity.this,LoginActivity.class);
                        startActivity(intent);




                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });


    }
}