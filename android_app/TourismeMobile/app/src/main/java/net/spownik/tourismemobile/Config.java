package net.spownik.tourismemobile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Configuration globale de l'application
 * permet notamment la connaissance globale des points dans l'application
 * Created by Spownik on 04/05/2015.
 */
public class Config
{

    private static Config instance = null;
    //private ArrayList<Lieu> lieux = new ArrayList<Lieu>();
    private ArrayList<LieuTouristique> lieuxTouristiques = new ArrayList<LieuTouristique>();
    private int nbResults = 50;
    private double latitudeMe = 0.0;
    private double longitudeMe = 0.0;

    private Config()
    {

    }

    public static Config get()
    {
        if(instance == null)
        {
            instance = new Config();
        }

        return instance;
    }

    /*public void lieuxTouristiquesToLieux()
    {
        ArrayList<Lieu> lx = new ArrayList<Lieu>();

        for(int i=0; i < lx.size(); ++i)
        {
            lx.add(new Lieu(lieuxTouristiques.get(i)));
        }

        this.lieux = lx;
    }*/

   /* public ArrayList<Lieu> getLieux() {
        return lieux;
    }*/

    public int getNbResults() {
        return nbResults;
    }

    public void setNbResults(int nbResults) {
        this.nbResults = nbResults;
    }

    public double getLatitudeMe() {
        return latitudeMe;
    }

    public void setLatitudeMe(double latitudeMe) {
        this.latitudeMe = latitudeMe;
    }

    public double getLongitudeMe() {
        return longitudeMe;
    }

    public void setLongitudeMe(double longitudeMe) {
        this.longitudeMe = longitudeMe;
    }

    public ArrayList<LieuTouristique> getLieuxTouristiques() {
        return lieuxTouristiques;
    }

    public void setLieuxTouristiques(ArrayList<LieuTouristique> lieuxTouristiques) {
        this.lieuxTouristiques = lieuxTouristiques;
    }

    public void trierLieux()
    {
        if(this.lieuxTouristiques != null)
            this.bubbleSort(this.lieuxTouristiques);
    }

    private static void bubbleSort(ArrayList<LieuTouristique> tab) {

        if(tab != null)
        {
            Collections.sort(tab, new Comparator<LieuTouristique>() {
                @Override
                public int compare(LieuTouristique lhs, LieuTouristique rhs)
                {

                    if(lhs != null && rhs != null)
                        if(lhs.getDistanceFromMe()<rhs.getDistanceFromMe())
                            return -1;
                        else
                            return 1;
                    return 0;
                }
            });
        }
    }



}
