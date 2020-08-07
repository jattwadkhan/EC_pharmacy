package com.ecPharmacy;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    TextView txtUsername, txtEmail, txtDob;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        txtUsername = view.findViewById(R.id.txtUsername);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtDob = view.findViewById(R.id.txtDob);

        getUserData();
        return view;
    }

    private void getUserData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            final String uid = user.getUid();

            FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
            DatabaseReference mDbRef = mDatabase.getReference().child("User").child(uid);;
            Log.v("USERID", mDbRef.getKey());

            mDbRef.addValueEventListener(new ValueEventListener(){
                String userEmail,username,dob ;
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)  {
                    HashMap<String, String> keyId = (HashMap<String, String>) dataSnapshot.getValue();
                    userEmail = keyId.get("Email");
                    username = keyId.get("username");
                    dob = keyId.get("DOB");

                    txtEmail.setText(userEmail);
                    txtUsername.setText(username);
                    txtDob.setText(dob);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }
}
