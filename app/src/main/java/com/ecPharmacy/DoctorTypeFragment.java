package com.ecPharmacy;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DoctorTypeFragment extends Fragment {

    private RecyclerView rcyDoctorCategory;
    private DoctorCategoryAdapter categoryAdapter;

    public DoctorTypeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctor_type, container, false);

        rcyDoctorCategory = view.findViewById(R.id.rcyDoctorCategory);
        categoryAdapter = new DoctorCategoryAdapter(getContext(), getDoctorCategory());
        rcyDoctorCategory.setAdapter(categoryAdapter);

        return view;
    }

    private ArrayList<DoctorCategoryData> getDoctorCategory() {
        ArrayList<DoctorCategoryData> list = new ArrayList<>();

        list.add(new DoctorCategoryData(1, R.drawable.gastroenterologist, "Gastroenterologist"));
        list.add(new DoctorCategoryData(2, R.drawable.nephrologist, "Nephrologist"));
        list.add(new DoctorCategoryData(3, R.drawable.neurologist, "Neurologist"));
        list.add(new DoctorCategoryData(4, R.drawable.otolaryngologist, "Otolaryngologist"));
        list.add(new DoctorCategoryData(5, R.drawable.pulmonologist, "Pulmonologist"));

        return list;
    }
}
