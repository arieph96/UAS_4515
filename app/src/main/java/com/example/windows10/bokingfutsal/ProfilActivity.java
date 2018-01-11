package com.example.windows10.bokingfutsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener{

    /*private FirebaseAuth firebaseAuth;
    private TextView textView;
    private Button button;*/

    private String mpost_key = null;
    private DatabaseReference mDatabase;

    private ImageView image;
    private TextView nmObjek;
    private TextView nmLokasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("objek_keterangan");
        mpost_key = getIntent().getExtras().getString("objek_id");
        //Toast.makeText(ProfileActivity.this,mpost_key,Toast.LENGTH_SHORT).show();

        image = (ImageView) findViewById(R.id.imageView3);
        nmObjek = (TextView) findViewById(R.id.id_nama);
        nmLokasi = (TextView)findViewById(R.id.lokasi);

        mDatabase.child(mpost_key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String pos_nama= (String) dataSnapshot.child("nama").getValue();
                String pos_lokasi= (String) dataSnapshot.child("lokasi").getValue();
                String pos_image= (String) dataSnapshot.child("image").getValue();

                nmObjek.setText(pos_nama);
                nmLokasi.setText(pos_lokasi);
                Picasso.with(ProfilActivity.this).load(pos_image).into(image);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });












        /*firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null ){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textView = (TextView) findViewById(R.id.textViewUserEmail);
        textView.setText("Welcome "+user.getEmail());

        button = (Button) findViewById(R.id.buttonLogOut);

        button.setOnClickListener(this);*/
    }

    @Override
    public void onClick(View view) {
       /* if(view == button){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }*/

    }

    public void doBooking(View view) {
        startActivity(new Intent(this, MenuBookingActivity.class));
    }
}
