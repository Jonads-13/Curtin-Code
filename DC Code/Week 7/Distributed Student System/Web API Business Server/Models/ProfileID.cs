using System.ComponentModel.DataAnnotations;

namespace Web_API_Business_Server.Models
{
    public class ProfileID
    {
        [Key]
        public int ID { get; set; }

        public string Username { get; set; }
        public string Email { get; set; }

        public ICollection<Account> Accounts { get; } = new List<Account>();
    }
}
