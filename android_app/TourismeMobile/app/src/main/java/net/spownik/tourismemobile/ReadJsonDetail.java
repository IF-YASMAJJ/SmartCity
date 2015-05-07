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
public class ReadJsonDetail extends AsyncTask<Integer, Void, LieuTouristique> {

    private static final String BASE_URL = "http://ec2-52-28-66-140.eu-central-1.compute.amazonaws.com/api/lieux/";
    private volatile LieuxProchesActivity screen;
    private HttpClient client;
    private Exception exception;

    public ReadJsonDetail(LieuxProchesActivity s) {
        this.screen = s;
        this.client = new DefaultHttpClient();
    }

    @Override
    protected LieuTouristique doInBackground(Integer... params) {
        HttpResponse response = null;
        JsonParser jsonParser = new JsonParser();
        LieuTouristique lieu = new LieuTouristique();
        try {
            String uri = BASE_URL + Integer.toString(params[0]);
            HttpGet request = new HttpGet(uri);
            response = this.client.execute(request);

            lieu = jsonParser.json2object(response.getEntity().getContent());
        } catch (Exception e) {
            this.exception = e;
        }
        return lieu;
    }

    @Override
    protected void onPostExecute(LieuTouristique lieu) {
        //Config.get().setLieuxTouristiques(lieux);
        //Config.get().lieuxTouristiquesToLieux();
    }
}