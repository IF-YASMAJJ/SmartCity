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
        private ModelContainer db = new ModelContainer();

        // GET: api/Lieux
        public IHttpActionResult GetLieuSet()
        {
            return Json(db.LieuResume.ToList<LieuResume>());
        }

        // GET: api/Lieux/5
        [ResponseType(typeof(Lieu))]
        public async Task<IHttpActionResult> GetLieu(int id)
        {
            Lieu lieu = await db.LieuSet.FindAsync(id);
            if (lieu == null)
            {
                return NotFound();
            }

            return Json(lieu);
        }

        // GET: api/Lieux/4.5/45.8/
        //Ne pas oublier / à la fin.
        public async Task<IHttpActionResult> GetLieuByPosition(float latitude, float longitude)
        {
            short[] coordonneesSmartphone = new short[2];
            DonneesGeographiques.calculerCoordonnees(longitude, latitude, coordonneesSmartphone);

            List<LieuResume> res;
            short c = coordonneesSmartphone[0];
            short d = coordonneesSmartphone[1];
            //res = (DbSet<Lieu>)db.LieuSet.Where(l => l.longitude == coordonneesSmartphone[0] && l.latitude == coordonneesSmartphone[1]);
            res = db.LieuResume.Where(l => l.abscisses == c && l.ordonnees == d).ToList<LieuResume>(); //.Where(l => l.longitude == coordonneesSmartphone[0]);

            return Json(res);
        }

        public async Task<IHttpActionResult> GetLieuByPositionLimite(float latitude, float longitude, int limite)
        {
            short[] coordonneesSmartphone = new short[2];
            DonneesGeographiques.calculerCoordonnees(longitude, latitude, coordonneesSmartphone);

            List<LieuResume> res;
            short c = coordonneesSmartphone[0];
            short d = coordonneesSmartphone[1];
            //res = (DbSet<Lieu>)db.LieuSet.Where(l => l.longitude == coordonneesSmartphone[0] && l.latitude == coordonneesSmartphone[1]);
            res = db.LieuResume.Where(l => l.abscisses == c && l.ordonnees == d).ToList<LieuResume>(); //.Where(l => l.longitude == coordonneesSmartphone[0]);

            return Json(res);
        }
        
        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool LieuExists(int id)
        {
            return db.LieuSet.Count(e => e.Id == id) > 0;
        }
    }
}