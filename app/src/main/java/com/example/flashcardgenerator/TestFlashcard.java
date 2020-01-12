package com.example.flashcardgenerator;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestFlashcard extends AppCompatActivity {

    public static boolean cardFlipped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_flashcard);
    }

    public void flipCard(View v) {
        if (cardFlipped) {
            cardFlipped = false;
            TextView topRow = findViewById(R.id.top_row);
            TextView botRow = findViewById(R.id.bot_row);
            topRow.setText(getResources().getString(R.string.py_flashcards));
            botRow.setText(getResources().getString(R.string.cc_flashcards));
        } else {
            cardFlipped = true;
            TextView topRow = findViewById(R.id.top_row);
            TextView botRow = findViewById(R.id.bot_row);
            topRow.setText(getResources().getString(R.string.def_flashcards));
            botRow.setText("");
        }
    }
}
