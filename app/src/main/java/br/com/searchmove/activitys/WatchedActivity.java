package br.com.searchmove.activitys;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import br.com.searchmove.R;
import br.com.searchmove.service.ServiceApi;

public class WatchedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watched);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        ListView lvMovies = (ListView)findViewById(R.id.lv_movies);
//
//        ServiceApi task = new ServiceApi(this, lvMovies);
//        task.execute();
    }
}
