package com.example.ahsan.myfoodapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahsan.myfoodapp.R;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class ActivityCheckOut extends AppCompatActivity {

    EditText EDname,EDphone,EDemail,EDaddress;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        EDname = findViewById(R.id.edtName);
        EDphone = findViewById(R.id.edtPhone);
        EDemail = findViewById(R.id.edtEmail);
        EDaddress = findViewById(R.id.edtOrderList);
        btn = findViewById(R.id.btnSend);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    Toast.makeText(ActivityCheckOut.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent =  new Intent(ActivityCheckOut.this,ActivityConfirmation.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public boolean validate(){
        String name,phone,email,address;
        boolean error = false;
        name = EDname.getText().toString().trim();
        phone = EDphone.getText().toString().trim();
        email = EDemail.getText().toString().trim();
        address = EDaddress.getText().toString().trim();

        String regexStr = "^[0-9]{11}$";
        String namestr = "/^[A-Za-z\\s]+$/";

        if(name.equals("")){
            error = true;
            EDname.setError("Field Empty");
        }
        if(phone.equals("")){
            error = true;
            EDphone.setError("Field Empty");
        }
        if(address.equals("")){
            error = true;
            EDaddress.setError("Field Empty");
        }
        if(email.equals("")){
            error = true;
            EDemail.setError("Field Empty");
        }

        if(error){
            return false;
        }

        if(!phone.matches(regexStr)){
            error = true;
            EDphone.setError("Invalid Phone Number");
        }
        if(!isValidEmailAddress(email)){
            error = true;

            EDemail.setError("Invalid Email Address");
        }

        if(address.length()<10){
            error = true;
            EDaddress.setError("Invalid Address");
        }

//        if(!name.matches(namestr)){
//            error = true;
//            EDname.setError("Invalid Name");
//        }

        return !error;
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
}