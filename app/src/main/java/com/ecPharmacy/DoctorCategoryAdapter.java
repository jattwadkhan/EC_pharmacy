package com.ecPharmacy;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DoctorCategoryAdapter extends RecyclerView.Adapter<DoctorCategoryAdapter.DoctorCategoryViewHolder> {

    private Context context;
    private ArrayList<DoctorCategoryData> list;

    public DoctorCategoryAdapter(Context context, ArrayList<DoctorCategoryData> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public DoctorCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_doctor_category_item, parent, false);
        return new DoctorCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorCategoryViewHolder holder, int position) {
        holder.imgCategory.setImageResource(list.get(position).image);
        holder.txtCategoryName.setText(list.get(position).name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DoctorCategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCategory;
        TextView txtCategoryName;

        public DoctorCategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCategory = itemView.findViewById(R.id.imgCategory);
            txtCategoryName = itemView.findViewById(R.id.txtCategoryName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DoctorListActivity.class);
                    intent.putExtra("id", list.get(getAdapterPosition()).id);
                    intent.putExtra("name", list.get(getAdapterPosition()).name);
                    context.startActivity(intent);
                }
            });
        }
    }
}
