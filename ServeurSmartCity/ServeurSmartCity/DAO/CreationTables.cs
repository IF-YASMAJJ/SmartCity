using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;


namespace ServeurSmartCity.DAO
{
    public class CreationTables
    {
        
        public void create()
        {
            SqlConnection connection = DatabaseInfos.getConnection();

        }
    }
}