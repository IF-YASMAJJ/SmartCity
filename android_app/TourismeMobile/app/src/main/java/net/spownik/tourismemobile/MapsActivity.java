package net.spownik.tourismemobile;


import java.util.*;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.location.LocationProvider;
import android.widget.Toast;

import android.widget.TextView;
import android.util.Log;
import android.location.Location;
import android.location.Criteria;
import android.content.Context;
import android.content.IntentSender;

import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationRequest;


import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.MapFragment;

import java.util.ArrayList;
//import com.google.android.gms.maps.LocationSource;

public class MapsActivity extends FragmentActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, OnMapReadyCallback {

    private TextView latituteField;
    private TextView longitudeField;
    private TextView locationText;
    private LocationManager locationManager;
    private String provider;
    private Location location;

    private LocationRequest mLocationRequest;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private GoogleApiClient mGoogleApiClient;
    public static final String TAG = MapsActivity.class.getSimpleName();

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    // HANDLE NEW LOCATION


    private void handleNewLocation(Location location) {

        Log.d(TAG, location.toString());

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        LatLng latLng = new LatLng(currentLatitude, currentLongitude);

        MarkerOptions OPTIONS = new MarkerOptions()
                .position(latLng)
                .title("Je suis ici!");


        latituteField.setText(String.valueOf(currentLatitude));
        longitudeField.setText(String.valueOf(currentLongitude));

        mMap.addMarker(OPTIONS);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
    // DISPLAY MAP

    private void setUpMap() {
        // initiate MAP
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        locationText = (TextView) findViewById(R.id.lblLocationInfo);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        latituteField = (TextView) findViewById(R.id.TextView02);
        longitudeField = (TextView) findViewById(R.id.TextView04);

        ArrayList<LocationProvider> providers = new ArrayList<LocationProvider>();
        ArrayList<String> names = (ArrayList) locationManager.getProviders(true);

        for(String name : names)
            providers.add(locationManager.getProvider(name));

        /*Criteria critere = new Criteria();

        critere.setAccuracy(Criteria.ACCURACY_FINE);

        critere.setAltitudeRequired(true);

        critere.setBearingRequired(true);

        critere.setCostAllowed(false);

        critere.setPowerRequirement(Criteria.POWER_HIGH);

        critere.setSpeedRequired(true);*/


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 150, this);


        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds


        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        latituteField.setText(String.valueOf(loc.getLatitude()));
        longitudeField.setText(String.valueOf(loc.getLongitude()));
       ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);
        if(mMap == null)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Erreur")
                    .setMessage("mMap null")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }
    }

    /*@Override
    protected void onResume() {

        setUpMap();
        if(checkPlayServices())
            mGoogleApiClient.connect();
        super.onResume();
    }*/
/*
    protected void onPause() {

        if(checkPlayServices())
        if (mGoogleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (com.google.android.gms.location.LocationListener) this);
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }*/

    @Override
    public void onLocationChanged(Location location) {
        double lat = (int) (location.getLatitude());
        double lng = (int) (location.getLongitude());
        System.out.println( "Latitude " + location.getLatitude() + " et longitude " + location.getLongitude());

        latituteField.setText(String.valueOf(lat));
        longitudeField.setText(String.valueOf(lng));
        handleNewLocation(location);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    public void onConnected(Bundle bundle) {
        Log.i(TAG, "Location services connected.");
        //if(checkPlayServices())
        {
            Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, (com.google.android.gms.location.LocationListener) this);
            } else {
                handleNewLocation(location);
            }
        }
    }

    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    // Kill providers
    @Override
    public void onDestroy(){
        locationManager.removeUpdates(this);
        /*//if(checkPlayServices())
            if (mGoogleApiClient.isConnected())
            {
                LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, (com.google.android.gms.location.LocationListener) this);
                mGoogleApiClient.disconnect();
            }*/
        super.onDestroy();
    }

    // Handle Errors


    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, CONNECTION_FAILURE_RESOLUTION_REQUEST);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    private boolean checkPlayServices()
    {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS)
        {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode))
            {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        1000).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
    }
}

