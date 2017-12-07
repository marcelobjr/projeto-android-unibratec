package br.com.searchmove.maps;

import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.searchmove.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener {

 

    private GoogleMap mMap;
    private GoogleMap mMap2;
    private GoogleMap mMap3;
    private GoogleMap mMap4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {



        mMap = googleMap;
        mMap2 = googleMap;
        mMap3 = googleMap;
        mMap4 = googleMap;

        //botão de zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng riomar = new LatLng(-8.085143, -34.895161);
        mMap.addMarker(new MarkerOptions().position(riomar).title("Shopping RioMar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(riomar));

        LatLng recife = new LatLng(-8.116244, -34.903370);
        mMap2.addMarker(new MarkerOptions().position(recife).title("Shopping Recife"));
        mMap2.moveCamera(CameraUpdateFactory.newLatLng(recife));

        LatLng tacaruna = new LatLng(-8.037768, -34.871809);
        mMap3.addMarker(new MarkerOptions().position(tacaruna).title("Shopping Tacaruna"));
        mMap3.moveCamera(CameraUpdateFactory.newLatLng(tacaruna));

        LatLng Guararapes = new LatLng(-8.168955, -34.917484);
        mMap4.addMarker(new MarkerOptions().position(Guararapes).title("Shopping Tacaruna"));
        mMap4.moveCamera(CameraUpdateFactory.newLatLng(Guararapes));


    }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(this, "Descrição dos filmes", Toast.LENGTH_SHORT).show();
    }
}
