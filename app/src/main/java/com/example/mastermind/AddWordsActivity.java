package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddWordsActivity extends AppCompatActivity {
    EditText editText_name,editText_type;
    Button button_add,button_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        editText_name = findViewById(R.id.edittext_name);
        editText_type = findViewById(R.id.type);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString();
                String type = editText_type.getText().toString();

                if (stringName.length() <=0 || type.length() <=0){
                    Toast.makeText(AddWordsActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }else {
                    DBHelper databaseHelperClass = new DBHelper(AddWordsActivity.this);
                    WordsModelClass wordsModelClass = new WordsModelClass(stringName,type);
                    databaseHelperClass.addWords(wordsModelClass);
                    Toast.makeText(AddWordsActivity.this, "Add word Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddWordsActivity.this, ViewWordsActivity.class);
                startActivity(intent);
            }
        });

    }
}