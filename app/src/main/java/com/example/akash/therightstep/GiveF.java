package com.example.akash.therightstep;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class GiveF extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private DatabaseReference databaseReference;
    private View view;
    private List<get> listItems;

    public GiveF() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      view=inflater.inflate(R.layout.fragment_give, container, false);

        recyclerView=(RecyclerView)view.findViewById(R.id.giveRe);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        listItems=new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        FirebaseUser firebaseUser=mAuth.getCurrentUser();
        String uid=firebaseUser.getUid();

        databaseReference.child("get").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                get g=ds.getValue(get.class);
                listItems.add(g);
                }
                pAdapter adapter;
                adapter=new pAdapter(listItems,getActivity());
                recyclerView.setAdapter(adapter);


            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return  view;
    }


}
