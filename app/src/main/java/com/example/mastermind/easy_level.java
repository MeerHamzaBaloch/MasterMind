package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class easy_level extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener  {


    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME="mypref";
    private static final String KEY_NAME="username";
    TextView tv_name, score;
    ImageView logout_btn;

    //for game logic
    TextView question;
    EditText answer;
    Button check;
    int score_int;

    String[] pets = new String[]{
            "BIRD","DOG","CAT","PANDA",
            "TURTLE","RABBIT","MOUSE",
            "HORSE","COW","FOX","CAMEL",
            "WOLF","HEN","GOAT","DONKEY"
    };

    String pet;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_level);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);
        tv_name = (TextView) findViewById(R.id.username_tv_easy);
        logout_btn = (ImageView) findViewById(R.id.logout);

        question = (TextView) findViewById(R.id.question_text_easy);
        answer = (EditText) findViewById(R.id.easy_answer);
        check= (Button) findViewById(R.id.check_btn_easy);
        score = (TextView) findViewById(R.id.score_easy);




        pet = pets[random.nextInt(pets.length)];
        question.setText(mixWords(pet));


        if (name != null){
            tv_name.setText(name);
        }


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (answer.getText().toString().isEmpty()){
                    Toast.makeText(easy_level.this,"Enter you guess first", Toast.LENGTH_SHORT).show();
                }
                else if (answer.getText().toString().equalsIgnoreCase(pet)){
                    Toast.makeText(easy_level.this,"You are correct", Toast.LENGTH_SHORT).show();

                    score_int +=10;
                    score.setText(String.valueOf(score_int));
                    answer.setText("");


                    pet = pets[random.nextInt(pets.length)];
                    question.setText(mixWords(pet));
                }else {
                    Toast.makeText(easy_level.this,"You are wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });









    }



    @Override
    public  void  onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(easy_level.this);
        builder.setMessage("Are you sure you want to exit?");
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

                Intent intent = new Intent(easy_level.this,level_select.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.options);
        popup.show();
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_option:
                final AlertDialog.Builder builder = new AlertDialog.Builder(easy_level.this);
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

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        Toast.makeText(easy_level.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                        finish();

                        Intent intent = new Intent(easy_level.this,LoginActivity.class);
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            default:
                return false;
        }
    }


    private String mixWords(String word){

        List<String> words = Arrays.asList(word.split(""));
        Collections.shuffle(words);
        String mixed ="";

        for (String i:words){

            mixed += i;

        }
        return mixed;
    }

}