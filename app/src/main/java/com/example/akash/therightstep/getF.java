package com.example.akash.therightstep;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class
getF extends Fragment {
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    View view;



    public getF() {
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
        view = inflater.inflate(R.layout.fragment_get, container, false);
        mAuth = FirebaseAuth.getInstance();
        Button post,calcel;
        post=(Button)view.findViewById(R.id.post);
        EditText t1,t2;
        t1=(EditText)view.findViewById(R.id.question);
        t2=(EditText)view.findViewById(R.id.p);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
                databaseReference= FirebaseDatabase.getInstance().getReference();

                String uid = u.getUid();
                String user=u.getDisplayName();
                EditText t1,t2;
                t1=(EditText)view.findViewById(R.id.question);
                t2=(EditText)view.findViewById(R.id.p);
                String question,discription;
                question=t1.getText().toString();
                discription=t2.getText().toString();
                get obj=new get(question,discription,user,uid);
                databaseReference.child("get").push().setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "Posted", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });
        return view;
    }
}
