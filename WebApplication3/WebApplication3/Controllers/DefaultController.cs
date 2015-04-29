using WebApplication3.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web.Http;
using Newtonsoft.Json;
using WebApplication3.JsonModel;
using System.Net.Http;

namespace ProductsApp.Controllers
{
    public class ProductController : ApiController
    {
        Class1[] products = new Class1[] 
        { 
            new Class1 { Id = 1, Name = "Tomato Soup", Category = "Groceries", Price = 1 }, 
            new Class1 { Id = 2, Name = "Yo-yo", Category = "Toys", Price = 3.75M }, 
            new Class1 { Id = 3, Name = "Hammer", Category = "Hardware", Price = 16.99M } 
        };

        public IHttpActionResult GetAllProducts()
        {
            HttpClient c = new HttpClient();
            Uri uri = new Uri("https://download.data.grandlyon.com/wfs/rdata?SERVICE=WFS&VERSION=2.0.0&outputformat=GEOJSON&request=GetFeature&typename=sit_sitra.sittourisme");

            String json = c.GetStringAsync(uri).Result;
            
            RootObject r = JsonConvert.DeserializeObject<RootObject>(json);
            return Ok(r);
                        
        }

        public IHttpActionResult GetProduct(int id)
        {
            var product = products.FirstOrDefault((p) => p.Id == id);
            if (product == null)
            {
                return NotFound();
            }
            return Ok(product);
        }

       
    }
}