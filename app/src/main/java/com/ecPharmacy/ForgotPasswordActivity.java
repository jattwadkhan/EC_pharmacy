package com.ecPharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView back;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        back = findViewById(R.id.back);
        btnSubmit = findViewById(R.id.btnSubmit);

        back.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                onBackPressed();
                break;

            case R.id.btnSubmit:
                Toast.makeText(this, "Password send in your email", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
        }
    }
}
