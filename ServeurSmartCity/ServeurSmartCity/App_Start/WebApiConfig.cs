﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using ServeurSmartCity.JsonReader;
using ServeurSmartCity.DAO;

namespace ServeurSmartCity
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // Configuration et services API Web

            // Itinéraires de l'API Web
            config.MapHttpAttributeRoutes();

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );

            DatabaseInfos.onStart();

            JsonReader.JsonReader json = new JsonReader.JsonReader();
            json.readJson();
        }
    }
}
