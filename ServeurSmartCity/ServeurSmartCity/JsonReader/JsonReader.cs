using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Net.Http;
using ServeurSmartCity.JsonModel;
using Newtonsoft.Json;

using ServeurSmartCity.DAO;
using ServeurSmartCity.Models;

namespace ServeurSmartCity.JsonReader
{
    public class JsonReader
    {

        private RootObject data;
        private float   minLat = (float)90.0D, maxLat = (float)0.0D, 
                        minLong = (float)180.0D, maxLong = (float)-180.0D;

        async public void readJson()
        {
            HttpClient c = new HttpClient();
            Uri uri = new Uri("https://download.data.grandlyon.com/wfs/rdata?SERVICE=WFS&VERSION=2.0.0&outputformat=GEOJSON&request=GetFeature&typename=sit_sitra.sittourisme");

            String json = await c.GetStringAsync(uri);

            data = JsonConvert.DeserializeObject<RootObject>(json);
            float longBuf;
            float latBuf;
            foreach (Feature f in data.features)
            {
                longBuf = (float)f.geometry.coordinates[0];
                latBuf = (float)f.geometry.coordinates[1];
                
                if (longBuf < minLong)  minLong = longBuf;
                if (longBuf > maxLong)  maxLong = longBuf;
                if (latBuf < minLat) minLat = latBuf;
                if (latBuf > maxLat) maxLat = latBuf;
            }

            //http://blogs.msdn.com/b/ogdifrance/archive/2011/07/13/de-la-g-233-o-et-des-maths.aspx
            var latitude1 = minLat * Math.PI/180;
            var latitude2 = maxLat * Math.PI / 180;
            var longitude1 = minLong * Math.PI / 180;
            var longitude2 = maxLong * Math.PI / 180;
            var R = 6371d;
            //d : distance en kilomètres entre les points extremums du domaine (testé)
            //TODO : définir la taille des carrés
            var d = R * Math.Acos(Math.Cos(latitude1) * Math.Cos(latitude2) *
                    Math.Cos(longitude2 - longitude1) + Math.Sin(latitude1) *
                             Math.Sin(latitude2));

            /*
             * Calcul custom par approximation
             * Semble donner utiliser une bonne approximation par rapport au calcul ci-dessus
             * Utilisable selon nos besoins (grille) car on sépare le calcul des distance sur les latitudes et longitudes.
             * */
            var dLat = Math.Abs(R*(latitude2 - latitude1));
            var dLong = Math.Abs(R * (Math.Cos(((maxLat+minLat)/2) * Math.PI/180)) * (longitude2 - longitude1));
            var dCustom = Math.Sqrt(Math.Pow(dLat,2.0) + Math.Pow(dLong,2.0));

            editModel();
        }

        private async void editModel()
        {
            LieuDAO lDao = new LieuDAO();
            lDao.deleteLieux();
            foreach (Feature f in data.features){
                Lieu l = new Lieu();
                await lDao.addLieu(l.createLieu(f));
            }
        }
    }
}