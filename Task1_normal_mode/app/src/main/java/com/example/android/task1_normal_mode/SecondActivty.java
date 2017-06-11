package com.example.android.task1_normal_mode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activty);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.STRINGTEXT);
        TextView itemName = (TextView) findViewById(R.id.item_name);
        itemName.setText(message);
    }
}
