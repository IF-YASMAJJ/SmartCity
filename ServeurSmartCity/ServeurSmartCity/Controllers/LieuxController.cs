using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Description;
using ServeurSmartCity.Models;
using ServeurSmartCity.DAO;

namespace ServeurSmartCity.Controllers
{
    public class LieuxController : ApiController
    {

        private LieuDAO dao = new LieuDAO();
        private const int nbResultatsMinimum = 10;


        // GET: api/Lieux
        public IHttpActionResult GetLieuSet()
        {
             return Json(dao.listAll());        
        }

        // GET: api/Lieux/5
        [ResponseType(typeof(Lieu))]
        public async Task<IHttpActionResult> GetLieu(int id)
        {
            Lieu lieu = await dao.getById(id);
            if (lieu == null)
            {
                return NotFound();
            }

            return Json(lieu);
        }

        // GET: api/Lieux/4.83/45.76/
        //Ne pas oublier / à la fin.
        public async Task<IHttpActionResult> GetLieuByPosition(float latitude, float longitude)
        {
            short[] coordonneesSmartphone = new short[2];
            List<LieuResume> res;

            DonneesGeographiques.calculerCoordonnees(longitude, latitude, coordonneesSmartphone);
            if (!DonneesGeographiques.coordonneesDansLimites(coordonneesSmartphone)) return Json("Le point donné n'est pas dans les limites");

            res = dao.requeteChercherProximite(coordonneesSmartphone[0], coordonneesSmartphone[1], 1, nbResultatsMinimum);

            return Json(res);
        }

        // GET: api/Lieux/4.83/45.76/50
        public async Task<IHttpActionResult> GetLieuByPositionLimite(float latitude, float longitude, short limite)
        {
            short[] coordonneesSmartphone = new short[2];
            DonneesGeographiques.calculerCoordonnees(longitude, latitude, coordonneesSmartphone);
            if (!DonneesGeographiques.coordonneesDansLimites(coordonneesSmartphone)) return Json("Le point donné n'est pas dans les limites");

            List<LieuResume> res = dao.requeteChercherProximite(coordonneesSmartphone[0], coordonneesSmartphone[1], 1, limite);

            return Json(res);
        }
        
        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                dao.dispose();
            }
            base.Dispose(disposing);
        }

        private bool LieuExists(int id)
        {
            return dao.LieuExists(id);
        }

        
    }
}