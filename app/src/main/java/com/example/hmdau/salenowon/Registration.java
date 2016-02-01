package com.example.hmdau.salenowon;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void LoginClickHandler(View view) {

        Intent intent = new Intent(Registration.this, MainActivity.class);
        startActivity(intent);
    }

    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


    public void RegistrationClickHandler(View view) {



        DBHelper db = new DBHelper(getBaseContext());
      //  db.checkDataBase();

        EditText et = (EditText) findViewById(R.id.name);
        EditText editText = (EditText) findViewById(R.id.email);
        EditText editText1 = (EditText) findViewById(R.id.password);

        String savedName = et.getText().toString();
        String savedEmail = editText.getText().toString();
        String savedPassword = editText1.getText().toString();



        if (TextUtils.isEmpty(savedName.trim())){
            et.setError("Field cannot be empty");

        }
        else if (TextUtils.isEmpty(savedEmail.trim())){

            editText.setError("Field cannot be empty");


        }
        else if (!(isEmailValid(savedEmail))) {

            editText.setError("email is invalid");

        }
        else if (TextUtils.isEmpty(savedPassword)) {

            editText1.setError("Field cannot be empty");

        } else {

            long insertVariablesInDB = db.insertData(savedName, savedEmail, savedPassword);

            if (insertVariablesInDB == -1) {

                Toast.makeText(Registration.this, "User already exist, please Login", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Registration.this, MainActivity.class);
                startActivity(intent);

            } else {


                Toast.makeText(Registration.this, "Registration successful", Toast.LENGTH_LONG).show();

                db.getAllEmails();
                System.out.println("print,,..............." + insertVariablesInDB + DBHelper.email);
                System.out.println("print,,..............." + savedName + "" + savedEmail + "" + savedPassword);

                db.close();


            }
        }


    }
}
