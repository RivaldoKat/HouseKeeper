package com.example.housekeepers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ClientLoginRegisterActivity extends AppCompatActivity {
    private Button clientLoginButton;
    private Button clientRegisterButton;
    private TextView clientRegisterLink;
    private TextView clientStatus;
    private EditText EmailClient;
    private EditText PasswordClient;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login_register);

        clientLoginButton = (Button) findViewById(R.id.client_login_btn);
        clientRegisterButton = (Button) findViewById(R.id.client_register_btn);
        clientRegisterLink = (TextView) findViewById(R.id.register_client_link);
        clientStatus = (TextView) findViewById(R.id.keeper_status);
        EmailClient = (EditText) findViewById(R.id.email_client);
        PasswordClient = (EditText) findViewById(R.id.password_client);

        mAuth = FirebaseAuth.getInstance();

        clientRegisterButton.setVisibility(View.INVISIBLE);
        clientRegisterButton.setEnabled(false);
        clientRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientLoginButton.setVisibility(View.INVISIBLE);
                clientRegisterLink.setVisibility(View.INVISIBLE);
                clientStatus.setText("Register to request service");

                clientRegisterButton.setVisibility(View.VISIBLE);
                clientRegisterButton.setEnabled(true);
            }
        });

        clientRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailClient.getText().toString();
                String password = PasswordClient.getText().toString();

                Registering(email , password);
            }
        });

        clientLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailClient.getText().toString();
                String password = PasswordClient.getText().toString();

                SignInClient(email , password);
            }
        });

    }

    private void SignInClient(String email, String password) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(ClientLoginRegisterActivity.this , "Please write email" , Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(ClientLoginRegisterActivity.this , "Please enter password" , Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ClientLoginRegisterActivity.this , "Client Sign in successfully" , Toast.LENGTH_SHORT).show();
                        Intent keeperIntent = new Intent(ClientLoginRegisterActivity.this , MainActivity.class);
                        startActivity(keeperIntent);
                    }
                    else{
                        Toast.makeText(ClientLoginRegisterActivity.this , "Sign in Unsuccessful! Please try again." , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void Registering(String email, String password) {
        if(TextUtils.isEmpty(email)){
            Toast.makeText(ClientLoginRegisterActivity.this , "Please write email" , Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(ClientLoginRegisterActivity.this , "Please enter password" , Toast.LENGTH_SHORT).show();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(ClientLoginRegisterActivity.this , "Client registered successfully" , Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(ClientLoginRegisterActivity.this , "registration Unsuccessful! Please try again." , Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}