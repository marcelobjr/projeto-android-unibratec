package br.com.searchmove.fragments;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import br.com.searchmove.R;
import br.com.searchmove.dataBase.DatabaseEvent;
import br.com.searchmove.dataBase.DbDao;
import br.com.searchmove.model.Result;

public class DetailMovieFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    TextView originalTitle;
    TextView releaseDate;
    TextView original_language;
    TextView overViewer;

    FloatingActionButton floatingActionButton;
    Result result;
    DbDao dbDao;
    boolean isFavorite;
    CollapsingToolbarLayout appBarLayout;

    public static DetailMovieFragment newInstance(Result result){
        Bundle bundle = new Bundle();
        bundle.putSerializable("result",result);

        DetailMovieFragment detailTmDbFragment = new DetailMovieFragment();
        detailTmDbFragment.setArguments(bundle);
        return detailTmDbFragment;
    }

    public DetailMovieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        if (getResources().getBoolean(R.bool.tablet)) {
            result = (Result) getArguments().get("result");
        }else {
            result = (Result) getActivity().getIntent().getSerializableExtra("result");
        }


        if (getResources().getBoolean(R.bool.phone)) {
            android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) view.findViewById(R.id.toolbar);
            toolbar.setTitle(result.getTitle());
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            appBarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.toolbar_layout);

        }

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                seveOrRemoveFavorite();
            }
        });
        changeFloatingButton();

        ImageView imgBackdropPath = (ImageView) view.findViewById(R.id.item_poster_patch);
        String imageUr1 = String.format("%s%s%s", "https://image.tmdb.org/t/p/", // base
                "w1280", // size
                result.getBackdropPath());

        Picasso.with(getActivity())
                .load(imageUr1)
                .into(imgBackdropPath);

        originalTitle     = (TextView) view.findViewById(R.id.txt_original_title);
        originalTitle.setText("Original Title: " + result.getOriginalTitle());

        releaseDate       = (TextView) view.findViewById(R.id.txt_release_date);
        releaseDate.setText("Release Date: " + result.getReleaseDate());

        original_language = (TextView) view.findViewById(R.id.txt_original_language);
        original_language.setText("Original Language: " + result.getOriginalLanguage());

        overViewer        = (TextView) view.findViewById(R.id.txt_over_viewer);
        overViewer.setText("Overview: " + result.getOverview());

        dbDao = DbDao.getInstance(getActivity().getApplication().getApplicationContext());
        result = dbDao.getResult(result);
        isFavorite = result == null ? false : true;
        changeFloatingButton();

        return view;
    }

    private void changeFloatingButton() {
        int resource = isFavorite ? R.drawable.ic_favorite_black_24dp : R.drawable.ic_favorite_border_black_24dp ;
        floatingActionButton.setImageResource(resource);
    }

    public void seveOrRemoveFavorite(){

        if (getResources().getBoolean(R.bool.tablet)) {
            result = (Result) getArguments().get("result");
        }else {
            result = (Result) getActivity().getIntent().getSerializableExtra("result");
        }
            Log.d("DEBUG",">>>>>>>>>>>> FAVORITE"+ result.getPosterPath());
        if(isFavorite){
            dbDao.deleteDb(result);
            isFavorite = false;
        }else{
            dbDao.insertDb(result);
            isFavorite = true;
        }
        changeFloatingButton();
        EventBus.getDefault().post(new DatabaseEvent());
    }
}
