package com.smm.sapp.sproject.Activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.provider.Settings;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.smm.sapp.sproject.R;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final float DEFAULT_ZOOM = 15f;
    private boolean mLocationPermissionGranted = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        searchLocation();
        getLocationPermission();
    }

    private void searchLocation() {
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(place.getLatLng()).title(place.getName().toString()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(place.getLatLng()));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(place.getLatLng(), 12.0f));

                LatLng latLng = place.getLatLng();
                double lng = latLng.longitude;
                double lat = latLng.latitude;

                Intent returnIntent = new Intent();
                returnIntent.putExtra("lng", lng);
                returnIntent.putExtra("lat", lat);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }

            @Override
            public void onError(Status status) {

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (mLocationPermissionGranted) {
            getDeviceLocation();

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true); //to get blue marker with GPS icon
            mMap.getUiSettings().setMyLocationButtonEnabled(false); //to hide GPS icon

            // Assem
            googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                @Override
                public void onMapClick(LatLng latLng) {
                    if (marker != null) { //if marker exists (not null or whatever)
                        marker.setPosition(latLng);
                    } else {
                        marker = mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .title("test")
                                .draggable(true));
                        setupAddBookmarkDialog(latLng);
                    }
                }
            });
        }
    }

    // Assem
    private void sendDataBack(LatLng latLng) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("lng", latLng.longitude);
        returnIntent.putExtra("lat", latLng.latitude);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    // Assem
    private void setupAddBookmarkDialog(final LatLng latLng) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        @SuppressLint("InflateParams") final View mView = LayoutInflater.from(this).inflate(R.layout.dialog_add_project_place, null);
        Button addBtn = mView.findViewById(R.id.dialog_add_bookmark_add_btn);
        Button cancelBtn = mView.findViewById(R.id.dialog_add_bookmark_cancel_btn);

        builder.setView(mView);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataBack(latLng);
                alertDialog.dismiss();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }

    // Assem
    public void customMarker(Context context, GoogleMap mMap, int drawable, String title, LatLng latLng) {
        int height = 80;
        int width = 80;
        BitmapDrawable bitmapDraw = (BitmapDrawable) context.getResources().getDrawable(drawable);
        Bitmap b = bitmapDraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
        MarkerOptions mMarkerOptions = new MarkerOptions();
        mMarkerOptions.title(title).position(latLng)
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
        mMap.addMarker(mMarkerOptions);
    }

    private void initMAP() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapActivity.this);
    }

    private void getDeviceLocation() {
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if (mLocationPermissionGranted) {
                Log.e("rrrrrr", "rrrr");
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Location current_location = (Location) task.getResult();

                            try {
                                moveCamera(new LatLng(current_location.getLatitude(), current_location.getLongitude()), DEFAULT_ZOOM);

                            } catch (Exception e) {
                                onBackPressed();
                                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                Toast.makeText(MapActivity.this, "الرجاء قم بإعطاء الصلاحيات للموقع", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MapActivity.this, "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Log.e("rrrrrr", "ffff");

            }
        } catch (SecurityException e) {
            Log.e("error", e.getMessage());
        }
    }


    private void moveCamera(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }

    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMAP();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);
            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);

        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = false;
                            return;
                        }
                    }
                    mLocationPermissionGranted = true;
                    //initialize our map
                    initMAP();
                }
            }

        }
    }

}
