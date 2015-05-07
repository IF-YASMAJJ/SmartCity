package net.spownik.tourismemobile;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.location.LocationManager;
//import com.google.android.gms.location.LocationListener;
import android.location.LocationProvider;
import android.location.Location;
import android.widget.Toast;
import android.location.Criteria;
import android.content.Context;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;



/**
 * Created by Spownik on 28/04/2015.
 */
//Blaise Pascal 45.781959, 4.872770
//temporaire
//double maLat = 45.780101;
//double maLong= 4.878742;
public class LieuxProchesActivity extends Activity implements LocationListener {

    private ArrayList<LieuTouristique> lieux = Config.get().getLieuxTouristiques();
    private Config config = Config.get();
    private LocationManager locationManager;
    private LocationRequest mLocationRequest;
    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;
    private String provider;


    private Timer timerUpdateGeo = new Timer();
    TimerTask task;
    private boolean mutexUpdate = true;

    final Handler updateHandler = new Handler();
    final Runnable updateRun = new Runnable()
    {
        public void run()
        {
            //WHAT TO DO IN THE THREAD
            if(mutexUpdate)//Protection de la zone processus de rafraïchissement
            {
                mutexUpdate = false;
                System.out.println("nb = " + config.getLieuxTouristiques().size());
                displayLieux();
                mutexUpdate = true;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lieux_proches);

        Config.get();
        initRequest();
        initGUI();
        initGeoLocation();

        initLieux();


        displayLieux();

        //Timer pour rafraîchir l'affichage
        task = new TimerTask() {

            @Override
            public void run() {
                //runOnUiThread(updateRun);
                updateGUI();
            }
        };

        timerUpdateGeo.scheduleAtFixedRate(task, 0, 1000);

/*
        if(Config.get().getLieuxTouristiques() == null)
            new AlertDialog.Builder(this)
                    .setTitle("LieuxTs")
                    .setMessage("NULL !")
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
        new AlertDialog.Builder(this)
                .setTitle("LectureJSON")
                .setMessage("nb = " + Config.get().getLieuxTouristiques().size())
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
                .show();*/

    }

    @Override
    protected void onStop()
    {
        locationManager.removeUpdates(this);
        timerUpdateGeo.cancel();
        super.onStop();

        // The activity is no longer visible (it is now "stopped")
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        // The activity is about to be destroyed.
    }

    private void initGUI()
    {
        //----------onclick btnCarte
        Button btnCarte = (Button) findViewById(R.id.btnCarte);

        btnCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivityMaps();
            }
        });
    }

    private void handleNewLocation(Location location) {

        double currentLatitude = location.getLatitude();
        double currentLongitude = location.getLongitude();
        Config.get().setLatitudeMe(currentLatitude);
        Config.get().setLongitudeMe(currentLongitude);
    }

    private void initRequest()
    {
        new ReadJson(this).execute(Config.get().getLongitudeMe(),Config.get().getLatitudeMe());
        //new ReadJson(this).execute(4.872770, 45.781959);//test
    }
    private void initGeoLocation()
    {

        // First we need to check availability of play services
        if (!checkPlayServices())
        {
            System.out.println("Merde Play Services indisponible");
        }

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        ArrayList<LocationProvider> providers = new ArrayList<LocationProvider>();
        ArrayList<String> names = (ArrayList) locationManager.getProviders(true);

        for(String name : names)
            providers.add(locationManager.getProvider(name));


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 1, this);


        mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)        // 10 seconds, in milliseconds
                .setFastestInterval(1 * 1000); // 1 second, in milliseconds

        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Config.get().setLatitudeMe(loc.getLatitude());
        Config.get().setLongitudeMe(loc.getLongitude());


    }

    private void initLieux()
    {
        LinearLayout layoutListeLieux = (LinearLayout)findViewById(R.id.layoutListeLieux);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        params.weight = 1.0f;
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.setMargins(5,5,5,10);
        Button btn;
        for(int i = 0; i < Config.get().getNbResults(); ++i)
        {
            btn = new Button(this);
            btn.setText("");
            btn.setBackgroundResource(R.drawable.shape);
            //btn.setVisibility(View.GONE);
            layoutListeLieux.addView(btn,params);
        }

        /*//Temporaire
        lieux.add(new Lieu(1,"Crêperie", "Restauration", "Ouvert 11h-23h" ,  45.779, 4.854495));
        lieux.add(new Lieu(2,"Tête d'Or", "Parc", "Ouvert tous les jours" ,  45.728799, 4.875));
        lieux.add(new Lieu(3,"Toto", "Statue", "Ouvert tous les jours" ,  45.778799, 4.814415));
        lieux.add(new Lieu(4,"Tony", "Maison", "Ouvert tous les jours" ,  48.095055, 5.1501655));
        lieux.add(new Lieu(5,"Titi", "Appartement", "Ouvert tous les jours" ,  45.780101, 4.878742));
        lieux.add(new Lieu(6,"Chez Robert", "Armurerie", "Ouvert tous les jours" ,  45.7799, 4.84715));
        lieux.add(new Lieu(7,"Chez Robert", "Armurerie", "Ouvert tous les jours" ,  45.799, 4.84415));
        lieux.add(new Lieu(8,"Toilettes Jiji", "Toilettes publiques", "Ouvert tous les jours" ,  45.777299, 4.8548415));
        lieux.add(new Lieu(9,"Toilettes Jiji", "Toilettes publiques", "Ouvert tous les jours" ,  45.7812299, 4.854815));
        lieux.add(new Lieu(10,"Toilettes Jiji", "Toilettes publiques", "Ouvert tous les jours" ,  45.7412299, 4.85415));*/


    }

    private void displayLieux()
    {


        LinearLayout layoutListeLieux = (LinearLayout)findViewById(R.id.layoutListeLieux);
        lieux = Config.get().getLieuxTouristiques();
        int nbLieux =config.getLieuxTouristiques().size();
        //Config.get().trierLieux();
        if(nbLieux != 0)
        {
            for (int i = 0; i < nbLieux; ++i) {
                Button btn = (Button) layoutListeLieux.getChildAt(i);
                if (btn != null) {
                    String laDistance = "";

                    if (geoDistanceFromMe(lieux.get(i)) >= 1.0) {
                        laDistance = laDistance + String.format("%.2f", lieux.get(i).getDistanceFromMe());
                        laDistance = laDistance + " km";
                    } else {
                        laDistance = laDistance + String.format("%.2f",lieux.get(i).getDistanceFromMe() * 1000);
                        laDistance = laDistance + " m";
                    }

                    System.out.println(lieux.get(i).getNom() + " - " + laDistance);
                    btn.setText(lieux.get(i).getNom() + "\n" + lieux.get(i).getType() + "\n" + lieux.get(i).getOuverture() + "\n" + laDistance);
                }
            }
            for(int i = nbLieux; i< Config.get().getNbResults(); ++i)
            {
                Button btn = (Button) layoutListeLieux.getChildAt(i);
                btn.setVisibility(View.GONE);
            }
        }
        else
        {
            System.out.println("Oh putain de merde !");
        }
    }

    private void clearLieux()
    {
        LinearLayout layoutListeLieux = (LinearLayout)findViewById(R.id.layoutListeLieux);
        if(layoutListeLieux != null)
        {
            for(int i = 0; i < Config.get().getNbResults(); ++i)
            {
                Button btn = (Button)layoutListeLieux.getChildAt(i);
                btn.setText("");
            }
        }

    }

    private void updateGeoDistance()
    {

    }


    private double calculDistance(double lat1, double long1, double lat2, double long2)
    {
        double distance;

        double latitude1 = lat1 * Math.PI / 180;
        double latitude2 = lat2 * Math.PI / 180;

        double longitude1 = long1 * Math.PI / 180;
        double longitude2 = long2 * Math.PI / 180;

        double R = 6371d; // la valeur de la longueur du rayon à 60° de latitude sachant
        // que le rayon terrestre moyen vaut 6371 km

        distance = R * Math.acos(Math.cos(latitude1) * Math.cos(latitude2) *
                Math.cos(longitude2 - longitude1) + Math.sin(latitude1) * Math.sin(latitude2));

        return distance;
    }

    private double geoDistanceLieuToLieu(LieuTouristique l1, LieuTouristique l2)
    {
        return calculDistance(l1.getLatitude(), l1.getLongitude(), l2.getLatitude(), l2.getLongitude());

    }

    /**
     * Retourne la distance et affecte la distance au Lieu passé en paramètre
     *
     * @param l
     * @return distance
     */
    private double geoDistanceFromMe(LieuTouristique l)
    {
        double maLat = Config.get().getLatitudeMe();
        double maLong= Config.get().getLongitudeMe();

        double distance;

        distance = calculDistance(l.getLatitude(), l.getLongitude(), maLat, maLong);

        l.setDistanceFromMe(distance);

        return distance;
    }

    private void goActivityMaps()
    {
        /*new AlertDialog.Builder(this)
                .setTitle("Position")
                .setMessage(Config.get().getLongitudeMe() + "\n" + Config.get().getLatitudeMe())
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
                .show();*/

        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
    }

    private void updateGUI()
    {
        //updateRun
        updateHandler.post(updateRun);
    }

    public void onLocationChanged(Location location) {
        double lat = (location.getLatitude());
        double lng = (location.getLongitude());
        Config.get().setLatitudeMe(lat);
        Config.get().setLongitudeMe(lng);
        //latituteField.setText(String.valueOf(lat));
        //longitudeField.setText(String.valueOf(lng));
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


    /**
     * Method to verify google play services on the device
     * */
    private boolean checkPlayServices()
    {
        /*int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS)
        {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode))
            {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }*/
        return true;
    }

    public Config getConfig() {
        return config;
    }
}
