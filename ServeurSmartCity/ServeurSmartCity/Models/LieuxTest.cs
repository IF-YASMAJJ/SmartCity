using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ServeurSmartCity.Models
{
    public class LieuxTest
    {
        public int id { get; set; }
        public string nom { get; set; }
        public string type { get; set; }
        public string ouverture { get; set; }
        public CoordonneesTest coordinates { get; set; }
    }
}