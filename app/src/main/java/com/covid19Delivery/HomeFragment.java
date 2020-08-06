package com.covid19Delivery;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ArrayList<ProductModel> list = new ArrayList<>();
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    public static ArrayList<CartModel> cartList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.rcyProduct);
        createList();

        productAdapter = new ProductAdapter(getContext());
        recyclerView.setAdapter(productAdapter);
        productAdapter.updateAdapter(list);
        return view;
    }


    private void createList() {
        ArrayList<ProductModel> list = new ArrayList<>();

        list.add(new ProductModel(1, "Mask", R.drawable.mask, 1.0));
        list.add(new ProductModel(2, "Sanitizer", R.drawable.sanitizer, 5.0));
        list.add(new ProductModel(3, "Medicine", R.drawable.medicine, 10.0));
        list.add(new ProductModel(4, "HydroxyChloroquine", R.drawable.hydroxychloroquine, 15.0));
        list.add(new ProductModel(5, "Testing Kit", R.drawable.testkit, 20.0));

        this.list = list;
    }
}
