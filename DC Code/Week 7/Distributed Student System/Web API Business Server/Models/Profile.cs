namespace Web_API_Business_Server.Models
{
    public class Profile
    {
        public string Fname { get; set; }
        public string Lname { get; set; }
        public string Address { get; set; }
        public string phone { get; set; }
        public string UserPassword { get; set; }

        public int ID { get; set; }
        public ProfileID ProfileID { get; set; } = null!;
    }
}
