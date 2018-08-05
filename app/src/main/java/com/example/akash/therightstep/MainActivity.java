package com.example.akash.therightstep;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth mAuth;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private static final String TAG ="MainActivity";
    private List<post> listItems;
    private static String userName;
    private DatabaseReference databaseReference,databaseReference2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=(RecyclerView)findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems=new ArrayList<>();
        mAuth = FirebaseAuth.getInstance();

        databaseReference= FirebaseDatabase.getInstance().getReference();
        FirebaseUser u = FirebaseAuth.getInstance().getCurrentUser();
        String uid = u.getUid();
        databaseReference.child("post").child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    post p=ds.getValue(post.class);
                        listItems.add(p);
                                   }
                myAdapter adapter;
                adapter=new myAdapter(listItems,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //******************************************************************************************************




    }
    public interface onCallback {
        public void call(List<post> posts);

    }

    public void read(onCallback callback, String user, final String h1, final String h2){
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("signUp").child(user).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                signUp s=dataSnapshot.getValue(signUp.class);
                Toast.makeText(MainActivity.this, s.getUser(), Toast.LENGTH_SHORT).show();
                userName =s.getUser();
                post p=new post(h1,h2,userName);
                listItems.add(p);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();

            Intent intent =new Intent(getApplicationContext(),login.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            Fragment get = getSupportFragmentManager().findFragmentByTag("get");
            if(get!=null)
            getSupportFragmentManager().beginTransaction().remove(get).commit();
           Fragment give = getSupportFragmentManager().findFragmentByTag("give");
           if(give!=null)
           getSupportFragmentManager().beginTransaction().remove(give).commit();
            Fragment post = getSupportFragmentManager().findFragmentByTag("post");
            if (post!=null)
            getSupportFragmentManager().beginTransaction().remove(post).commit();
            Fragment Response = getSupportFragmentManager().findFragmentByTag("response");
            if (Response!=null)
                getSupportFragmentManager().beginTransaction().remove(Response).commit();


            // Handle the camera action
        } else if (id == R.id.get) {
            getF obj = new getF();
            getSupportFragmentManager().beginTransaction().replace(R.id.f1,obj,"get").commit();
        } else if (id == R.id.give) {
            GiveF giveF=new GiveF();
            getSupportFragmentManager().beginTransaction().replace(R.id.f1,giveF,"give").commit();

        } else if (id == R.id.post) {

            PostF postF=new PostF();
            getSupportFragmentManager().beginTransaction().replace(R.id.f1,postF,"post").commit();


        } else if (id == R.id.share) {
            Toast.makeText(this, "to be made", Toast.LENGTH_SHORT).show();

        }
        else if(id ==R.id.response ){
            Response response=new Response();
            getSupportFragmentManager().beginTransaction().replace(R.id.f1,response,"response").commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
