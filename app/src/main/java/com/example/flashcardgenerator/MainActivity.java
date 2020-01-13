package com.example.flashcardgenerator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ArrayList<String[]> cardData = new ArrayList<String[]>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void testPress(View view) {
        if (cardData.isEmpty()) {  // .csv file not imported
            TextView debugView = findViewById(R.id.debug_text);
            debugView.setText("ERROR: No .csv file imported - import a .csv file first");
        } else {
            Intent intent = new Intent(this, TestFlashcard.class);
            intent.putExtra("FLASHCARD_DATA", cardData);
            startActivity(intent);
        }
    }

    public void importPress(View view) {
        // Check if permissions granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {  // Permission not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
        } else {  // Permission already granted
            String path = Environment.getExternalStorageDirectory().getPath();
            String csvPath = path + "/Download/flashcard-data.csv";
            CSVReader csv = new CSVReader();
            cardData = csv.readCSV(csvPath);
            String[] titles = cardData.get(0);
            TextView debugView = findViewById(R.id.debug_text);
            debugView.setText(Arrays.toString(titles));
        }
    }
}
