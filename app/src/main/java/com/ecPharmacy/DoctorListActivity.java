package com.ecPharmacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorListActivity extends AppCompatActivity {

    private RecyclerView rcyDoctor;
    private DoctorAdapter doctorAdapter;
    private ImageView back;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);

        rcyDoctor = findViewById(R.id.rcyDoctor);
        title = findViewById(R.id.title);
        back = findViewById(R.id.back);
        int id = getIntent().getIntExtra("id", 0);
        String name = getIntent().getStringExtra("name");

        title.setText(name);

        GetDoctorList getDoctorList = new GetDoctorList();

        doctorAdapter = new DoctorAdapter(this, getDoctorList.getDoctorList(id));
        rcyDoctor.setAdapter(doctorAdapter);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
