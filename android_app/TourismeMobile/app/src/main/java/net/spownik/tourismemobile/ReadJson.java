package net.spownik.tourismemobile;

import android.os.AsyncTask;



import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

/**
 * Created by yukaiwang on 05/05/15.
 */
public class ReadJson extends AsyncTask<Double, Void, ArrayList<LieuTouristique> > {

    private static final String BASE_URL = "http://ec2-52-28-66-140.eu-central-1.compute.amazonaws.com/api/lieux/";
    private volatile LieuxProchesActivity screen;
    private HttpClient client;
    private Exception exception;

    public ReadJson(LieuxProchesActivity s) {
        this.screen = s;
        this.client = new DefaultHttpClient();
    }

    @Override
    protected ArrayList<LieuTouristique> doInBackground(Double... params) {
        HttpResponse response = null;
        JsonParser jsonParser = new JsonParser();
        ArrayList<LieuTouristique> listLieux = new ArrayList<>();
        try {
            String uri = BASE_URL + String.valueOf(params[0]) + "/" + String.valueOf(params[1]) + "/";
            HttpGet request = new HttpGet(uri);
            response = this.client.execute(request);

            listLieux = jsonParser.json2arrayOfObjects(response.getEntity().getContent());
        } catch (Exception e) {
            this.exception = e;
        }
        return listLieux;
    }

    @Override
    protected void onPostExecute(ArrayList<LieuTouristique> lieux) {
        //System.out.println(lieux.size() + "\n"+ lieux.get(1).getNom());
        screen.getConfig().setLieuxTouristiques(lieux);


        //screen.getConfig().lieuxTouristiquesToLieux();

    }
}
