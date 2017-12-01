package br.com.searchmove.activitys;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.searchmove.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                HomePage();

            }
        }, 2000);
    }

    private void HomePage() {
        Intent intent = new Intent(this,
                MainActivity.class);
        startActivity(intent);
        finish();

    }
}
