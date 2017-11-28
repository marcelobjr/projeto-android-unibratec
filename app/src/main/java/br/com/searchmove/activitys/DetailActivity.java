package br.com.searchmove.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import br.com.searchmove.R;
import br.com.searchmove.fragments.DetailMovieFragment;
import br.com.searchmove.model.Result;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Result result = (Result) getIntent().getSerializableExtra("result");

        DetailMovieFragment detailMovieFragment = DetailMovieFragment.newInstance(result);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_detail, detailMovieFragment,"detail")
                .commit();


    }

}
