package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        // Display a welcome message with the logged-in username
        TextView welcomeTextView = findViewById(R.id.textViewWelcome);

        // Retrieve the username from SharedPreferences
        String username = getSharedPreferences("com.example.lab8", MODE_PRIVATE)
                .getString("username", "");

        // Display the welcome message
        welcomeTextView.setText("Welcome, " + username + "!");
    }
}
