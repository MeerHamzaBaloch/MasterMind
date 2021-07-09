package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewWordsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_words);

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

    }
}