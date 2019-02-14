package com.example.morgansweatman.lab3;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList secretWords = new ArrayList(Arrays.asList("APPLE", "BANANA", "CHERRY"));
    private String word;
    private Random rand = new Random();

    private TextView t;
    private TextView c;
    private EditText e;

    private int index;
    private StringBuilder sb;

    private String getShuffledWord() {
        // set one of your random words from secretWords to word
        int random = rand.nextInt(secretWords.size());
        word = (String) secretWords.get(random);

        String shuffledWord = "";
        ArrayList<String> splitWord = new ArrayList(Arrays.asList(word.split("")));
        Collections.shuffle(splitWord);
        for (String c : splitWord) {
            shuffledWord += c;
        }
        return shuffledWord;
    }

    public void guessButtonClicked(View v) {
        String guess = e.getText().toString();
        guess = guess.toUpperCase();

        if(guess.charAt(0) == (word.charAt(index))){
            sb.append(guess);
        }
        else {
            c.setText("Incorrect guess!");
        }
        c.setText(sb.toString());
        e.setText("");
        index++;

        if(sb.length() == word.length()){
            c.setText("You correctly guessed the word!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // display scrambled word to the user
        e = (EditText) findViewById(R.id.playerGuess);
        c = (TextView) findViewById(R.id.correctGuesses);
        sb = new StringBuilder();
        index = 0;

        // set scrambled word TextView to a random scrambled word onCreate
        String s = getShuffledWord();
        t = (TextView) findViewById(R.id.scrambledWordLabel);
        t.setText(s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
