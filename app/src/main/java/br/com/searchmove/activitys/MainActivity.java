package br.com.searchmove.activitys;

import android.content.Intent;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.searchmove.R;
import br.com.searchmove.fragments.DetailMovieFragment;
import br.com.searchmove.fragments.FavoritesMovieFragment;
import br.com.searchmove.fragments.ListMovieFragment;
import br.com.searchmove.interfaces.OnDbClick;
import br.com.searchmove.model.Result;

public class MainActivity extends AppCompatActivity implements OnDbClick, NavigationView.OnNavigationItemSelectedListener {

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        buildViewPager();



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_solucoes) {
            // Handle the camera action
        } else if (id == R.id.nav_categoria) {

        } else if (id == R.id.nav_search) {

        } else if (id == R.id.nav_favoritos) {

        } else if (id == R.id.nav_assistidos) {

        } else if (id == R.id.nav_info) {

        } else if (id == R.id.nav_exit) {

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void mostrarDialog() {
//        ProgressDialog dialog = ProgressDialog.show(this, "",
//                "Loading. Please wait...", true);
//


//        dialog.dismiss();


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
                    return "Lista";//getResources().getString(R.string.activity_list);
                case 1:
                default:
                    return "favorito";//getResources().getString(R.string.activity_favorite);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}