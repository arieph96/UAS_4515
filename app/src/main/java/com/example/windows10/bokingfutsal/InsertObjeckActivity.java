package com.example.windows10.bokingfutsal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class InsertObjeckActivity extends AppCompatActivity {


    private EditText edtnama;
    private EditText edtlokasi;
    private ImageButton imgbtn;
    private Button btnsimpan;
    private Uri mImageUri = null;
    private FirebaseAuth mAuth;

    private DatabaseReference mDatabasesObjeck;

    private StorageReference mStorageImg;

    private static final int GALLERY_REQUEST = 1 ;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_insert_objeck);

            mAuth = FirebaseAuth.getInstance();
            mStorageImg = FirebaseStorage.getInstance().getReference().child("objek_image");
            mDatabasesObjeck = FirebaseDatabase.getInstance().getReference().child("objek_keterangan");

            imgbtn = (ImageButton) findViewById(R.id.imageButton);
            edtnama = (EditText) findViewById(R.id.nama_objek);
            edtlokasi = (EditText) findViewById(R.id.lokasi_objek);
            btnsimpan = (Button) findViewById(R.id.btnsave);

            imgbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent galleryIntent = new Intent();
                    galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                    galleryIntent.setType("image/*");
                    startActivityForResult(galleryIntent,GALLERY_REQUEST);
                }
            });

            btnsimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startSetUpObjeck();
                }
            });

        }
    }

    public void startSetUpObjeck(){
        final String name = edtnama.getText().toString().trim();
        final String lokasi = edtlokasi.getText().toString().trim();
        //String objeck_id = mAuth.getCurrentUser().getUid();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(name) && mImageUri != null){
            StorageReference filepath = mStorageImg.child(mImageUri.getLastPathSegment());

            filepath.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   String downloadUri = taskSnapshot.getDownloadUrl().toString();
                    DatabaseReference newPost = mDatabasesObjeck.push();

                    newPost.child("nama").setValue(name);
                    newPost.child("lokasi").setValue(lokasi);
                    newPost.child("image").setValue(downloadUri.toString());

                    Toast.makeText(InsertObjeckActivity.this,"Objeck berhasil di simpan.. ",Toast.LENGTH_SHORT).show();
                    imgbtn.setImageResource(R.drawable.objeck);
                    edtnama.setText("");
                    edtlokasi.setText("");
                    /*String downloaduri = taskSnapshot.getDownloadUrl().toString();

                    mDatabaseuser.child(objeck_id).child("name").setValue(name);
                    mDatabaseuser.child(objeck_id).child("lokasi").setValue(lokasi);
                    mDatabaseuser.child(objeck_id).child("deskripsi").setValue(deskripsi);
                    mDatabaseuser.child(objeck_id).child("image").setValue(downloaduri);
                    Toast.makeText(InsertObjeckActivity.this,"Objeck berhasil di simpan.. ",Toast.LENGTH_SHORT).show();
                    imgbtn.setImageResource(R.drawable.objeck);
                    edtnama.setText("");
                    edtlokasi.setText("");
                    edtdeskripsi.setText("");*/
                }
            });
        }
    }
   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){
            mImageUri = data.getData();
            imgbtn.setImageURI(mImageUri);
           /* CropImage.activity(imageuri)
                    .setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);
        }


        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK){
                mImageUri = result.getUri();
                imgbtn.setImageURI(mImageUri);
            }else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception eror = result.getError();
            }*/

        }

    }
}
