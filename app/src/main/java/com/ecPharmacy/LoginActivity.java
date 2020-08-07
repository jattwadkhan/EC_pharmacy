package com.ecPharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogin;
    TextView txtForgot, txtRegister;
    String email, password;
    EditText edtEmail, edtPassword;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAuth = FirebaseAuth.getInstance();

        btnLogin = findViewById(R.id.btnLogin);
        txtForgot = findViewById(R.id.txtForgot);
        txtRegister = findViewById(R.id.txtRegister);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin.setOnClickListener(this);
        txtForgot.setOnClickListener(this);
        txtRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnLogin:
                email = edtEmail.getText().toString();
                password = edtPassword.getText().toString();

                if (email.isEmpty() && password.isEmpty()) {
                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else {
                    loginApi();
                }

                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.txtForgot:
                intent = new Intent(this, ForgotPasswordActivity.class);
                startActivity(intent);
                break;

            case R.id.txtRegister:

                intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void loginApi() {
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Uploading...");
        mDialog.setCancelable(false);
        mDialog.show();

        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull final Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                }
                else  {
                    mDialog.dismiss();
                    Intent inToHome = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(inToHome);
                    finish();

                }
            }
        });
    }
}
