package com.dhacks.hacku;

import android.content.Intent;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import javax.annotation.Nullable;

public class LoginActivity extends AppCompatActivity {

    //region Private Field Variables
    private TextInputLayout loginEmailTextInput;
    private TextInputEditText loginEmailEditText;
    private TextInputLayout loginPasswordTextInput;
    private TextInputEditText loginPasswordEditText;
    private Button btnHackuLogin;

    private FirebaseAuth mAuth;

    private String email;
    private String password;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        setupViews();

    }

    /**
     * Private method that initializes all the necessary views
     */
    private void setupViews() {
        loginEmailTextInput = findViewById(R.id.login_email_text_input);
        loginEmailEditText = findViewById(R.id.login_email_edit_text);
        loginPasswordTextInput = findViewById(R.id.login_password_text_input);
        loginPasswordEditText = findViewById(R.id.login_password_edit_text);
        btnHackuLogin = findViewById(R.id.btn_hacku_login);

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

                    email = loginEmailEditText.getText().toString();
                    password = loginPasswordEditText.getText().toString();

                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "Login successful!", Toast.LENGTH_LONG).show();

                                        Intent newIntent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(newIntent);
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Login failed! Please try again later", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                    //TODO: DOUBLE CHECK THE LOGICAL FLOW HERE
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
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
