package com.example.akash.therightstep;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class give1 extends AppCompatActivity {

    TextView tt1,tt2,tt3;
    EditText tt4;
    Button b5;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_give1);

        final String question, discription,X;
        tt1=(TextView) findViewById(R.id.gg1);
        tt2=(TextView) findViewById(R.id.gg2);
        tt3=(TextView)findViewById(R.id.gg3);
        tt4=(EditText)findViewById(R.id.gg4);
       question= getIntent().getExtras().getString("heading");
       discription= getIntent().getExtras().getString("discription");
       X= getIntent().getExtras().getString("author");
       final String uid1=getIntent().getExtras().getString("uid");
       tt1.setText(question);
       tt2.setText(discription);
       tt3.setText(X);
       b5=(Button)findViewById(R.id.gg5);
       b5.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String respose=tt4.getText().toString().trim();
               databaseReference= FirebaseDatabase.getInstance().getReference();
               mAuth=FirebaseAuth.getInstance();
               FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
               String uid = u.getUid();
               String user =u.getDisplayName();

               input obj=new input(question,respose,user);
               databaseReference.child("input").child(uid1).push().setValue(obj);



           }
       });
       Button gg6=(Button)findViewById(R.id.gg6);
       gg6.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               databaseReference=FirebaseDatabase.getInstance().getReference();
               databaseReference.child("input").addValueEventListener(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                       for(DataSnapshot ds:dataSnapshot.getChildren()){
                           String u=ds.getValue(input.class).getiRespose();



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
