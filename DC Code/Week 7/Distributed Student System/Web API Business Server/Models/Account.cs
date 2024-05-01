using System.ComponentModel.DataAnnotations;

namespace Web_API_Business_Server.Models
{
    public class Account
    {
        [Key]
        public int Number { get; set; }
        public double Balance { get; set; }

        public int ID {  get; set; }
        public ProfileID ProfileID { get; set; } = null!;
    }
}
