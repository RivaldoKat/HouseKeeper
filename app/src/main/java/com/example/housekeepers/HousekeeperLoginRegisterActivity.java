package com.example.housekeepers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HousekeeperLoginRegisterActivity extends AppCompatActivity {
    private Button keeperLoginButton;
    private Button keeperRegisterButton;
    private TextView keeperRegisterLink;
    private TextView keeperStatus;
    private EditText EmailKeeper;
    private EditText PasswordKeeper;


    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeper_login_register);

        mAuth = FirebaseAuth.getInstance();

        keeperLoginButton = (Button) findViewById(R.id.keeper_login_btn);
        keeperRegisterButton = (Button) findViewById(R.id.keeper_register_btn);
        keeperRegisterLink = (TextView) findViewById(R.id.register_keeper_link);
        keeperStatus = (TextView) findViewById(R.id.keeper_status);
        EmailKeeper = (EditText) findViewById(R.id.keeper_text_email);
        PasswordKeeper = (EditText) findViewById(R.id.keeper_text_password);


        keeperRegisterButton.setVisibility(View.INVISIBLE);
        keeperRegisterButton.setEnabled(false);
        keeperRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keeperLoginButton.setVisibility(View.INVISIBLE);
                keeperRegisterLink.setVisibility(View.INVISIBLE);
                keeperStatus.setText("Register to start working");

                keeperRegisterButton.setVisibility(View.VISIBLE);
                keeperRegisterButton.setEnabled(true);
            }
        });
        keeperLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailKeeper.getText().toString();
                String password = PasswordKeeper.getText().toString();
                
                SignInKeeper(email , password);
            }
        });

        keeperRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailKeeper.getText().toString();
                String password = PasswordKeeper.getText().toString();

                RegisterKeeper(email , password);
            }
        });


    }

    private void SignInKeeper(String email, String password) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(HousekeeperLoginRegisterActivity.this , "Please write email" , Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(HousekeeperLoginRegisterActivity.this , "Please enter password" , Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(HousekeeperLoginRegisterActivity.this , "HouseKeeper logged in successfully" , Toast.LENGTH_SHORT).show();
                        Intent keeperIntent = new Intent(HousekeeperLoginRegisterActivity.this , MainActivity.class);
                        startActivity(keeperIntent);
                    }
                    else{
                        Toast.makeText(HousekeeperLoginRegisterActivity.this , "log in Unsuccessful! Please try again." , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void RegisterKeeper(String email, String password) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(HousekeeperLoginRegisterActivity.this , "Please write email" , Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(HousekeeperLoginRegisterActivity.this , "Please enter password" , Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(HousekeeperLoginRegisterActivity.this , "HouseKeeper registered successfully" , Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(HousekeeperLoginRegisterActivity.this , "registration Unsuccessful! Please try again." , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}