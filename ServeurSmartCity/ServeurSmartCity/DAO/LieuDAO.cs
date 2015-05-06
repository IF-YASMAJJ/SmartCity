using ServeurSmartCity.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Threading.Tasks;
using ServeurSmartCity.JsonModel;
using System.Data.Entity;

namespace ServeurSmartCity.DAO
{
    public class LieuDAO
    {

        private ModelContainer db = new ModelContainer();

        
        public async Task<int> addLieu(Lieu lieu)
        {
             db.LieuSet.Add(lieu);
            return await  db.SaveChangesAsync();   
        }

        public async Task<int> deleteLieux()
        {
            try
            {
                return await db.Database.ExecuteSqlCommandAsync("TRUNCATE TABLE [LieuSet]");
            }
            catch(Exception e)
            {
                return -1;
            }
        }

        public async void deleteLieu(int id)
        {
            Lieu lieu = await  db.LieuSet.FindAsync(id);
            if (lieu == null)
            {
                return;
            }

             db.LieuSet.Remove(lieu);
            await  db.SaveChangesAsync();

        }

        public async Task<List<LieuResume>> listAll()
        {
            try
            { 
                return await db.LieuResume.ToListAsync<LieuResume>();
            }
            catch(Exception e)
            {
                return new List<LieuResume>();
            }
        }

        public async Task<Lieu> getById(int id)
        {
            Lieu lieu = await db.LieuSet.FindAsync(id);
            return lieu;
        }

        public async Task<List<LieuResume>> requeteChercherProximite(short abscTelephone, short ordTelephone, short ecart, int nbResultatsMinimum)
        {
            List<LieuResume> liste = await db.LieuResume.ToListAsync<LieuResume>();
            if (nbResultatsMinimum >= liste.Count)
            {
                return liste;
            }
            else
            {
                List<LieuResume> res = await db.LieuResume.Where(l => l.abscisses >= abscTelephone - ecart &&
                                                        l.abscisses <= abscTelephone + ecart &&
                                                        l.ordonnees >= ordTelephone - ecart &&
                                                        l.ordonnees <= ordTelephone + ecart).ToListAsync<LieuResume>();


                if (res.Count < nbResultatsMinimum)
                {
                    return await requeteChercherProximite(abscTelephone, ordTelephone, ++ecart, nbResultatsMinimum);
                }
                else
                {
                    return res;
                }
            }
        }

        public void dispose()
        {
            db.Dispose();
        }

        public bool LieuExists(int id)
        {
            return db.LieuSet.Count(e => e.Id == id) > 0;
        }
    }
}