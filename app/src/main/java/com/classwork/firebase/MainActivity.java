package com.classwork.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.classwork.firebase.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.butn.setOnClickListener(v -> {
            String email = binding.email.getText().toString();
            String pass = binding.passweord.getText().toString();

            //send data to firebase



            //sent data to another activity

            Intent intent = new Intent(MainActivity.this, MainActivity2.class);

            intent.putExtra("email", email);
            intent.putExtra("pass", pass);

            startActivity(intent);
            // Toast.makeText(this, email+" "+pass, Toast.LENGTH_SHORT).show();
        });


    }
}