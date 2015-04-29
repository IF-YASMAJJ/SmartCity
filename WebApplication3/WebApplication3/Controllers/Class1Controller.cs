using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.ModelBinding;
using System.Web.Http.OData;
using System.Web.Http.OData.Query;
using System.Web.Http.OData.Routing;
using WebApplication3.Models;
using Microsoft.Data.OData;

namespace WebApplication3.Controllers
{
    /*
    La classe WebApiConfig peut exiger d'autres modifications pour ajouter un itinéraire à ce contrôleur. Fusionnez ces instructions dans la méthode Register de la classe WebApiConfig, le cas échéant. Les URL OData sont sensibles à la casse.

    using System.Web.Http.OData.Builder;
    using System.Web.Http.OData.Extensions;
    using WebApplication3.Models;
    ODataConventionModelBuilder builder = new ODataConventionModelBuilder();
    builder.EntitySet<Class1>("Class1");
    config.Routes.MapODataServiceRoute("odata", "odata", builder.GetEdmModel());
    */
    public class Class1Controller : ODataController
    {
        private static ODataValidationSettings _validationSettings = new ODataValidationSettings();

        // GET: odata/Class1
        public IHttpActionResult GetClass1(ODataQueryOptions<Class1> queryOptions)
        {
            // validate the query.
            try
            {
                queryOptions.Validate(_validationSettings);
            }
            catch (ODataException ex)
            {
                return BadRequest(ex.Message);
            }

            // return Ok<IEnumerable<Class1>>(class1);
            return StatusCode(HttpStatusCode.NotImplemented);
        }

        // GET: odata/Class1(5)
        public IHttpActionResult GetClass1([FromODataUri] int key, ODataQueryOptions<Class1> queryOptions)
        {
            // validate the query.
            try
            {
                queryOptions.Validate(_validationSettings);
            }
            catch (ODataException ex)
            {
                return BadRequest(ex.Message);
            }

            // return Ok<Class1>(class1);
            return StatusCode(HttpStatusCode.NotImplemented);
        }

        // PUT: odata/Class1(5)
        public IHttpActionResult Put([FromODataUri] int key, Delta<Class1> delta)
        {
            Validate(delta.GetEntity());

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            // TODO: Get the entity here.

            // delta.Put(class1);

            // TODO: Save the patched entity.

            // return Updated(class1);
            return StatusCode(HttpStatusCode.NotImplemented);
        }

        // POST: odata/Class1
        public IHttpActionResult Post(Class1 class1)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            // TODO: Add create logic here.

            // return Created(class1);
            return StatusCode(HttpStatusCode.NotImplemented);
        }

        // PATCH: odata/Class1(5)
        [AcceptVerbs("PATCH", "MERGE")]
        public IHttpActionResult Patch([FromODataUri] int key, Delta<Class1> delta)
        {
            Validate(delta.GetEntity());

            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            // TODO: Get the entity here.

            // delta.Patch(class1);

            // TODO: Save the patched entity.

            // return Updated(class1);
            return StatusCode(HttpStatusCode.NotImplemented);
        }

        // DELETE: odata/Class1(5)
        public IHttpActionResult Delete([FromODataUri] int key)
        {
            // TODO: Add delete logic here.

            // return StatusCode(HttpStatusCode.NoContent);
            return StatusCode(HttpStatusCode.NotImplemented);
        }
    }
}
