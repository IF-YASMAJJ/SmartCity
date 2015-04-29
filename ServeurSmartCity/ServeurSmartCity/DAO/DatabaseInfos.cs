using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using ServeurSmartCity.Models;

namespace ServeurSmartCity.DAO
{
    public static class DatabaseInfos
    {
        public static ModelContainer db { get; private set; }
        public static void onStart()
        {
            if(db == null)
            {
                db = new ModelContainer();
            }
        }
    }
}