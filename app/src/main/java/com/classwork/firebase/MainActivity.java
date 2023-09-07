package com.classwork.firebase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.classwork.firebase.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//initialize firebasedatabase reference
        databaseReference = FirebaseDatabase.getInstance().getReference("users");

//save data onlclick litener
        binding.butn.setOnClickListener(v -> {
            String email = binding.email.getText().toString();
            String pass = binding.passweord.getText().toString();

            //send data to firebase

            saveData(email, pass);


            // Toast.makeText(this, email+" "+pass, Toast.LENGTH_SHORT).show();
        });

        binding.authusinggoogle.setOnClickListener(v -> {
            //sent  to another activity
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);


            startActivity(intent);

        });
    }

    private void saveData(String email, String pass) {

//getting key
        String key = databaseReference.push().getKey();
        Model model = new Model(email, pass);
        databaseReference.child(key).setValue(model);

        //show alertdialog
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Data saved");
        builder.setMessage("Data saved successfully");
        builder.setPositiveButton("ok", (dialog, which) -> dialog.dismiss());
        builder.show();


    }
}