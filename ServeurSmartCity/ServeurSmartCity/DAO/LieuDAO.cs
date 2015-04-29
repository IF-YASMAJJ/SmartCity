using ServeurSmartCity.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Threading.Tasks;

namespace ServeurSmartCity.DAO
{
    public class LieuDAO
    {
        public async Task<int> addLieu(Lieu lieu)
        {
            DatabaseInfos.db.LieuSet.Add(lieu);
            return await DatabaseInfos.db.SaveChangesAsync();   
        }

        public int deleteLieux()
        {
            return DatabaseInfos.db.Database.ExecuteSqlCommand("TRUNCATE TABLE [LieuSet]");
        }

        public async void deleteLieu(int id)
        {
            Lieu lieu = await DatabaseInfos.db.LieuSet.FindAsync(id);
            if (lieu == null)
            {
                return;
            }

            DatabaseInfos.db.LieuSet.Remove(lieu);
            await DatabaseInfos.db.SaveChangesAsync();

        }
    }
}