package com.classwork.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.classwork.firebase.databinding.ActivityMain2Binding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {
    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);


        binding.sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressBar.setVisibility(View.VISIBLE);
                String number = binding.mobilenumber.getText().toString();

                if (number.isEmpty()) {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.mobilenumber.setError("Please enter your mobile number");
                    binding.mobilenumber.requestFocus();

                    return;
                } else if (number.length() < 10) {
                    binding.progressBar.setVisibility(View.GONE);
                    binding.mobilenumber.setError("Please enter valid mobile number");
                    binding.mobilenumber.requestFocus();
                    return;
                } else {
                    binding.progressBar.setVisibility(View.GONE);
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+880" + number,
                            +60,
                            TimeUnit.SECONDS,
                            MainActivity2.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    binding.progressBar.setVisibility(View.GONE);
                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    binding.progressBar.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity2.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String varificatioID, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(varificatioID, forceResendingToken);

                                    binding.progressBar.setVisibility(View.GONE);
                                    Intent intent = new Intent(MainActivity2.this, VerifyOtp.class);
                                    intent.putExtra("mobile", number);
                                    intent.putExtra("verificationId", varificatioID);
                                    startActivity(intent);

                                }
                            });

                }

            }
        });

    }
}