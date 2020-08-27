package com.ecPharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DoctorDetailActivity extends AppCompatActivity {

    private Button btnBooking;
    private TextView txtDate, txtTime;
    private DoctorData data;
    private ImageView imgDoctor;
    private TextView txtDoctorName, txtDoctorSpecialist, txtDoctorLocation, txtExperience, txtAdmissionFee;
    private FirebaseDatabase database;
    private DatabaseReference userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        database = FirebaseDatabase.getInstance();

        btnBooking = findViewById(R.id.btnBooking);
        txtDate = findViewById(R.id.txtDate);
        txtTime = findViewById(R.id.txtTime);

        imgDoctor = findViewById(R.id.imgDoctor);
        txtDoctorName = findViewById(R.id.txtDoctorName);
        txtDoctorSpecialist = findViewById(R.id.txtDoctorSpecialist);
        txtDoctorLocation = findViewById(R.id.txtDoctorLocation);
        txtExperience = findViewById(R.id.txtExperience);
        txtAdmissionFee = findViewById(R.id.txtAdmissionFee);

        data = new Gson().fromJson(getIntent().getStringExtra("data"), DoctorData.class);

        imgDoctor.setImageResource(data.image);
        txtDoctorName.setText(data.name);
        txtDoctorSpecialist.setText(data.specialist);
        txtDoctorLocation.setText(data.location);
        txtExperience.setText("Experience: "+data.experience);
        txtAdmissionFee.setText("Admission Fee :"+ data.fee);

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtDate.getText().toString().equals("")) {
                    Toast.makeText(DoctorDetailActivity.this, "Please set the date", Toast.LENGTH_SHORT).show();
                } else if (txtTime.getText().toString().equals("")) {
                    Toast.makeText(DoctorDetailActivity.this, "Please set the time", Toast.LENGTH_SHORT).show();
                } else {
                    booking();
                }

            }
        });

        txtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(DoctorDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDate.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        txtTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new TimePickerDialog(DoctorDetailActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        },
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        false
                ).show();
            }
        });
    }

    void booking() {
        Map<String, Object> updateInfo = new HashMap<>();
        updateInfo.put("name", data.name);
        updateInfo.put("image", data.image);
        updateInfo.put("location", data.location);
        updateInfo.put("specialist", data.specialist);
        updateInfo.put("specialistId", data.specialistId);
        updateInfo.put("experience", data.experience);
        updateInfo.put("fee", data.fee);
        updateInfo.put("date", txtDate.getText().toString());
        updateInfo.put("time", txtTime.getText().toString());

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userReference = database.getReference().child("Booking").child(""+user.getUid());
        String bookingId = updateInfo.get("specialistId") + "_" + Calendar.getInstance().getTimeInMillis();
        userReference.child(bookingId).setValue(updateInfo);

        Toast.makeText(DoctorDetailActivity.this, "Booking Successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DoctorDetailActivity.this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        /*userReference.updateChildren(updateInfo).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                } else
                    Toast.makeText(DoctorDetailActivity.this, "Information uploaded failed !", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
