package com.example.finapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.snackbar.ContentViewCallback;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class enterMob extends AppCompatActivity {

    EditText enterNumber;
    Button otpButton;
    ProgressBar pBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        enterNumber = findViewById(R.id.enter_mobile_number);
        otpButton = findViewById(R.id.buttonGetOtp);
        pBar = findViewById(R.id.otpProgBar);

        otpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enterNumber.getText().toString().trim().isEmpty()) {
                    if ((enterNumber.getText().toString().trim()).length() == 10) {
                        pBar.setVisibility(View.VISIBLE);
                        otpButton.setVisibility(View.INVISIBLE);

                        /*FireBase code*/
                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + enterNumber.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                enterMob.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        pBar.setVisibility(View.GONE);
                                        otpButton.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        pBar.setVisibility(View.GONE);
                                        otpButton.setVisibility(View.VISIBLE);
                                        Toast.makeText(enterMob.this,
                                                e.getMessage(),
                                                Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String otpReceived, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        pBar.setVisibility(View.GONE);
                                        otpButton.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), verifyOTP.class);
                                        intent.putExtra("mobile", enterNumber.getText().toString());
                                        intent.putExtra("otp received", otpReceived);

                                        startActivity(intent);
                                    }
                                }
                        );

                        /*End FireBase*/


//                        Intent intent = new Intent(getApplicationContext(), verifyOTP.class);
//                        intent.putExtra("mobile",enterNumber.getText().toString());
//                        startActivity(intent);
                    } else {
                        Toast.makeText(enterMob.this,
                                "please enter mob number",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(enterMob.this,
                            "Please enter correct number",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
