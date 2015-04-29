using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Script.Serialization;
using Newtonsoft.Json;
using ServeurSmartCity.Models;

namespace ServeurSmartCity.Controllers
{
    public class BonjourController : ApiController
    {
        // Controller simple de test
        private ListeLieuxTest liste;
        public BonjourController()
        {
            // Création des données
            LieuxTest[] lieux = new LieuxTest[] 
            { 
                new LieuxTest { id = 100, nom = "Marché de Gros Lyon", type = "PATRIMOINE_CULTUREL", ouverture = "Lun au Vendr", coordinates = new CoordonneesTest { latitude = 4.9, longitude = 49.6 } }, 
                new LieuxTest { id = 410, nom = "Comité Départemental du Cyclisme", type = "COMMERCE_ET_SERVICE", ouverture = "Mar au Vendr", coordinates = new CoordonneesTest { latitude = 4.8, longitude = 49.8 } }, 
            };

            liste = new ListeLieuxTest { nb = lieux.Length, points = lieux };

        }


        // GET : /api/Bonjour
        public IHttpActionResult GetAllLieux()
        {
            return Json(liste);
        }

        // GET : /api/Bonjour/"un id"
        public IHttpActionResult GetLieux(int id)
        {
            var lieu = liste.points.FirstOrDefault((p) => p.id == id);
            if (lieu == null)
            {
                return NotFound();
            }
            return Json(lieu);
        }
    }
}
