namespace WebServer.Models
{
    public class Database
    {
        static List<Client> clients = new();

        public static List<Client> GetClients()
        {
            return clients;
        }

        public static void AddClient(Client client)
        {
            clients.Add(client);
        }
    }
}
