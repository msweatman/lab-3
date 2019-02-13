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

    ArrayList secretWords = new ArrayList(Arrays.asList("APPLE", "BANANA", "CHERRY"));
    private Random rand = new Random();

    // set one of your random words from secretWords to word
    int random = rand.nextInt(secretWords.size());
    String word = (String) secretWords.get(random);

    private String getScrambledWord() {
        String shuffledWord = "";
        ArrayList<String> splitWord = new ArrayList(Arrays.asList(word.split("")));
        Collections.shuffle(splitWord);
        for (String c : splitWord) {
            shuffledWord += c;
        }
        return shuffledWord;
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
        String s = getScrambledWord();
        TextView t = (TextView) findViewById(R.id.scrambledWordLabel);
        t.setText(s);
    }

    public void guessButtonClicked(View v) {
        EditText e = (EditText) findViewById(R.id.playerGuess);
        TextView t = (TextView) findViewById(R.id.correctGuesses);
        int index = 0;
        StringBuilder sb = new StringBuilder();

        String guess = e.getText().toString();
        guess = guess.toUpperCase();

        if(guess.charAt(0) == (word.charAt(index))){
            sb.append(guess);
        }
        else {
            t.setText("Incorrect guess!");
        }

        t.setText(sb.toString());
        e.setText("");
        index++;
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
