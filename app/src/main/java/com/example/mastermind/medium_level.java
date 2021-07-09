package com.example.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class medium_level extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener  {


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

    String[] vegs = new String[]{
            "CORN","CARROT","GINGER","POTATO",
            "ONION","LETTUCE","LEMON",
            "TOMATO","RADISH","PEAS","GARLIC",
            "OLIVE","MINT","MASHROOM","TURNIP"
    };

    String veg;
    Random random = new Random();
    ProgressBar progressBar;
    CountDownTimer count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_level);
        MediaPlayer player =MediaPlayer.create(medium_level.this,R.raw.bgsoundbtn);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);
        tv_name = (TextView) findViewById(R.id.username_tv_medium);
        logout_btn = (ImageView) findViewById(R.id.logout);

        question = (TextView) findViewById(R.id.question_text_medium);
        answer = (EditText) findViewById(R.id.medium_answer);
        check= (Button) findViewById(R.id.check_btn_medium);
        score = (TextView) findViewById(R.id.score_medium);
        progressBar = (ProgressBar) findViewById(R.id.Progressbar_medium);




        veg = vegs[random.nextInt(vegs.length)];
        question.setText(mixWords(veg));


        //timer
        count = new CountDownTimer(60*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progressBar.setProgress((int) (millisUntilFinished/600));
            }

            @Override
            public void onFinish() {
                final AlertDialog.Builder builder2 = new AlertDialog.Builder(medium_level.this);
                builder2.setMessage("Do you want to play again?");
                builder2.setCancelable(false);
                builder2.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(medium_level.this,level_select.class);
                        startActivity(intent);
                    }
                });
                builder2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        veg = vegs[random.nextInt(vegs.length)];
                        question.setText(mixWords(veg));
                        answer.setText("");
                        score_int = 0;
                        score.setText(String.valueOf(score_int));

                        count.start();

                    }
                });
                AlertDialog alertDialog2 = builder2.create();
                alertDialog2.show();
            }

        };

        count.start();


        if (name != null){
            tv_name.setText(name);
        }


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.start();

                if (answer.getText().toString().isEmpty()){
                    Toast.makeText(medium_level.this,"Enter you guess first", Toast.LENGTH_SHORT).show();
                }
                else if (answer.getText().toString().equalsIgnoreCase(veg)){
                    Toast.makeText(medium_level.this,"You are correct", Toast.LENGTH_SHORT).show();

                    score_int +=10;
                    score.setText(String.valueOf(score_int));
                    answer.setText("");


                    veg = vegs[random.nextInt(vegs.length)];
                    question.setText(mixWords(veg));

                    count.start();
                }else {
                    Toast.makeText(medium_level.this,"You are wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });









    }



    @Override
    public  void  onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(medium_level.this);
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

                Intent intent = new Intent(medium_level.this,level_select.class);
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
                final AlertDialog.Builder builder10 = new AlertDialog.Builder(medium_level.this);
                builder10.setMessage("Are you sure you want to logout?");
                builder10.setCancelable(true);
                builder10.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder10.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.clear();
                        editor.commit();
                        Toast.makeText(medium_level.this, "Log out successfully", Toast.LENGTH_SHORT).show();
                        finish();

                        Intent intent = new Intent(medium_level.this,LoginActivity.class);
                        startActivity(intent);
                    }
                });
                AlertDialog alertDialog = builder10.create();
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