package br.com.searchmove.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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
import br.com.searchmove.dataBase.DatabaseEvent;
import br.com.searchmove.dataBase.DbDao;
import br.com.searchmove.interfaces.OnDbClick;
import br.com.searchmove.model.Result;


public class FavoritesMovieFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    ListView mlistView;
    List<Result> resultList;
    public FavoritesMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);

        mlistView = (ListView)view.findViewById(R.id.list_movie);
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if(getActivity() instanceof OnDbClick){
                    Result result = (Result) mlistView.getItemAtPosition(position);
                    ((OnDbClick) getActivity()).onDbClick(result);
                }
            }
        });
        updateList();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        EventBus.getDefault().register(this);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void updateList(){
        resultList = DbDao.getInstance(getActivity()
                .getApplication()
                .getApplicationContext()).getFavoriteTmDb();
        mlistView.setAdapter(new DBAdapter(getActivity(), resultList));
        Log.d("OPAAAAAAAAAAAAAA", "updateList: " + resultList.size());
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessagerEvent(DatabaseEvent event){
        updateList();
    }
}
