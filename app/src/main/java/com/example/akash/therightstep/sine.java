package com.example.akash.therightstep;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class sine extends AppCompatActivity {

    Spinner spin;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    Button button;
// ...


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sine);
        spin=(Spinner)findViewById(R.id.spin);
        ArrayList<String> list=new ArrayList<>();
        list.add("select one");
        list.add("Below 10th");
        list.add("Below 12th");
        list.add("Under Graduate");
        list.add("Graduate");
        list.add("Post graduate");
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,list);
        spin.setAdapter(adapter);


        mAuth = FirebaseAuth.getInstance();

        button=(Button)findViewById(R.id.sign);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String user, email, password, gender, qualification;
                EditText t1, t2, t3;
                RadioButton m = (RadioButton) findViewById(R.id.male);
                RadioButton f = (RadioButton) findViewById(R.id.female);
                t1 = (EditText) findViewById(R.id.t1);
                t2 = (EditText) findViewById(R.id.t2);
                t3 = (EditText) findViewById(R.id.t3);

                user = t1.getText().toString();
                password = t2.getText().toString();
                email = t3.getText().toString();
                qualification = spin.getSelectedItem().toString();

                if (f.isChecked()) {
                    gender = "female";

                } else if (m.isChecked()) {
                    gender = "male";
                } else {
                    Toast.makeText(sine.this, "Please enter gender", Toast.LENGTH_SHORT).show();
                    return;
                }
                databaseReference= FirebaseDatabase.getInstance().getReference("signUp");
                Query query=databaseReference.orderByChild("user").equalTo(user);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.getValue()==null){
                         mAuth=FirebaseAuth.getInstance();
                            mAuth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(sine.this, new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            Toast.makeText(sine.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();


                                            if (!task.isSuccessful()) {
                                                Toast.makeText(sine.this, "Authentication failed." + task.getException(),
                                                        Toast.LENGTH_SHORT).show();
                                            } else {
                                                FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();

                                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                                        .setDisplayName(user)

                                                        .build();

                                                user1.updateProfile(profileUpdates)
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(sine.this, "user added", Toast.LENGTH_SHORT).show();
                                                                }

                                                            }
                                                        });

                                                String uid=user1.getUid();
                                                databaseReference= FirebaseDatabase.getInstance().getReference();
                                                signUp obj=new signUp(user,password,email,gender,qualification);
                                                databaseReference.child("signUp").child(uid).setValue(obj).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()){
                                                            Toast.makeText(sine.this, "account created", Toast.LENGTH_SHORT).show();
                                                            Intent i=new Intent(getApplicationContext(),login.class);
                                                            startActivity(i);
                                                        }
                                                        else
                                                        {
                                                            Toast.makeText(sine.this, "error", Toast.LENGTH_SHORT).show();
                                                        }

                                                    }
                                                });
                                            }
                                        }
                                    });




                        }
                        else
                        {
                            Toast.makeText(sine.this, "user name already exist", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }


        });


    }
}
