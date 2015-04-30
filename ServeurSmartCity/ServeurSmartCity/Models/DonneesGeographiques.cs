using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ServeurSmartCity.Models
{
    public static class DonneesGeographiques
    {
        public const float rayonTerre = 6371f;
        public const float tailleCarreGrilleRad = 0.5f / rayonTerre;
    }
}