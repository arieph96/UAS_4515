package com.example.windows10.bokingfutsal;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar pgr;
    int progress=0;
    Handler h=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pgr=(ProgressBar)findViewById(R.id.progressBar1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<5; i++)
                {
                    progress += 20;
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            pgr.setProgress(progress);
                            if(progress==pgr.getMax())
                            {
                                //pgr.setVisibility(4);
                                Intent in = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(in);
                            }
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }
                }
            }
        }).start();

    }
}
