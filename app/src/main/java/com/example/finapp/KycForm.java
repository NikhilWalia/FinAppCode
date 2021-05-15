package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class KycForm extends AppCompatActivity {

    private static final String TAG = "KycForm";
    private TextInputEditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    FirebaseDatabase mkycDatabase;
    DatabaseReference mdataBaseRef;

    Button msubmitKycButton = findViewById(R.id.idsubmitkycbutton);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_form);
        mDisplayDate = findViewById(R.id.iddob);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextInputEditText fName = findViewById(R.id.idfirstname);
                TextInputEditText lName = findViewById(R.id.idlastname);
                TextInputEditText pan = findViewById(R.id.idpan);
                TextInputEditText adhr = findViewById(R.id.idadhr);
                TextInputEditText email = findViewById(R.id.idemail);
                TextInputEditText altemail = findViewById(R.id.idaltemail);
                TextInputEditText phone = findViewById(R.id.idphonenumber);
                TextInputEditText dob = findViewById(R.id.iddob);

                Calendar cal = Calendar.getInstance();
                int yy = cal.get(Calendar.YEAR);
                int mm = cal.get(Calendar.MONTH);
                int dd = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        KycForm.this,
                        android.R.style.Widget_Holo_ActionBar_Solid,
                        mDateSetListener,
                        yy, mm, dd);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                msubmitKycButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String _fName = fName.getText().toString();
                        String _lName = lName.getText().toString();
                        String _adhar = adhr.getText().toString();
                        String _phone = phone.getText().toString();
                        String _pan  = pan.getText().toString();
                        String _dob = dob.getText().toString();
                        String _email = email.getText().toString();
                        String _altEmail = altemail.getText().toString();

                        mkycDatabase = FirebaseDatabase.getInstance();
                        mdataBaseRef = mkycDatabase.getReference("kyc");
                        //Helper class to get and set kyc of user
                        KycHelper kycHelper = new KycHelper(_fName, _lName, _adhar, _pan, _phone, _dob, _email, _altEmail);
                        mdataBaseRef.child(_adhar).setValue(kycHelper);
                    }
                 }

                );
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String dob = dayOfMonth + "/" + month + "/" + year;
                //Log.d(TAG, "entered dob is dd/mm/yy ", dob);
                mDisplayDate.setText(dob);
            }
        };

    }
}