package com.example.flashcardgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class TestFlashcardActivity extends AppCompatActivity {

    boolean cardFlipped = false;
    ArrayList<String[]> cardData = new ArrayList<String[]>();
    int cardIndex = 1;
    int numCards;
    TextView topRow;
    TextView botRow;
    EditText cardIndexText;
    TextView numCardsText;
    Random rand = new Random();

    ConstraintLayout constraintLayout;
    ConstraintSet constraintSet = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_flashcard);
        final View flashcard = findViewById(R.id.flashcard);
        topRow = findViewById(R.id.top_row);
        botRow = findViewById(R.id.bot_row);
        numCardsText = findViewById(R.id.num_cards);
        cardIndexText = findViewById(R.id.card_index);
        cardIndexText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionID, KeyEvent event) {
                if (actionID == EditorInfo.IME_ACTION_DONE ||
                        event != null &&
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (event == null || !event.isShiftPressed()) {  // User is done typing
                        int inputIndex = Integer.parseInt(cardIndexText.getText().toString());
                        if (inputIndex > numCards) {  // Input number too high
                            cardIndex = numCards;
                        } else if (inputIndex == 0) {  // Input number too low
                            cardIndex = 1;
                        } else {  // Input number between 1 and length of list
                            cardIndex = inputIndex;
                        }
                        displayInfo(cardIndex, false);
                        return true;
                    }
                }
                return false;
            }
        });

        constraintLayout = findViewById(R.id.flashcard_constraint);
        constraintSet.clone(constraintLayout);

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
        numCards = cardData.size() - 1;
        numCardsText.setText(getResources().getString(R.string.num_cards, numCards));
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
            // Modify constraints to match the answer side
            constraintSet.connect(R.id.top_row, ConstraintSet.TOP, R.id.flashcard, ConstraintSet.TOP);
            constraintSet.connect(R.id.top_row, ConstraintSet.BOTTOM, R.id.flashcard, ConstraintSet.BOTTOM);
            constraintSet.connect(R.id.bot_row, ConstraintSet.TOP, R.id.flashcard, ConstraintSet.BOTTOM);
            constraintSet.applyTo(constraintLayout);

            // Modify text
            topRow.setText(cardData.get(cardIndex)[2]);
            botRow.setText("");
            topRow.setTextSize(25);

            // Modify index
            cardIndexText.setText(getResources().getString(R.string.card_index, cardIndex));
        } else {  // Info for card not flipped (question)
            // Modify constraints to match the question side
            constraintSet.connect(R.id.top_row, ConstraintSet.TOP, R.id.py_guideline, ConstraintSet.TOP);
            constraintSet.connect(R.id.top_row, ConstraintSet.BOTTOM, R.id.cc_guideline, ConstraintSet.BOTTOM);
            constraintSet.connect(R.id.bot_row, ConstraintSet.TOP, R.id.cc_guideline, ConstraintSet.BOTTOM);
            constraintSet.applyTo(constraintLayout);

            // Modify text
            topRow.setText(cardData.get(index)[0]);
            botRow.setText(cardData.get(index)[1]);
            topRow.setTextSize(40);

            // Modify index
            cardIndexText.setText(getResources().getString(R.string.card_index, cardIndex));
        }
    }

    public void randomPress(View view) {
        int next_index = rand.nextInt(numCards) + 1;
        cardIndex = next_index;
        displayInfo(cardIndex, false);
    }
}
