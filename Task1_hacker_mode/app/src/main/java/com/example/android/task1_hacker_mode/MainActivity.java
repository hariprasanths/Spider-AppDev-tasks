package com.example.android.task1_hacker_mode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String STRINGTEXT = "com.example.myfirstapp.MESSAGE";
    ArrayList<String> arrayList = new ArrayList<String>();
    String inputText;
    int inputPosition;
    ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText addTextbox = (EditText) findViewById(R.id.add_textbox);
        Button addButton = (Button) findViewById(R.id.add_button);
        final ListView listView = (ListView) findViewById(R.id.list);
        listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(listAdapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputText = addTextbox.getText().toString();
                if (inputText.equals(""))
                    Toast.makeText(getApplicationContext(), "Enter the text and then click add", Toast.LENGTH_SHORT).show();
                else {
                    arrayList.add(inputText);
                    listAdapter.notifyDataSetChanged();
                    addTextbox.getText().clear();
                }
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this, SecondActivty.class);
                intent.putExtra(STRINGTEXT, listView.getItemAtPosition(position).toString());
                startActivity(intent);
            }
        });
        Button removeButton = (Button) findViewById(R.id.remove_button);
        final EditText removeTextbox = (EditText) findViewById(R.id.remove_textbox);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
