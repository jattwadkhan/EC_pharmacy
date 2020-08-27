package com.ecPharmacy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.BookingViewHolder> {

    private Context context;
    private ArrayList<BookingData> list;

    public BookingAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void updateAdapter(ArrayList<BookingData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_booking_item, parent, false);
        return new BookingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingViewHolder holder, int position) {
        holder.imgCategory.setImageResource(list.get(position).image);
        holder.txtDoctorName.setText(list.get(position).name);
        holder.txtDoctorSpecialist.setText(list.get(position).specialist);
        holder.txtDoctorLocation.setText(list.get(position).location);
        holder.txtDate.setText(list.get(position).date);
        holder.txtTime.setText(list.get(position).time);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BookingViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCategory;
        TextView txtDoctorName, txtDoctorSpecialist, txtDoctorLocation, txtDate, txtTime;

        public BookingViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategory = itemView.findViewById(R.id.imgCategory);
            txtDoctorName = itemView.findViewById(R.id.txtDoctorName);
            txtDoctorSpecialist = itemView.findViewById(R.id.txtDoctorSpecialist);
            txtDoctorLocation = itemView.findViewById(R.id.txtDoctorLocation);
            txtDate = itemView.findViewById(R.id.txtDate);
            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }
}
