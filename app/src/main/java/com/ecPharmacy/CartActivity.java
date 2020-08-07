package com.ecPharmacy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {

    CartAdapter cartAdapter;
    RecyclerView recyclerView;
    TextView txtTotalPrice;
    LinearLayout lnrCart;
    Button btnCheckout;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.rcyCart);
        txtTotalPrice = findViewById(R.id.txtTotalPrice);
        lnrCart = findViewById(R.id.lnrCart);
        btnCheckout = findViewById(R.id.btnCheckout);
        back = findViewById(R.id.back);

        cartAdapter = new CartAdapter(this);
        recyclerView.setAdapter(cartAdapter);

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CartActivity.this, AddAddressActivity.class);
                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        updatePrice();
    }

    public void updatePrice() {
        if (HomeFragment.cartList.isEmpty()) {
            lnrCart.setVisibility(View.GONE);
        } else {
            lnrCart.setVisibility(View.VISIBLE);
            double sum = 0;

            for (int i=0; i<HomeFragment.cartList.size(); i++) {
                int quantity = HomeFragment.cartList.get(i).getQuantity();
                double price = HomeFragment.cartList.get(i).getProduct().getPrice();
                double tempSum = quantity * price;

                sum = sum + tempSum;
            }

            txtTotalPrice.setText("$"+sum);
            cartAdapter.notifyDataSetChanged();
        }
    }
}
