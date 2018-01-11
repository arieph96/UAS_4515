package com.example.windows10.bokingfutsal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MenuObjek extends AppCompatActivity {
    private RecyclerView recycle;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_objek);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("objek_keterangan");

        recycle=(RecyclerView)findViewById(R.id.list);
        recycle.setHasFixedSize(true);
        recycle.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<ListActivity, Blogview> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ListActivity, Blogview>(
                ListActivity.class,
                R.layout.activity_setrecycleview,
                Blogview.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(Blogview viewHolder, ListActivity model, int position) {

                final String post_key = getRef(position).getKey();

                viewHolder.setNama(model.getNama());
                //viewHolder.setName(model.getName());
                viewHolder.setLokasi(model.getLokasi());
                viewHolder.setImage(getApplicationContext(),model.getImage());

                viewHolder.mview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Toast.makeText(MenuObjek.this,post_key,Toast.LENGTH_SHORT).show();
                        Intent singelBlog = new Intent(MenuObjek.this,ProfilActivity.class);
                        singelBlog.putExtra("objek_id",post_key);
                        startActivity(singelBlog);

                    }
                });
            }
        };
        recycle.setAdapter(firebaseRecyclerAdapter);
    }

    public static class Blogview extends RecyclerView.ViewHolder{
        View mview;

        public Blogview(View itemView) {
            super(itemView);
            mview = itemView;
        }

        public void setNama(String nama){
            TextView post_nama = (TextView)  mview.findViewById(R.id.post_nama);
            post_nama.setText(nama);
        }

        public void setLokasi(String Lokasi){
            TextView post_Lokasi = (TextView)  mview.findViewById(R.id.post_lokasi);
            post_Lokasi.setText(Lokasi);
        }

        public void setImage(Context ctx, String image){
            ImageView post_image = (ImageView) mview.findViewById(R.id.post_image);
            Picasso.with(ctx).load(image).into(post_image);
        }

    }
}
