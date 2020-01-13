package com.example.flashcardgenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
    public static ArrayList<String[]> readCSV(String csvPath) {
        BufferedReader br = null;
        String line = "";
        String delimiter = ",";
        ArrayList<String[]> words = new ArrayList<String[]>();
        try {
            br = new BufferedReader(new FileReader(csvPath));
            while ((line = br.readLine()) != null) {
                String[] wordInfo = line.split(delimiter);
                words.add(wordInfo);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return words;
    }
}
