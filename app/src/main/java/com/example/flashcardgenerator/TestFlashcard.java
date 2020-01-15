package com.example.flashcardgenerator;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TestFlashcard extends AppCompatActivity {

    boolean cardFlipped = false;
    ArrayList<String[]> cardData = new ArrayList<String[]>();
    int cardIndex = 1;
    TextView topRow;
    TextView botRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_flashcard);
        final View flashcard = findViewById(R.id.flashcard);
        topRow = findViewById(R.id.top_row);
        botRow = findViewById(R.id.bot_row);

        flashcard.setOnTouchListener(new OnSwipeTouchListener() {
            @Override
            public void onSwipeLeft() {
                cardIndex += 1;
                if (cardIndex < cardData.size()) {
                    displayInfo(cardIndex, false);
                } else if (cardIndex == cardData.size()) {
                    topRow.setText("Reached end of list");
                    botRow.setText("");
                    topRow.setTextSize(40);
                } else {
                    cardIndex = 1;
                    displayInfo(cardIndex, false);
                }
            }

            @Override
            public void onSwipeRight() {
                cardIndex -= 1;
                if (cardIndex > 0) {
                    displayInfo(cardIndex, false);
                } else if (cardIndex == 0) {
                    topRow.setText("Reached start of list");
                    botRow.setText("");
                    topRow.setTextSize(40);
                } else {
                    cardIndex = cardData.size() - 1;
                    displayInfo(cardIndex, false);
                }
            }
        });
        Intent intent = getIntent();
        cardData = (ArrayList<String[]>) intent.getSerializableExtra("FLASHCARD_DATA");
        displayInfo(cardIndex, false);
        cardFlipped = false;
    }

    public void flipCard(View v) {
        if (cardIndex > 0 && cardIndex < cardData.size()) {  // Data to display when flipped
            cardFlipped = !cardFlipped;
            displayInfo(cardIndex, cardFlipped);
        }
    }

    private void displayInfo(int index, boolean flipState) {
        cardFlipped = flipState;
        if (flipState) {  // Info for card flipped (answer)
            topRow.setText(cardData.get(cardIndex)[2]);
            botRow.setText("");
            topRow.setTextSize(30);
        } else {  // Info for card not flipped (question)
            topRow.setText(cardData.get(index)[0]);
            botRow.setText(cardData.get(index)[1]);
            topRow.setTextSize(40);
        }
    }
}
