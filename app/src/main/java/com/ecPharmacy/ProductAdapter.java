package com.ecPharmacy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.CategoryViewHolder> {

    private Context context;
    private ArrayList<ProductModel> list;

    public ProductAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void updateAdapter(ArrayList<ProductModel> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_category_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.img.setImageResource(list.get(position).getImage());

        holder.name.setText(list.get(position).getName());
        holder.price.setText("Price: $"+list.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView name, addToCart, price;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_category);
            name = itemView.findViewById(R.id.txtCategory);
            price = itemView.findViewById(R.id.txtPrice);
            addToCart = itemView.findViewById(R.id.txtAddToCart);
            addToCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Add Product into Cart.", Toast.LENGTH_SHORT).show();
                    if (HomeFragment.cartList.isEmpty()) {
                        CartModel cartModel = new CartModel(list.get(getAdapterPosition()), 1);
                        HomeFragment.cartList.add(cartModel);
                    } else {
                        boolean isSameExist = false;
                        for (int i=0; i<HomeFragment.cartList.size(); i++) {
                            if (HomeFragment.cartList.get(i).getProduct().getId() == list.get(getAdapterPosition()).getId()) {
                                isSameExist = true;
                                int k = HomeFragment.cartList.get(i).getQuantity();
                                k++;
                                HomeFragment.cartList.get(i).setQuantity(k);

                                break;
                            }
                        }

                        if (!isSameExist) {
                            CartModel cartModel = new CartModel(list.get(getAdapterPosition()), 1);
                            HomeFragment.cartList.add(cartModel);
                        }
                    }
                }
            });
        }
    }
}
