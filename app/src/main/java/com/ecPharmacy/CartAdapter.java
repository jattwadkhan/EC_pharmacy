package com.ecPharmacy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;

    public CartAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_cart_item, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        holder.img.setImageResource(HomeFragment.cartList.get(position).getProduct().getImage());
        holder.name.setText(HomeFragment.cartList.get(position).getProduct().getName());
        holder.quantity.setText(""+HomeFragment.cartList.get(position).getQuantity());

        Double price = HomeFragment.cartList.get(position).getProduct().getPrice() * HomeFragment.cartList.get(position).getQuantity();
        holder.price.setText("$"+price);
    }

    @Override
    public int getItemCount() {
        return HomeFragment.cartList.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView img, minus, plus;
        TextView name, quantity, price, remove;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.imgProduct);
            minus = itemView.findViewById(R.id.minus);
            plus = itemView.findViewById(R.id.plus);

            name = itemView.findViewById(R.id.txtProductName);
            quantity = itemView.findViewById(R.id.txtQuantity);
            price = itemView.findViewById(R.id.txtPrice);
            remove = itemView.findViewById(R.id.txRemove);

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (HomeFragment.cartList.get(getAdapterPosition()).getQuantity() > 1) {
                        int k = HomeFragment.cartList.get(getAdapterPosition()).getQuantity();
                        k--;
                        HomeFragment.cartList.get(getAdapterPosition()).setQuantity(k);
                        ((CartActivity) context).updatePrice();
                    }
                }
            });

            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int k = HomeFragment.cartList.get(getAdapterPosition()).getQuantity();
                    k++;
                    HomeFragment.cartList.get(getAdapterPosition()).setQuantity(k);

                    ((CartActivity) context).updatePrice();
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HomeFragment.cartList.remove(getAdapterPosition());
                    ((CartActivity) context).updatePrice();
                }
            });
        }
    }
}
