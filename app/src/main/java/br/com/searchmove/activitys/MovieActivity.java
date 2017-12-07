package br.com.searchmove.activitys;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import br.com.searchmove.R;
import br.com.searchmove.adapter.MovieAdapter;
import br.com.searchmove.interfaces.OnDbClick;
import br.com.searchmove.maps.MapsActivity;
import br.com.searchmove.model.FilmeMock;
import br.com.searchmove.model.Popular;
import br.com.searchmove.model.Movie;
import br.com.searchmove.model.Result;

public class MovieActivity extends AppCompatActivity implements OnDbClick, NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private List<FilmeMock> movieList;
    DrawerLayout drawerlayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        drawerlayout = findViewById(R.id.drawerlayout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerlayout, toolbar, R.string.app_name,R.string.app_name);
        drawerlayout.addDrawerListener(actionBarDrawerToggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        initCollapsingToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareAlbums();

        try {
            Glide.with(this).load(R.drawable.filmes).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        if (id == R.id.nav_categoria) {
            Intent intent = new Intent(this,
                    MovieActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_search) {
            Intent intent = new Intent(this,
                    MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_favoritos) {
            Intent intent = new Intent(this,
                    MainActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_assistidos) {
            Intent it = new Intent(this, WatchedActivity.class);
            startActivity(it);

        } else if (id == R.id.nav_localcine) {
            Intent it = new Intent(this, MapsActivity.class);
            startActivity(it);

        } else if (id == R.id.nav_info) {
            Intent intent = new Intent(this, Act_ingrante2.class);
            startActivity(intent);

        } else if (id == R.id.nav_exit) {
            new AlertDialog.Builder(this)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage(getResources().getString(R.string.exit_app))
                    .setPositiveButton(getResources().getString(R.string.yes),
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    finish();
                                    System.exit(1);
                                }
                            })
                    .setNegativeButton(getResources().getString(R.string.no), null)
                    .show();

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawerlayout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Initializing collapsing toolbar
     * Will show and hide the toolbar title on scroll
     */
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    /**
     * Adding few albums for testing
     */
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.movie1,
                R.drawable.movie2,
                R.drawable.movie3,
                R.drawable.movie4,
                R.drawable.movie5,
                R.drawable.movie6,
                R.drawable.movie7,
                R.drawable.movie8,
                R.drawable.movie9,
                R.drawable.movie10};

        FilmeMock a = new FilmeMock("Guardiões da Galáxia Vol. 2", 13, covers[0]);
        movieList.add(a);

        a = new FilmeMock("Logan", 8, covers[1]);
        movieList.add(a);

        a = new FilmeMock("Liga da Justiça", 11, covers[2]);
        movieList.add(a);

        a = new FilmeMock("Homem-Aranha: De Volta ao Lar", 12, covers[3]);
        movieList.add(a);

        a = new FilmeMock("Thor: Ragnarok", 14, covers[4]);
        movieList.add(a);

        a = new FilmeMock("Mulher Maravilha", 1, covers[5]);
        movieList.add(a);

        a = new FilmeMock("Star Wars: Os Últimos Jedi", 11, covers[6]);
        movieList.add(a);

        a = new FilmeMock("It: A Coisa", 14, covers[7]);
        movieList.add(a);

        a = new FilmeMock("Velozes e Furiosos 8", 11, covers[8]);
        movieList.add(a);

        a = new FilmeMock("Transformers: O Último Cavaleiro", 17, covers[9]);
        movieList.add(a);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDbClick(Result result) {

    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

}