using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Net.Http;
using ServeurSmartCity.JsonModel;
using Newtonsoft.Json;

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
         
        }
    }
}