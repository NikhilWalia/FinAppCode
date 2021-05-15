package com.example.finapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verifyOTP extends AppCompatActivity {

    EditText otp1, otp2, otp3, otp4, otp5, otp6;

    String getReceivedOtp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);

        otp1 = findViewById(R.id.opt1);
        otp2 = findViewById(R.id.opt2);
        otp3 = findViewById(R.id.opt3);
        otp4 = findViewById(R.id.opt4);
        otp5 = findViewById(R.id.opt5);
        otp6 = findViewById(R.id.opt6);
        final Button submit = findViewById(R.id.buttonsubmitOtp);
        TextView textview = findViewById(R.id.enter_mobile_number);
        textview.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));

        getReceivedOtp = getIntent().getStringExtra("otpReceived");
        final ProgressBar pbar = findViewById(R.id.otpProgBar);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!otp1.getText().toString().trim().isEmpty() &&
                        !otp2.getText().toString().trim().isEmpty() &&
                        !otp3.getText().toString().trim().isEmpty() &&
                        !otp4.getText().toString().trim().isEmpty() &&
                        !otp5.getText().toString().trim().isEmpty() &&
                        !otp6.getText().toString().trim().isEmpty()) {
                    String enteredOtp = otp1.getText().toString() +
                            otp2.getText().toString() +
                            otp3.getText().toString() +
                            otp4.getText().toString() +
                            otp5.getText().toString() +
                            otp6.getText().toString();
                    if (getReceivedOtp != null && enteredOtp.equals(getReceivedOtp)) {
                        submit.setVisibility(View.INVISIBLE);
                        pbar.setVisibility(View.VISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getReceivedOtp, enteredOtp
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        submit.setVisibility(View.VISIBLE);
                                        pbar.setVisibility(View.GONE);
                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), KycForm.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(verifyOTP.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                                        }

                                    }

                                });

                    } else {
                        Toast.makeText(verifyOTP.this, "network error", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(verifyOTP.this, "otp verify", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(verifyOTP.this, "please enter otp to verify", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numerOtpMove();
        findViewById(R.id.resendOtp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        verifyOTP.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(verifyOTP.this,
                                        e.getMessage(),
                                        Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String otpReceived, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                getReceivedOtp = otpReceived;
                                Toast.makeText(verifyOTP.this,"send OTP successfully", Toast.LENGTH_LONG).show();
                            }
                        }
                );

            }
        });
    }

    private void numerOtpMove() {
        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        otp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    otp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}