using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ServeurSmartCity.Models
{
    public class LieuxTest
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string Category { get; set; }
        public int Latitude { get; set; }
        public int Longitude { get; set; }
    }
}