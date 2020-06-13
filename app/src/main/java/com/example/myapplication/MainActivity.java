package com.example.myapplication;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ThreadLocalRandom;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MainActivity extends AppCompatActivity {
    TextView score, orangeBox, blueBox, redBox, greenBox;
    int selectedNum = -1, randomNum = -1, result = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviews();
        getRandomNumber();
        clickListener();

    }

    @SuppressLint("SetTextI18n")
    private void checkResult() {
        if (randomNum == selectedNum) {
            result++;
            score.setText(" " + result);
            goBackToNormalState();
            getRandomNumber();
        }

    }

    private void goBackToNormalState() {
        orangeBox.setBackgroundColor(Color.YELLOW);
        blueBox.setBackgroundColor(Color.BLUE);
        redBox.setBackgroundColor(Color.RED);
        greenBox.setBackgroundColor(Color.GREEN);
    }

    private void setColor() {
        if (randomNum == 0) {
            orangeBox.setBackgroundColor(Color.parseColor("#dbdbdb"));

        } else if (randomNum == 1) {
            blueBox.setBackgroundColor(Color.parseColor("#dbdbdb"));

        } else if (randomNum == 2) {
            redBox.setBackgroundColor(Color.parseColor("#dbdbdb"));

        } else {
            greenBox.setBackgroundColor(Color.parseColor("#dbdbdb"));

        }
    }

    private void getRandomNumber() {
        randomNum = ThreadLocalRandom.current().nextInt(0, 4);
        setColor();
    }

    private void clickListener() {
        orangeBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNum = 0;
                checkResult();

            }
        });
        blueBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNum = 1;
                checkResult();

            }
        });
        redBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNum = 2;
                checkResult();

            }
        });
        greenBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedNum = 3;
                checkResult();

            }
        });

    }

    private void findviews() {
        score = findViewById(R.id.score);
        orangeBox = findViewById(R.id.orangeBox);
        blueBox = findViewById(R.id.blueBox);
        redBox = findViewById(R.id.redBox);
        greenBox = findViewById(R.id.greenBox);
    }
}