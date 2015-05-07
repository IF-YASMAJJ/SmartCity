package net.spownik.tourismemobile;

import android.os.Environment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



/**
 * Created by yukaiwang on 28/04/15.
 */
public class JsonParser {

    public LieuTouristique json2object(InputStream in) {
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in, "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return gson.fromJson(br, LieuTouristique.class);
    }

    public ArrayList<LieuTouristique> json2arrayOfObjects(InputStream in) {
        Type listType = new TypeToken<ArrayList<LieuTouristique>>() {}.getType();
        Gson gson = new Gson();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in, "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return gson.fromJson(br, listType);
    }
}
