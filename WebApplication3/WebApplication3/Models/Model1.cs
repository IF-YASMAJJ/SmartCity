namespace WebApplication3.Models
{
    using System;
    using System.Data.Entity;
    using System.ComponentModel.DataAnnotations.Schema;
    using System.Linq;

    public partial class Model1 : DbContext
    {
        public Model1()
            : base("name=Model1")
        {
        }

        public virtual DbSet<Table> Table { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Table>()
                .Property(e => e.texte)
                .IsUnicode(false);

            modelBuilder.Entity<Table>()
                .Property(e => e.ndix)
                .IsFixedLength();
        }

        public System.Data.Entity.DbSet<WebApplication3.Models.Class1> Class1 { get; set; }
    }
}
