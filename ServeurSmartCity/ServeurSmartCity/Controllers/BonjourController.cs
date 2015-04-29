using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

using ServeurSmartCity.Models;

namespace ServeurSmartCity.Controllers
{
    public class BonjourController : ApiController
    {
        // Controller simple de test

        // Création des données
        LieuxTest[] lieux = new LieuxTest[] 
        { 
            new LieuxTest { Id = 1, Name = "INSA", Category = "Education", Longitude = 4, Latitude = 45 }, 
            new LieuxTest { Id = 2, Name = "Bellecour", Category = "Tourisme", Longitude = 5, Latitude = 42 }, 
            new LieuxTest { Id = 3, Name = "UGC Cite Internationale", Category = "Loisir", Longitude = 3, Latitude = 48 } 
        };

        // GET : /api/Bonjour
        public IHttpActionResult GetAllProducts()
        {
            return Ok(lieux);

        }

        // GET : /api/Bonjour/"un id"
        public IHttpActionResult getLieux(int id)
        {
            var lieu = lieux.FirstOrDefault((p) => p.Id == id);
            if (lieu == null)
            {
                return NotFound();
            }
            return Ok(lieu);
        }
    }
}
