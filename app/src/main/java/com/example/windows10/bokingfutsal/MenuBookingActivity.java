package com.example.windows10.bokingfutsal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuBookingActivity extends AppCompatActivity {
    private Button btn_kirim;
    private EditText nm_pms;
    private EditText al_pms;
    private EditText no_tlp;
    private EditText wktu;
    private  EditText jm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_booking);


        btn_kirim = (Button)findViewById(R.id.kirim);
        nm_pms = (EditText)findViewById(R.id.namapms);
        al_pms = (EditText)findViewById(R.id.alamatpms);
        no_tlp = (EditText)findViewById(R.id.notlp);
        wktu = (EditText)findViewById(R.id.waktupms);
        jm = (EditText)findViewById(R.id.jam);

    }

    public void doKirim(View view) {
        Toast.makeText(MenuBookingActivity.this,"Kirim ke operator.. ",Toast.LENGTH_SHORT).show();
    }
}
