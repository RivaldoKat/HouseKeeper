package com.example.housekeepers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button WelcomeKeeperButtton;//driver
    private Button WelcomeClientButtton;//customer


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        WelcomeClientButtton = (Button) findViewById(R.id.welcome_client);
        WelcomeKeeperButtton = (Button) findViewById(R.id.welcome_keeper);

        WelcomeClientButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginRegisterClientIntent = new Intent(WelcomeActivity.this , ClientLoginRegisterActivity.class);
                startActivity(LoginRegisterClientIntent);
            }
        });

        WelcomeKeeperButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginRegisterkeeperIntent = new Intent(WelcomeActivity.this , HousekeeperLoginRegisterActivity.class);
                startActivity(LoginRegisterkeeperIntent);
            }
        });
    }
}