package br.com.searchmove.maps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
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
        GoogleMap.OnMapClickListener, LocationListener {

    public static final String TAG = "Act_Location";


    private LocationManager locationManager;

    private GoogleMap mMap;
    private GoogleMap mMap2;
    private GoogleMap mMap3;
    private GoogleMap mMap4;
    private GoogleMap mMapLOc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onResume() {
        super.onResume();

        //ativei o gps
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 1, this);
    }

    @Override
    public void onPause() {
        super.onPause();

        //ativei o gps
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);
    }


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
        mMap4.getUiSettings().setZoomControlsEnabled(true);


        try {
            locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);
            Toast.makeText(this, "Provider: " + provider, Toast.LENGTH_SHORT).show();

        mMapLOc = googleMap;
        mMapLOc.setOnMapClickListener(this);
        mMapLOc.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        mMap.setMyLocationEnabled(true);

    }catch (SecurityException ex){
        Log.e(TAG,"error", ex );
    }


        }

    @Override
    public void onMapClick(LatLng latLng) {
        Toast.makeText(this, "Descrição dos filmes", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {

        Toast.makeText(this, "Posição Alterada", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(this, "Provider Habilitado", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {

        Toast.makeText(this, "Provider Desabilitado", Toast.LENGTH_SHORT).show();
    }
}

