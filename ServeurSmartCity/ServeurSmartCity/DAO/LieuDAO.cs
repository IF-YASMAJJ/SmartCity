using ServeurSmartCity.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Threading.Tasks;
using ServeurSmartCity.JsonModel;

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

        public int deleteLieux()
        {
            return  db.Database.ExecuteSqlCommand("TRUNCATE TABLE [LieuSet]");
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
    }
}