package com.example.findword;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    TextView tvInfo;
    TextView tvWord;
    EditText edi;
    Button buCheck;
    Button buNew;

    String currentWord;
    Queue<String> dictionary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvInfo = findViewById(R.id.tv_info);
        tvWord = findViewById(R.id.tv_word);

        edi = findViewById(R.id.et_guess);

        buCheck = findViewById(R.id.buCheck);
        buNew = findViewById(R.id.buNew);
        dictionary = setUpDictionary();
    }

    public void checkWord(View view) {
        if (edi.getText().toString().equalsIgnoreCase(currentWord)) {
            tvInfo.setText("Correct");
            buNew.setEnabled(true);
            buCheck.setEnabled(false);
        } else {
            tvInfo.setText("Try again");
        }
    }


    public void newEvent(View view) {
        if (dictionary.isEmpty()) {
            tvInfo.setText("You won");
            buNew.setEnabled(false);
        } else {
            tvInfo.setText("");
            buNew.setEnabled(true);
            currentWord = dictionary.poll();
            tvWord.setText(shuffleWord(currentWord));
            edi.setText("");
            buCheck.setEnabled(true);
        }
    }

    private Queue<String> setUpDictionary() {
        Queue<String> dictionary = new PriorityQueue<>();
        dictionary.add("apple");
        dictionary.add("banana");
        dictionary.add("orange");

        return dictionary;
    }

    private String shuffleWord(String word) {
        List<String> letters = Arrays.asList(word.split(""));
        Collections.shuffle(letters);
        String emptyString = "";
        for (String letter : letters) {
            emptyString += letter;
        }
        return emptyString;
    }



}