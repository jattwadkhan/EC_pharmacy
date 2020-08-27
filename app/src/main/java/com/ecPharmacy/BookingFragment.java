package com.ecPharmacy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends Fragment {

    private RecyclerView rcyBooking;
    private BookingAdapter bookingAdapter;
    ArrayList<BookingData> list = new ArrayList<>();

    public BookingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_booking, container, false);
        rcyBooking = view.findViewById(R.id.rcyBooking);

        bookingAdapter = new BookingAdapter(getContext());
        rcyBooking.setAdapter(bookingAdapter);

        getBooking();

        return view;
    }

    void getBooking() {
        final ProgressDialog mDialog = new ProgressDialog(getContext());
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Booking").child(user.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Log.e("data", dataSnapshot.toString());
                mDialog.dismiss();
                if (dataSnapshot.getValue() == null)
                    return;
                list.clear();

                HashMap chats = (HashMap) dataSnapshot.getValue();

                for (final Object entry : chats.keySet()) {
                    HashMap booking = (HashMap) chats.get(entry);

                    BookingData bookingData = new BookingData(Integer.parseInt(booking.get("image").toString()),
                            Integer.parseInt(booking.get("specialistId").toString()),
                            booking.get("name").toString(),
                            booking.get("specialist").toString(),
                            booking.get("location").toString(),
                            booking.get("experience").toString(),
                            booking.get("fee").toString(),
                            booking.get("date").toString(),
                            booking.get("time").toString());

                    list.add(bookingData);
                }

                bookingAdapter.updateAdapter(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mDialog.dismiss();
            }
        });
    }
}
