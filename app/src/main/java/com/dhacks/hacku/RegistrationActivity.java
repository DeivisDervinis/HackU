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

public class RegistrationActivity extends AppCompatActivity {

    //region Private Field Variables
    private TextInputLayout emailTextInput;
    private TextInputEditText emailEditText;

    private TextInputLayout passwordTextInput;
    private TextInputEditText passwordEditText;
    private Button btnCreateAcct;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        setupViews();
    }

    /**
     * Private method that's responsible for setting up the views
     */
    private void setupViews() {
        emailTextInput = findViewById(R.id.email_text_input);
        emailEditText = findViewById(R.id.email_edit_text);
        passwordTextInput = findViewById(R.id.password_text_layout);
        passwordEditText = findViewById(R.id.password_edit_text);
        btnCreateAcct = findViewById(R.id.btn_create_acct);

        emailEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (!isEmailValid(emailEditText.getText())) {
                    emailTextInput.setError("Email is invalid!");
                }
                return false;
            }
        });

        passwordEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError("Password must be");
                }
                return false;
            }
        });

        btnCreateAcct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEmailValid(emailEditText.getText())) {
                    emailTextInput.setError("Email is invalid!");
                }
                if (!isPasswordValid(passwordEditText.getText())) {
                    passwordTextInput.setError("Password is either empty or less than 8 characters");
                }
                else {
                    emailTextInput.setError(null);
                    passwordTextInput.setError(null);

                    //TODO: ADD REGISTRATION LOGIC HERE

                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    /**
     * Private method for validating email format
     * @param email The editable text to be validated
     * @return True if email is a valid format, False otehrwise
     */
    private boolean isEmailValid(@Nullable Editable email) {
        return email != null && email.toString().contains("^(.+)@(.+)$");
    }

    /**
     * Private method for validating password format
     *
     * @param password the editable text to be validated
     * @return True if the password is not null and is 8 or more characters; False otherwise
     */
    private boolean isPasswordValid(@Nullable Editable password) {
        return password != null && password.length() >= 8;
    }
}
