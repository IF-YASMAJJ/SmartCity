namespace WebApplication3.Models
{
    using System;
    using System.Collections.Generic;
    using System.ComponentModel.DataAnnotations;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Data.Entity.Spatial;

    [Table("Table")]
    public partial class Table
    {
        public int Id { get; set; }

        [Column(TypeName = "text")]
        public string texte { get; set; }

        public string max { get; set; }

        [StringLength(10)]
        public string ndix { get; set; }

        [StringLength(50)]
        public string nv50 { get; set; }
    }
}
