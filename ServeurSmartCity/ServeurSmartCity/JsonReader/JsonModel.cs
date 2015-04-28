using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ServeurSmartCity.JsonModel
{
    public class Properties
    {
        public string id { get; set; }              //nope
        public string id_sitra1 { get; set; }       //nope
        public string type { get; set; }
        public string type_detail { get; set; }
        public string nom { get; set; }
        public string adresse { get; set; }
        public string codepostal { get; set; }
        public string commune { get; set; }
        public string telephone { get; set; }
        public string fax { get; set; }
        public string telephonefax { get; set; } // nope
        public string email { get; set; }
        public string siteweb { get; set; }
        public string facebook { get; set; }
        public string classement { get; set; }
        public string ouverture { get; set; }
        public string tarifsenclair { get; set; }
        public string tarifsmin { get; set; }
        public string tarifsmax { get; set; }
        public string producteur { get; set; }
        public string gid { get; set; }             //nope
        public string date_creation { get; set; }   //nope
        public string last_update { get; set; }     //nope
        public string last_update_fme { get; set; } //nope
    }

    public class Geometry
    {
        public string type { get; set; }            //nope
        public List<double> coordinates { get; set; }
    }

    public class Feature
    {
        public string type { get; set; }            //nope
        public Properties properties { get; set; }
        public Geometry geometry { get; set; }
    }

    public class RootObject
    {
        public string type { get; set; }            //nope
        public List<Feature> features { get; set; }
    }
}