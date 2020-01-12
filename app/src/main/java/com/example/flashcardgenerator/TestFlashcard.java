package com.example.flashcardgenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TestFlashcard extends AppCompatActivity {

    public static boolean cardFlipped = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_flashcard);
        TextView debugView = findViewById(R.id.debug_default);
        debugView.setText(Boolean.toString(cardFlipped));
    }

    public void flipCard(View v) {
        if (!cardFlipped) {
            setContentView(R.layout.flipped_flashcard);
            cardFlipped = true;
            TextView debugView = findViewById(R.id.debug_flip);
            debugView.setText(Boolean.toString(cardFlipped));
        } else {
            setContentView(R.layout.activity_test_flashcard);
            cardFlipped = false;
            TextView debugView = findViewById(R.id.debug_default);
            debugView.setText(Boolean.toString(cardFlipped));
        }
    }
}
