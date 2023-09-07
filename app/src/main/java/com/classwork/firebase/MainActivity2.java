package com.classwork.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String email = getIntent().getStringExtra("email");
        String pass = getIntent().getStringExtra("pass");
        TextView views= findViewById(R.id.textView);
        views.setText(email+" "+pass);

    }
}