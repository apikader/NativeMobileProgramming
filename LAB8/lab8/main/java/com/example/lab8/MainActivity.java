package com.example.lab8;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.buttonLogin);

        // Initialize SharedPreferences
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        // Check if username and password are already stored
        if (isUserLoggedIn()) {
            openSuccessPage();
        }

        // Set onClickListener for the login button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get entered username and password
                String enteredUsername = usernameEditText.getText().toString();
                String enteredPassword = passwordEditText.getText().toString();

                // Check if entered username and password are correct
                if (isValidCredentials(enteredUsername, enteredPassword)) {
                    // Store username and password in SharedPreferences
                    saveUserCredentials(enteredUsername, enteredPassword);

                    // Open success page
                    openSuccessPage();
                } else {
                    // Display a toast message for invalid credentials
                    Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isUserLoggedIn() {
        // Check if username and password are stored in SharedPreferences
        return sharedPreferences.contains("username") && sharedPreferences.contains("password");
    }

    private boolean isValidCredentials(String enteredUsername, String enteredPassword) {
        // For simplicity, compare with hardcoded values
        return enteredUsername.equals("user") && enteredPassword.equals("password");
    }

    private void saveUserCredentials(String username, String password) {
        // Save username and password in SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    private void openSuccessPage() {
        // Open success page
        Intent intent = new Intent(MainActivity.this, SuccessActivity.class);
        startActivity(intent);
        finish();  // Finish the current activity to prevent going back to the login page
    }
}
