package com.ecPharmacy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> {

    private Context context;
    private ArrayList<DoctorData> list;

    public DoctorAdapter(Context context, ArrayList<DoctorData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_doctor_item, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        holder.imgDoctor.setImageResource(list.get(position).image);
        holder.txtDoctorName.setText(list.get(position).name);
        holder.txtDoctorSpecialist.setText(list.get(position).specialist);
        holder.txtDoctorLocation.setText(list.get(position).location);
        holder.txtExperience.setText("Experience: "+list.get(position).experience);
        holder.txtAdmissionFee.setText("Admission Fee :"+ list.get(position).fee);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DoctorViewHolder extends RecyclerView.ViewHolder {

        ImageView imgDoctor;
        TextView txtDoctorName, txtDoctorSpecialist, txtDoctorLocation, txtExperience, txtAdmissionFee;

        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);

            imgDoctor = itemView.findViewById(R.id.imgDoctor);
            txtDoctorName = itemView.findViewById(R.id.txtDoctorName);
            txtDoctorSpecialist = itemView.findViewById(R.id.txtDoctorSpecialist);
            txtDoctorLocation = itemView.findViewById(R.id.txtDoctorLocation);
            txtExperience = itemView.findViewById(R.id.txtExperience);
            txtAdmissionFee = itemView.findViewById(R.id.txtAdmissionFee);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DoctorDetailActivity.class);
                    intent.putExtra("data", new Gson().toJson(list.get(getAdapterPosition())));
                    context.startActivity(intent);
                }
            });
        }
    }
}
