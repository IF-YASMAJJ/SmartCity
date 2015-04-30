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

            editModel();
        }

        private async void editModel()
        {
            LieuDAO lDao = new LieuDAO();
            lDao.deleteLieux();
            foreach (Feature f in data.features){
                Lieu l = new Lieu();
                short abscisses = (short)((f.geometry.coordinates[1] - minLat)/DonneesGeographiques.tailleCarreGrilleRad);
                short ordonnees = (short)((f.geometry.coordinates[0] - minLong) / DonneesGeographiques.tailleCarreGrilleRad);
                await lDao.addLieu(l.createLieu(f, abscisses, ordonnees));
            }
        }
    }
}