package br.com.searchmove.activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import br.com.searchmove.R;
import br.com.searchmove.fragments.DetailMovieFragment;
import br.com.searchmove.fragments.FavoritesMovieFragment;
import br.com.searchmove.fragments.ListMovieFragment;
import br.com.searchmove.interfaces.OnDbClick;
import br.com.searchmove.model.Result;

public class MainActivity extends AppCompatActivity implements OnDbClick {

    private Toolbar toolbar;
    ListMovieFragment listMovieFragment;
    FavoritesMovieFragment favoritesTmDbFragment;
    ViewPager mViewPager;
    SelectorPageAdapter selectorPageAdapter;
    TabLayout tab;
    DrawerLayout drawerlayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerlayout = findViewById(R.id.drawerlayout);

        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.app_name,R.string.app_name);
        drawerlayout.addDrawerListener(actionBarDrawerToggle);

//        ProgressDialog dialog = ProgressDialog.show(this, "",
//                "Loading. Please wait...", true);

        buildViewPager();

    }

    private void buildViewPager(){
        mViewPager = (ViewPager)findViewById(R.id.container);
        selectorPageAdapter = new SelectorPageAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(selectorPageAdapter);

        tab = (TabLayout) findViewById(R.id.tabs);
        tab.setupWithViewPager(mViewPager);
    }


    @Override
    public void onDbClick(Result result) {
        if (getResources().getBoolean(R.bool.phone)) {
            Intent it = new Intent(this, DetailActivity.class);
            it.putExtra("result", result);
            startActivity(it);
        } else {

            DetailMovieFragment detailTmDbFragment = DetailMovieFragment.newInstance(result);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content_detail, detailTmDbFragment, "datail")
                    .commit();
        }
    }

    public class SelectorPageAdapter extends FragmentPagerAdapter{
        public SelectorPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (listMovieFragment == null) {
                        listMovieFragment = new ListMovieFragment();
                    }
                    return listMovieFragment;
                case 1:
                default:
                    if (favoritesTmDbFragment == null) {
                        favoritesTmDbFragment = new FavoritesMovieFragment();
                    }
                    return favoritesTmDbFragment;
            }
        }

        @Override
        public int getCount(){return 2;}

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return getResources().getString(R.string.activity_list);
                case 1:
                default:
                    return getResources().getString(R.string.activity_favorite);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}