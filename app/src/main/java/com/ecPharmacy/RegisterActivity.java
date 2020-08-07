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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back;
    Button btnRegister;
    FirebaseAuth mFirebaseAuth;
    String username, dob, email, password, confirmPassword;
    EditText edtUsername, edtDob, edtEmail, edtPassword, edtConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirebaseAuth = FirebaseAuth.getInstance();

        back = findViewById(R.id.back);
        btnRegister = findViewById(R.id.btnRegister);

        edtUsername = findViewById(R.id.edtUsername);
        edtDob = findViewById(R.id.edtDob);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);


        back.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            case R.id.btnRegister:
                email = edtEmail.getText().toString();
                username = edtUsername.getText().toString();
                dob = edtDob.getText().toString();
                password = edtPassword.getText().toString();
                confirmPassword = edtConfirmPassword.getText().toString();

                if (email.isEmpty() && username.isEmpty() && dob.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {
                    Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                } else if (username.isEmpty()) {
                    Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show();
                } else if (dob.isEmpty()) {
                    Toast.makeText(this, "Please enter date of birth", Toast.LENGTH_SHORT).show();
                } else if (password.isEmpty()) {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
                } else if (password.length()<6) {
                    Toast.makeText(this, "Password length greater than 5", Toast.LENGTH_SHORT).show();
                } else if (confirmPassword.isEmpty()) {
                    Toast.makeText(this, "Please enter confirm password", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
                } else {
                    registerApi();
                }


                break;
        }
    }

    private void registerApi() {
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);
        mDialog.show();
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(!task.isSuccessful()) {
                    mDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "SignUp Unsuccessful, Please try Again",Toast.LENGTH_SHORT).show();
                }
                else {
                    final HashMap<String,Object> map = new HashMap<>();
                    map.put("username", username);
                    map.put("DOB", dob);
                    map.put("Email", email);
                    map.put("image", "");
                    map.put("userId", task.getResult().getUser().getUid());

                    FirebaseDatabase.getInstance().getReference("User")
                            .child(task.getResult().getUser().getUid())
                            .updateChildren(map)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    mDialog.dismiss();
                                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            });

                }
            }
        });
    }
}
