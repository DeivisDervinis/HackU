package com.dhacks.hacku;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import javax.annotation.Nullable;

public class LoginActivity extends AppCompatActivity {

    //region Private Field Variables
    private TextInputLayout loginEmailTextInput;
    private TextInputEditText loginEmailEditText;
    private TextInputLayout loginPasswordTextInput;
    private TextInputEditText loginPasswordEditText;
    private Button btnHackuLogin;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupViews();
    }

    /**
     * Private method that initializes all the necessary views
     */
    private void setupViews() {
        loginEmailTextInput = findViewById(R.id.login_email_text_input);
        loginEmailEditText = findViewById(R.id.login_email_edit_text);
        loginPasswordTextInput = findViewById(R.id.login_password_text_input);
        loginPasswordEditText = findViewById(R.id.login_email_edit_text);
        btnHackuLogin = findViewById(R.id.btn_hacku_login);

        loginEmailEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (!isEmailValid(loginEmailEditText.getText())) {
                    loginEmailTextInput.setError("Email is invalid!");
                }
                return false;
            }
        });

        loginPasswordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (!isPasswordValid(loginPasswordEditText.getText())) {
                    loginPasswordTextInput.setError("Password is either empty or less than 8 characters!");
                }
                return false;
            }
        });

        btnHackuLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmailValid(loginEmailEditText.getText())) {
                    loginEmailTextInput.setError("Email is invalid!");
                }
                if (!isPasswordValid(loginPasswordEditText.getText())) {
                    loginPasswordTextInput.setError("Password is either empty or less than 8 characters!");
                }
                else {
                    loginEmailTextInput.setError(null);
                    loginPasswordTextInput.setError(null);

                    //TODO: ADD LOGIN LOGIC HERE

                    //TODO: DOUBLE CHECK THE LOGICAL FLOW HERE
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Private method for very simple email format validation
     *
     * @param email The Editable email text to be validated
     * @return True if the format is valid, False otherwise
     */
    private boolean isEmailValid(@Nullable Editable email) {
        return email != null && email.toString().contains("^(.+)@(.+)$");
    }

    /**
     * Private method for very simple password format validation
     *
     * @param password The Editable password text to be validated
     * @return True of the password is not null and 8 or more characters, False otherwise
     */
    private boolean isPasswordValid(@Nullable Editable password) {
        return password != null && password.length() >= 8;
    }
}
