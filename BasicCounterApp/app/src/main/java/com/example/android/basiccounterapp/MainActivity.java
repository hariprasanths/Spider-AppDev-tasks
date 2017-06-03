package com.example.android.basiccounterapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int i=0;
    public static final String pref = "My Prefs File";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharefprefs = getSharedPreferences(pref,0);
        i = sharefprefs.getInt("count",0);
        display(i);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                display(++i);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = getSharedPreferences(pref,0).edit();
        editor.putInt("count",i);
        editor.commit();
    }

    void display(int i)
    {
        TextView textView = (TextView) findViewById(R.id.textview);
        textView.setText(String.valueOf(i));
    }

}
