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
        async public void readJson()
        {
            HttpClient c = new HttpClient();
            Uri uri = new Uri("https://download.data.grandlyon.com/wfs/rdata?SERVICE=WFS&VERSION=2.0.0&outputformat=GEOJSON&request=GetFeature&typename=sit_sitra.sittourisme");

            String json = await c.GetStringAsync(uri);

            data = JsonConvert.DeserializeObject<RootObject>(json);

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