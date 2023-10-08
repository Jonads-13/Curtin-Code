using Microsoft.EntityFrameworkCore;
using Web_API_Business_Server.Models;

namespace Web_API_Data_Server.Data
{
    public class DBManager : DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlite(@"Data Source = Bank.db;");
        }

        public DbSet<ProfileID> ProfileIDs { get; set; }
        public DbSet<Account> Accounts { get; set; }
        public DbSet<Profile> Profiles { get; set; }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Account>()
                .HasOne(e => e.ProfileID)
                .WithMany(e => e.Accounts)
                .HasForeignKey(e => e.ID)
                .IsRequired();

            modelBuilder.Entity<ProfileID>()
                .HasMany(e => e.Accounts)
                .WithOne(e => e.ProfileID)
                .HasForeignKey(e => e.ProfileID)
                .IsRequired();

            modelBuilder.Entity<Profile>()
                .HasOne(e => e.ProfileID);
        }
    }
}
