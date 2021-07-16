package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddWordsActivity extends AppCompatActivity {
    EditText editText_name,editText_type;
    Button button_add,button_view;
    Button btn_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        editText_name = findViewById(R.id.edittext_name);
        editText_type = findViewById(R.id.type);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);
        btn_logout = (Button) findViewById(R.id.add_words_logout);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString().toUpperCase();
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


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddWordsActivity.this);
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



                        Toast.makeText(AddWordsActivity.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent = new Intent(AddWordsActivity.this,LoginActivity.class);
                        startActivity(intent);




                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

    }
}