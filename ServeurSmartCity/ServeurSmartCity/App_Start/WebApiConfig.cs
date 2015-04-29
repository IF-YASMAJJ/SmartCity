using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http.Headers;
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

            //ajout d'une nouvelle route vers getLieuByPosition
            config.Routes.MapHttpRoute(
                name: "localisation",
                routeTemplate: "api/{controller}/{latitude}/{longitude}"
            );


            config.Formatters.JsonFormatter.SupportedMediaTypes.Add(new MediaTypeHeaderValue("application/json"));

            JsonReader.JsonReader json = new JsonReader.JsonReader();
            json.readJson();
        }
    }
}
