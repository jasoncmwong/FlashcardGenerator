package com.example.flashcardgenerator;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TestFlashcard extends AppCompatActivity {

    public static boolean cardFlipped = false;
    ArrayList<String[]> cardData = new ArrayList<String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_flashcard);
        View flashcard = findViewById(R.id.flashcard);
        flashcard.setOnTouchListener(new OnSwipeTouchListener() {
            @Override
            public void onSwipeLeft() {
                TextView topRow = findViewById(R.id.top_row);
                TextView botRow = findViewById(R.id.bot_row);
                topRow.setText(cardData.get(2)[0]);
                botRow.setText(cardData.get(2)[1]);
            }

            @Override
            public void onSwipeRight() {
                TextView topRow = findViewById(R.id.top_row);
                TextView botRow = findViewById(R.id.bot_row);
                topRow.setText(cardData.get(0)[0]);
                botRow.setText(cardData.get(0)[1]);
            }
        });
        Intent intent = getIntent();
        cardData = (ArrayList<String[]>) intent.getSerializableExtra("FLASHCARD_DATA");
        TextView topRow = findViewById(R.id.top_row);
        TextView botRow = findViewById(R.id.bot_row);
        topRow.setText(cardData.get(1)[0]);
        botRow.setText(cardData.get(1)[1]);
    }

    public void flipCard(View v) {
        if (cardFlipped) {
            cardFlipped = false;
            TextView topRow = findViewById(R.id.top_row);
            TextView botRow = findViewById(R.id.bot_row);
            topRow.setText(cardData.get(1)[0]);
            topRow.setTextSize(40);
            botRow.setText(cardData.get(1)[1]);
        } else {
            cardFlipped = true;
            TextView topRow = findViewById(R.id.top_row);
            TextView botRow = findViewById(R.id.bot_row);
            topRow.setText(cardData.get(1)[2]);
            topRow.setTextSize(20);
            botRow.setText("");
        }
    }
}
