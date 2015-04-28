using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;

namespace ServeurSmartCity.DAO
{
    public static class DatabaseInfos
    {
        private static SqlConnection connection = new SqlConnection();
        private static String baseName = "Database1";
        private static String tableName = "Lieux";
        private static String serveurName = "localhost";

        /*
         * Opens the connection to the database or simply returns it if already made.
         */
        public static SqlConnection getConnection()
        {
            if (connection.ConnectionString == "" || connection.State == System.Data.ConnectionState.Closed)
            {
                try{
                    String connectionString = "Data Source=" + serveurName + ";Initial Catalog="+ baseName + ";User ID=UserName;Password=Password";
                    connection = new SqlConnection(connectionString);
                }
                catch (Exception ex){
                    //TODO : give informations about connection problems.
                }
                
            }

            return connection;
        }

        public static void closeConnection(){
            try
            {
                connection.Close();
            }
            catch (Exception ex)
            {
                //TODO : give informations about connection problems.
            }
        }
    }
}