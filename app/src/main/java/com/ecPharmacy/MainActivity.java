package com.ecPharmacy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent intent;
                    if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                        intent = new Intent(MainActivity.this, HomeActivity.class);
                    } else {
                        intent = new Intent(MainActivity.this, LoginActivity.class);
                    }
                    startActivity(intent);
                    finish();
                }
            }
        }).start();
    }
}
