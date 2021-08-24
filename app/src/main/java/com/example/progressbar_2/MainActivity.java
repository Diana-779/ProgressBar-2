package com.example.progressbar_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    static  int progress;
    ProgressBar progressBar;
    int progressStatus = 0;
    Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = 0;
        progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setMax(100);
        new Thread((new Runnable() {
            @Override
            public void run() {
                while(progressStatus<100)
                {
                    progressStatus = doSomeWork();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                }

handler.post(new Runnable() {
    @Override
    public void run() {
        progressBar.setVisibility(View.GONE);
    }
});
            }


            private int doSomeWork() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                return  ++progress;
            }
        })).start();
    }
}