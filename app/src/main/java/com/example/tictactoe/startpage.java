package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class startpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startpage);
        Button start = findViewById(R.id.startbutton);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent("com.example.tictactoe.MainActivity");
                EditText e1 = findViewById(R.id.p1name);
                EditText e2 = findViewById(R.id.p2name);
                i1.putExtra("pname1",e1.getText().toString());
                i1.putExtra("pname2",e2.getText().toString());
                startActivity(i1);
            }
        });
    }
}
