using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

using ServeurSmartCity.JsonModel;

namespace ServeurSmartCity.Models
{
    public partial class Lieu
    {
        public Lieu createLieu(Feature f)
        {
            //Lieu ret = new Lieu();
            Id = int.Parse(f.properties.id);
            nom = f.properties.nom;
            type = f.properties.type;
            type_detail = f.properties.type_detail;
            classement = f.properties.classement;
            adresse = f.properties.adresse;
            codepostal = f.properties.codepostal;
            commune = f.properties.commune;
            telephone = f.properties.telephone;
            fax = f.properties.fax;
            email = f.properties.email;
            siteweb = f.properties.siteweb;
            facebook = f.properties.facebook;
            ouverture = f.properties.ouverture;
            tarifsenclair = f.properties.tarifsenclair;
            tarifsmin = f.properties.tarifsmin;
            tarifsmax = f.properties.tarifsmax;
            producteur = f.properties.producteur;
            longitude = f.geometry.coordinates[0];
            latitude = f.geometry.coordinates[1];
            //TODO : abscisses et ordonnées

            return this;
        }
    }
}