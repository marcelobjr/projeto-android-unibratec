package br.com.searchmove.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import br.com.searchmove.R;
import br.com.searchmove.adapter.DBAdapter;
import br.com.searchmove.interfaces.OnDbClick;
import br.com.searchmove.model.Result;
import br.com.searchmove.model.SearchResult;
import br.com.searchmove.service.RetrofitService;
import br.com.searchmove.service.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;


public class ListMovieFragment extends Fragment implements SearchView.OnQueryTextListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ListView mListMovie;
    RetrofitService mRetrofitService;
    private ProgressDialog dialag;

    public ListMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mListMovie = (ListView) view.findViewById(R.id.list_movie);
        mListMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (getActivity() instanceof OnDbClick) {
                    Result result = (Result) mListMovie.getItemAtPosition(position);
                    ((OnDbClick) getActivity()).onDbClick(result);
                }
            }
        });
        mRetrofitService = RetrofitUtil.getService();
        EventBus.getDefault().register(this);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
    }

    public void setAdapter(List<Result> results ){
        mListMovie.setAdapter(new DBAdapter(getActivity(), results));
    }

    private void loadMovie(String titulo) {
        dialag = ProgressDialog.show(this.getContext(),"Search Move","Aguarde...");
        mRetrofitService.searchMovie(titulo).enqueue(new Callback<SearchResult>() {
            @Override
            public void onResponse(Call<SearchResult> call, retrofit2.Response<SearchResult> response) {
                List<Result> results = response.body().getResults();
                EventBus.getDefault().postSticky(results);
                dialag.dismiss();
            }

            @Override
            public void onFailure(Call<SearchResult> call, Throwable t) {
                dialag.dismiss();
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        loadMovie(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onMessageEvent(List<Result>event) {
        setAdapter(event);
    }
}
