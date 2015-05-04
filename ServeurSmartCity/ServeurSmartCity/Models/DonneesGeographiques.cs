using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ServeurSmartCity.Models
{
    public static class DonneesGeographiques
    {
        private const float rayonTerre = 6371f;
        private const float tailleCarreGrille = 0.5f;

        /// <summary>
        /// <b>Attention</b> Valeur des angles en radian
        /// </summary>
        private static float minLong, maxLong, minLat, maxLat;
        private static short abscisseMax, coordonneeMax;
        const short abscisseMin = 0, coordonneeMin = 0;

        /// <summary>
        /// Initialise les extremums de la classe. Les paramètres doivent être donnés en degrés décimaux.
        /// </summary>
        /// <param name="minLo"></param>
        /// <param name="maxLo"></param>
        /// <param name="minLa"></param>
        /// <param name="maxLa"></param>
        public static void initExtremums(float minLo, float maxLo, float minLa, float maxLa)
        {
            minLong = minLo * (float)Math.PI / 180;
            maxLong = maxLo * (float)Math.PI / 180;
            minLat = minLa * (float)Math.PI / 180;
            maxLat = maxLa * (float)Math.PI / 180;

            abscisseMax = (short)(Math.Abs(rayonTerre * (Math.Cos(((maxLat + minLat) / 2) * Math.PI / 180)) * (maxLong - minLong)) / DonneesGeographiques.tailleCarreGrille);
            coordonneeMax = (short)(Math.Abs(rayonTerre * (maxLat - minLat)) / DonneesGeographiques.tailleCarreGrille);
        }

        /// <summary>
        /// Calcule les coordonnées du point donné en paramètre.
        /// </summary>
        /// <param name="longitude"></param>
        /// <param name="latitude"></param>
        /// <param name="coordonnees">Tableau [longitude, latitude] modifié par la fonction.</param>
        public static void calculerCoordonnees(float longitude, float latitude, short[] coordonnees)
        {
            longitude = longitude * (float)Math.PI / 180;
            latitude = latitude * (float)Math.PI / 180;

            float dLong = (float)Math.Abs(rayonTerre * (Math.Cos(((maxLat + minLat) / 2) * Math.PI / 180)) * (longitude - minLong));
            float dLat = Math.Abs(rayonTerre * (latitude - minLat));

            coordonnees[0] = (short)(dLong / DonneesGeographiques.tailleCarreGrille);
            coordonnees[1] = (short)(dLat / DonneesGeographiques.tailleCarreGrille);
        }

        /// <summary>
        /// Calcule la distance en kilomètres entre deux points.
        /// <b>Attention ! </b>Les paramètres rentrés doivent être en degré.
        /// </summary>
        /// <param name="long1"></param>
        /// <param name="long2"></param>
        /// <param name="lat1"></param>
        /// <param name="lat2"></param>
        public static float distance(float long1, float long2, float lat1, float lat2)
        {
            //Conversion en radians.
            long1 = long1 * (float)Math.PI / 180;
            long2 = long2 * (float)Math.PI / 180;
            lat1 = lat1 * (float)Math.PI / 180;
            lat2 = lat2 * (float)Math.PI / 180;

            // Calcul indépendant sur les axes de latitude et longitude, puis utilisation du théorème de Pythagore pour la distance finale.
            float dLat = Math.Abs(rayonTerre*(lat2 - lat1));
            float dLong = (float) Math.Abs(rayonTerre * (Math.Cos(((maxLat+minLat)/2) * Math.PI/180)) * (long2 - long1));
            
            return (float) Math.Sqrt(Math.Pow(dLat,2.0) + Math.Pow(dLong,2.0));
        }

        public static bool coordonneesDansLimites(short[] coordonnees)
        {
            if (coordonnees[0] < abscisseMin) return false;
            if (coordonnees[0] > abscisseMax) return false;
            if (coordonnees[1] < coordonneeMin) return false;
            if (coordonnees[1] > coordonneeMax) return false;

            return true;
        }
    }
}